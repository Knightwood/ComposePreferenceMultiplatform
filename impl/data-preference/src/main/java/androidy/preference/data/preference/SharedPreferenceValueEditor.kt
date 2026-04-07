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
import androidy.preference.data.core.ISingleValueEditor
import androidy.preference.helper.preference.PrefEditors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import androidx.core.content.edit
import kotlinx.coroutines.CoroutineScope
import kotlin.reflect.KClass

/**
 * 提供偏好值的读写，MMsp实现功能版本
 */
class SharedPreferenceValueEditor<T : Any>(
    private val sp: SharedPreferences,
    val keyName: String,
    val cls: KClass<T>,
    val scope: CoroutineScope,
) : ISingleValueEditor<T> {
    val TAG = "prefs_tool"

    private val _flow = MutableStateFlow<T?>(null)
    override val flow: Flow<T?> get() = _flow
    var prefEditor = PrefEditors.parseEditor<T>(cls)

    init {
        _flow.value = prefEditor.read(sp, keyName)
    }


    override fun readValue(): T? {
        return prefEditor.read(sp, keyName)
    }

    override fun write(data: T?) {
        if (data == null) {
            sp.edit { remove(keyName) }
            _flow.value = null
        } else {
            prefEditor.write(sp, keyName, data)
            _flow.value = data
        }
    }

    override suspend fun readValueAsync(): T? {
        return readValue()
    }
    override suspend fun writeAsync(data: T?) {
        write(data)
    }
}
