/*
 * Copyright 2024 [KnightWood]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package androidy.preference.data.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

/**
 * 单值编辑器接口
 *
 * 此接口定义了单个偏好值的读写操作能力，是偏好系统的核心抽象。
 * 实现类需要提供对特定类型偏好值的异步和同步读写支持，以及基于Flow的响应式数据流。
 *
 * @param T 偏好值的类型，必须是Any的子类
 *
 * 主要功能：
 * - 提供响应式的Flow数据流，可用于Compose UI的状态观察
 * - 支持协程异步读写操作
 * - 提供阻塞式同步读写方法（内部使用runBlocking）
 *
 * 使用示例：
 * ```kotlin
 * val editor: ISingleValueEditor<Boolean> = holder.getOnePrefEditor("switch_key")
 *
 * // 异步读取
 * val value = editor.readValueAsync()
 *
 * // 异步写入
 * editor.writeAsync(true)
 *
 * // 在Compose中观察状态
 * @Composable
 * fun MyComposable() {
 *     val state = editor.state(defaultValue = false)
 * }
 * ```
 */
interface ISingleValueEditor<T> {

    /**
     * 偏好值的响应式数据流
     *
     * 当偏好值发生变化时，此Flow会发射新的值。
     * 可用于Compose的collectAsState或其他响应式场景。
     *
     * @return Flow<T?> 可能为null的偏好值流
     */
    val flow: Flow<T?>

    /**
     * 异步读取偏好值
     *
     * 此方法应在协程作用域中调用，不会阻塞当前线程。
     *
     * @return T? 当前存储的偏好值，如果不存在则返回null
     * @throws Exception 读取过程中可能抛出异常
     */
    suspend fun readValueAsync(): T?

    /**
     * 异步写入偏好值
     *
     * 此方法应在协程作用域中调用，不会阻塞当前线程。
     * 传入null值通常表示删除该偏好项或恢复默认值。
     *
     * @param data 要写入的偏好值，null表示删除或重置
     * @throws Exception 写入过程中可能抛出异常
     */
    suspend fun writeAsync(data: T?)

    /**
     * 同步读取偏好值
     *
     * 此方法会在当前线程阻塞直到读取完成，不推荐在主线程调用。
     * 内部使用runBlocking实现，适用于非协程环境或测试场景。
     *
     * @return T? 当前存储的偏好值，如果不存在则返回null
     */
    fun readValue(): T? = runBlocking { readValueAsync() }

    /**
     * 同步写入偏好值
     *
     * 此方法会在当前线程阻塞直到写入完成，不推荐在主线程调用。
     * 内部使用runBlocking实现，适用于非协程环境或测试场景。
     *
     * @param data 要写入的偏好值，null表示删除或重置
     */
    fun write(data: T?) = runBlocking { writeAsync(data) }
}

/**
 * Composable函数：获取偏好值的状态
 *
 * 这是一个便捷的扩展函数，用于在Compose UI中观察偏好值的状态。
 * 如果编辑器为null或Flow返回null，则使用提供的默认值。
 *
 * @param T 偏好值的类型，必须是Any的子类且可具体化（reified）
 * @param defaultValue 当编辑器为null或值为null时的默认返回值
 * @return T 当前的偏好值，如果为null则返回defaultValue
 *
 * 使用示例：
 * ```kotlin
 * @Composable
 * fun MySwitch() {
 *     val editor: ISingleValueEditor<Boolean>? = getEditor()
 *     val isChecked = editor.state(defaultValue = false)
 *
 *     Switch(
 *         checked = isChecked,
 *         onCheckedChange = { editor?.write(it) }
 *     )
 * }
 * ```
 *
 * 注意事项：
 * - 此函数必须在@Composable作用域中调用
 * - 会自动处理null情况，保证返回值不为null
 * - 当偏好值变化时，会自动重组使用该状态的Composable
 */
@Composable
inline fun <reified T : Any> ISingleValueEditor<T>?.state(defaultValue: T): T {
    if (this == null) {
        return defaultValue
    }
    return this.flow.collectAsState(defaultValue).value ?: defaultValue
}

/**
 * collect 偏好值变化
 */
suspend fun <T> ISingleValueEditor<T>.collect(collector: suspend (T?) -> Unit) {
    this.flow.collect(collector)
}
