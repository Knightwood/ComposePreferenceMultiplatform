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
 * 子类实现此接口以提供具体的每个偏好值的读写能力
 */
interface ISingleValueEditor<T> {

    val flow: Flow<T?>

    suspend fun readValueAsync(): T?

    suspend fun writeAsync(data: T?)

    fun readValue(): T? = runBlocking { readValueAsync() }

    fun write(data: T?) = runBlocking { writeAsync(data) }
}

/**
 * @param defaultValue 组件的enabled状态
 * @return 若节点存在，则返回节点的enabled状态，否则返回组件输入的enabled状态
 */
@Composable
inline fun <reified T : Any> ISingleValueEditor<T>?.state(defaultValue: T): T {
    if (this == null) {
        return defaultValue
    }
    return this.flow.collectAsState(defaultValue).value ?: defaultValue
}
