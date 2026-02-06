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

import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import androidy.preference.data.datastore.DataStorePreferenceHolder
import androidy.preference.helper.datastore.getDataStore
import androidy.preference.ui.list_item.normal_style.ListItemDefaults
import androidy.preference.ui.list_item.LocalListItemStyle

fun main() = application {
    val desktopPath = System.getProperty("user.home") + "/Desktop"
    val dataStore = getDataStore("$desktopPath/ee.preferences_pb")
    val holder = DataStorePreferenceHolder.instance(dataStore)
    Window(
        onCloseRequest = ::exitApplication,
        title = "ComposePreference-Multi",
        state = rememberWindowState(width = 600.dp, height = 800.dp)
    ) {
        MaterialTheme {
//            MainScreen(holder)
            LocalListItemStyle.provide(
                ListItemDefaults.style(
                    containerShape = MaterialTheme.shapes.large,
                )
            ) {
                Box() {
                    val scrollstate = rememberScrollState()
                    ListItemTest(
                        modifier = Modifier
                            .padding(8.dp)
                            .verticalScroll(scrollstate)
                    )
                    VerticalScrollbar(
                        adapter = rememberScrollbarAdapter(scrollstate),
                        modifier = Modifier.align(Alignment.CenterEnd),
                    )
                }
            }
        }
    }
}
