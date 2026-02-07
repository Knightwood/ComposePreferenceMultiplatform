package androidy.preference.ui.list_item.normal_style


import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Transition
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.key
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidy.preference.ui.list_item.expressive_style.rememberAnimatedShape


/**
 * 根据不同交互状态，返回不同颜色
 */
@Immutable
data class StateColors(
    val enabledColor: Color,
    val disabledColor: Color = enabledColor,
    val selectedColor: Color = Color.Unspecified,
    val draggedColor: Color = Color.Unspecified,
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
    context(transition: Transition<InteractiveListColorState>)
    internal fun animateColorState(
        colorAnimationSpec: () -> FiniteAnimationSpec<Color>,
    ): State<Color> {
        return transition.animateColor(transitionSpec = { colorAnimationSpec() }) { state ->
            get(
                enabled = state.enabled,
                selected = state.selected,
                dragged = state.dragged,
            )
        }
    }
}

internal data class InteractiveListColorState(
    val enabled: Boolean,
    val selected: Boolean,
    val dragged: Boolean,
)

@Immutable
class StateShapes(
    val shape: Shape,
    val selectedShape: Shape = RectangleShape,
    val pressedShape: Shape = RectangleShape,
    val focusedShape: Shape = RectangleShape,
    val hoveredShape: Shape = RectangleShape,
    val draggedShape: Shape = RectangleShape,
) {
    fun get() = shape
    fun get(selected: Boolean) = when {
        selected -> selectedShape
        else -> shape
    }

    fun get(
        selected: Boolean,
        pressed: Boolean,
        focused: Boolean,
        hovered: Boolean,
        dragged: Boolean,
    ): Shape {
        return when {
            dragged -> draggedShape
            hovered -> hoveredShape
            focused -> focusedShape
            pressed -> pressedShape
            selected -> selectedShape
            else -> shape
        }
    }

    /** Returns a copy of this [StateShapes], optionally overriding some of the values. */
    fun copy(
        shape: Shape? = this.shape,
        selectedShape: Shape? = this.selectedShape,
        pressedShape: Shape? = this.pressedShape,
        focusedShape: Shape? = this.focusedShape,
        hoveredShape: Shape? = this.hoveredShape,
        draggedShape: Shape? = this.draggedShape,
    ): StateShapes =
        StateShapes(
            shape = shape.takeOrElse { this.shape },
            selectedShape = selectedShape.takeOrElse { this.selectedShape },
            pressedShape = pressedShape.takeOrElse { this.pressedShape },
            focusedShape = focusedShape.takeOrElse { this.focusedShape },
            hoveredShape = hoveredShape.takeOrElse { this.hoveredShape },
            draggedShape = draggedShape.takeOrElse { this.draggedShape },
        )

    internal fun Shape?.takeOrElse(block: () -> Shape): Shape = this ?: block()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is StateShapes) return false

        if (shape != other.shape) return false
        if (selectedShape != other.selectedShape) return false
        if (pressedShape != other.pressedShape) return false
        if (focusedShape != other.focusedShape) return false
        if (hoveredShape != other.hoveredShape) return false
        if (draggedShape != other.draggedShape) return false

        return true
    }

    override fun hashCode(): Int {
        var result = shape.hashCode()
        result = 31 * result + selectedShape.hashCode()
        result = 31 * result + pressedShape.hashCode()
        result = 31 * result + focusedShape.hashCode()
        result = 31 * result + hoveredShape.hashCode()
        result = 31 * result + draggedShape.hashCode()
        return result
    }
}

private val StateShapes.hasRoundedCornerShapes: Boolean
    get() =
        shape is RoundedCornerShape &&
                selectedShape is RoundedCornerShape &&
                pressedShape is RoundedCornerShape &&
                focusedShape is RoundedCornerShape &&
                hoveredShape is RoundedCornerShape &&
                draggedShape is RoundedCornerShape

@Composable
internal fun StateShapes.shapeForInteraction(
    selected: Boolean,
    pressed: Boolean,
    focused: Boolean,
    hovered: Boolean,
    dragged: Boolean,
    animationSpec: FiniteAnimationSpec<Float>,
): Shape {
    val shape =
        when {
            pressed -> pressedShape
            dragged -> draggedShape
            selected -> selectedShape
            focused -> focusedShape
            hovered -> hoveredShape
            else -> shape
        }

    if (hasRoundedCornerShapes) {
        return key(this) { rememberAnimatedShape(shape as RoundedCornerShape, animationSpec) }
    }

    return shape
}

/**
 * Represents the elevation of a list item in different states.
 *
 * @param elevation the default elevation of the list item.
 * @param draggedElevation the elevation of the list item when dragged.
 */
@Immutable
class StateElevation(val elevation: Dp, val draggedElevation: Dp = elevation) {
    fun get() = elevation
    fun get(dragged: Boolean) = when {
        dragged -> draggedElevation
        else -> elevation
    }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is StateElevation) return false

        if (elevation != other.elevation) return false
        if (draggedElevation != other.draggedElevation) return false

        return true
    }

    override fun hashCode(): Int {
        var result = elevation.hashCode()
        result = 31 * result + draggedElevation.hashCode()
        return result
    }
}

