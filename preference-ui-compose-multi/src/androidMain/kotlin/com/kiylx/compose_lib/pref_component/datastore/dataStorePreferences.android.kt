package com.kiylx.compose_lib.pref_component.datastore

import android.content.Context
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
 * @param fileName should be end with ".preferences_pb"
 * @param corruptionHandler
 * @param coroutineScope
 * @param migrations
 * @return
 */
fun getDataStore(
    context: Context,
    fileName: String,
    corruptionHandler: ReplaceFileCorruptionHandler<Preferences>? = null,
    coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
    migrations: List<DataMigration<Preferences>> = emptyList(),
): DataStore<Preferences> = DataStoreConfig.getDataStore(
    corruptionHandler = corruptionHandler,
    migrations = migrations,
    coroutineScope = coroutineScope,
    path = {
        context.filesDir.resolve(fileName).absolutePath
    }
)
