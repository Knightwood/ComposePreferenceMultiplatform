package androidy.preference.ui.component

import androidx.compose.animation.*
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.IntSize.Companion
import androidx.compose.ui.unit.dp
import androidy.preference.ui.basic.icons.ExpandLess
import androidy.preference.ui.basic.icons.ExpandMore
import androidy.preference.ui.theme.LocalPreferenceTheme
import androidy.ui.material3.listitem.interactive.StateShapes
import androidy.ui.material3.listitem.normal_style.ListItemColors
import androidy.ui.material3.listitem.normal_style.ListItemStyle

@Composable
fun PreferenceCollapsedItem(
    modifier: Modifier = Modifier,
    style: ListItemStyle = LocalPreferenceTheme.current.itemStyle,
    shapes: StateShapes? = null,
    colors: ListItemColors? = null,
    enabled: Boolean = true,
    indication: Indication? = ripple(),
    interactionSource: MutableInteractionSource? = null,
    expand: Boolean,
    onExpandChange: (Boolean) -> Unit,
    onLongClick: (() -> Unit)? = null,
    onLongClickLabel: String? = null,
    start: @Composable (() -> Unit)? = null,
    end: @Composable (() -> Unit)? = {
        IconButton(onClick = { onExpandChange(!expand) }) {
            Icon(
                if (expand) Icons.Outlined.ExpandLess else Icons.Outlined.ExpandMore,
                contentDescription = null
            )
        }
    },
    description: @Composable (() -> Unit)? = null,
    title: @Composable () -> Unit,
    /* animateContentSize */
    animationSpec: FiniteAnimationSpec<IntSize> =
        spring(
            stiffness = Spring.StiffnessMediumLow,
            visibilityThreshold = IntSize.VisibilityThreshold,
        ),
    finishedListener: ((initialValue: IntSize, targetValue: IntSize) -> Unit)? = null,
    /* 展开内容 */
    content: @Composable () -> Unit,
) {
    Column(modifier = Modifier.animateContentSize(animationSpec, finishedListener)) {
        PreferenceCheckBoxItem(
            title = title,
            description = description,
            checked = expand,
            onCheckedChange = onExpandChange,
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
        if (expand)
            content()
    }
}
