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

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlin.reflect.KClass

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
 * collect preference value flow
 */
inline fun <reified T : Any> ISingleValueEditor<T>.observe(keyName: String): Flow<T?> {
    val editor = this
    return editor.flow
}

/**
 * collect preference value flow with default value
 */
inline fun <reified T : Any> ISingleValueEditor<T>.observe(keyName: String, defaultValue: T?): Flow<T?> {
    return observe<T>(keyName).map { v -> v ?: defaultValue }
}


