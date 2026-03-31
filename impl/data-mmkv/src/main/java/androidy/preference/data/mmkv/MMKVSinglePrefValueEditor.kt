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

package androidy.preference.data.mmkv

import androidy.preference.data.core.ISinglePrefValueEditor
import androidy.preference.helper.mmkv.MMKVEditor
import androidy.preference.helper.mmkv.MMKVEditors
import com.tencent.mmkv.MMKV
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * 提供偏好值的读写，MMKV实现功能版本
 */
class MMKVSinglePrefValueEditor<T : Any>(
    val kv: MMKV,
    val keyName: String,
    val defaultValue: T,
) : ISinglePrefValueEditor<T> {
    val TAG = "mmkv_tool"

    private val flow: MutableStateFlow<T> = MutableStateFlow<T>(defaultValue)
    var readWrite: MMKVEditor<T> = MMKVEditors.parseEditor(defaultValue::class)

    init {
        flow.value = readWrite.read(kv, keyName) ?: defaultValue
    }

    override fun flow(): Flow<T> {
        return flow
    }

    override fun readValue(): T {
        return readWrite.read(kv, keyName) ?: defaultValue
    }

    override suspend fun write(data: T?) {
        if (data == null) {
            kv.removeValueForKey(keyName)
            flow.emit(defaultValue)
            return
        }
        readWrite.write(kv, keyName, data)
        flow.emit(data)
    }
}
