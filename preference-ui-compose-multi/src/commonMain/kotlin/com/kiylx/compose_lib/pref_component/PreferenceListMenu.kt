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

package com.kiylx.compose_lib.pref_component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kiylx.compose_lib.pref_component.icons.Check
import com.kiylx.compose_lib.pref_component.icons.MoreVert
import kotlinx.coroutines.launch

@Composable
fun PreferenceListMenu(
    title: String,
    keyName: String,
    description: String? = null,
    list: List<MenuItem>,
    icon: Any? = null,
    enabled: Boolean = true,
    dependenceKey: String? = null,
    changed: (newLabelKey: Int) -> Unit = {},
) {
    val scope = rememberCoroutineScope()
    val prefStoreHolder = LocalPrefs.current
    val pref = prefStoreHolder.getReadWriteTool(keyName = keyName, defaultValue = 0)
    //注册自身节点，并且获取目标节点的状态
    val dependenceState = prefStoreHolder.getDependence(
        keyName,
        enabled,
        dependenceKey
    ).enableStateFlow.collectAsState()
    var expanded by remember { mutableStateOf(false) }

    //当前的选择
    var selectLabelKey by remember {
        mutableStateOf(0)
    }

    LaunchedEffect(key1 = Unit, block = {
        pref.read().collect {
            selectLabelKey = it
            changed(it)
        }
    })
    fun write(labelKey: Int) {
        scope.launch {
            pref.write(labelKey)
        }
    }

    Surface(
        modifier = Modifier.toggleable(
            value = expanded,
            enabled = dependenceState.value,
        ) { checked ->
            expanded = checked
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.all.horizontal_start.dp, Dimens.all.vertical_top.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            WrappedIcon(icon = icon, enabled = dependenceState.value)
            MediumTextContainer(icon = icon) {
                PreferenceItemTitleText(text = title, enabled = dependenceState.value, maxLines = 2)
                if (description != null) PreferenceItemDescriptionText(
                    text = description,
                    enabled = dependenceState.value
                )
            }

            Surface {
//                IconButton(onClick = { expanded = true }, enabled = dependenceState.value) {
//                    JustIcon(
//                        icon = Icons.Default.MoreVert,
//                        enabled = dependenceState.value,
//                    )
//                }
                WrappedIcon(
                    icon = Icons.Default.MoreVert,
                    enabled = dependenceState.value,
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    repeat(list.size) {
                        val entity = list[it]
                        when (entity) {
                            is MenuDivider -> {
                                HorizontalDivider()
                            }

                            is MenuEntity -> {
                                DropdownMenuItem(
                                    enabled = dependenceState.value,
                                    text = { Text(entity.text) },
                                    onClick = {
                                        selectLabelKey = entity.labelKey
                                        write(selectLabelKey)
                                        expanded = false
                                    },
                                    leadingIcon = {
                                        JustIcon(
                                            icon = entity.leadingIcon,
                                            enabled = dependenceState.value
                                        )
                                    },
                                    trailingIcon = {
                                        if (selectLabelKey == entity.labelKey) {
                                            JustIcon(
                                                icon = Icons.Default.Check,
                                                enabled = dependenceState.value
                                            )
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

sealed class MenuItem

/**
 * 菜单项
 */
data class MenuEntity(
    var leadingIcon: Any? = null,
    val text: String = "",
    val labelKey: Int = -1,
) : MenuItem()

/**
 * menu的分割线 entity
 */
data object MenuDivider : MenuItem()
