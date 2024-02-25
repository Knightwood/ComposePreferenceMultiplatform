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
import com.kiylx.libx.pref_component.core.DependenceNode
import com.kiylx.libx.pref_component.core.PreferenceHolder

@Composable
fun PreferencePage(holder: PreferenceHolder){
    PreferencesScope(holder = holder) {
        val customNodeName = "customNode"
        //创建一个自定义节点
        val node = holder.registerDependence(customNodeName, true)
        PreferencesCautionCard(title = "PreferencesCautionCard")
        PreferencesHintCard(title = "PreferencesHintCard")

        PreferenceItemLargeTitle(title = "PreferenceItem大标题")
        PreferenceItem(
            title = "普通的PreferenceItem",
            description = "可以带图标",
            icon = Icons.Filled.Favorite,
            endIcon = Icons.Filled.Share
        )
        PreferenceItem(
            title = "绝大多数可带图标",
            icon = Icons.Filled.Edit,
            endIcon = Icons.Filled.Share
        )

        PreferenceItemVariant(
            title = "PreferenceItem变种",
            icon = Icons.Filled.AccountCircle,
            endIcon = Icons.Filled.Share,
            description = "但是用起来无差别,但是用起来无差别,但是用起来无差别,但是用起来无差别,但是用起来无差别,但是用起来无差别"
        )

        HorizontalDivider()
        PreferenceItemSubTitle(title = "编辑框Preference")
        OutlinedEditTextPreference(
            title = "outlined编辑框", keyName = "edit11",
            defaultValue = "默认文本",
            icon = Icons.Filled.AccountCircle,
        )

        FilledEditTextPreference(
            defaultValue = "默认文本",
            title = "Filled编辑框",
            keyName = "edit12",
            icon = Icons.Filled.AccountCircle,
            changed = {

            }
        )
        HorizontalDivider()


        PreferenceItemSubTitle(title = "PreferenceItemSubTitle")
        PreferenceSwitch(
            keyName = "bol2",
            title = "启用下面全部",
            dependenceKey = DependenceNode.rootName,//指定依赖为根结点，这样自身就不会受到影响
            description = "关闭开关以禁用下面内容",
        ) {
            node.enableStateFlow.tryEmit(it)
        }
        PreferenceCollapseBox(
            title = "可折叠菜单",
            description = "preference描述",
            dependenceKey = customNodeName,
        ) {

            PreferenceSwitch(
                keyName = "bol",
                title = "title",
                dependenceKey = DependenceNode.rootName,//指定依赖为根结点，这样自身就不会受到影响
                description = "description",
                icon = Icons.Filled.AccountCircle
            ) { state ->
                //这里获取并修改了当前的enable状态，
                //依赖这个节点的会改变显示状态，
                //如果当前没有指定依赖，自身也会受到影响
                holder.getDependence("bol")?.let {
                    it.enableStateFlow.tryEmit(state)
                }
            }
            //依赖keyName为bol的PreferenceSwitch的state
            PreferenceSwitch(
                keyName = "bol3",
                title = "title",
                dependenceKey = "bol",
                description = "description",
                icon = Icons.Filled.Face
            )

        }

        PreferenceListMenu(
            title = "list菜单",
            keyName = "PreferenceListMenu",
            description = "list菜单介绍",
            dependenceKey = customNodeName,
            list = listOf(
                MenuEntity(
                    leadingIcon = Icons.Outlined.Edit,
                    text = "edit",
                    labelKey = 0
                ),
                MenuEntity(
                    leadingIcon = Icons.Outlined.Settings,
                    text = "Settings",
                    labelKey = 1
                ),
                MenuDivider,
                MenuEntity(
                    leadingIcon = Icons.Outlined.Email,
                    text = "Send Feedback",
                    labelKey = 2
                ),
            )
        ) {
            println( "menu item labelKey: $it")
        }
        HorizontalDivider()

        PreferenceItemSubTitle(
            title = "各种switch",
            dependenceKey = customNodeName,
        )
        PreferenceSwitchWithContainer(
            keyName = "bol4",
            title = "带Container的Switch",
            description = "描述，描述，描述，描述，描述",
            dependenceKey = customNodeName,
            icon = null
        )

        PreferenceSwitch(
            keyName = "www",
            title = "夜间模式",
            dependenceKey = customNodeName,//指定依赖为根结点，这样自身就不会受到影响
            description = "开关描述",
        ) {
            println( "FirstPage: $it")
//                    isDarkFlow.tryEmit(it)
        }

        PreferenceSwitchWithDivider(
            keyName = "bol5",
            title = "前部可点击switch",
            dependenceKey = customNodeName,
            description = "description",
            icon = Icons.Filled.Face
        )
        HorizontalDivider()

        PreferenceItemSubTitle(
            title = "RadioGroup",
            dependenceKey = customNodeName,
        )
        PreferenceRadioGroup(
            keyName = "radioGroup",
            dependenceKey = customNodeName,
            labelPairs = listOf(
                "first" to 3,
                "second" to 1
            ), changed = {
                println( "radio: ${it}")
            }
        )
        HorizontalDivider()

        PreferenceItemSubTitle(
            title = "CheckBoxGroup",
            dependenceKey = customNodeName,
        )
        PreferenceCheckBoxGroup(
            keyName = "CheckBoxGroup",
            dependenceKey = customNodeName,
            labels = listOf(
                "first",
                "second"
            ), changed = {
                println("checkbox: ${it.joinToString(",")}")
            }
        )
        HorizontalDivider()

        PreferenceItemSubTitle(
            title = "PreferenceSlider",
            dependenceKey = customNodeName,
        )
        PreferenceSlider(
            keyName = "slider",
            dependenceKey = customNodeName, //依赖key为customNode的状态
            min = 0f,
            max = 10f, steps = 9, defaultValue = 0f, changed = {
                println("slider: $it")
            }
        )
    }
}