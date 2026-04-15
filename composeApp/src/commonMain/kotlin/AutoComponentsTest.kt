import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidy.preference.data.core.AbstractValueEditorHolder
import androidy.preference.data.core.IAutoScope
import androidy.preference.data.core.LocalAutoPreference
import androidy.preference.data.core.state
import androidy.preference.ui.component.PreferenceAlertDialog
import androidy.preference.ui.component.PreferenceCheckBoxItem
import androidy.preference.ui.component.PreferenceCollapsedItem
import androidy.preference.ui.component.PreferenceItem
import androidy.preference.ui.component.PreferenceRadioButtonItem
import androidy.preference.ui.component.PreferenceSubTitle
import androidy.preference.ui.component.PreferenceSwitchItem
import androidy.preference.ui.component.PreferenceSwitchWithContainer
import androidy.preference.ui.component.PreferenceWithDividerSwitch
import androidy.preference.ui.component.PreferencesCautionCard
import androidy.preference.ui.theme.LocalPreferenceTheme
import androidy.ui.material3.listitem.LocalListItemStyle
import androidy.ui.material3.listitem.expressive_style.SegmentedListItemStyleDefaults
import androidy.ui.material3.listitem.normal_style.colors
import kotlinx.coroutines.launch

@Composable
fun AutoComponentsTestScreen(holder: AbstractValueEditorHolder) {
    LocalListItemStyle.Provide {
        LocalPreferenceTheme.Provide {
            LocalAutoPreference.Provide(holder) {
                AutoComponentsTest(
                    Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 8.dp)
                )
            }
        }
    }
}

@Composable
fun AutoComponentsTest(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        PreferenceSubTitle(content = { Text("开关") })
        SwitchTest()
        PreferenceSubTitle(content = { Text("多选框") })
        CheckBoxTest()
        PreferenceSubTitle(content = { Text("单选框") })
        RadioTest()
    }
}

@Composable
private fun SwitchTest(
) {
    IAutoScope<Boolean>("switch1") { editor, tree ->
        val checked = editor.state(true)
        PreferenceSwitchItem(
            start = { Icon(Icons.Outlined.LocalDining, null) },
            checked = checked,
            title = { Text("开关1") },
            description = { Text("关闭此开关将禁用开关2") },
            onCheckedChange = { editor.write(it) }
        )
    }
    IAutoScope<Boolean>("switch2") { editor, tree ->
        val checked = editor.state(true)
        val enabled = tree.get("switch2") {
            dependenceValueFrom<Boolean>("switch1") { it ?: true }
        }.state()
        PreferenceWithDividerSwitch(
            start = { Icon(Icons.Outlined.CloudSync, null) },
            checked = checked,
            enabled = enabled,
            title = { Text("开关2") },
            description = { Text("enable状态依赖开关1的值") },
            onCheckedChange = {
                editor.write(it)
            }
        )
    }
    IAutoScope<Boolean>("switch3") { editor, tree ->
        val checked = editor.state(true)
        val enabled = tree.get("switch3") {
            dependenceStateFrom("switch2") { it ?: false }
        }.state()
        PreferenceSwitchWithContainer(
            title = { Text("开关3") },
            description = { Text("enable状态依赖开关2的enable状态") },
            checked = checked,
            enabled = enabled,
            start = { Icon(Icons.Outlined.AccountCircle, null) },
            onCheckedChange = { editor.write(it) }
        )
    }

}

@Composable
private fun RadioTest(enabled: Boolean = true) {
    IAutoScope<String>("radio") { editor, tree ->
        val selected = editor.state("1")
        val radioMenu = remember {
            listOf(
                CheckItemInfo("1", "激活背包", "使用更大的背包"),
                CheckItemInfo("2", "天空材质", "使用更精美的天空材质贴图"),
                CheckItemInfo("3", "非官方修复补丁", "可能会带来新的bug")
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            radioMenu.forEachIndexed { index, info ->
                PreferenceRadioButtonItem(
                    title = { Text(info.title) },
                    selected = selected == info.key,
                    enabled = enabled,
                    description = { Text("使用更大的背包") },
                    onClick = { editor.write(info.key) }
                )
            }
        }
    }
}

@Composable
private fun CheckBoxTest(enabled: Boolean = true) {
    IAutoScope<String>("checkbox") { editor, tree ->
        val selected = editor.state("").split(", ")
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            val checkItems = remember {
                listOf(
                    CheckItemInfo("1", "激活背包", "使用更大的背包"),
                    CheckItemInfo("2", "天空材质", "使用更精美的天空材质贴图"),
                    CheckItemInfo("3", "非官方修复补丁", "可能会带来新的bug")
                )
            }
            checkItems.forEachIndexed { index, info ->
                PreferenceCheckBoxItem(
                    title = { Text(info.title) },
                    checked = selected.contains(info.key),
                    enabled = enabled,
                    description = { Text(info.desc) },
                    onCheckedChange = {
                        if (it) {
                            editor.write(selected.toMutableSet().apply { add(info.key) }.joinToString())
                        } else {
                            editor.write(selected.toMutableSet().apply { remove(info.key) }.joinToString())
                        }
                    },
                    colors = LocalListItemStyle.currentSegmented.colors()
                        .copy(containerColor = MaterialTheme.colorScheme.surfaceContainer),
                    shapes = SegmentedListItemStyleDefaults.segmentedShape(index, 3),
                )
            }
        }
    }
}
