package androidy.preference.ui.component

import androidx.compose.foundation.Indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.DialogProperties
import androidy.preference.ui.theme.LocalPreferenceTheme
import androidy.ui.material3.listitem.interactive.StateShapes
import androidy.ui.material3.listitem.normal_style.ListItemColors
import androidy.ui.material3.listitem.normal_style.ListItemStyle

@Composable
fun PreferenceAlertDialog(
    modifier: Modifier = Modifier,
    style: ListItemStyle = LocalPreferenceTheme.current.itemStyle,
    shapes: StateShapes? = null,
    colors: ListItemColors? = null,
    enabled: Boolean = true,
    indication: Indication? = ripple(),
    interactionSource: MutableInteractionSource? = null,
    visible: Boolean,
    onVisibleChange: (Boolean) -> Unit,
    onLongClick: (() -> Unit)? = null,
    onLongClickLabel: String? = null,
    start: @Composable (() -> Unit)? = null,
    end: @Composable (() -> Unit)? = null,
    description: @Composable (() -> Unit)? = null,
    title: @Composable () -> Unit,
    /* AlertDialog */
    confirmButton: @Composable () -> Unit,
    dismissButton: @Composable (() -> Unit)? = null,
    dialogIcon: @Composable (() -> Unit)? = start,
    dialogTitle: @Composable (() -> Unit)? = title,
    dialogText: @Composable (() -> Unit)? = description,
    dialogShape: Shape = AlertDialogDefaults.shape,
    dialogContainerColor: Color = AlertDialogDefaults.containerColor,
    dialogIconContentColor: Color = AlertDialogDefaults.iconContentColor,
    dialogTitleContentColor: Color = AlertDialogDefaults.titleContentColor,
    dialogTextContentColor: Color = AlertDialogDefaults.textContentColor,
    dialogTonalElevation: Dp = AlertDialogDefaults.TonalElevation,
    dialogProperties: DialogProperties = DialogProperties(),
) {
    PreferenceItem(
        title = title,
        description = description,
        onClick = { onVisibleChange(true) },
        end = end,
        start = start,
        modifier = modifier,
        style = style,
        shapes = shapes,
        colors = colors,
        enabled = enabled,
        indication = indication,
        interactionSource = interactionSource,
        onLongClick = onLongClick,
        onLongClickLabel = onLongClickLabel,
    )
    if (visible) {
        AlertDialog(
            onDismissRequest = { onVisibleChange(false) },
            icon = dialogIcon,
            title = dialogTitle,
            text = dialogText,
            confirmButton = confirmButton,
            dismissButton = dismissButton,
            shape = dialogShape,
            containerColor = dialogContainerColor,
            iconContentColor = dialogIconContentColor,
            titleContentColor = dialogTitleContentColor,
            textContentColor = dialogTextContentColor,
            tonalElevation = dialogTonalElevation,
            properties = dialogProperties
        )
    }
}
