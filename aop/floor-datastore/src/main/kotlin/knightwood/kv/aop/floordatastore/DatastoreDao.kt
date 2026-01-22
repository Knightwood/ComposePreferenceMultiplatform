package knightwood.kv.aop.floordatastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import knightwood.kv.aop.floorcore.core.IPreferenceDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

/**
 * @param key 存储库在koin中的key
 *
 * 设计：
 * 1. 每个标记了KVStore的data class，都对应一个DatastoreDao实例。
 *  注解包含字段：可提供Datastore实例
 * 2. 每个data class的属性，都对应一个字段
 * 2. 使用koin提供不同datastore实例
 */
abstract class DatastoreDao<T : Any>(val key: String? = null) : IPreferenceDao<T>, KoinComponent {
    private val kv_store_ds: DataStore<Preferences> by inject { parametersOf(key) }
    protected val ds: DataStore<Preferences> get() = if (key == null) DatastoreFloor.db else kv_store_ds

    override val flow: Flow<T>
        get() = ds.data.map { value -> value.asT() }

    override val value: T
        get() = runBlocking { ds.data.first().asT() }

    override suspend fun update(value: T) {
        ds.edit { preferences ->
            value.modify(preferences)
        }
    }

    override suspend fun clear() {
        ds.edit { preferences ->
            keys.forEach { key ->
                preferences.remove(key)
            }
        }
    }

    /**
     * 存储此存储库中所有的key
     */
    abstract val keys: List<Preferences.Key<*>>

    /**
     * 用于将Preferences数据转换成T
     */
    abstract fun Preferences.asT(): T

    /**
     * 用于将T数据转换成Preferences
     */
    abstract fun T.modify(target: MutablePreferences): Preferences
}
