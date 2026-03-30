package androidy.preference.uiauto.component

import androidx.compose.foundation.Indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidy.preference.ui.basic.BasicPreferenceItem
import androidy.preference.ui.theme.LocalPreferenceTheme
import androidy.preference.uiauto.PreferenceNodeBase
import androidy.ui.material3.listitem.interactive.StateShapes
import androidy.ui.material3.listitem.normal_style.ListItemColors
import androidy.ui.material3.listitem.normal_style.ListItemStyle

@Composable
fun AutoPreferenceCheckBoxItem(
    modifier: Modifier = Modifier,
    style: ListItemStyle = LocalPreferenceTheme.current.itemStyle,
    shapes: StateShapes? = null,
    colors: ListItemColors? = null,
    enabled: Boolean = true,
    keyName: String,
    dependenceKey: String?,
    indication: Indication? = ripple(),
    interactionSource: MutableInteractionSource? = null,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onLongClick: (() -> Unit)? = null,
    onLongClickLabel: String? = null,
    start: @Composable (() -> Unit)? = null,
    end: @Composable (() -> Unit)? = {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    },
    description: @Composable (() -> Unit)? = null,
    title: @Composable () -> Unit,
) {
    PreferenceNodeBase(
        dependenceKey = dependenceKey,
        keyName = keyName, defaultValue = checked,
        enabled = enabled
    ) { scope, state, provider, writer ->
        val prefValue = provider()

        var checked by remember {
            mutableStateOf(checked)
        }
        remember(prefValue) {
            if (prefValue != checked)
                checked = prefValue
            onCheckedChange.invoke(prefValue)
            provider
        }
        BasicPreferenceItem(
            modifier = modifier,
            style = style,
            shapes = shapes,
            colors = colors,
            enabled = state,
            indication = indication,
            interactionSource = interactionSource,
            checked = checked,
            onCheckedChange = {
                onCheckedChange.invoke(it)
                writer.invoke(it)
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

