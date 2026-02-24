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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    val vm = MainViewModel()
    vm.initAPP()
    Window(
        onCloseRequest = ::exitApplication,
        title = "ComposePreference-Multi",
        state = rememberWindowState(width = 600.dp, height = 800.dp)
    ) {
        MaterialTheme {
            Scaffold {
                Column {
                    BasicButton("datastore读取") {
                        vm.read()
                    }
                    BasicButton("datastore写入") {
                        vm.write()
                    }
                }
            }
        }
    }
}

