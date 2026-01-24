package com.knightwood.floor.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.knightwood.floor.core.core.IPreferenceDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

/**
 * @param dbFileName DataStore文件名
 */
abstract class DatastoreDao<T : Any>(val dbFileName: String? = null) : IPreferenceDao<T> {
    protected val ds: DataStore<Preferences> by lazy {
        DatastoreFloor.getDb(dbFileName)
    }

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
