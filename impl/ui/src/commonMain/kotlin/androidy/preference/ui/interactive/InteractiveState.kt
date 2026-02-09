package androidy.preference.ui.interactive

import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

/**
 * 存储不同触摸状态下的组件样式，用于交互时提供动画以及动态修改组件样式。
 */
class InteractiveState(
    pressed: Boolean?,
    focused: Boolean?,
    hovered: Boolean?,
    dragged: Boolean?,
) {
    constructor() : this(false, false, false, false)

    val pressed = pressed?.let { mutableStateOf(it) }
    val focused = focused?.let { mutableStateOf(it) }
    val hovered = hovered?.let { mutableStateOf(it) }
    val dragged = dragged?.let { mutableStateOf(it) }

    val isPressed get() = pressed?.value ?: false
    val isFocused get() = focused?.value ?: false
    val isHovered get() = hovered?.value ?: false
    val isDragged get() = dragged?.value ?: false

    operator fun component1(): MutableState<Boolean>? = pressed
    operator fun component2(): MutableState<Boolean>? = focused
    operator fun component3(): MutableState<Boolean>? = hovered
    operator fun component4(): MutableState<Boolean>? = dragged
}

enum class CurrentInteractiveState {
    ENABLED,
    SELECTED,
    PRESSED,
    FOCUSED,
    HOVERED,
    DRAGGED
}

/**
 * collect 交互流，收集交互状态并将当前交互状态设置给InteractiveState
 */
@Composable
fun InteractionSource.CollectInteractionsAsState(state: InteractiveState) {
    val (pressedState, focusedState, hoveredState, draggedState) = state
    LaunchedEffect(this) {
        val pressInteractions = pressedState?.let { mutableListOf<PressInteraction.Press>() }
        val focusInteractions = focusedState?.let { mutableListOf<FocusInteraction.Focus>() }
        val hoverInteractions = hoveredState?.let { mutableListOf<HoverInteraction.Enter>() }
        val dragInteractions = draggedState?.let { mutableListOf<DragInteraction.Start>() }

        interactions.collect { interaction ->
            when (interaction) {
                // press
                is PressInteraction.Press -> pressInteractions?.add(interaction)
                is PressInteraction.Release -> pressInteractions?.remove(interaction.press)
                is PressInteraction.Cancel -> pressInteractions?.remove(interaction.press)
                // focus
                is FocusInteraction.Focus -> focusInteractions?.add(interaction)
                is FocusInteraction.Unfocus -> focusInteractions?.remove(interaction.focus)
                // hover
                is HoverInteraction.Enter -> hoverInteractions?.add(interaction)
                is HoverInteraction.Exit -> hoverInteractions?.remove(interaction.enter)
                // drag
                is DragInteraction.Start -> dragInteractions?.add(interaction)
                is DragInteraction.Stop -> dragInteractions?.remove(interaction.start)
                is DragInteraction.Cancel -> dragInteractions?.remove(interaction.start)
            }
            if (pressedState != null && pressInteractions != null) {
                pressedState.value = pressInteractions.isNotEmpty()
            }
            if (focusedState != null && focusInteractions != null) {
                focusedState.value = focusInteractions.isNotEmpty()
            }
            if (hoveredState != null && hoverInteractions != null) {
                hoveredState.value = hoverInteractions.isNotEmpty()
            }
            if (draggedState != null && dragInteractions != null) {
                draggedState.value = dragInteractions.isNotEmpty()
            }
        }
    }
}

@Composable
fun rememberInteractiveState(
    sources: InteractionSource,
    pressed: Boolean?,
    focused: Boolean?,
    hovered: Boolean?,
    dragged: Boolean?,
): InteractiveState {
    return remember {
        InteractiveState(pressed, focused, hovered, dragged)
    }.apply {
        sources.CollectInteractionsAsState(this)
    }
}

@Composable
fun rememberInteractiveState(sources: InteractionSource): InteractiveState {
    return remember {
        InteractiveState()
    }.apply {
        sources.CollectInteractionsAsState(this)
    }
}

