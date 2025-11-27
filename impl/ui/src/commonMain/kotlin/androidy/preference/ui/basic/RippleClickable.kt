package androidy.preference.ui.basic

import androidx.compose.foundation.Indication
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalUseFallbackRippleImplementation
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

class RippleClickable {

}

/**
 * Indication用于提供点击效果，比如点击时的水波纹效果。
 * 这个函数改过好几次了，现在相当无用。
 * [androidx.compose.foundation.LocalIndication]
 */
@Composable
@Deprecated("")
internal fun rippleOrFallbackImplementation(
    bounded: Boolean = true,
    radius: Dp = Dp.Unspecified,
    color: Color = Color.Unspecified,
): Indication {
    return remember { ripple(bounded, radius, color) }
}
