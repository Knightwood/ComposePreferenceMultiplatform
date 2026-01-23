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

package androidy.preference.helper.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.core.PreferencesSerializer.defaultValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

/**
 * 可以在程序初始化时调用此方法，将datastore中的数据全部加载到内存中
 */
suspend fun DataStore<Preferences>.preLoad() {
    this.data.first()
}

/**
 * 批量添加或编辑，参数使用 key to value 生成[Preferences.Pair]
 */
suspend inline fun <reified T> DataStore<Preferences>.putAll(vararg pairs: Preferences.Pair<T>) {
    this.updateData {
        it.toMutablePreferences().putAll(*pairs)
        it
    }
}

/**
 * 传入某个偏好值的key，得到这个偏好值可观察的flow
 *
 * @param T
 * @param key
 * @param defaultValue
 * @return
 */
inline fun <reified T> DataStore<Preferences>.asDataFlow(
    key: Preferences.Key<T>,
    defaultValue: T,
): Flow<T> {
    return this.data.map { preferences ->
        // No type safety.
        preferences[key] ?: defaultValue
    }
}

/**
 * 传入某个偏好值的key，得到这个偏好值可观察的flow
 *
 * @param T
 * @param key
 * @return
 */
inline fun <reified T> DataStore<Preferences>.asDataFlow(
    key: String,
): Flow<T?> {
    val key1 = getKey<T>(key)
    return this.data.map { preferences ->
        // No type safety.
        preferences[key1]
    }
}


/**
 * 以同步的方式读取某个key的数据
 *
 * @param T
 * @param key
 * @param defaultValue
 * @return
 */
fun <T> DataStore<Preferences>.read(
    key: Preferences.Key<T>,
    defaultValue: T,
): T {
    val ds = this
    return runBlocking { ds.data.first()[key] ?: defaultValue }
}

/**
 * 以同步的方式读取某个key的数据
 *
 * @param T
 * @param keyname
 * @return
 */
inline fun <reified T> DataStore<Preferences>.read(
    keyname: String,
    defaultValue: T,
): T {
    val key = getKey<T>(keyname)
    return read(key, defaultValue)
}

/**
 * 以同步的方式读取某个key的数据
 *
 * @param T
 * @param key
 * @param defaultValue
 * @return
 */
fun <T> DataStore<Preferences>.readNullable(
    key: Preferences.Key<T>,
): T? {
    val ds = this
    return runBlocking { ds.data.first()[key] }
}

/**
 * 以同步的方式读取某个key的数据
 *
 * @param T
 * @param keyname
 * @return
 */
inline fun <reified T> DataStore<Preferences>.readNullable(
    keyname: String,
): T? {
    val key = getKey<T>(keyname)
    return readNullable(key)
}

/**
 * 将值保存到datastore
 *
 * @param T
 * @param key
 * @param value 如果为null，则删除该key
 */
suspend fun <T> DataStore<Preferences>.save(key: Preferences.Key<T>, value: T) {
    this.edit {
        it[key] = value
    }
}

/**
 * 将值保存到datastore
 *
 * @param T
 * @param value 如果为null，则删除该key
 * @param key
 */
suspend inline fun <reified T> DataStore<Preferences>.save(keyname: String, value: T) {
    val key = getKey<T>(keyname)
    this.edit {
        it[key] = value
    }
}

inline fun <reified T> MutablePreferences.saveOrRemove(keyname: String, value: T?) {
    val key = getKeyInternal<T>(keyname, T::class)
    saveOrRemove(key, value)
}

inline fun <reified T> MutablePreferences.saveOrRemove(key: Preferences.Key<T>, value: T?) {
    if (value != null) {
        this[key] = value
    } else {
        this.remove(key)
    }
}

suspend inline fun <reified T> DataStore<Preferences>.remove(keyname: String) {
    val key = getKey<T>(keyname)
    this.edit {
        it.remove(key)
    }
}

/**
 * 设置新值，如果为null则删除该key
 */
fun <T> MutablePreferences.setOrRemove(key: Preferences.Key<T>, value: T?) {
    if (value != null) {
        set(key, value)
    } else {
        remove(key)
    }
}

//<editor-fold desc="获取 datastore key">
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
    return getKeyInternal(keyName, T::class)
}

/**
 * ```
 * val keyName by dataStoreKey<String>()
 * ```
 *
 * @param T
 */
inline fun <reified T> dataStoreKey() = DataStoreKeyProvider.of<T>()

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
inline fun <reified T> DataStore<Preferences>.key() = DataStoreKeyProvider.of<T>()


@PublishedApi
internal fun <T> getKeyInternal(keyName: String, cls: KClass<*>): Preferences.Key<T> {
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
    private val type: KClass<*>,
) {
    companion object {
        inline fun <reified T> of(): DataStoreKeyProvider<T> {
            return DataStoreKeyProvider(T::class)
        }
    }

    operator fun provideDelegate(thisRef: Any?, property: KProperty<*>): DataStoreKeyHolder<T> {
        return DataStoreKeyHolder(getKeyInternal<T>(property.name, type))
    }
}

class DataStoreKeyHolder<T>(private val key: Preferences.Key<T>) :
    ReadOnlyProperty<Any?, Preferences.Key<T>> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): Preferences.Key<T> {
        return key
    }
}


//</editor-fold>

