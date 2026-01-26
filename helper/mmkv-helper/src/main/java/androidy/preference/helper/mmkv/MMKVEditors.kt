package androidy.preference.helper.mmkv

import android.os.Parcelable
import com.tencent.mmkv.MMKV
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty


class MMKVEditor<T>(
    /* MMKV, key */
    private val reader: (MMKV, String) -> T?,
    /* MMKV, key, value */
    private val writer: (MMKV, String, T) -> Boolean,
) {
    fun read(mmkv: MMKV, key: String): T? {
        return reader(mmkv, key)
    }

    fun write(mmkv: MMKV, key: String, value: T): Boolean {
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


    fun <T> parseEditor(cls: KClass<*>): MMKVEditor<T> {
        return when (cls) {
            Int::class -> intMMKVEditor
            Long::class -> longMMKVEditor
            String::class -> stringMMKVEditor
            Float::class -> floatMMKVEditor
            Double::class -> doubleMMKVEditor
            Boolean::class -> booleanMMKVEditor
            ByteArray::class -> bytesMMKVEditor
            Parcelable::class -> parcelableMMKVEditor<Parcelable>()
            else -> throw IllegalArgumentException("Not support type: $cls")
        } as MMKVEditor<T>
    }

    inline fun <reified T> parseEditor(): MMKVEditor<T> {
        val cls = T::class
        return parseEditor<T>(cls)
    }
}


open class MMKVDelegate<T>(
    val key: String?,
    val defaultValue: T? = null,
    val mmkv: MMKV,
    val editor: MMKVEditor<T>,
) {
    open operator fun getValue(thisRef: Any?, property: KProperty<*>): T? {
        return editor.read(mmkv, key ?: property.name) ?: defaultValue
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        if (value == null) {
            mmkv.removeValueForKey(key ?: property.name)
        } else {
            editor.write(mmkv, key ?: property.name, value)
        }
    }
}

/**
 * 使用：
 * ```
 * class MMKVTest(val mmkv: MMKV) {
 *     var seedId by mmkv.getting<Int>()
 * }
 * ```
 *
 * @param key 键，默认为属性名
 */
inline fun <reified T> MMKV.getting(defaultValue: T? = null, key: String? = null): MMKVDelegate<T> {
    return MMKVDelegate(key, defaultValue, this, MMKVEditors.parseEditor<T>())
}


class MMKVDelegateNoNull<T>(
    key: String?,
    defaultValue: T,
    mmkv: MMKV,
    editor: MMKVEditor<T>,
) : MMKVDelegate<T>(key, defaultValue, mmkv, editor) {

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return (editor.read(mmkv, key ?: property.name) ?: defaultValue)!!
    }

}

/**
 * 使用：
 * ```
 * class MMKVTest(val mmkv: MMKV) {
 *     var seedId by mmkv.gettingNoNull<Int>()
 * }
 * ```
 *
 * @param defaultValue 默认值
 * @param key 键，默认为属性名
 */
inline fun <reified T> MMKV.gettingNoNull(defaultValue: T, key: String? = null): MMKVDelegateNoNull<T> {
    return MMKVDelegateNoNull(key, defaultValue, this, MMKVEditors.parseEditor<T>())
}
