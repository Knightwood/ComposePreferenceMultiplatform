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

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidy.compose.datastore.DataStorePreferenceHolder
import androidy.compose.datastore.asDataFlow
import androidy.compose.datastore.getDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlin.random.Random

fun main() = application {
    val desktopPath = System.getProperty("user.home") + "/Desktop"
    val dataStore = getDataStore("$desktopPath/ee.preferences_pb")
    val holder = DataStorePreferenceHolder.instance(dataStore)
//    val scope = CoroutineScope(Dispatchers.IO)
//    var username by dataStore.getting(11, scope)

    Window(onCloseRequest = ::exitApplication, title = "ComposePreference-Multi") {
        MaterialTheme {
            /*
            val va = dataStore.asDataFlow<Int>("username").collectAsState(initial = 11)
            Column {
                Button(onClick = {
                    val randoms = Random.nextInt(0, 11)
                    username = randoms
                    println(username)
                }) {
                    Text("Random")
                }
                Text("value:${va.value}")
            }*/
//            NewComponents()
            NewComponents2(holder)
        }
    }
}
