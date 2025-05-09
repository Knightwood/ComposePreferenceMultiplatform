import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.CloudSync
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.LocalDining
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.TipsAndUpdates
import androidx.compose.material.icons.outlined.TouchApp
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidy.compose.preference.component.cross.PreferenceCheckBox
import androidy.compose.preference.component.cross.PreferenceCollapseItem
import androidy.compose.preference.component.cross.PreferenceItem
import androidy.compose.preference.component.cross.PreferenceRadio
import androidy.compose.preference.component.cross.PreferenceSlider
import androidy.compose.preference.component.cross.PreferenceSubTitle
import androidy.compose.preference.component.cross.PreferenceSwitch
import androidy.compose.preference.component.cross.PreferenceSwitchWithContainer
import androidy.compose.preference.component.cross.PreferenceWithDividerSwitch
import androidy.compose.preference.component.cross.PreferencesCautionCard
import androidy.compose.preference.theme.PreferenceIconStyle
import androidy.compose.preference.theme.Preferences

@Composable
fun NewComponents() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Preferences.SetTheme(
//            boxStyle = defaultPreferenceBoxStyle.copy(
//                color = MaterialTheme.colorScheme.secondaryContainer
//            ),
            iconStyle = PreferenceIconStyle(
                paddingValues = PaddingValues(8.dp),
                tint = MaterialTheme.colorScheme.onPrimary,
                backgroundColor = MaterialTheme.colorScheme.primary,
            )
        ) {
            PreferenceItemTest()
            PreferenceSubTitle(
                modifier = Modifier.padding(top = 8.dp),
                title = "其他"
            )
            var progress by remember {
                mutableStateOf(0f)
            }
            PreferenceSlider(
                value = progress,
                desc = "滑动条描述",
                onValueChanged = { progress = it })
            SwitchTest()
            PreferenceSubTitle(title = "多选框", modifier = Modifier)
            CheckBoxTest()
            PreferenceSubTitle(title = "单选框", modifier = Modifier)
            RadioTest()
            PreferenceSubTitle(title = "折叠", modifier = Modifier)
            var expand by remember { mutableStateOf(false) }
            PreferenceCollapseItem(
                expand = expand,
                title = "附加内容",
                stateChanged = { expand = !expand })
            {
                Preferences.SetTheme {
                    Column(modifier = Modifier.padding(top = 12.dp, start = 12.dp, end = 12.dp)) {
                        PreferenceItemTest()
                    }
                }
            }
        }
    }
}

@Composable
private fun PreferenceItemTest() {
    PreferencesCautionCard(
        title = "调整您的设置信息",
        desc = "账户、翻译、帮助信息等",
        icon = Icons.Outlined.AccountCircle,
    )
    PreferenceItem(
        modifier = Modifier,
        title = "账户",
//        icon = Icons.Outlined.AccountCircle,
//        desc = "本地、谷歌",
    )
    PreferenceItem(
        title = "颜色和样式",
        icon = Icons.Outlined.Palette,
        desc = "主题、色调样式、字体",
    )
    PreferenceItem(
        title = "动画",
        icon = Icons.Outlined.TouchApp,
        desc = "动画反馈、触感反馈",
    )
    PreferenceItem(
        title = "语言",
        desc = "中文(zh)",
        icon = Icons.Outlined.Language,
    )
    PreferenceItem(
        enabled = false,
        title = "关于",
        desc = "开源信息、版权",
        icon = Icons.Outlined.TipsAndUpdates,
    )
}

@Composable
private fun SwitchTest() {
    var checked by remember { mutableStateOf(false) }
    PreferenceWithDividerSwitch(
        icon = Icons.Outlined.CloudSync,
        isChecked = checked,
        title = "同步",
        desc = "同步您的账户数据"
    ) {
        checked = it
    }

    var checked2 by remember { mutableStateOf(false) }
    PreferenceSwitch(
        icon = Icons.Outlined.LocalDining,
        isChecked = checked2,
        title = "餐馆",
        desc = "查找附近的餐馆"
    ) {
        checked2 = it
    }

    PreferenceSwitchWithContainer(
        title = "调整您的设置信息",
        desc = "账户、翻译、帮助信息等",
        isChecked = checked2,
        icon = Icons.Outlined.AccountCircle,
    ) {
        checked2 = it
    }

}

@Composable
private fun RadioTest() {

    var selected by remember {
        mutableStateOf(1)
    }
    PreferenceRadio(
        title = "激活背包",
        selected = selected == 1,
        desc = "使用更大的背包",
    ) {
        if (it) selected = 1
    }
    PreferenceRadio(
        title = "天空材质",
        selected = selected == 2,
        desc = "使用更精美的天空材质贴图",
    ) {
        if (it) selected = 2

    }

    PreferenceRadio(
        title = "非官方修复补丁",
        selected = selected == 3,
        desc = "可能会带来新的bug",
    ) {
        if (it) selected = 3

    }
}

@Composable
private fun CheckBoxTest() {
    var check1 by remember {
        mutableStateOf(false)
    }
    PreferenceCheckBox(
        title = "激活背包",
        checked = check1,
        desc = "使用更大的背包",
    ) {
        check1 = it
    }

    var check2 by remember {
        mutableStateOf(false)
    }
    PreferenceCheckBox(
        title = "天空材质",
        checked = check2,
        desc = "使用更精美的天空材质贴图",
    ) {
        check2 = it
    }

    var check3 by remember {
        mutableStateOf(false)
    }
    PreferenceCheckBox(
        title = "非官方修复补丁",
        checked = check3,
        desc = "可能会带来新的bug",
    ) {
        check3 = it
    }
}
