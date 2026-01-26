package androidy.preference.helper.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

object DataStoreKeyUtils {
    inline fun <reified T> getKey(keyName: String): Preferences.Key<T> {
        return getKey(keyName, T::class)
    }

    fun <T> getKey(keyName: String, cls: KClass<*>): Preferences.Key<T> {
        return (when (cls) {
            Int::class -> {
                intPreferencesKey(keyName)
            }

            Boolean::class -> {
                booleanPreferencesKey(keyName)
            }

            String::class -> {
                stringPreferencesKey(keyName)
            }

            Double::class -> {
                doublePreferencesKey(keyName)
            }

            Float::class -> {
                floatPreferencesKey(keyName)
            }

            Long::class -> {
                longPreferencesKey(keyName)
            }

            Set::class -> {
                stringSetPreferencesKey(keyName)
            }

            else -> {
                throw IllegalArgumentException("not support")

            }
        }) as Preferences.Key<T>

    }
}

/**
 * ```
 * val nightModeKey = ds.getKey<Int>("ms_1")
 *
 * ```
 *
 * @param T
 * @param keyName
 * @receiver 接受者类型没用到，统一、好看而已
 * @return
 */
inline fun <reified T> DataStore<Preferences>.getKey(keyName: String): Preferences.Key<T> {
    return DataStoreKeyUtils.getKey(keyName, T::class)
}

/**
 * 使用委托的方式生成datastore的key
 *
 * ```
 * //使用扩展函数版
 * private val keyName by dataStore.key<String>()
 * //实际上也有非扩展函数版，他两个没什么本质上的区别，无非就是加上datastore形式上好看一点
 * private val keyName by dataStoreKey<String>()
 *
 * ```
 */
class DataStoreKeyProvider<T>(
    private val name: String? = null,
    private val type: KClass<*>,
) {
    companion object {
        inline fun <reified T> of(name: String? = null): DataStoreKeyProvider<T> {
            return DataStoreKeyProvider(name, T::class)
        }
    }

    operator fun provideDelegate(thisRef: Any?, property: KProperty<*>): DataStoreKeyDelegate<T> {
        return DataStoreKeyDelegate(DataStoreKeyUtils.getKey<T>(name ?: property.name, type))
    }
}

class DataStoreKeyDelegate<T>(private val key: Preferences.Key<T>) :
    ReadOnlyProperty<Any?, Preferences.Key<T>> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): Preferences.Key<T> {
        return key
    }
}


/**
 * ```
 * val keyName by dataStoreKey<String>()
 * ```
 *
 * @param T
 */
inline fun <reified T> dataStoreKey(name: String? = null) = DataStoreKeyProvider.of<T>(name)

/**
 * ```
 * val keyName by dataStore.key<String>()
 * ```
 *
 * same as [dataStoreKey]
 *
 * @param T
 *
 * @receiver 接受者类型没用到，统一、好看而已
 */
inline fun <reified T> DataStore<Preferences>.key(name: String? = null) = DataStoreKeyProvider.of<T>(name)

