package androidy.ui.material3.listitem.interactive


import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.updateTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color


/**
 * 根据不同交互状态，返回不同颜色
 */
@Immutable
data class StateColors(
    val enabledColor: Color,
    val disabledColor: Color = enabledColor,
    val selectedColor: Color = enabledColor,
    val draggedColor: Color = enabledColor,
    val hoveredColor: Color = enabledColor,
) {
    fun get(enabled: Boolean): Color {
        return when {
            enabled -> enabledColor
            else -> disabledColor
        }
    }

    fun get(
        enabled: Boolean,
        selected: Boolean,
        dragged: Boolean,
    ): Color {
        return when {
            dragged -> draggedColor
            selected -> selectedColor
            enabled -> enabledColor
            else -> disabledColor
        }
    }

    @Composable
    internal fun animateColorState(
        enabled: Boolean,
        selected: Boolean,
        interactiveState: InteractiveState,
        colorAnimationSpec: () -> FiniteAnimationSpec<Color>,
    ): State<Color> {
        val colorState = InteractiveListColorState(
            enabled = enabled,
            selected = selected,
            dragged = interactiveState.isDragged,
        )
        return animateColorState(colorState, colorAnimationSpec)
    }

    @Composable
    internal fun animateColorState(
        colorState: InteractiveListColorState,
        colorAnimationSpec: () -> FiniteAnimationSpec<Color>,
    ): State<Color> {
        val transition = updateTransition(colorState, "ListColor")
        return transition.animateColor(transitionSpec = { colorAnimationSpec() }) { state ->
            get(
                enabled = state.enabled,
                selected = state.selected,
                dragged = state.dragged,
            )
        }
    }
}

@Immutable
class InteractiveListColorState(
    val enabled: Boolean,
    val selected: Boolean,
    val dragged: Boolean,
    val pressed: Boolean,
    val focused: Boolean,
    val hovered: Boolean,
) {
    constructor(enabled: Boolean) : this(
        enabled = enabled,
        selected = false,
        dragged = false,
        pressed = false,
        focused = false,
        hovered = false,
    )

    constructor(enabled: Boolean, selected: Boolean) : this(
        enabled = enabled,
        selected = selected,
        dragged = false,
        pressed = false,
        focused = false,
        hovered = false,
    )

    constructor(enabled: Boolean, selected: Boolean, dragged: Boolean) : this(
        enabled = enabled,
        selected = selected,
        dragged = dragged,
        pressed = false,
        focused = false,
        hovered = false,
    )
}

