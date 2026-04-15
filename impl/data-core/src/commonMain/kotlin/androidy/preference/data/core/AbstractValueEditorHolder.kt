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

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.plus
import kotlin.reflect.KClass

/**
 *
 * 此类是一组偏好值的核心管理类，负责：
 * 1. 管理所有已注册的偏好值编辑器实例（通过偏好值key映射）
 * 2. 提供组件依赖关系树
 *
 * 实现类需要提供具体的ISinglePrefValueEditorProvider来创建不同偏好值的编辑器实例。
 *
 * @param scope 协程作用域，用于执行异步读写操作。默认为IO调度器 + SupervisorJob
 *
 * 使用示例：
 * ```kotlin
 * class MyPreferenceHolder : AbstractValueEditorHolder() {
 *     override val editorProvider = object : ISinglePrefValueEditorProvider {
 *
 *         // 根据偏好值 key和此偏好值的类型创建对应的编辑器实例
 *         override fun <T : Any> createOnePrefEditor(keyName: String, cls: KClass<T>): ISingleValueEditor<T> {
 *             // 根据类型创建具体的编辑器实现
 *         }
 *     }
 * }
 *
 * val holder = MyPreferenceHolder()
 * // 获取“my_switch”这个偏好值的编辑器实例
 * val mySwitchEditor = holder.getOnePrefEditor<Boolean>("my_switch")
 * // 读取“my_switch”偏好值
 * val mySwitchValue = mySwitchEditor.readValueAsync()
 * mySwitchValue.collect { value -> }
 *
 * ```
 */
abstract class
AbstractValueEditorHolder(val scope: CoroutineScope) {
    constructor() : this(CoroutineScope(Dispatchers.IO) + SupervisorJob())

    /**
     * 每个偏好值都对应一个key，一种类型
     * 此接口提供单个偏好值对应的编辑器实例
     */
    abstract val editorProvider: ISinglePrefValueEditorProvider

    /**
     * 偏好编辑器缓存映射表
     *
     * Key: 偏好值的键名
     * Value: 此偏好值对应的编辑器实例
     *
     * 此Map采用懒加载策略，编辑器在首次通过getOnePrefEditor请求时创建并缓存，
     * 后续相同key的请求将直接返回缓存的实例，保证每个key只有一个编辑器实例。
     */
    val prefEditorHashMap: HashMap<String, ISingleValueEditor<*>> = hashMapOf()

    /**
     * 依赖关系树
     *
     * 用于管理不同偏好项之间的启用/禁用依赖关系。
     * 例如：当开关A关闭、disable时，选项B、C、D观察到A的状态变化，将自己disable。
     */
    val dependenceTree = EnableStateNodeTree(this)

    //<editor-fold desc="偏好值读写">
    /**
     * 获取某偏好值对应的编辑器实例，若实例不存在则自动创建并注册
     *
     * @param T 偏好值类型
     * @param keyName 偏好值的唯一标识键名
     * @return ISingleValueEditor<T> 对应类型的单值编辑器实例
     *
     * 使用示例：
     * ```kotlin
     * // 获取Boolean类型的编辑器
     * val switchEditor = holder.getOnePrefEditor<Boolean>("auto_sync")
     *
     * // 读取值
     * val isAutoSync = switchEditor.readValueAsync()
     *
     * // 写入值
     * switchEditor.writeAsync(true)
     * ```
     *
     * 注意事项：
     * - 类型T必须与实际存储的类型匹配，否则可能导致ClassCastException
     * - ISingleValueEditor实现类使用的偏好值存储系统（比如datastore、mmkv）必须支持此类型T，否则可能导致ClassCastException
     * - 返回的编辑器实例会被缓存，多次调用相同key会返回同一实例
     * - 编辑器线程安全，可在多个协程中并发使用
     */
    inline fun <reified T : Any> getOnePrefEditor(
        keyName: String,
    ): ISingleValueEditor<T> {
        return prefEditorHashMap.getOrPut(keyName, {
            editorProvider.createOnePrefEditor<T>(keyName, T::class)
        }) as ISingleValueEditor<T>
    }
    //</editor-fold>

    /**
     * 偏好值编辑器提供者接口
     *
     * 此接口定义了如何创建某偏好值对应的编辑器实例。
     * 实现类需要根据类型参数和key创建对应的ISingleValueEditor实现。
     *
     * 使用示例：
     * ```kotlin
     * val provider = object : ISinglePrefValueEditorProvider {
     *     override fun <T : Any> createOnePrefEditor(
     *         keyName: String,
     *         cls: KClass<T>
     *     ): ISingleValueEditor<T> {
     *         return when (cls) {
     *             Boolean::class -> DataStoreBooleanEditor(keyName, dataStore)
     *             String::class -> DataStoreStringEditor(keyName, dataStore)
     *             Int::class -> DataStoreIntEditor(keyName, dataStore)
     *             else -> throw IllegalArgumentException("Unsupported type: ${cls.simpleName}")
     *         } as ISingleValueEditor<T>
     *     }
     * }
     * ```
     */
    interface ISinglePrefValueEditorProvider {
        /**
         * 创建单个偏好值的编辑器实例
         *
         * @param T 偏好值的类型，必须是Any的子类
         * @param keyName 偏好值的唯一标识键名
         * @param cls 类型的KClass反射对象，用于运行时类型判断
         * @return ISingleValueEditor<T> 创建的编辑器实例
         * @throws IllegalArgumentException 当不支持该类型时抛出
         */
        fun <T : Any> createOnePrefEditor(
            keyName: String,
            cls: KClass<T>,
        ): ISingleValueEditor<T>
    }

}
