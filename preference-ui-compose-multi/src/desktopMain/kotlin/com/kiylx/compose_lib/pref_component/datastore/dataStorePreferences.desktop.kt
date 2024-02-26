package com.kiylx.compose_lib.pref_component.datastore

import androidx.datastore.core.DataMigration
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

/**
 * Get data store
 *
 * @param filePath should be end with ".preferences_pb"
 * @param corruptionHandler
 * @param coroutineScope
 * @param migrations
 * @return
 */
fun getDataStore(
    filePath: String,
    corruptionHandler: ReplaceFileCorruptionHandler<Preferences>? = null,
    coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
    migrations: List<DataMigration<Preferences>> = emptyList(),
): DataStore<Preferences> = DataStoreConfig.getDataStore(
    corruptionHandler = corruptionHandler,
    migrations = migrations,
    coroutineScope = coroutineScope,
    path = {
        filePath
    }
)