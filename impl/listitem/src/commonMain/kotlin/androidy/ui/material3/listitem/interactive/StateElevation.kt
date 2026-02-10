package androidy.ui.material3.listitem.interactive

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.ui.unit.Dp

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

    @Composable
    fun animateElevation(
        interactiveState: InteractiveState,
        animationSpec: () -> AnimationSpec<Dp>,
    ): State<Dp> {
        val targetElevation = if (interactiveState.isDragged) draggedElevation else elevation
        return animateDpAsState(
            targetValue = targetElevation,
            animationSpec = animationSpec(),
            label = "StateElevation",
        )
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
