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

package androidy.preference.helper.mmkv

import android.os.Parcelable
import com.tencent.mmkv.MMKV
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * 使用方式：
 *
 * ```
 * class MMKVHelper private constructor(val mmkv: MMKV) {
 * //使用委托的方式生成一个委托对象，除了[parcelableM]方法，初始值可选
 * var name by mv.strM("tom","初始值")
 * }
 *
 * //1. 获取单例
 * val helper =MMKVHelper.getInstance(prefs)
 * //2. 使用赋值将值存入
 * helper.name="Tom"
 * //3. 直接使用即读取值，如果没有值写入，读取出来的会是默认值。
 * log.d(TAG,helper.name)*
 * ```
 */
@Deprecated("Use mmkv.getting() instead")
class MExt

@Deprecated("Use mmkv.getting() instead")
fun <T> MMKV.delegate(
    key: String? = null,
    defaultValue: T,
    editor: MMKVEditor<T>,
): ReadWriteProperty<Any, T> = object : ReadWriteProperty<Any, T> {

    override fun getValue(thisRef: Any, property: KProperty<*>): T =
        editor.read(this@delegate, key ?: property.name) ?: defaultValue

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        editor.write(this@delegate, key ?: property.name, value)
    }
}

/**
 * read write string
 *
 * @param key
 * @param defValue
 * @return
 */
@Deprecated("Use mmkv.getting() instead")
fun MMKV.strM(
    key: String,
    defValue: String = "",
): ReadWriteProperty<Any, String> {
    return delegate<String>(key, defValue, MMKVEditors.stringMMKVEditor)
}

@Deprecated("Use mmkv.getting() instead")
fun MMKV.intM(
    key: String,
    defValue: Int = 0,
): ReadWriteProperty<Any, Int> {
    return delegate<Int>(key, defValue, MMKVEditors.intMMKVEditor)
}

@Deprecated("Use mmkv.getting() instead")
fun MMKV.boolM(
    key: String,
    defValue: Boolean = false,
): ReadWriteProperty<Any, Boolean> {
    return delegate(key, defValue, MMKVEditors.booleanMMKVEditor)
}

@Deprecated("Use mmkv.getting() instead")
fun MMKV.longM(
    key: String,
    defValue: Long = 0L,
): ReadWriteProperty<Any, Long> {
    return delegate(key, defValue, MMKVEditors.longMMKVEditor)
}

@Deprecated("Use mmkv.getting() instead")
fun MMKV.floatM(
    key: String,
    defValue: Float = 0F,
): ReadWriteProperty<Any, Float> {
    return delegate(key, defValue, MMKVEditors.floatMMKVEditor)
}

@Deprecated("Use mmkv.getting() instead")
fun MMKV.doubleM(
    key: String,
    defValue: Double = 0.0,
): ReadWriteProperty<Any, Double> {
    return delegate(key, defValue, MMKVEditors.doubleMMKVEditor)
}

@Deprecated("Use mmkv.getting() instead")
fun MMKV.bytesM(
    key: String,
    defValue: ByteArray = byteArrayOf(),
): ReadWriteProperty<Any, ByteArray> {
    return delegate(key, defValue, MMKVEditors.bytesMMKVEditor)
}

@Deprecated("Use mmkv.getting() instead")
inline fun <reified T : Parcelable> MMKV.parcelableM(
    key: String,
    defValue: T,
): ReadWriteProperty<Any, T> {
    return delegate(key, defValue, MMKVEditors.parcelableMMKVEditor())
}


