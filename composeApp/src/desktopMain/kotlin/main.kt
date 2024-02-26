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

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.kiylx.compose_lib.pref_component.FilledEditTextPreference
import com.kiylx.compose_lib.pref_component.HorizontalDivider
import com.kiylx.compose_lib.pref_component.MenuDivider
import com.kiylx.compose_lib.pref_component.MenuEntity
import com.kiylx.compose_lib.pref_component.OutlinedEditTextPreference
import com.kiylx.compose_lib.pref_component.PreferenceCheckBoxGroup
import com.kiylx.compose_lib.pref_component.PreferenceCollapseBox
import com.kiylx.compose_lib.pref_component.PreferenceItem
import com.kiylx.compose_lib.pref_component.PreferenceItemLargeTitle
import com.kiylx.compose_lib.pref_component.PreferenceItemSubTitle
import com.kiylx.compose_lib.pref_component.PreferenceItemVariant
import com.kiylx.compose_lib.pref_component.PreferenceListMenu
import com.kiylx.compose_lib.pref_component.PreferenceRadioGroup
import com.kiylx.compose_lib.pref_component.PreferenceSlider
import com.kiylx.compose_lib.pref_component.PreferenceSwitch
import com.kiylx.compose_lib.pref_component.PreferenceSwitchWithContainer
import com.kiylx.compose_lib.pref_component.PreferenceSwitchWithDivider
import com.kiylx.compose_lib.pref_component.PreferencesCautionCard
import com.kiylx.compose_lib.pref_component.PreferencesHintCard
import com.kiylx.compose_lib.pref_component.PreferencesScope
import com.kiylx.compose_lib.pref_component.composepreference_multi.DataStorePreferenceHolder
import com.kiylx.compose_lib.pref_component.datastore.getDataStore
import com.kiylx.libx.pref_component.core.DependenceNode

fun main() = application {
    val dataStore = getDataStore("/home/kiylxf/桌面/新建文件夹/ee.preferences_pb")
    val holder = DataStorePreferenceHolder.instance(dataStore)
    Window(onCloseRequest = ::exitApplication, title = "ComposePreference-Multi") {

        MaterialTheme {
            PreferencePage(holder)
        }
    }
}
