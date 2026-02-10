package androidy.ui.material3.listitem

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidy.ui.material3.listitem.expressive_style.ExpressiveListItemDefaults
import androidy.ui.material3.listitem.expressive_style.SegmentedListItemStyleDefaults
import androidy.ui.material3.listitem.normal_style.ListItemDefaults
import androidy.ui.material3.listitem.normal_style.ListItemStyle

object LocalListItemStyle {

    private val LocalListItemStyle = compositionLocalOf<ListItemStyle> { error("No style provided") }
    private val LocalExpressiveListItemStyle = compositionLocalOf<ListItemStyle> { error("No style provided") }
    private val LocalSegmentedExpressiveListItemStyle = compositionLocalOf<ListItemStyle> { error("No style provided") }

    @Suppress("ComposableNaming")
    @Composable
    fun provide(
        style: ListItemStyle = ListItemDefaults.defaultStyle,
        expressiveStyle: ListItemStyle = ExpressiveListItemDefaults.expressiveDefaultStyle,
        segmentedExpressiveStyle: ListItemStyle = SegmentedListItemStyleDefaults.segmentedDefaultStyle,
        content: @Composable () -> Unit,
    ) {
        CompositionLocalProvider(
            LocalListItemStyle provides style,
            LocalExpressiveListItemStyle provides expressiveStyle,
            LocalSegmentedExpressiveListItemStyle provides segmentedExpressiveStyle,
        ) {
            content()
        }
    }

    val current
        @Composable
        get() = LocalListItemStyle.current

    val currentExpressive
        @Composable
        get() = LocalExpressiveListItemStyle.current

    val currentSegmentedExpressive
        @Composable
        get() = LocalSegmentedExpressiveListItemStyle.current
}
