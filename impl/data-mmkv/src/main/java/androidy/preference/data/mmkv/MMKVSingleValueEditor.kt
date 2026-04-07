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

import androidy.preference.data.core.ISingleValueEditor
import androidy.preference.helper.mmkv.MMKVEditor
import androidy.preference.helper.mmkv.MMKVEditors
import com.tencent.mmkv.MMKV
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.reflect.KClass

/**
 * 提供偏好值的读写，MMKV实现功能版本
 */
class MMKVSingleValueEditor<T : Any>(
    val kv: MMKV,
    val keyName: String,
    val cls: KClass<T>,
    val scope: CoroutineScope,
) : ISingleValueEditor<T> {
    val TAG = "mmkv_tool"

    private val _flow = MutableStateFlow<T?>(null)
    override val flow: Flow<T?> get() = _flow
    var mMKVEditor: MMKVEditor<T> = MMKVEditors.parseEditor(cls)

    init {
        _flow.value = mMKVEditor.read(kv, keyName)
    }

    override fun readValue(): T? {
        return mMKVEditor.read(kv, keyName)
    }

    override fun write(data: T?) {
        if (data == null) {
            kv.removeValueForKey(keyName)
            _flow.value = null
            return
        }
        mMKVEditor.write(kv, keyName, data)
        _flow.value = data
    }

    override suspend fun readValueAsync(): T? {
        return readValue()
    }

    override suspend fun writeAsync(data: T?) {
        write(data)
    }
}
