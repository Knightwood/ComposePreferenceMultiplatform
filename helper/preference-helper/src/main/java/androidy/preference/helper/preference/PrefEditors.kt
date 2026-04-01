package androidy.preference.helper.preference

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Parcelable
import kotlin.reflect.KClass
import kotlin.reflect.KProperty


//class PrefEditor<T>(
//    /* SharedPreferences, key */
//    private val reader: (SharedPreferences, String) -> T?,
//    /* SharedPreferences, key, value */
//    private val writer: (SharedPreferences.Editor, String, T) -> Unit,
//) {
//    fun read(sharedPreferences: SharedPreferences, key: String): T? {
//        return reader(sharedPreferences, key)
//    }
//
//    fun write(sharedPreferences: SharedPreferences, key: String, value: T) {
//        val editor = sharedPreferences.edit()
//        writer(editor, key, value)
//        editor.apply()
//    }
//}


/**
 * 使用统一的形式读写任意类型键值对
 */
interface PrefEditor<T> {
    fun read(sharedPreferences: SharedPreferences, key: String): T?
    fun write(sharedPreferences: SharedPreferences, key: String, value: T): Unit
}

@PublishedApi
internal fun <T> prefEditor(
    /* SharedPreferences, key */
    reader: (SharedPreferences, String) -> T?,
    /* SharedPreferences, key, value */
    writer: (SharedPreferences.Editor, String, T) -> Unit,
) = object : PrefEditor<T> {
    override fun read(sharedPreferences: SharedPreferences, key: String): T? {
        return reader(sharedPreferences, key)
    }

    @SuppressLint("UseKtx")
    override fun write(sharedPreferences: SharedPreferences, key: String, value: T): Unit {
        val editor = sharedPreferences.edit()
        writer(editor, key, value)
        editor.apply()
    }
}

object PrefEditors {
    val intPrefEditor = prefEditor(
        reader = { sp, key -> sp.getInt(key, 0) },
        writer = { editor, key, value -> editor.putInt(key, value) }
    )
    val longPrefEditor = prefEditor(
        reader = { sp, key -> sp.getLong(key, 0L) },
        writer = { editor, key, value -> editor.putLong(key, value) }
    )
    val stringPrefEditor = prefEditor(
        reader = { sp, key -> sp.getString(key, null) },
        writer = { editor, key, value -> editor.putString(key, value) }
    )
    val floatPrefEditor = prefEditor(
        reader = { sp, key -> sp.getFloat(key, 0f) },
        writer = { editor, key, value -> editor.putFloat(key, value) }
    )
    val booleanPrefEditor = prefEditor(
        reader = { sp, key -> sp.getBoolean(key, false) },
        writer = { editor, key, value -> editor.putBoolean(key, value) }
    )
    val stringSetPrefEditor = prefEditor(
        reader = { sp, key -> sp.getStringSet(key, null) },
        writer = { editor, key, value -> editor.putStringSet(key, value) }
    )


    fun <T> parseEditor(cls: KClass<*>): PrefEditor<T> {
        return when (cls) {
            Int::class -> intPrefEditor
            Long::class -> longPrefEditor
            String::class -> stringPrefEditor
            Float::class -> floatPrefEditor
            Boolean::class -> booleanPrefEditor
            Set::class -> stringSetPrefEditor
            else -> throw IllegalArgumentException("Not support type: $cls")
        } as PrefEditor<T>
    }

    inline fun <reified T> parseEditor(): PrefEditor<T> {
        val cls = T::class
        return parseEditor<T>(cls)
    }
}

//<editor-fold desc="受DataStore读写启发">
/**
 * ```
 * val sharedPref = activity?.getSharedPreferences(
 *         getString(R.string.preference_file_key), Context.MODE_PRIVATE)
 * val value :Int = sharedPref["key"]?: 0
 * ```
 */
inline operator fun <reified T> SharedPreferences.get(key: String): T? {
    val editor = PrefEditors.parseEditor<T>(T::class)
    return editor.read(this, key)
}

/**
 * ```
 * val sharedPref = activity?.getSharedPreferences(
 *         getString(R.string.preference_file_key), Context.MODE_PRIVATE
 * )
 * sharedPref["key"] = 1
 */
inline operator fun <reified T> SharedPreferences.set(key: String, value: T) {
    val editor = PrefEditors.parseEditor<T>(T::class)
    editor.write(this, key, value)
}
//</editor-fold>

//<editor-fold desc="另一种通用读写方式">


inline fun <reified T : Any> SharedPreferences.read(key: String, defaultValue: T): T {
    return when (T::class) {
        Int::class -> getInt(key, defaultValue as Int)
        Long::class -> getLong(key, defaultValue as Long)
        String::class -> getString(key, defaultValue as String)
        Float::class -> getFloat(key, defaultValue as Float)
        Boolean::class -> getBoolean(key, defaultValue as Boolean)
        Set::class -> getStringSet(key, defaultValue as Set<String>)
        else -> throw IllegalArgumentException("Not support type: ${T::class}")
    } as T
}

inline fun <reified T : Any> SharedPreferences.write(key: String, value: T) {
    this.edit().run {
        when (T::class) {
            Int::class -> putInt(key, value as Int)
            Long::class ->putLong(key, value as Long)
            String::class -> putString(key, value as String)
            Float::class -> putFloat(key, value as Float)
            Boolean::class -> putBoolean(key, value as Boolean)
            Set::class -> putStringSet(key, value as Set<String>)
            else -> throw IllegalArgumentException("Not support type: ${T::class}")
        }
        this.apply()
    }
}

//</editor-fold>

//<editor-fold desc="委托">

open class PrefDelegate<T>(
    val key: String?,
    val defaultValue: T? = null,
    val sharedPreferences: SharedPreferences,
    val editor: PrefEditor<T>,
) {
    open operator fun getValue(thisRef: Any?, property: KProperty<*>): T? {
        return editor.read(sharedPreferences, key ?: property.name) ?: defaultValue
    }

    @SuppressLint("UseKtx")
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        if (value == null) {
            sharedPreferences.edit().remove(key ?: property.name).apply()
        } else {
            editor.write(sharedPreferences, key ?: property.name, value)
        }
    }
}

/**
 * 使用：
 * ```
 * class PrefTest(val prefs: SharedPreferences) {
 *     var seedId by prefs.getting<Int>()
 * }
 * ```
 *
 * @param defaultValue 默认值
 * @param key 键，默认为属性名
 */
inline fun <reified T> SharedPreferences.getting(defaultValue: T? = null, key: String? = null): PrefDelegate<T> {
    return PrefDelegate(key, defaultValue, this, PrefEditors.parseEditor<T>())
}


class PrefDelegateNoNull<T>(
    key: String?,
    defaultValue: T,
    sharedPreferences: SharedPreferences,
    editor: PrefEditor<T>,
) : PrefDelegate<T>(key, defaultValue, sharedPreferences, editor) {

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return (editor.read(sharedPreferences, key ?: property.name) ?: defaultValue)!!
    }

}

/**
 * 使用：
 * ```
 * class PrefTest(val prefs: SharedPreferences) {
 *     var seedId by prefs.gettingNoNull<Int>()
 * }
 * ```
 *
 * @param defaultValue 默认值
 * @param key 键，默认为属性名
 */
inline fun <reified T> SharedPreferences.gettingNoNull(
    defaultValue: T,
    key: String? = null,
): PrefDelegateNoNull<T> {
    return PrefDelegateNoNull(key, defaultValue, this, PrefEditors.parseEditor<T>())
}
//</editor-fold>
