package androidy.preference.ui.theme.model

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.dp
import androidy.ui.material3.listitem.LocalListItemStyle
import androidy.ui.material3.listitem.interactive.StateShapes
import androidy.ui.material3.listitem.normal_style.ListItemContentPaddingValues
import androidy.ui.material3.listitem.normal_style.ListItemStyle
import androidy.ui.material3.listitem.normal_style.ListItemStyleType

/**
 * preference 组件主题
 */
@Immutable
class PreferenceItemTheme(
    val itemStyle: ListItemStyle,
    val titleStyle: PreferenceTitleStyle,
    val hintCardItemStyle: ListItemStyle,
    val cautionCardItemStyle: ListItemStyle,
    val largeTitleStyle: PreferenceTitleStyle,
    val labelTitleStyle: PreferenceTitleStyle,
) {

    /**
     * 在主题中提供一个基础样式，通过此接口修改基础样式，使之派生出基础样式变体
     * 例如itemStyle为item的样式，使用此接口修改，派生出hintCardItem样式
     */
    fun interface StyleModifiedProvider<T> {
        /**
         * @param basicStyle preference item 的基础样式
         * @return 修改后的样式
         */
        @Composable
        fun provide(basicStyle: T): T
    }

    fun copy(
        itemStyle: ListItemStyle? = null,
        titleStyle: PreferenceTitleStyle? = null,
        hintCardItemStyle: ListItemStyle? = null,
        cautionCardItemStyle: ListItemStyle? = null,
        largeTitleStyle: PreferenceTitleStyle? = null,
        labelTitleStyle: PreferenceTitleStyle? = null,
    ): PreferenceItemTheme {
        return PreferenceItemTheme(
            itemStyle = itemStyle ?: this.itemStyle,
            titleStyle = titleStyle ?: this.titleStyle,
            hintCardItemStyle = hintCardItemStyle ?: this.hintCardItemStyle,
            cautionCardItemStyle = cautionCardItemStyle ?: this.cautionCardItemStyle,
            largeTitleStyle = largeTitleStyle ?: this.largeTitleStyle,
            labelTitleStyle = labelTitleStyle ?: this.labelTitleStyle
        )
    }

    companion object Factory {
        /**
         * 创建preference item 主题
         */
        @Composable
        fun instance(
            itemStyle: ListItemStyle,
            titleStyle: PreferenceTitleStyle,
        ): PreferenceItemTheme = PreferenceItemTheme(
            itemStyle, titleStyle,
            DefaultHintCardItemStyleProvider.provide(itemStyle),
            DefaultCautionCardItemStyleProvider.provide(itemStyle),
            DefaultLargeTitleStyleProvider.provide(titleStyle),
            DefaultLabelTitleStyleProvider.provide(titleStyle)
        )

        /**
         * 创建preference item 主题
         */
        @Composable
        fun instance(
            itemStyleType: ListItemStyleType,
            titleStyle: PreferenceTitleStyle,
        ): PreferenceItemTheme {
            val itemStyle = when (itemStyleType) {
                ListItemStyleType.BASELINE -> LocalListItemStyle.current
                ListItemStyleType.EXPRESSIVE -> LocalListItemStyle.currentExpressive
                ListItemStyleType.SEGMENTED -> LocalListItemStyle.currentSegmented
            }
            return PreferenceItemTheme(
                itemStyle = itemStyle,
                titleStyle = titleStyle,
                hintCardItemStyle = DefaultHintCardItemStyleProvider.provide(itemStyle),
                cautionCardItemStyle = DefaultCautionCardItemStyleProvider.provide(itemStyle),
                largeTitleStyle = DefaultLargeTitleStyleProvider.provide(titleStyle),
                labelTitleStyle = DefaultLabelTitleStyleProvider.provide(titleStyle)
            )
        }
    }
}

internal object DefaultHintCardItemStyleProvider : PreferenceItemTheme.StyleModifiedProvider<ListItemStyle> {
    @Composable
    override fun provide(basicStyle: ListItemStyle): ListItemStyle {
        return basicStyle.copy(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentPadding = ListItemContentPaddingValues(PaddingValues(16.dp)),
            containerShape = MaterialTheme.shapes.extraLarge
        )
    }
}

internal object DefaultCautionCardItemStyleProvider : PreferenceItemTheme.StyleModifiedProvider<ListItemStyle> {
    @Composable
    override fun provide(basicStyle: ListItemStyle): ListItemStyle {
        return basicStyle.copy(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentPadding = ListItemContentPaddingValues(PaddingValues(16.dp)),
            containerShape = MaterialTheme.shapes.extraLarge
        )
    }
}

internal object DefaultLargeTitleStyleProvider : PreferenceItemTheme.StyleModifiedProvider<PreferenceTitleStyle> {
    @Composable
    override fun provide(basicStyle: PreferenceTitleStyle): PreferenceTitleStyle {
        return basicStyle.copy()
    }
}

internal object DefaultLabelTitleStyleProvider : PreferenceItemTheme.StyleModifiedProvider<PreferenceTitleStyle> {
    @Composable
    override fun provide(basicStyle: PreferenceTitleStyle): PreferenceTitleStyle {
        return basicStyle.copy()
    }
}
