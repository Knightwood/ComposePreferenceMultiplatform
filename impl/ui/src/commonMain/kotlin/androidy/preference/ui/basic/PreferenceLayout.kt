package androidy.preference.ui.basic

import androidx.compose.foundation.Indication
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidy.ui.material3.listitem.BaselineListItem
import androidy.ui.material3.listitem.ExpressiveListItem
import androidy.ui.material3.listitem.SegmentedListItem
import androidy.ui.material3.listitem.interactive.StateShapes
import androidy.ui.material3.listitem.normal_style.ListItemColors
import androidy.ui.material3.listitem.normal_style.ListItemStyle
import androidy.ui.material3.listitem.normal_style.ListItemStyleType

private const val TAG = "PreferenceLayout"

@Composable
fun BasicPreferenceItem(
    modifier: Modifier = Modifier,
    style: ListItemStyle,
    shapes: StateShapes? = null,
    colors: ListItemColors? = null,
    enabled: Boolean = true,
    indication: Indication? = ripple(),
    interactionSource: MutableInteractionSource? = null,
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    onLongClickLabel: String? = null,
    start: @Composable (() -> Unit)? = null,
    end: @Composable (() -> Unit)? = null,
    description: @Composable (() -> Unit)? = null,
    title: @Composable () -> Unit,
) {
    when (style.type) {
        ListItemStyleType.BASELINE -> {
            BaselineListItem(
                modifier = modifier
                    .semantics(mergeDescendants = true, properties = {
                        role = Role.Button
                    })
                    .background(color =style.containerColor.get(enabled), shape =style.containerShape.get())
                    .clip(style.containerShape.get())
                    .combinedClickable(
                        interactionSource = interactionSource,
                        indication = indication,
                        enabled = enabled,
                        onLongClick = onLongClick,
                        onLongClickLabel = onLongClickLabel,
                        onClick = onClick,
                    ),
                shapes = shapes,
                colors = colors,
                enabled = enabled,
                leadingContent = start,
                trailingContent = end,
                headlineContent = title,
                supportingContent = description,
                style = style,
            )
        }

        ListItemStyleType.EXPRESSIVE -> {
            ExpressiveListItem(
                modifier = modifier,
                shapes = shapes,
                colors = colors,
                enabled = enabled,
                leadingContent = start,
                trailingContent = end,
                headlineContent = title,
                supportingContent = description,
                style = style,
                onClick = onClick,
                onLongClick = onLongClick,
                onLongClickLabel = onLongClickLabel,
                interactionSource = interactionSource,
                indication = indication,
            )
        }

        ListItemStyleType.SEGMENTED -> {
            SegmentedListItem(
                modifier = modifier,
                shapes = shapes,
                colors = colors,
                enabled = enabled,
                leadingContent = start,
                trailingContent = end,
                headlineContent = title,
                supportingContent = description,
                style = style,
                onClick = onClick,
            )
        }
    }
}


@Composable
fun BasicPreferenceItem(
    modifier: Modifier = Modifier,
    style: ListItemStyle,
    shapes: StateShapes? = null,
    colors: ListItemColors? = null,
    enabled: Boolean = true,
    indication: Indication? = ripple(),
    interactionSource: MutableInteractionSource? = null,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onLongClick: (() -> Unit)? = null,
    onLongClickLabel: String? = null,
    start: @Composable (() -> Unit)? = null,
    end: @Composable (() -> Unit)? = null,
    description: @Composable (() -> Unit)? = null,
    title: @Composable () -> Unit,
) {
    when (style.type) {
        ListItemStyleType.BASELINE -> {
            BaselineListItem(
                modifier = modifier
                    .semantics(mergeDescendants = true, properties = {
                        role = Role.Checkbox
                    })
                    .combinedClickable(
                        interactionSource = interactionSource,
                        indication = indication,
                        enabled = enabled,
                        onLongClick = onLongClick,
                        onLongClickLabel = onLongClickLabel,
                        onClick = {
                            onCheckedChange(!checked)
                        },
                    ),
                shapes = shapes,
                colors = colors,
                enabled = enabled,
                leadingContent = start,
                trailingContent = end,
                headlineContent = title,
                supportingContent = description,
                style = style,
            )
        }

        ListItemStyleType.EXPRESSIVE -> {
            ExpressiveListItem(
                modifier = modifier,
                shapes = shapes,
                colors = colors,
                enabled = enabled,
                leadingContent = start,
                trailingContent = end,
                headlineContent = title,
                supportingContent = description,
                style = style,
                checked = checked,
                onCheckedChange = onCheckedChange,
                onLongClick = onLongClick,
                onLongClickLabel = onLongClickLabel,
                interactionSource = interactionSource,
                indication = indication,
            )
        }

        ListItemStyleType.SEGMENTED -> {
            SegmentedListItem(
                modifier = modifier,
                shapes = shapes,
                colors = colors,
                enabled = enabled,
                leadingContent = start,
                trailingContent = end,
                headlineContent = title,
                supportingContent = description,
                style = style,
                checked = checked,
                onCheckedChange = onCheckedChange,
            )
        }
    }
}

@Composable
fun BasicPreferenceItem(
    modifier: Modifier = Modifier,
    style: ListItemStyle,
    shapes: StateShapes? = null,
    colors: ListItemColors? = null,
    enabled: Boolean = true,
    indication: Indication? = ripple(),
    interactionSource: MutableInteractionSource? = null,
    selected: Boolean,
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    onLongClickLabel: String? = null,
    start: @Composable (() -> Unit)? = null,
    end: @Composable (() -> Unit)? = null,
    description: @Composable (() -> Unit)? = null,
    title: @Composable () -> Unit,
) {
    when (style.type) {
        ListItemStyleType.BASELINE -> {
            BaselineListItem(
                modifier = modifier
                    .semantics(mergeDescendants = true, properties = {
                        role = Role.RadioButton
                    })
                    .combinedClickable(
                        interactionSource = interactionSource,
                        indication = indication,
                        enabled = enabled,
                        onLongClick = onLongClick,
                        onLongClickLabel = onLongClickLabel,
                        onClick = onClick,
                    ),
                shapes = shapes,
                colors = colors,
                enabled = enabled,
                leadingContent = start,
                trailingContent = end,
                headlineContent = title,
                supportingContent = description,
                style = style,
            )
        }

        ListItemStyleType.EXPRESSIVE -> {
            ExpressiveListItem(
                modifier = modifier,
                shapes = shapes,
                colors = colors,
                enabled = enabled,
                leadingContent = start,
                trailingContent = end,
                headlineContent = title,
                supportingContent = description,
                style = style,
                selected = selected,
                onClick = onClick,
                onLongClick = onLongClick,
                onLongClickLabel = onLongClickLabel,
                interactionSource = interactionSource,
                indication = indication,
            )
        }

        ListItemStyleType.SEGMENTED -> {
            SegmentedListItem(
                modifier = modifier,
                shapes = shapes,
                colors = colors,
                enabled = enabled,
                leadingContent = start,
                trailingContent = end,
                headlineContent = title,
                supportingContent = description,
                style = style,
                selected = selected,
                onClick = onClick,
            )
        }
    }
}
