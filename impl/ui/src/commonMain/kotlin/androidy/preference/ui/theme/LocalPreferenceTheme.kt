package androidy.preference.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidy.preference.ui.theme.model.PreferenceItemTheme
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

    @Composable
    fun Provide(
        baselineStyle: ListItemStyle = ListItemDefaults.style(),
        expressiveStyle: ListItemStyle = ExpressiveListItemDefaults.style(),
        segmentedExpressiveStyle: ListItemStyle = SegmentedListItemStyleDefaults.style(),
        styleType: ListItemStyleType = ListItemStyleType.SEGMENTED,
        theme: PreferenceItemTheme? = null,
        content: @Composable () -> Unit,
    ) {
        val finalTheme =
            if (theme == null) {
                val finalItemStyle = when (styleType) {
                    ListItemStyleType.BASELINE -> baselineStyle
                    ListItemStyleType.EXPRESSIVE -> expressiveStyle
                    ListItemStyleType.SEGMENTED -> segmentedExpressiveStyle
                }
                PreferenceDefaults.itemStyle = finalItemStyle
                PreferenceDefaults.style()
            } else {
                theme
            }
        LocalListItemStyle.provide(
            baselineStyle, expressiveStyle, segmentedExpressiveStyle,
            LocalPreferenceTheme provides finalTheme
        ) {
            content()
        }
    }
}
