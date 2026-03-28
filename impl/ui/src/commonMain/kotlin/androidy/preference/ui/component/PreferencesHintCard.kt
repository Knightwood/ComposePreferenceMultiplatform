package androidy.preference.ui.component

import androidx.compose.foundation.Indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidy.preference.ui.basic.BasicPreferenceItem
import androidy.preference.ui.theme.LocalPreferenceTheme
import androidy.ui.material3.listitem.interactive.StateShapes
import androidy.ui.material3.listitem.normal_style.ListItemColors
import androidy.ui.material3.listitem.normal_style.ListItemStyle

@Composable
fun PreferencesHintCard(
    modifier: Modifier = Modifier,
    style: ListItemStyle = LocalPreferenceTheme.current.hintCardItemStyle,
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
) = BasicPreferenceItem(
    modifier = modifier,
    style = style,
    shapes = shapes,
    colors = colors,
    enabled = enabled,
    indication = indication,
    interactionSource = interactionSource,
    onClick = onClick,
    onLongClick = onLongClick,
    onLongClickLabel = onLongClickLabel,
    start = start,
    end = end,
    description = description,
    title = title
)
