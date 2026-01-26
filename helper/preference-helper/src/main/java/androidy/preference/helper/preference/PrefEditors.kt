package androidy.preference.helper.preference

import android.content.SharedPreferences
import kotlin.reflect.KClass
import kotlin.reflect.KProperty


class PrefEditor<T>(
    /* SharedPreferences, key */
    private val reader: (SharedPreferences, String) -> T?,
    /* SharedPreferences, key, value */
    private val writer: (SharedPreferences.Editor, String, T) -> Unit,
) {
    fun read(sharedPreferences: SharedPreferences, key: String): T? {
        return reader(sharedPreferences, key)
    }

    fun write(sharedPreferences: SharedPreferences, key: String, value: T) {
        val editor = sharedPreferences.edit()
        writer(editor, key, value)
        editor.apply()
    }
}

object PrefEditors {
    val intPrefEditor = PrefEditor(
        reader = { sp, key -> sp.getInt(key, 0) },
        writer = { editor, key, value -> editor.putInt(key, value) }
    )
    val longPrefEditor = PrefEditor(
        reader = { sp, key -> sp.getLong(key, 0L) },
        writer = { editor, key, value -> editor.putLong(key, value) }
    )
    val stringPrefEditor = PrefEditor(
        reader = { sp, key -> sp.getString(key, null) },
        writer = { editor, key, value -> editor.putString(key, value) }
    )
    val floatPrefEditor = PrefEditor(
        reader = { sp, key -> sp.getFloat(key, 0f) },
        writer = { editor, key, value -> editor.putFloat(key, value) }
    )
    val booleanPrefEditor = PrefEditor(
        reader = { sp, key -> sp.getBoolean(key, false) },
        writer = { editor, key, value -> editor.putBoolean(key, value) }
    )
    val stringSetPrefEditor = PrefEditor(
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


open class PrefDelegate<T>(
    val key: String?,
    val defaultValue: T? = null,
    val sharedPreferences: SharedPreferences,
    val editor: PrefEditor<T>,
) {
    open operator fun getValue(thisRef: Any?, property: KProperty<*>): T? {
        return editor.read(sharedPreferences, key ?: property.name) ?: defaultValue
    }

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
inline fun <reified T> SharedPreferences.gettingNoNull(defaultValue: T, key: String? = null): PrefDelegateNoNull<T> {
    return PrefDelegateNoNull(key, defaultValue, this, PrefEditors.parseEditor<T>())
}
