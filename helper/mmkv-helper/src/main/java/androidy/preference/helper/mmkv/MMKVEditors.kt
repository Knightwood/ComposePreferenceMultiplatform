package androidy.preference.helper.mmkv

import android.os.Parcelable
import com.tencent.mmkv.MMKV
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.time.Duration


//class MMKVEditor<T>(
//    /* MMKV, key */
//    private val reader: (MMKV, String) -> T?,
//    /* MMKV, key, value */
//    private val writer: (MMKV, String, T) -> Boolean,
//) {
//    fun read(mmkv: MMKV, key: String): T? {
//        return reader(mmkv, key)
//    }
//
//    fun write(mmkv: MMKV, key: String, value: T): Boolean {
//        return writer(mmkv, key, value)
//    }
//}


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

//<editor-fold desc="受DataStore读写启发">
/**
 * 使用：
 * ```
 * val mmkv = MMKV.mmkvWithID("mmkv")
 * val value :Int = mmkv["key"]?: 0
 * ```
 */
inline operator fun <reified T> MMKV.get(key: String): T? {
    val editor = MMKVEditors.parseEditor<T>(T::class)
    return editor.read(this, key)
}

/**
 * 使用：
 * ```
 * val mmkv = MMKV.mmkvWithID("mmkv")
 * mmkv["key"] = 0
 *  ```
 */
inline operator fun <reified T> MMKV.set(key: String, value: T) {
    val editor = MMKVEditors.parseEditor<T>(T::class)
    editor.write(this, key, value)
}
//</editor-fold>

//<editor-fold desc="另一种通用读写方式">
inline fun <reified T : Any> MMKV.read(key: String): T? {
    return when (T::class) {
        Int::class -> decodeInt(key)
        Long::class -> decodeLong(key)
        String::class -> decodeString(key)
        Float::class -> decodeFloat(key)
        Double::class -> decodeDouble(key)
        Boolean::class -> decodeBool(key)
        ByteArray::class -> decodeBytes(key)
        Parcelable::class -> decodeParcelable(key, Parcelable::class.java)
        else -> throw IllegalArgumentException("Not support type: ${T::class}")
    } as T?
}

inline fun <reified T : Any> MMKV.read(key: String, defaultValue: T): T {
    return when (T::class) {
        Int::class -> decodeInt(key, defaultValue as Int)
        Long::class -> decodeLong(key, defaultValue as Long)
        String::class -> decodeString(key, defaultValue as String)
        Float::class -> decodeFloat(key, defaultValue as Float)
        Double::class -> decodeDouble(key, defaultValue as Double)
        Boolean::class -> decodeBool(key, defaultValue as Boolean)
        ByteArray::class -> decodeBytes(key, defaultValue as ByteArray)
        Parcelable::class -> decodeParcelable(key, Parcelable::class.java, defaultValue as Parcelable)
        else -> throw IllegalArgumentException("Not support type: ${T::class}")
    } as T
}

inline fun <reified T : Any> MMKV.write(key: String, value: T) {
    when (T::class) {
        Int::class -> encode(key, value as Int)
        Long::class -> encode(key, value as Long)
        String::class -> encode(key, value as String)
        Float::class -> encode(key, value as Float)
        Double::class -> encode(key, value as Double)
        Boolean::class -> encode(key, value as Boolean)
        ByteArray::class -> encode(key, value as ByteArray)
        Parcelable::class -> encode(key, value as Parcelable)
    }
}

inline fun <reified T : Any> MMKV.write(key: String, value: T, expireInSecond: Int) {
    when (T::class) {
        Int::class -> encode(key, value as Int, expireInSecond)
        Long::class -> encode(key, value as Long, expireInSecond)
        String::class -> encode(key, value as String, expireInSecond)
        Float::class -> encode(key, value as Float, expireInSecond)
        Double::class -> encode(key, value as Double, expireInSecond)
        Boolean::class -> encode(key, value as Boolean, expireInSecond)
        ByteArray::class -> encode(key, value as ByteArray, expireInSecond)
        Parcelable::class -> encode(key, value as Parcelable, expireInSecond)
    }
}
//</editor-fold>

//<editor-fold desc="委托">

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
//</editor-fold>
