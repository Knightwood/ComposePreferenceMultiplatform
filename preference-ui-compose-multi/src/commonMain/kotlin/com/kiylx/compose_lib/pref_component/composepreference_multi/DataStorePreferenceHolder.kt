package com.kiylx.compose_lib.pref_component.composepreference_multi;

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.kiylx.libx.pref_component.core.IPreferenceReadWrite
import com.kiylx.libx.pref_component.core.PreferenceHolder

/** 向界面提供、管理PreferenceProvider */
class DataStorePreferenceHolder internal constructor(
    private val datastore: DataStore<Preferences>
) : PreferenceHolder() {
    private fun dataStore() = datastore

    override fun <T : Any> getReadWriteTool(
        keyName: String,
        defaultValue: T,
    ): IPreferenceReadWrite<T> {
        return hashMap[keyName]?.let {
            it as IPreferenceReadWrite<T>
        } ?: let {
            val tmp = DataStoreReadWritePrefTool(keyName, defaultValue, dataStore())
            hashMap[keyName] = tmp
            tmp
        }
    }

    companion object {
        @Volatile
        var ps: DataStorePreferenceHolder? = null
        fun instance(
            datastore: DataStore<Preferences>
        ): DataStorePreferenceHolder {
            return ps ?: synchronized(this) {
                ps ?: DataStorePreferenceHolder(datastore).also { ps = it }
            }
        }
    }
}