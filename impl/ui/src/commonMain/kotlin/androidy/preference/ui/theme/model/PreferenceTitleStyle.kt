package androidy.preference.ui.theme.model

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * 分组标题样式
 */
@Immutable
data class PreferenceTitleStyle(
    val textStyle: TextStyle,
    val contentColor: Color,
    val containerColor: Color,
    val padding: PaddingValues,
    val shape: Shape,
    val tonalElevation: Dp,
    val shadowElevation: Dp,
    val border: BorderStroke? = null,
) {
    companion object {

        @Composable
        fun style() = PreferenceTitleStyle(
            textStyle = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
            contentColor = MaterialTheme.colorScheme.primary,
            containerColor = MaterialTheme.colorScheme.surface,
            padding = PaddingValues(top = 36.dp, bottom = 4.dp, start = 16.dp),
            shape = RectangleShape,
            tonalElevation = 0.dp,
            shadowElevation = 0.dp,
            border = null,
        )
    }
}
