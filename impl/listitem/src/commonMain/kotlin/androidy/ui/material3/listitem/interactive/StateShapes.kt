package androidy.ui.material3.listitem.interactive

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.key
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import org.slf4j.LoggerFactory

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

    @Composable
    internal fun StateShapes.shapeForInteraction(
        selected: Boolean,
        pressed: Boolean,
        focused: Boolean,
        hovered: Boolean,
        dragged: Boolean,
        animationSpec: () -> FiniteAnimationSpec<Float>,
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
            return key(this) { rememberAnimatedShape(shape as RoundedCornerShape, animationSpec()) }
        }

        return shape
    }

    val StateShapes.hasRoundedCornerShapes: Boolean
        get() =
            shape is RoundedCornerShape &&
                    selectedShape is RoundedCornerShape &&
                    pressedShape is RoundedCornerShape &&
                    focusedShape is RoundedCornerShape &&
                    hoveredShape is RoundedCornerShape &&
                    draggedShape is RoundedCornerShape

    private val logger = LoggerFactory.getLogger("StateShapes")

    @Composable
    fun collectAsState(
        selected: Boolean,
        state: InteractiveState,
        animationSpec: () -> FiniteAnimationSpec<Float>,
    ): Shape {
//        LaunchedEffect(state.isHovered, state.isPressed, state.isFocused, state.isDragged) {
//            val a = state
//            logger.info("interactiveState: isHovered: ${a.isHovered}; isPressed: ${a.isPressed}; isFocused: ${a.isFocused}; isDragged: ${a.isDragged} ")
//        }
        return shapeForInteraction(
            selected,
            state.isPressed,
            state.isFocused,//jvm平台下此状态永远为true，会导致shape无法回到正常状态
            state.isHovered,
            state.isDragged,
            animationSpec,
        )
    }

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
