package knightwood.kv.aop.floordatastore

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlin.reflect.KClass

object DataStoreUtils {
    fun <T> getKeyInternal(keyName: String, cls: KClass<*>): Preferences.Key<T> {
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
