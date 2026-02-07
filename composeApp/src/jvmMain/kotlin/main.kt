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

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidy.preference.ui.list_item.normal_style.ListItemIconStyle

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "ComposePreference-Multi",
        state = rememberWindowState(width = 396.dp, height = 900.dp)
    ) {
        MaterialTheme {
/*            Column {
                var alignment by remember { mutableStateOf(Alignment.Top) }
                var isCheck by remember { mutableStateOf(false) }
                Row(modifier = Modifier.requiredHeight(160.dp)) {
                    Button(onClick = {}, modifier = Modifier.align( alignment)) {
                        Text("Save")
                    }
                }
                Switch(isCheck, onCheckedChange = {
                    isCheck = !isCheck
                    alignment = if (it) Alignment.CenterVertically else Alignment.Top
                })
            }*/
            preferenceTest()
        }
    }
}

@Composable
fun preferenceTest() {
    val desktopPath = System.getProperty("user.home") + "/Desktop"
    val dataStore = getDataStore("$desktopPath/ee.preferences_pb")
    val holder = DataStorePreferenceHolder.instance(dataStore)
    LocalListItemStyle.provide(
        ListItemDefaults.style(
            containerShape = MaterialTheme.shapes.large,
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
            leadingIconStyle = ListItemIconStyle.leadingAvatarStyle()
        )
    ) {
        MainScreen(holder)
    }
}
