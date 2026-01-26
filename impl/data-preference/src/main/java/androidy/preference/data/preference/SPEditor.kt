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

package androidy.preference.data.preference

import android.content.SharedPreferences
import androidy.preference.data.core.IPreferenceEditor
import androidy.preference.helper.preference.PrefEditors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

/**
 * 提供偏好值的读写，MMsp实现功能版本
 */
class SPEditor<T : Any>(
    private val sp: SharedPreferences,
    val keyName: String,
    val defaultValue: T,
) : IPreferenceEditor<T> {
    val TAG = "prefs_tool"

    private val flow: MutableSharedFlow<T> = MutableSharedFlow<T>(1)
    var readWrite = PrefEditors.parseEditor<T>(defaultValue::class)

    init {
        flow.tryEmit(readWrite.read(sp, keyName)?: defaultValue)
    }

    override fun flow(): Flow<T> {
        return flow
    }

    override fun readValue(): T {
        return readWrite.read(sp,keyName)?: defaultValue
    }

    override suspend fun write(data: T) {
        readWrite.write(sp, keyName,data)
        flow.emit(data)
    }
}
