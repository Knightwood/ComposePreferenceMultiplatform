package androidy.ui.material3.listitem

import androidx.compose.foundation.Indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.toggleableState
import androidx.compose.ui.state.ToggleableState
import androidy.ui.material3.listitem.interactive.StateShapes
import androidy.ui.material3.listitem.normal_style.ListItemColors
import androidy.ui.material3.listitem.normal_style.ListItemStyle
import androidy.ui.material3.listitem.normal_style.merge
import org.slf4j.LoggerFactory

private val logger = LoggerFactory.getLogger("SegmentedListItem")

@Composable
fun SegmentedListItem(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: ListItemStyle = LocalListItemStyle.currentSegmented,
    shapes: StateShapes? = null,
    colors: ListItemColors? = null,
    indication: Indication? = ripple(),
    leadingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    overlineContent: @Composable (() -> Unit)? = null,
    supportingContent: @Composable (() -> Unit)? = null,
    onLongClick: (() -> Unit)? = null,
    onLongClickLabel: String? = null,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable () -> Unit,
) {
    val finalStyle = remember(style, shapes, colors) {
        mutableStateOf(style.merge(colors, shapes))
    }
    InteractiveListItem(
        modifier = modifier,
        headlineContent = content,
        leadingContent = leadingContent,
        trailingContent = trailingContent,
        overlineContent = overlineContent,
        supportingContent = supportingContent,
        style = finalStyle.value,
        indication = indication,
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
    style: ListItemStyle = LocalListItemStyle.currentSegmented,
    shapes: StateShapes? = null,
    colors: ListItemColors? = null,
    indication: Indication? = ripple(),
    leadingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    overlineContent: @Composable (() -> Unit)? = null,
    supportingContent: @Composable (() -> Unit)? = null,
    onLongClick: (() -> Unit)? = null,
    onLongClickLabel: String? = null,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable () -> Unit,
) {
    val finalStyle = remember(style, shapes, colors) {
        mutableStateOf(style.merge(colors, shapes))
    }
    InteractiveListItem(
        modifier = modifier,
        headlineContent = content,
        leadingContent = leadingContent,
        trailingContent = trailingContent,
        overlineContent = overlineContent,
        supportingContent = supportingContent,
        style = finalStyle.value,
        indication = indication,
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
    style: ListItemStyle = LocalListItemStyle.currentSegmented,
    shapes: StateShapes? = null,
    colors: ListItemColors? = null,
    indication: Indication? = ripple(),
    leadingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    overlineContent: @Composable (() -> Unit)? = null,
    supportingContent: @Composable (() -> Unit)? = null,
    onLongClick: (() -> Unit)? = null,
    onLongClickLabel: String? = null,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable () -> Unit,
) {
    val finalStyle = remember(style, shapes, colors) {
        mutableStateOf(style.merge(colors, shapes))
    }
    InteractiveListItem(
        modifier = modifier,
        headlineContent = content,
        leadingContent = leadingContent,
        trailingContent = trailingContent,
        overlineContent = overlineContent,
        supportingContent = supportingContent,
        style = finalStyle.value,
        indication = indication,
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
