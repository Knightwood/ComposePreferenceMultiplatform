//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.outlined.*
//import androidx.compose.material3.Icon
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidy.preference.data.core.DependenceNode
//import androidy.preference.data.core.AbstractPreferenceHolder
//import androidy.preference.ui.component.PreferenceCheckBoxItem
//import androidy.preference.ui.component.PreferenceItem
//import androidy.preference.ui.component.PreferenceRadioButtonItem
//import androidy.preference.ui.component.PreferenceSubTitle
//import androidy.preference.ui.theme.LocalPreferenceTheme
//import androidy.preference.uiauto.component.*
//import androidy.preference.uiauto.domain.LocalAutoPreference
//import androidy.ui.material3.listitem.LocalListItemStyle
//import androidy.ui.material3.listitem.expressive_style.SegmentedListItemStyleDefaults
//import androidy.ui.material3.listitem.normal_style.colors
//import kotlinx.coroutines.launch
//
//@Composable
//fun AutoComponentsTestScreen(holder: AbstractPreferenceHolder) {
//    LocalListItemStyle.Provide {
//        LocalPreferenceTheme.Provide {
//            LocalAutoPreference.Provide(holder) {
//                AutoComponentsTest(
//                    holder,
//                    Modifier
//                        .verticalScroll(rememberScrollState())
//                        .padding(horizontal = 8.dp)
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun AutoComponentsTest(
//    holder: AbstractPreferenceHolder,
//    modifier: Modifier = Modifier,
//) {
//    val customNodeName = "customNode"
//    //创建一个自定义节点
//    val node = holder.registerDependence(customNodeName, true)
//    val scope = rememberCoroutineScope()
//
//    Column(
//        modifier = modifier,
//        verticalArrangement = Arrangement.spacedBy(4.dp)
//    ) {
//        PreferenceSubTitle(content = { Text("Item") })
//        AutoPreferencesCautionCard(
//            title = { Text("调整您的设置信息") },
//            dependenceKey = customNodeName,
//            description = { Text("账户、翻译、帮助信息等") },
//            start = { Icon(Icons.Outlined.AccountCircle, null) },
//        )
//        PreferenceSubTitle(content = { Text("折叠") })
//        var expand by remember { mutableStateOf(false) }
//        AutoPreferenceCollapsedItem(
//            title = { Text("折叠菜单") },
//            description = { Text("折叠菜单") },
//            expand = expand,
//            dependenceKey = customNodeName,
//            onExpandChange = { expand = it },
//        ) {
//            Column(modifier = Modifier.padding(vertical = 16.dp)) {
//                PreferenceItem(
//                    title = { Text("动画") },
//                    start = { Icon(Icons.Outlined.TouchApp, null) },
//                    description = { Text("动画反馈、触感反馈") },
//                )
//                PreferenceItem(
//                    title = { Text("语言") },
//                    description = { Text("中文(zh)") },
//                    start = { Icon(Icons.Outlined.Language, null) },
//                )
//            }
//        }
//
//        PreferenceSubTitle(content = { Text("开关") })
//        var checked by remember { mutableStateOf(false) }
//        AutoPreferenceWithDividerSwitch(
//            start = { Icon(Icons.Outlined.CloudSync, null) },
//            checked = checked,
//            dependenceKey = DependenceNode.rootName,
//            keyName = "s1",
//            title = { Text("同步") },
//            description = { Text("同步您的账户数据") },
//            onCheckedChange = {
//                checked = it
//                //这里获取并修改了当前的enable状态，
//                //依赖这个节点的会改变显示状态，
//                //如果当前没有指定依赖，自身也会受到影响
//                scope.launch {
//                    holder.getDependence("s1")?.setEnabled(it)
//                }
//            }
//        )
//        var checked2 by remember { mutableStateOf(false) }
//        AutoPreferenceSwitchItem(
//            start = { Icon(Icons.Outlined.LocalDining, null) },
//            checked = checked2,
//            dependenceKey = "s1",
//            title = { Text("餐馆") },
//            description = { Text("查找附近的餐馆") },
//            onCheckedChange = { checked2 = it },
//            keyName = "s2"
//        )
//        AutoPreferenceSwitchWithContainer(
//            title = { Text("调整您的设置信息") },
//            description = { Text("账户、翻译、帮助信息等") },
//            keyName = "b2",
//            dependenceKey = DependenceNode.rootName,
//            checked = checked2,
//            start = { Icon(Icons.Outlined.AccountCircle, null) },
//            onCheckedChange = {
//                checked2 = it
//                scope.launch {
//                    node.setEnabled(it)
//                }
//            }
//        )
//
//
//        PreferenceSubTitle(content = { Text("多选框") })
//        CheckBoxTest()
//        PreferenceSubTitle(content = { Text("单选框") })
//        RadioTest()
//    }
//
//}
//
//
//
//@Composable
//private fun PreferenceItemTest() {
//
//    PreferenceItem(
//        title = { Text("颜色和样式") },
//        start = { Icon(Icons.Outlined.Palette, null) },
//        description = { Text("主题、色调样式、字体") },
//    )
//    PreferenceItem(
//        title = { Text("动画") },
//        start = { Icon(Icons.Outlined.TouchApp, null) },
//        description = { Text("动画反馈、触感反馈") },
//    )
//    PreferenceItem(
//        title = { Text("语言") },
//        description = { Text("中文(zh)") },
//        start = { Icon(Icons.Outlined.Language, null) },
//    )
//    PreferenceItem(
//        enabled = false,
//        title = { Text("关于") },
//        description = { Text("开源信息、版权") },
//        start = { Icon(Icons.Outlined.TipsAndUpdates, null) },
//    )
//}
//
//
//@Composable
//private fun RadioTest() {
//
//    var selected by remember {
//        mutableStateOf(1)
//    }
//    PreferenceRadioButtonItem(
//        title = { Text("激活背包") },
//        selected = selected == 1,
//        description = { Text("使用更大的背包") },
//        onClick = { selected = 1 }
//    )
//    PreferenceRadioButtonItem(
//        title = { Text("天空材质") },
//        selected = selected == 2,
//        description = { Text("使用更精美的天空材质贴图") },
//        onClick = { selected = 2 }
//    )
//    PreferenceRadioButtonItem(
//        title = { Text("非官方修复补丁") },
//        selected = selected == 3,
//        description = { Text("可能会带来新的bug") },
//        onClick = { selected = 3 }
//    )
//}
//

//
//@Composable
//private fun CheckBoxTest() {
//    val checkedMap = remember { mutableStateMapOf<Int, Boolean>() }
//    val checkItems = remember {
//        listOf(
//            CheckItemInfo("激活背包", "使用更大的背包"),
//            CheckItemInfo("天空材质", "使用更精美的天空材质贴图"),
//            CheckItemInfo("非官方修复补丁", "可能会带来新的bug")
//        )
//    }
//    checkItems.forEachIndexed { index, info ->
//        PreferenceCheckBoxItem(
//            title = { Text(info.title) },
//            checked = checkedMap[index] ?: false,
//            description = { Text(info.desc) },
//            onCheckedChange = { checkedMap[index] = it },
//            colors = LocalListItemStyle.currentSegmented.colors()
//                .copy(containerColor = MaterialTheme.colorScheme.surfaceContainer),
//            shapes = SegmentedListItemStyleDefaults.segmentedShape(index, 3),
//        )
//    }
//}
