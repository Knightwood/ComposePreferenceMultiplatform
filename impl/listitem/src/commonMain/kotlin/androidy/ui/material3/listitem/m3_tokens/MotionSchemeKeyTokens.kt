package androidy.ui.material3.listitem.m3_tokens

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MotionScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable

internal enum class MotionSchemeKeyTokens {
    DefaultSpatial,
    FastSpatial,
    SlowSpatial,
    DefaultEffects,
    FastEffects,
    SlowEffects,
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Stable
internal fun <T> MotionScheme.fromToken(value: MotionSchemeKeyTokens): FiniteAnimationSpec<T> {
    return when (value) {
        MotionSchemeKeyTokens.DefaultSpatial -> defaultSpatialSpec()
        MotionSchemeKeyTokens.FastSpatial -> fastSpatialSpec()
        MotionSchemeKeyTokens.SlowSpatial -> slowSpatialSpec()
        MotionSchemeKeyTokens.DefaultEffects -> defaultEffectsSpec()
        MotionSchemeKeyTokens.FastEffects -> fastEffectsSpec()
        MotionSchemeKeyTokens.SlowEffects -> slowEffectsSpec()
    }
}

/**
 * Converts a [MotionSchemeKeyTokens] key to the [FiniteAnimationSpec] provided by the
 * [MotionScheme].
 */
@Composable
@ReadOnlyComposable
@OptIn(ExperimentalMaterial3ExpressiveApi::class)
internal fun <T> MotionSchemeKeyTokens.value(): FiniteAnimationSpec<T> =
    MaterialTheme.motionScheme.fromToken(this)
