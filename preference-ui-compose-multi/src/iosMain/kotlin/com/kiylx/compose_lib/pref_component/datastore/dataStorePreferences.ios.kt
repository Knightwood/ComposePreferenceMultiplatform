package com.kiylx.compose_lib.pref_component.datastore

import androidx.datastore.core.DataMigration
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.CoroutineScope

/**
 * Get data store
 *
 * @param fileName should be end with ".preferences_pb"
 * @param corruptionHandler
 * @param coroutineScope
 * @param migrations
 * @return
 */
@OptIn(ExperimentalForeignApi::class)
fun getDataStore(
    fileName:String,
    corruptionHandler: ReplaceFileCorruptionHandler<Preferences>? = null,
    coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
    migrations: List<DataMigration<Preferences>> = emptyList(),
): DataStore<Preferences> = createDataStoreWithDefaults(
    corruptionHandler = corruptionHandler,
    migrations = migrations,
    coroutineScope = coroutineScope,
    path = {
        val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        (requireNotNull(documentDirectory).path + "/$fileName")
    }
)

