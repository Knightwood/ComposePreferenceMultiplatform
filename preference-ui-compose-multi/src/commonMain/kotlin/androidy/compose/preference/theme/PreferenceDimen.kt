package androidy.compose.preference.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * 描述preference组件的padding
 *
 * @property boxMarginValues item surface的边距
 * @property boxPaddingValues item内row的边距
 * @property startPaddingValues item中的左侧内容的边距
 * @property contentPaddingValues 中间内容的边距
 * @property endPaddingValues item右侧内容的边距
 */
@Immutable
data class PreferenceDimen(
    val heightMin: Dp = 64.dp,
    val iconSize: Dp = 24.dp,

    val boxMarginValues: PaddingValues = PaddingValues(
        start = 12.dp,
        end = 12.dp,
        top = 0.dp,
        bottom = 0.dp
    ),

    val boxPaddingValues: PaddingValues = PaddingValues(
        start = 8.dp,
        end = 8.dp,
        top = 12.dp,
        bottom = 12.dp
    ),

    val startPaddingValues: PaddingValues = PaddingValues(
        start = 8.dp,
        end = 8.dp
    ),
    val contentPaddingValues: PaddingValues = PaddingValues(
        start = 8.dp,
        end = 4.dp,
    ),
    val endPaddingValues: PaddingValues = PaddingValues(
        start = 4.dp,
        end = 4.dp,
    ),
)

internal val LocalPreferenceDimens = compositionLocalOf<PreferenceDimen> {
    PreferenceDimen()
}
