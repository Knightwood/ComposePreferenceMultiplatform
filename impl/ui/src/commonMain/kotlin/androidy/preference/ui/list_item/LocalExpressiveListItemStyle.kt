package androidy.preference.ui.list_item

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidy.preference.ui.list_item.expressive_style.ExpressiveListItemDefaults
import androidy.preference.ui.list_item.normal_style.ListItemDefaults
import androidy.preference.ui.list_item.normal_style.ListItemStyle

object LocalExpressiveListItemStyle {

    private val LocalListItemStyle = compositionLocalOf<ListItemStyle> { error("No style provided") }

    @Suppress("ComposableNaming")
    @Composable
    fun provide(style: ListItemStyle = ExpressiveListItemDefaults.defaultStyle, content: @Composable () -> Unit) {
        CompositionLocalProvider(LocalListItemStyle provides style) {
            content()
        }
    }

    val current
        @Composable
        get() = LocalListItemStyle.current
}
