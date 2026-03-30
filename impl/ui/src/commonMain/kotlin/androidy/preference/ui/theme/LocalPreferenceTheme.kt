package androidy.preference.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidy.preference.ui.theme.model.PreferenceItemTheme
import androidy.ui.material3.listitem.BaselineListItem
import androidy.ui.material3.listitem.LocalListItemStyle
import androidy.ui.material3.listitem.expressive_style.ExpressiveListItemDefaults
import androidy.ui.material3.listitem.expressive_style.SegmentedListItemStyleDefaults
import androidy.ui.material3.listitem.normal_style.ListItemDefaults
import androidy.ui.material3.listitem.normal_style.ListItemStyle
import androidy.ui.material3.listitem.normal_style.ListItemStyleType

object LocalPreferenceTheme {
    private val LocalPreferenceTheme =
        compositionLocalOf<PreferenceItemTheme> { error("LocalPreferenceTheme not provided") }

    val current @Composable get() = LocalPreferenceTheme.current

    /**
     * 使用主题默认值设置
     */
    @Composable
    fun Provide(
        content: @Composable () -> Unit,
    ) {
        CompositionLocalProvider(
            LocalPreferenceTheme provides PreferenceDefaults.style()
        ) {
            content()
        }
    }


    /**
     * 此方法将根据styleType参数获取ListItem默认样式，并生成[PreferenceItemTheme]提供给[LocalPreferenceTheme]
     *
     * @param styleType preference item将使用哪种ListItem样式类型作为基底
     */
    @Composable
    fun Provide(
        styleType: ListItemStyleType = ListItemStyleType.SEGMENTED,
        content: @Composable () -> Unit,
    ) {
        val preferenceThemeBasedItemStyle = when (styleType) {
            ListItemStyleType.BASELINE -> ListItemDefaults.style()
            ListItemStyleType.EXPRESSIVE -> ExpressiveListItemDefaults.style()
            ListItemStyleType.SEGMENTED -> SegmentedListItemStyleDefaults.style()
        }
        val style = PreferenceDefaults.styleFromProvider(
            preferenceThemeBasedItemStyle,
        )
        CompositionLocalProvider(
            LocalPreferenceTheme provides style
        ) {
            content()
        }
    }

    @Composable
    fun Provide(
        style: PreferenceItemTheme,
        content: @Composable () -> Unit,
    ) {
        CompositionLocalProvider(
            LocalPreferenceTheme provides style
        ) {
            content()
        }
    }
}
