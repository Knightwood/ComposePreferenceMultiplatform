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

package androidy.compose.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass

/**
 * 批量添加或编辑，参数使用 key to value 生成[Preferences.Pair]
 */
suspend inline fun <reified T> DataStore<Preferences>.putAll(vararg pairs: Preferences.Pair<T>) {
    this.updateData {
        it.toMutablePreferences().putAll(*pairs)
        it
    }
}

inline fun <reified T> DataStore<Preferences>.asDataFlow(
    key: Preferences.Key<T>,
    defaultValue: T
): Flow<T> {
    return this.data.map { preferences ->
        // No type safety.
        preferences[key] ?: defaultValue
    }
}

inline fun <reified T> DataStore<Preferences>.asDataFlow(
    key: String,
): Flow<T?> {
    val key1 = getKey<T?>(key)
    return this.data.map { preferences ->
        // No type safety.
        preferences[key1]
    }
}

fun <T> DataStore<Preferences>.getKey(keyName: String, cls: KClass<*>): Preferences.Key<T> {
    return getKeyInternal(keyName, cls)

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

        else -> {
            throw IllegalArgumentException("not support")

        }
    }) as Preferences.Key<T>

}

inline fun <reified T> DataStore<Preferences>.getKey(keyName: String): Preferences.Key<T> {
    return getKey(keyName, T::class)
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
    private val type: KClass<*>
) {
    companion object {
        inline fun <reified T> of(): DataStoreKeyProvider<T> {
            return DataStoreKeyProvider(T::class)
        }
    }

    operator fun provideDelegate(thisRef: Any?, property: kotlin.reflect.KProperty<*>): DataStoreKeyHolder<T> {
        return DataStoreKeyHolder(getKeyInternal<T>(property.name, type))
    }
}
class DataStoreKeyHolder<T>(private val key: Preferences.Key<T>) : ReadOnlyProperty<Any?, Preferences.Key<T>> {
    override fun getValue(thisRef: Any?, property: kotlin.reflect.KProperty<*>): Preferences.Key<T> {
        return key
    }
}

inline fun <reified T> dataStoreKey() = DataStoreKeyProvider.of<T>()
inline fun <reified T> DataStore<Preferences>.key() = DataStoreKeyProvider.of<T>()


