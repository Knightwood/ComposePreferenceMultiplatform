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
import androidy.preference.ui.component.*
import androidy.preference.ui.theme.LocalPreferenceTheme
import androidy.ui.material3.listitem.LocalListItemStyle
import androidy.ui.material3.listitem.expressive_style.SegmentedListItemStyleDefaults
import androidy.ui.material3.listitem.normal_style.colors

@Composable
fun CrossComponentsTestScreen() {
    LocalListItemStyle.Provide {
        LocalPreferenceTheme.Provide(
//            PreferenceDefaults.styleFromProvider(
//                itemStyle = ExpressiveListItemDefaults.style(
//                    leadingIconStyle = ListItemIconStyle.leadingAvatarStyle()
//                ),
//            )
        ) {
            CrossComponentsTest(
                Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 8.dp)
            )
        }
    }
}

@Composable
fun CrossComponentsTest(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        PreferenceSubTitle(content = { Text("Item") })
        PreferenceItemTest()
        PreferenceSubTitle(content = { Text("滑动条") })
        var progress by remember {
            mutableStateOf(0f)
        }
        PreferenceItem(
            description = {
                Slider(value = progress, onValueChange = { progress = it })
            },
            title = { Text("伽马值") },
        )
        PreferenceSubTitle(content = { Text("杂项") })
        Others()
        PreferenceSubTitle(content = { Text("开关") })
        SwitchTest()
        PreferenceSubTitle(content = { Text("多选框") })
        CheckBoxTest()
        PreferenceSubTitle(content = { Text("单选框") })
        RadioTest()
    }

}

@Composable
private fun Others() {
    var expand by remember { mutableStateOf(false) }
    PreferenceCollapsedItem(
        title = { Text("折叠菜单") },
        description = { Text("折叠菜单") },
        expand = expand,
        onExpandChange = { expand = it },
    ) {
        Column(modifier = Modifier.padding(vertical = 16.dp)) {
            PreferenceItem(
                title = { Text("动画") },
                start = { Icon(Icons.Outlined.TouchApp, null) },
                description = { Text("动画反馈、触感反馈") },
            )
            PreferenceItem(
                title = { Text("语言") },
                description = { Text("中文(zh)") },
                start = { Icon(Icons.Outlined.Language, null) },
            )
        }
    }
    var showDialog by remember { mutableStateOf(false) }
    var alertText by remember { mutableStateOf("") }
    PreferenceAlertDialog(
        title = { Text("点击打开弹窗") },
        description = { Text("在弹窗中使用文本输入框") },
        start = { Icon(Icons.Outlined.Settings, null) },
        visible = showDialog,
        onVisibleChange = { showDialog = it },
        dialogText = {
            Column {
                Text("请输入内容，请输入内容，请输入内容，请输入内容，请输入内容，请输入内容，请输入内容")
                OutlinedTextField(
                    placeholder = { Text("请输入内容") },
                    label = { Text("请输入内容") },
                    value = alertText,
                    onValueChange = {
                        alertText = it
                    }
                )
            }
        },
        confirmButton = {
            TextButton(onClick = { showDialog = false }) {
                Text("确定")
            }
        }
    )
}

@Composable
private fun PreferenceItemTest() {
    PreferencesCautionCard(
        title = { Text("调整您的设置信息") },
        description = { Text("账户、翻译、帮助信息等") },
        start = { Icon(Icons.Outlined.AccountCircle, null) },
    )
    PreferenceItem(
        title = { Text("颜色和样式") },
        start = { Icon(Icons.Outlined.Palette, null) },
        description = { Text("主题、色调样式、字体") },
    )
    PreferenceItem(
        title = { Text("动画") },
        start = { Icon(Icons.Outlined.TouchApp, null) },
        description = { Text("动画反馈、触感反馈") },
    )
    PreferenceItem(
        title = { Text("语言") },
        description = { Text("中文(zh)") },
        start = { Icon(Icons.Outlined.Language, null) },
    )
    PreferenceItem(
        enabled = false,
        title = { Text("关于") },
        description = { Text("开源信息、版权") },
        start = { Icon(Icons.Outlined.TipsAndUpdates, null) },
    )
}

@Composable
private fun SwitchTest() {
    var checked by remember { mutableStateOf(false) }
    PreferenceWithDividerSwitch(
        start = { Icon(Icons.Outlined.CloudSync, null) },
        checked = checked,
        title = { Text("同步") },
        description = { Text("同步您的账户数据") },
        onCheckedChange = { checked = it }
    )

    var checked2 by remember { mutableStateOf(false) }
    PreferenceSwitchItem(
        start = { Icon(Icons.Outlined.LocalDining, null) },
        checked = checked2,
        title = { Text("餐馆") },
        description = { Text("查找附近的餐馆") },
        onCheckedChange = { checked2 = it }
    )

    PreferenceSwitchWithContainer(
        title = { Text("调整您的设置信息") },
        description = { Text("账户、翻译、帮助信息等") },
        checked = checked2,
        start = { Icon(Icons.Outlined.AccountCircle, null) },
        onCheckedChange = { checked2 = it }
    )

}

@Composable
private fun RadioTest() {

    var selected by remember {
        mutableStateOf(1)
    }
    PreferenceRadioButtonItem(
        title = { Text("激活背包") },
        selected = selected == 1,
        description = { Text("使用更大的背包") },
        onClick = { selected = 1 }
    )
    PreferenceRadioButtonItem(
        title = { Text("天空材质") },
        selected = selected == 2,
        description = { Text("使用更精美的天空材质贴图") },
        onClick = { selected = 2 }
    )
    PreferenceRadioButtonItem(
        title = { Text("非官方修复补丁") },
        selected = selected == 3,
        description = { Text("可能会带来新的bug") },
        onClick = { selected = 3 }
    )
}

@Composable
private fun CheckBoxTest() {
    val checkedMap = remember { mutableStateMapOf<Int, Boolean>() }
    val checkItems = remember {
        listOf(
            CheckItemInfo("激活背包", "使用更大的背包"),
            CheckItemInfo("天空材质", "使用更精美的天空材质贴图"),
            CheckItemInfo("非官方修复补丁", "可能会带来新的bug")
        )
    }
    checkItems.forEachIndexed { index, info ->
        PreferenceCheckBoxItem(
            title = { Text(info.title) },
            checked = checkedMap[index] ?: false,
            description = { Text(info.desc) },
            onCheckedChange = { checkedMap[index] = it },
            colors = LocalListItemStyle.currentSegmented.colors()
                .copy(containerColor = MaterialTheme.colorScheme.surfaceContainer),
            shapes = SegmentedListItemStyleDefaults.segmentedShape(index, 3),
        )
    }
}
