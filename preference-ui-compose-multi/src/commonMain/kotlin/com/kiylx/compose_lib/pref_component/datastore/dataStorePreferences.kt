package com.kiylx.compose_lib.pref_component.datastore

import androidx.datastore.core.DataMigration
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import kotlinx.atomicfu.locks.SynchronizedObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import okio.Path.Companion.toPath

object DataStoreConfig {
    private lateinit var dataStore: DataStore<Preferences>
    private val lock = SynchronizedObject()

    fun checkFileName(name: String): String {
        return if (!name.endsWith(".preferences_pb")) {
            throw IllegalArgumentException("should be end with \".preferences_pb\"")
        } else {
            name
        }
    }

    /** Gets the singleton DataStore instance, creating it if necessary. */
    fun getDataStore(
        corruptionHandler: ReplaceFileCorruptionHandler<Preferences>? = null,
        coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
        migrations: List<DataMigration<Preferences>> = emptyList(),
        path: () -> String,
    ): DataStore<Preferences> {
        return kotlinx.atomicfu.locks.synchronized(lock) {
            if (::dataStore.isInitialized) {
                dataStore
            } else {
                PreferenceDataStoreFactory
                    .createWithPath(
                        corruptionHandler = corruptionHandler,
                        scope = coroutineScope,
                        migrations = migrations,
                        produceFile = {
                            path().toPath()
                        }
                    )
                    .also {
                        dataStore = it
                    }
            }
        }
    }
}