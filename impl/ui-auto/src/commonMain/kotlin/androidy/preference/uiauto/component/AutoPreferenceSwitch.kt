package androidy.preference.uiauto.component

import androidx.compose.foundation.Indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidy.preference.ui.basic.BasicPreferenceItem
import androidy.preference.ui.theme.LocalPreferenceTheme
import androidy.preference.uiauto.AutoReadWriteScope
import androidy.ui.material3.listitem.interactive.StateShapes
import androidy.ui.material3.listitem.normal_style.ListItemColors
import androidy.ui.material3.listitem.normal_style.ListItemStyle

@Composable
fun AutoPreferenceSwitchItem(
    modifier: Modifier = Modifier,
    style: ListItemStyle = LocalPreferenceTheme.current.itemStyle,
    shapes: StateShapes? = null,
    colors: ListItemColors? = null,
    keyName: String,
    enabledDependOn: () -> Boolean = { true },
    indication: Indication? = ripple(),
    interactionSource: MutableInteractionSource? = null,
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)? = null,
    onLongClick: (() -> Unit)? = null,
    onLongClickLabel: String? = null,
    start: @Composable (() -> Unit)? = null,
    end: @Composable (() -> Unit)? = {
        Switch(
            enabled = enabledDependOn(),
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    },
    description: @Composable (() -> Unit)? = null,
    title: @Composable () -> Unit,
) {
    val enabled = enabledDependOn()
    AutoReadWriteScope<Boolean> {
        val newValue = observe(keyName, checked).collectAsState(checked)
        BasicPreferenceItem(
            modifier = modifier,
            style = style,
            shapes = shapes,
            colors = colors,
            enabled = enabled,
            indication = indication,
            interactionSource = interactionSource,
            checked = newValue.value,
            onCheckedChange = {
                onCheckedChange?.invoke(it)
                write(keyName, it)
            },
            onLongClick = onLongClick,
            onLongClickLabel = onLongClickLabel,
            start = start,
            end = end,
            description = description,
            title = title
        )
    }
}


@Composable
fun AutoPreferenceSwitchWithContainer(
    modifier: Modifier = Modifier,
    style: ListItemStyle = LocalPreferenceTheme.current.cautionCardItemStyle,
    shapes: StateShapes? = null,
    colors: ListItemColors? = null,
    keyName: String,
    enabledDependOn: () -> Boolean = { true },
    indication: Indication? = ripple(),
    interactionSource: MutableInteractionSource? = null,
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)? = null,
    onLongClick: (() -> Unit)? = null,
    onLongClickLabel: String? = null,
    start: @Composable (() -> Unit)? = null,
    end: @Composable (() -> Unit)? = {
        Switch(
            enabled = enabledDependOn(),
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    },
    description: @Composable (() -> Unit)? = null,
    title: @Composable () -> Unit,
) {
    AutoPreferenceSwitchItem(
        modifier = modifier,
        style = style,
        shapes = shapes,
        colors = colors,
        keyName = keyName,
        enabledDependOn = enabledDependOn,
        indication = indication,
        interactionSource = interactionSource,
        checked = checked,
        onCheckedChange = onCheckedChange,
        onLongClick = onLongClick,
        onLongClickLabel = onLongClickLabel,
        start = start,
        end = end,
        description = description,
        title = title
    )
}


@Composable
fun AutoPreferenceWithDividerSwitch(
    modifier: Modifier = Modifier,
    style: ListItemStyle = LocalPreferenceTheme.current.itemStyle,
    shapes: StateShapes? = null,
    colors: ListItemColors? = null,
    keyName: String,
    enabledDependOn: () -> Boolean = { true },
    indication: Indication? = ripple(),
    interactionSource: MutableInteractionSource? = null,
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)? = null,
    onLongClick: (() -> Unit)? = null,
    onLongClickLabel: String? = null,
    start: @Composable (() -> Unit)? = null,
    end: @Composable (() -> Unit)? = {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            VerticalDivider(
                modifier = Modifier
                    .height(24.dp)
                    .padding(end = 16.dp),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                thickness = 2f.dp
            )
            Switch(
                enabled = enabledDependOn(),
                checked = checked,
                onCheckedChange = onCheckedChange
            )
        }
    },
    description: @Composable (() -> Unit)? = null,
    title: @Composable () -> Unit,
) {
    AutoPreferenceSwitchItem(
        modifier = modifier,
        style = style,
        shapes = shapes,
        colors = colors,
        keyName = keyName,
        enabledDependOn = enabledDependOn,
        indication = indication,
        interactionSource = interactionSource,
        checked = checked,
        onCheckedChange = onCheckedChange,
        onLongClick = onLongClick,
        onLongClickLabel = onLongClickLabel,
        start = start,
        end = end,
        description = description,
        title = title
    )
}
