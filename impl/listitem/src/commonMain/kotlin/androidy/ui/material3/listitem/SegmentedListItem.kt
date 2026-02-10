package androidy.ui.material3.listitem

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.toggleableState
import androidx.compose.ui.state.ToggleableState
import androidy.ui.material3.listitem.interactive.StateShapes
import androidy.ui.material3.listitem.normal_style.ListItemStyle
import org.slf4j.LoggerFactory

private val logger = LoggerFactory.getLogger("SegmentedListItem")

@Composable
fun SegmentedListItem(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: ListItemStyle = LocalListItemStyle.currentSegmentedExpressive,
    shapes: StateShapes = style.containerShape,
    leadingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    overlineContent: @Composable (() -> Unit)? = null,
    supportingContent: @Composable (() -> Unit)? = null,
    onLongClick: (() -> Unit)? = null,
    onLongClickLabel: String? = null,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable () -> Unit,
) {
    val finalStyle = remember(style, shapes) {
        mutableStateOf(style.copy(shapes))
    }
    InteractiveListItem(
        modifier = modifier,
        headlineContent = content,
        leadingContent = leadingContent,
        trailingContent = trailingContent,
        overlineContent = overlineContent,
        supportingContent = supportingContent,
        style = finalStyle.value,
        enabled = enabled,
        selected = false,
        applySemantics = {},
        onClick = onClick,
        onLongClick = onLongClick,
        onLongClickLabel = onLongClickLabel,
        interactionSource = interactionSource,
    )
}

@Composable
fun SegmentedListItem(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: ListItemStyle = LocalListItemStyle.currentSegmentedExpressive,
    shapes: StateShapes = style.containerShape,
    leadingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    overlineContent: @Composable (() -> Unit)? = null,
    supportingContent: @Composable (() -> Unit)? = null,
    onLongClick: (() -> Unit)? = null,
    onLongClickLabel: String? = null,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable () -> Unit,
) {
    val finalStyle = remember(style, shapes) {
        mutableStateOf(style.copy(shapes))
    }
    InteractiveListItem(
        modifier = modifier,
        headlineContent = content,
        leadingContent = leadingContent,
        trailingContent = trailingContent,
        overlineContent = overlineContent,
        supportingContent = supportingContent,
        style = finalStyle.value,
        enabled = enabled,
        selected = checked,
        applySemantics = {
            toggleableState = ToggleableState(checked)
            role = Role.Checkbox
        },
        onClick = { onCheckedChange(!checked) },
        onLongClick = onLongClick,
        onLongClickLabel = onLongClickLabel,
        interactionSource = interactionSource,
    )
}

@Composable
fun SegmentedListItem(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: ListItemStyle = LocalListItemStyle.currentSegmentedExpressive,
    shapes: StateShapes = style.containerShape,
    leadingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    overlineContent: @Composable (() -> Unit)? = null,
    supportingContent: @Composable (() -> Unit)? = null,
    onLongClick: (() -> Unit)? = null,
    onLongClickLabel: String? = null,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable () -> Unit,
) {
    val finalStyle = remember(style, shapes) {
        mutableStateOf(style.copy(shapes))
    }
    InteractiveListItem(
        modifier = modifier,
        headlineContent = content,
        leadingContent = leadingContent,
        trailingContent = trailingContent,
        overlineContent = overlineContent,
        supportingContent = supportingContent,
        style = finalStyle.value,
        enabled = enabled,
        selected = selected,
        applySemantics = {
            toggleableState = ToggleableState(selected)
            role = Role.RadioButton
        },
        onClick = { onClick() },
        onLongClick = onLongClick,
        onLongClickLabel = onLongClickLabel,
        interactionSource = interactionSource,
    )
}
