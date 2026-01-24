package com.knightwood.floor.datastore

import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.byteArrayPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlin.reflect.KClass

object DataStoreUtils {

    fun <T> setOrRemove(preference: MutablePreferences, key: Preferences.Key<T>, value: T?) {
        if (value != null) {
            preference[key] = value
        } else {
            preference.remove(key)
        }
    }

    /**
     *  判断属性是否原生支持，不需要TypeConvertor转换
     */
    fun isSupport(type: String): Boolean {
        return when (type) {
            "Int", "Boolean", "String", "Float", "Long", "Double", "ByteArray", "Set" -> true
            else -> false
        }
    }

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

            ByteArray::class -> {
                byteArrayPreferencesKey(keyName)
            }

            else -> {
                throw IllegalArgumentException("not support")

            }
        }) as Preferences.Key<T>

    }
}
