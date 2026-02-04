package androidy.preference.ui.style

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidy.preference.ui.m3_tokens.TypographyKeyTokens
import androidy.preference.ui.m3_tokens.value

object ListItemStyleHolder {

    private val LocalListItemStyle = compositionLocalOf<ListItemStyle> { error("No style provided") }

    @Suppress("ComposableNaming")
    @Composable
    fun provider(style: ListItemStyle = defaultStyle, content: @Composable () -> Unit) {
        CompositionLocalProvider(LocalListItemStyle provides style) {
            content()
        }
    }

    @Composable
    fun get() = LocalListItemStyle.current
}
