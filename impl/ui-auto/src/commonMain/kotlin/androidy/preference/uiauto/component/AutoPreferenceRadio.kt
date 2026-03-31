package androidy.preference.uiauto.component

import androidx.compose.foundation.Indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.RadioButton
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidy.preference.ui.basic.BasicPreferenceItem
import androidy.preference.ui.theme.LocalPreferenceTheme
import androidy.preference.uiauto.AutoReadWriteScope
import androidy.ui.material3.listitem.interactive.StateShapes
import androidy.ui.material3.listitem.normal_style.ListItemColors
import androidy.ui.material3.listitem.normal_style.ListItemStyle

@Composable
fun AutoPreferenceRadioButtonItem(
    modifier: Modifier = Modifier,
    style: ListItemStyle = LocalPreferenceTheme.current.itemStyle,
    shapes: StateShapes? = null,
    colors: ListItemColors? = null,
    keyName: String,
    defaultValue: Int = 0,
    value: Int,
    enabledDependOn: () -> Boolean = { true },
    indication: Indication? = ripple(),
    interactionSource: MutableInteractionSource? = null,
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    onLongClickLabel: String? = null,
    start: @Composable (() -> Unit)? = null,
    end: @Composable ((selected: Boolean) -> Unit)? = { selected ->
        RadioButton(
            selected = selected,
            onClick = onClick
        )
    },
    description: @Composable (() -> Unit)? = null,
    title: @Composable () -> Unit,
) {
    val enabled = enabledDependOn()
    AutoReadWriteScope<Int> {
        val newSelectedValue = observe(keyName, defaultValue).collectAsState(defaultValue)
        val isSelected by remember {
            derivedStateOf {
                newSelectedValue.value == value
            }
        }
        BasicPreferenceItem(
            modifier = modifier,
            style = style,
            shapes = shapes,
            colors = colors,
            enabled = enabled,
            indication = indication,
            interactionSource = interactionSource,
            selected = isSelected,
            onClick = {
                onClick()
                write(keyName, value)
            },
            onLongClick = onLongClick,
            onLongClickLabel = onLongClickLabel,
            start = start,
            end = {
                end?.invoke(isSelected)
            },
            description = description,
            title = title
        )
    }


}
