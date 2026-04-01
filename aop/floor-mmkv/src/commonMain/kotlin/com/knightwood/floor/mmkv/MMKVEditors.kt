package com.knightwood.floor.mmkv

import android.os.Parcelable
import com.tencent.mmkv.MMKV
import kotlin.reflect.KClass


/**
 * 使用统一的形式读写任意类型键值对
 */
interface MMKVEditor<T> {
    fun read(mmkv: MMKV, key: String): T?
    fun write(mmkv: MMKV, key: String, value: T): Boolean
}

@PublishedApi
internal fun <T> mmkvEditor(
    /* MMKV, key */
    reader: (MMKV, String) -> T?,
    /* MMKV, key, value */
    writer: (MMKV, String, T) -> Boolean,
) = object : MMKVEditor<T> {
    override fun read(mmkv: MMKV, key: String): T? {
        return reader(mmkv, key)
    }

    override fun write(mmkv: MMKV, key: String, value: T): Boolean {
        return writer(mmkv, key, value)
    }
}

object MMKVEditors {
    val intMMKVEditor = mmkvEditor(
        reader = { mmkv, key -> mmkv.decodeInt(key) },
        writer = { mmkv, key, value -> mmkv.encode(key, value) }
    )
    val longMMKVEditor = mmkvEditor(
        reader = { mmkv, key -> mmkv.decodeLong(key) },
        writer = { mmkv, key, value -> mmkv.encode(key, value) }
    )
    val stringMMKVEditor = mmkvEditor(
        reader = { mmkv, key -> mmkv.decodeString(key) },
        writer = { mmkv, key, value -> mmkv.encode(key, value) }
    )
    val floatMMKVEditor = mmkvEditor(
        reader = { mmkv, key -> mmkv.decodeFloat(key) },
        writer = { mmkv, key, value -> mmkv.encode(key, value) }
    )
    val doubleMMKVEditor = mmkvEditor(
        reader = { mmkv, key -> mmkv.decodeDouble(key) },
        writer = { mmkv, key, value -> mmkv.encode(key, value) }
    )
    val booleanMMKVEditor = mmkvEditor(
        reader = { mmkv, key -> mmkv.decodeBool(key) },
        writer = { mmkv, key, value -> mmkv.encode(key, value) }
    )
    val bytesMMKVEditor = mmkvEditor(
        reader = { mmkv, key -> mmkv.decodeBytes(key) },
        writer = { mmkv, key, value -> mmkv.encode(key, value) }
    )

    inline fun <reified T : Parcelable> parcelableMMKVEditor() = mmkvEditor<T>(
        reader = { mmkv, key -> mmkv.decodeParcelable(key, T::class.java) },
        writer = { mmkv, key, value -> mmkv.encode(key, value) }
    )

    fun <T> parseEditor(cls: KClass<*>): MMKVEditor<T> {
        return when (cls) {
            Int::class -> MMKVEditors.intMMKVEditor
            Long::class -> MMKVEditors.longMMKVEditor
            String::class -> MMKVEditors.stringMMKVEditor
            Float::class -> MMKVEditors.floatMMKVEditor
            Double::class -> MMKVEditors.doubleMMKVEditor
            Boolean::class -> MMKVEditors.booleanMMKVEditor
            ByteArray::class -> MMKVEditors.bytesMMKVEditor
            Parcelable::class -> MMKVEditors.parcelableMMKVEditor<Parcelable>()
            else -> throw IllegalArgumentException("Not support type: $cls")
        } as MMKVEditor<T>
    }

    inline fun <reified T> parseEditor(): MMKVEditor<T> {
        val cls = T::class
        return parseEditor<T>(cls)
    }

}

