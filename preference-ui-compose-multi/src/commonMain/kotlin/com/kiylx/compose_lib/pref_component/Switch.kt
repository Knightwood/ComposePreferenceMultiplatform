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

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.kiylx.compose_lib.pref_component.icons.Check
import kotlinx.coroutines.launch


/**
 * @param keyName 标识存储偏好值的key的名称，也是标识此组件启用状态的节点名称
 * @param defaultValue 默认值、当前值
 * @param title 标题
 * @param description 标题下方的描述信息
 * @param icon 左侧图标
 * @param enabled 是否启用
 * @param dependenceKey 若为null,则启用状态依照enable值;若不为null,则启用状态依赖dependenceKey指向的节点
 * @param checkedIcon switch置为true时的图标
 * @param changed "存储的偏好值"初始化或更新后，会通过此参数通知
 */
@Composable
fun PreferenceSwitch(
    keyName: String,
    defaultValue: Boolean = false,
    title: String,
    description: String? = null,
    icon: Any? = null,
    enabled: Boolean = true,
    dependenceKey: String? = null,
    checkedIcon: ImageVector = Icons.Default.Check,
    changed: (newValue: Boolean) -> Unit = {},
) {
    val scope = rememberCoroutineScope()
    val prefStoreHolder = LocalPrefs.current
    val pref = prefStoreHolder.getReadWriteTool(keyName = keyName, defaultValue = defaultValue)
    //注册自身节点，并且获取目标节点的状态
    val dependenceState = prefStoreHolder.getDependence(
        keyName,
        enabled,
        dependenceKey
    ).enableStateFlow.collectAsState()

    var isChecked by remember {
        mutableStateOf(defaultValue)
    }
    LaunchedEffect(key1 = Unit, block = {
        pref.read().collect {
            isChecked = it
            changed(it)
        }
    })

    fun write(checked: Boolean) {
        scope.launch {
            pref.write(checked)
        }
    }

    val thumbContent: (@Composable () -> Unit)? = if (isChecked) {
        { ->
            Icon(
                imageVector = checkedIcon,
                contentDescription = null,
                modifier = Modifier.size(SwitchDefaults.IconSize),
            )
        }
    } else {
        null
    }
    Surface(
        modifier = Modifier.toggleable(
            value = isChecked,
            enabled = dependenceState.value,
            onValueChange = { checked ->
                write(checked)
            }
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    Dimens.all.horizontal_start.dp,
                    Dimens.all.vertical_top.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            WrappedIcon(
                icon = icon,
                enabled = dependenceState.value
            )
            MediumTextContainer(icon = icon) {
                PreferenceItemTitleText(text = title, enabled = dependenceState.value, maxLines = 2)
                description?.let {
                    PreferenceItemDescriptionText(
                        text = it,
                        enabled = dependenceState.value
                    )
                }
            }
            Switch(
                checked = isChecked,
                onCheckedChange = null,
                modifier = Modifier.padding(start = Dimens.end.start.dp, end = Dimens.end.end.dp),
                enabled = dependenceState.value,
                thumbContent = thumbContent
            )
        }
    }
}

/**
 * @param keyName 标识存储偏好值的key的名称，也是标识此组件启用状态的节点名称
 * @param defaultValue 默认值、当前值
 * @param title 标题
 * @param description 标题下方的描述信息
 * @param icon 左侧图标
 * @param enabled 是否启用
 * @param dependenceKey 若为null,则启用状态依照enable值;若不为null,则启用状态依赖dependenceKey指向的节点
 * @param checkedIcon switch置为true时的图标
 * @param changed "存储的偏好值"初始化或更新后，会通过此参数通知
 * @param onClick switch左侧区域点击事件
 */
@Composable
fun PreferenceSwitchWithDivider(
    keyName: String,
    defaultValue: Boolean = false,
    title: String,
    description: String? = null,
    icon: Any? = null,
    enabled: Boolean = true,
    dependenceKey: String? = null,
    isSwitchEnabled: Boolean = enabled,
    checkedIcon: ImageVector = Icons.Default.Check,
    changed: (it: Boolean) -> Unit = {},
    onClick: (() -> Unit) = {},
) {
    val scope = rememberCoroutineScope()
    val prefStoreHolder = LocalPrefs.current
    val pref = prefStoreHolder.getReadWriteTool(keyName = keyName, defaultValue = defaultValue)
    //注册自身节点，并且获取目标节点的状态
    val dependenceState = prefStoreHolder.getDependence(
        keyName,
        enabled,
        dependenceKey
    ).enableStateFlow.collectAsState()

    var isChecked by remember {
        mutableStateOf(defaultValue)
    }
    LaunchedEffect(key1 = Unit, block = {
        pref.read().collect {
            isChecked = it
            changed(it)
        }
    })

    fun write(checked: Boolean) {
        scope.launch {
            pref.write(checked)
        }
    }

    val thumbContent: (@Composable () -> Unit)? = if (isChecked) {
        {
            Icon(
                imageVector = checkedIcon,
                contentDescription = null,
                modifier = Modifier.size(SwitchDefaults.IconSize),
            )
        }
    } else {
        null
    }
    Surface(
        modifier = Modifier.clickable(
            enabled = dependenceState.value,
            onClick = onClick,
            onClickLabel = "open settings"
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.all.horizontal_start.dp, Dimens.all.vertical_top.dp)
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            WrappedIcon(icon = icon, enabled = dependenceState.value)
            MediumTextContainer(icon = icon) {
                PreferenceItemTitleText(text = title, enabled = dependenceState.value)
                if (!description.isNullOrEmpty()) PreferenceItemDescriptionText(
                    text = description,
                    enabled = dependenceState.value
                )
            }
            VerticalDivider(
                modifier = Modifier
                    .height(Dimens.large_xx.dp)
                    .padding(horizontal = Dimens.small.dp),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                thickness = 1f.dp
            )
            Switch(
                checked = isChecked,
                onCheckedChange = { checked ->
                    write(checked)
                },
                modifier = Modifier
                    .padding(start = Dimens.end.start.dp, end = Dimens.end.end.dp)
                    .semantics {
                        contentDescription = title
                    },
                enabled = (isSwitchEnabled && dependenceState.value),
                thumbContent = thumbContent
            )
        }
    }
}

/**
 * @param keyName 标识存储偏好值的key的名称，也是标识此组件启用状态的节点名称
 * @param defaultValue 默认值、当前值
 * @param title 标题
 * @param description 标题下方的描述信息
 * @param icon 左侧图标
 * @param enabled 是否启用
 * @param dependenceKey 若为null,则启用状态依照enable值;若不为null,则启用状态依赖dependenceKey指向的节点
 * @param changed "存储的偏好值"初始化或更新后，会通过此参数通知
 */
@Composable
fun PreferenceSwitchWithContainer(
    keyName: String,
    defaultValue: Boolean = false,
    title: String,
    description: String? = null,
    icon: Any? = null,
    enabled: Boolean = true,
    dependenceKey: String? = null,
    changed: (it: Boolean) -> Unit = {},
) {
    val scope = rememberCoroutineScope()
    val prefStoreHolder = LocalPrefs.current
    val pref = prefStoreHolder.getReadWriteTool(keyName = keyName, defaultValue = defaultValue)
    //注册自身节点，并且获取目标节点的状态
    val dependenceState = prefStoreHolder.getDependence(
        keyName,
        enabled,
        dependenceKey
    ).enableStateFlow.collectAsState()

    var isChecked by remember {
        mutableStateOf(defaultValue)
    }
    LaunchedEffect(key1 = Unit, block = {
        pref.read().collect {
            isChecked = it
            changed(it)
        }
    })
    fun write(checked: Boolean) {
        scope.launch {
            pref.write(checked)
        }
    }

    val thumbContent: (@Composable () -> Unit)? = if (isChecked) {
        {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                modifier = Modifier.size(SwitchDefaults.IconSize),
            )
        }
    } else {
        null
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = Dimens.all.horizontal_start.dp,
                vertical = Dimens.all.vertical_top.dp
            )
            .clip(MaterialTheme.shapes.extraLarge)
            .background(
                (if (isChecked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline).let {
                    it.applyOpacity(dependenceState.value)
                }
            )
            .toggleable(
                value = isChecked,
                enabled = dependenceState.value,
            ) { checked ->
                write(checked)
            }
            .padding(horizontal = Dimens.medium.dp, vertical = Dimens.large.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        WrappedIcon(
            icon = icon,
            enabled = dependenceState.value,
            tint = if (isChecked) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.surface
        )
        MediumTextContainer(icon = icon) {
            PreferenceItemTitleText(
                text = title,
                maxLines = 2,
                enabled = dependenceState.value,
                style = Typography.preferenceMediumTitle,
                color = if (isChecked) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.surface
            )
            if (!description.isNullOrEmpty()) PreferenceItemDescriptionText(
                text = description,
                enabled = dependenceState.value,
                color = if (isChecked) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.surface
            )
        }
        Switch(
            checked = isChecked,
            onCheckedChange = null,
            enabled = dependenceState.value,
            modifier = Modifier.padding(start = 12.dp, end = 6.dp),
            thumbContent = thumbContent,
            colors = SwitchDefaults.colors(
                checkedIconColor = MaterialTheme.colorScheme.onPrimary,
                checkedThumbColor = MaterialTheme.colorScheme.primary,
                checkedTrackColor = MaterialTheme.colorScheme.onPrimary,
                uncheckedBorderColor = Color.Transparent
            )
        )
    }
}
