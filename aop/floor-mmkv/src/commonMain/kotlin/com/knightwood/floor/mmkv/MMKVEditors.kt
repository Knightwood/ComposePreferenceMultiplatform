package com.knightwood.floor.mmkv

import android.os.Parcelable
import com.tencent.mmkv.MMKV
import kotlin.reflect.KClass


class MMKVEditor<T>(
    /* MMKV, key */
    private val reader: (MMKV, String) -> T?,
    /* MMKV, key, value */
    private val writer: (MMKV, String, T) -> Boolean,
) {
    fun read(mmkv: MMKV, key: String): T? {
        return reader(mmkv, key)
    }

    fun write(mmkv: MMKV, key: String, value: T?): Boolean {
        if (value == null) {
            mmkv.removeValueForKey(key)
            return true
        }
        return writer(mmkv, key, value)
    }
}

object MMKVEditors {
    val intMMKVEditor = MMKVEditor(
        reader = { mmkv, key -> mmkv.decodeInt(key) },
        writer = { mmkv, key, value -> mmkv.encode(key, value) }
    )
    val longMMKVEditor = MMKVEditor(
        reader = { mmkv, key -> mmkv.decodeLong(key) },
        writer = { mmkv, key, value -> mmkv.encode(key, value) }
    )
    val stringMMKVEditor = MMKVEditor(
        reader = { mmkv, key -> mmkv.decodeString(key) },
        writer = { mmkv, key, value -> mmkv.encode(key, value) }
    )
    val floatMMKVEditor = MMKVEditor(
        reader = { mmkv, key -> mmkv.decodeFloat(key) },
        writer = { mmkv, key, value -> mmkv.encode(key, value) }
    )
    val doubleMMKVEditor = MMKVEditor(
        reader = { mmkv, key -> mmkv.decodeDouble(key) },
        writer = { mmkv, key, value -> mmkv.encode(key, value) }
    )
    val booleanMMKVEditor = MMKVEditor(
        reader = { mmkv, key -> mmkv.decodeBool(key) },
        writer = { mmkv, key, value -> mmkv.encode(key, value) }
    )
    val bytesMMKVEditor = MMKVEditor(
        reader = { mmkv, key -> mmkv.decodeBytes(key) },
        writer = { mmkv, key, value -> mmkv.encode(key, value) }
    )

    inline fun <reified T : Parcelable> parcelableMMKVEditor() = MMKVEditor<T>(
        reader = { mmkv, key -> mmkv.decodeParcelable(key, T::class.java) },
        writer = { mmkv, key, value -> mmkv.encode(key, value) }
    )

    fun <T> parseEditor(cls: Class<*>): MMKVEditor<T> {
        return when (cls) {
            Int::class.java -> MMKVEditors.intMMKVEditor
            Long::class.java -> MMKVEditors.longMMKVEditor
            String::class.java -> MMKVEditors.stringMMKVEditor
            Float::class.java -> MMKVEditors.floatMMKVEditor
            Double::class.java -> MMKVEditors.doubleMMKVEditor
            Boolean::class.java -> MMKVEditors.booleanMMKVEditor
            ByteArray::class.java -> MMKVEditors.bytesMMKVEditor
            Parcelable::class.java -> MMKVEditors.parcelableMMKVEditor<Parcelable>()
            else -> throw IllegalArgumentException("Not support type: $cls")
        } as MMKVEditor<T>
    }

    inline fun <reified T> parseEditor(): MMKVEditor<T> {
        val cls = T::class.java
        return when (cls) {
            Int::class.java -> MMKVEditors.intMMKVEditor
            Long::class.java -> MMKVEditors.longMMKVEditor
            String::class.java -> MMKVEditors.stringMMKVEditor
            Float::class.java -> MMKVEditors.floatMMKVEditor
            Double::class.java -> MMKVEditors.doubleMMKVEditor
            Boolean::class.java -> MMKVEditors.booleanMMKVEditor
            ByteArray::class.java -> MMKVEditors.bytesMMKVEditor
            Parcelable::class.java -> MMKVEditors.parcelableMMKVEditor<Parcelable>()
            else -> throw IllegalArgumentException("Not support type: $cls")
        } as MMKVEditor<T>
    }

}

