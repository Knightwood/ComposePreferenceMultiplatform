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

import androidx.datastore.core.DataMigration
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.Serializer
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidy.preference.helper.common.IPreferenceProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.encodeToStream
import java.io.File
import java.io.InputStream
import java.io.OutputStream

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
): DataStore<Preferences> = PreferenceDataStoreFactory.create(
    corruptionHandler = corruptionHandler,
    scope = coroutineScope,
    migrations = migrations,
    produceFile = { File(filePath) }
)

/**
 * 另datastore使用json文件存储偏好值
 * ```
 * @Serializable
 * data class XSettings(
 *     val skikoRenderApi: SkikoRenderApi = SkikoRenderApi.SOFTWARE,
 *     val singleInstance: Boolean = true,
 *     val closeAppDirectly : Boolean = true,
 * )
 * //使用koin提供单例（非必需）
 * single<DataStore<XSettings>>(named("xSettingsDS")) {
 *             getJsonDatastore(
 *                 defaults = XSettingsProvider.defaultValue(),
 *                 produceFile = {  FilePathProvider.publicConfigDir.resolve("xSettings.json").toFile() },
 *                 corruptionHandler= ReplaceFileCorruptionHandler {
 *                     SwingUtilities.invokeLater {
 *                         val jFrame = JFrame("提示")
 *                         jFrame.iconImage = null
 *                         jFrame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
 *                         buildPreferencesErrorDialog(jFrame)
 *                     }
 *                     XSettingsProvider.defaultValue()
 *                 }
 *             )
 *         }
 * ```
 */
inline fun <reified T> getJsonDatastore(
    defaults: T,
    json: Json = Json(Json.Default) {
        this.prettyPrint = true
    },
    corruptionHandler: ReplaceFileCorruptionHandler<T>? = null,
    migrations: List<DataMigration<T>> = listOf(),
    scope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
    noinline produceFile: () -> File,
): DataStore<T> {
    return DataStoreFactory.create(
        serializer = object : Serializer<T> {
            override val defaultValue: T
                get() = defaults

            override suspend fun readFrom(input: InputStream): T {
                return runCatching { json.decodeFromStream<T>(input) }.getOrDefault(defaults)
            }

            override suspend fun writeTo(t: T, output: OutputStream) {
                json.encodeToStream(t, output)
            }
        },
        corruptionHandler = corruptionHandler,
        migrations = migrations,
        scope = scope,
        produceFile = produceFile,
    )
}

interface JsonConfProvider<T> : IPreferenceProvider<T>

//typealias JsonConfProvider<T> = IPreferenceProvider<T>

class JsonConfProviderImpl<T> constructor(val ds: DataStore<T>, val defaultValue: T) : JsonConfProvider<T> {
    override val flow: Flow<T>
        get() = ds.data

    override val value: T
        get() = runBlocking { flow.first() }

    override suspend fun update(value: T) {
        ds.updateData {
            value
        }
    }

    override suspend fun clear() {
        update(defaultValue)
    }
}

//private fun example(){
//    val dataStore = getDataStore("/home/kiylxf/桌面/新建文件夹/ee.preferences_pb")
//    val holder = DataStorePreferenceHolder.instance(dataStore)
//}
