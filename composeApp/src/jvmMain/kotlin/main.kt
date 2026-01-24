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

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidy.preference.data.datastore.DataStorePreferenceHolder
import androidy.preference.helper.datastore.getDataStore
import floor.UserIdBean
import floor.UserIdBean_Dao
import knightwood.kv.aop.floorcore.core.KVFloor
import knightwood.kv.aop.floordatastore.DatastoreFloor
import java.util.UUID

fun main() = application {
    val desktopPath = System.getProperty("user.home") + "/Desktop"
    val dataStore = getDataStore("$desktopPath/ee.preferences_pb")
    val holder = DataStorePreferenceHolder.instance(dataStore)

    DatastoreFloor
        .dbProvider(
            object : KVFloor.KVDBProvider<DataStore<Preferences>> {
                override fun get(name: String): DataStore<Preferences> {
                    return getDataStore(name)
                }

                override fun getDefault(): DataStore<Preferences> {
                    return dataStore
                }
            }
        )
        .preLoad()

    Window(
        onCloseRequest = ::exitApplication,
        title = "ComposePreference-Multi",
        state = rememberWindowState(width = 600.dp, height = 800.dp)
    ) {
        MaterialTheme {
            MainScreen(holder)
        }
    }
}

suspend fun UserIdBeanTest() {
    val dao = UserIdBean_Dao()
    val userId = dao.value
    dao.update(UserIdBean(UUID.randomUUID()))
}
