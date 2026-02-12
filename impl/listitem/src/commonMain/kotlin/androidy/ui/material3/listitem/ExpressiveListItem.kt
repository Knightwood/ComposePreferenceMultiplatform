package androidy.ui.material3.listitem

import androidx.compose.foundation.Indication
import androidx.compose.ui.layout.layout
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.toggleableState
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isSpecified
import androidx.compose.ui.unit.sp
import androidy.ui.material3.listitem.basic.FloatProducer
import androidy.ui.material3.listitem.basic.takeIf
import androidy.ui.material3.listitem.interactive.StateShapes
import androidy.ui.material3.listitem.interactive.rememberInteractiveState
import androidy.ui.material3.listitem.m3_tokens.MotionSchemeKeyTokens
import androidy.ui.material3.listitem.m3_tokens.ProvideContentColorTextStyle
import androidy.ui.material3.listitem.m3_tokens.value
import androidy.ui.material3.listitem.normal_style.ListItemColors
import androidy.ui.material3.listitem.normal_style.ListItemIconStyle
import androidy.ui.material3.listitem.normal_style.ListItemStyle
import androidy.ui.material3.listitem.normal_style.merge
import org.slf4j.LoggerFactory

private val logger = LoggerFactory.getLogger("ExpressiveListItem")

@Composable
fun ExpressiveListItem(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: ListItemStyle = LocalListItemStyle.currentExpressive,
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
        enabled = enabled,
        selected = false,
        indication = indication,
        applySemantics = {
            role = Role.Button
        },
        onClick = onClick,
        onLongClick = onLongClick,
        onLongClickLabel = onLongClickLabel,
        interactionSource = interactionSource,
    )
}

@Composable
fun ExpressiveListItem(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: ListItemStyle = LocalListItemStyle.currentExpressive,
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
fun ExpressiveListItem(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: ListItemStyle = LocalListItemStyle.currentExpressive,
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


@Composable
internal fun InteractiveListItem(
    modifier: Modifier = Modifier,
    headlineContent: @Composable () -> Unit,
    overlineContent: @Composable (() -> Unit)? = null,
    supportingContent: @Composable (() -> Unit)? = null,
    leadingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    selected: Boolean = false,
    style: ListItemStyle = LocalListItemStyle.currentExpressive,
    applySemantics: SemanticsPropertyReceiver.() -> Unit,
    indication: Indication? = ripple(),
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    onLongClickLabel: String? = null,
    interactionSource: MutableInteractionSource? = null,
) {
    with(style) {

        @Suppress("NAME_SHADOWING")
        val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
        val interactiveState = rememberInteractiveState(interactionSource)

        val colorAnimationSpec = MotionSchemeKeyTokens.DefaultEffects.value<Color>()
        val shapeAnimationSpec = MotionSchemeKeyTokens.FastSpatial.value<Float>()
        val elevationAnimationSpec = MotionSchemeKeyTokens.FastSpatial.value<Dp>()

        val containerShadowElevation = containerShadowElevation.animateElevation(interactiveState, { elevationAnimationSpec })
        val containerShape = this.containerShape.collectAsState(selected, interactiveState, { shapeAnimationSpec })
        val containerColor by containerColor.animateColorState(enabled, selected, interactiveState, { colorAnimationSpec })
        val contentColor by headlineColor.animateColorState(enabled, selected, interactiveState, { colorAnimationSpec })
        val leadingColor by leadingIconStyle.stateColors.animateColorState(enabled, selected, interactiveState, { colorAnimationSpec })
        val trailingColor by trailingIconStyle.stateColors.animateColorState(enabled, selected, interactiveState, { colorAnimationSpec })
        val overlineColor by overlineColor.animateColorState(enabled, selected, interactiveState, { colorAnimationSpec })
        val supportingColor by supportingTextColor.animateColorState(enabled, selected, interactiveState, { colorAnimationSpec })


        var supportingContentHeight: Int by remember { mutableIntStateOf(0) }
        val onelineHeightLimit = 30.sp.roundToPx()
        val listItemType by remember {
            derivedStateOf {
                ListItemType(
                    overlineContent != null,
                    supportingContent != null,
                    (supportingContentHeight > onelineHeightLimit) && alignment.enableAdjustAlignment
                )
            }
        }
        val realAlignment = alignment(listItemType)
        val realPadding = contentPadding(listItemType)
        Box(
            modifier = modifier
                .semantics(mergeDescendants = true, properties = applySemantics)
                .defaultMinSize(minHeight = containerHeightMin)
                .minimumInteractiveComponentSize()
                .zIndexLambda { if (containerShadowElevation.value > 0.dp) 1f else 0f }
                .graphicsLayer {
                    this.shadowElevation = with(density) { containerShadowElevation.value.toPx() }
                    this.shape = shape
                    clip = false
                }
                .background(color = containerColor, shape = containerShape)
                .clip(containerShape)
                .combinedClickable(
                    interactionSource = interactionSource,
                    indication = indication,
                    enabled = enabled,
                    onLongClick = onLongClick,
                    onLongClickLabel = onLongClickLabel,
                    onClick = onClick,
                )
        ) {
            Box {
                Row(
                    modifier = Modifier.align(Alignment.CenterStart)
                        .padding(realPadding)
                ) {
                    ListItemLeading(realAlignment, leadingContent, leadingColor)
                    Column(
                        modifier = Modifier.weight(bodyPercent).align(realAlignment),
                        verticalArrangement = if (bodyItemSpace != null && bodyItemSpace.value > 0)
                            Arrangement.spacedBy(bodyItemSpace) else Arrangement.Top
                    ) {
                        if (overlineContent != null) {
                            ProvideContentColorTextStyle(overlineColor, overlineTextStyle) {
                                overlineContent()
                            }
                        }
                        ProvideContentColorTextStyle(contentColor, headlineTextStyle) {
                            headlineContent()
                        }
                        if (supportingContent != null) {
                            Box(modifier = Modifier.onSizeChanged { size ->
                                supportingContentHeight = size.height
                            }) {
                                ProvideContentColorTextStyle(supportingColor, supportingTextStyle) {
                                    supportingContent()
                                }
                            }
                        }
                    }
                    ListItemTrailing(realAlignment, trailingContent, trailingColor)
                }
            }
        }
    }
}


//<editor-fold desc="Leading、Trailing内容视图">
/**
 * 给Leading、Trailing内容视图设置样式分为两部分：
 * 1. 对齐方式、padding、size或比例
 * 2. 内容的背景色、内容色、形状等
 * 此方法用于配置第一部分样式，第二部分样式需要[ListItemIconBox]进行配置
 */
@Composable
context(scope: RowScope)
private fun ListItemStyle.ListItemLeading(
    alignment: Alignment.Vertical,
    content: @Composable (() -> Unit)?,
    contentColor: Color,
) {
    with(scope) {
        content?.let { decor ->
            Box(
                modifier = Modifier
                    .align(alignment)
                    .padding(leadingPadding)
                    .takeIf({ leadingPercent != null }) {
                        weight(leadingPercent!!)
                    }
                    .takeIf({ leadingSize.isSpecified }) {
                        size(leadingSize)
                    }
            ) {
                ListItemIconBox(leadingIconStyle, decor, contentColor)
            }
        }
    }
}

@Composable
context(scope: RowScope)
private fun ListItemStyle.ListItemTrailing(
    alignment: Alignment.Vertical,
    content: @Composable (() -> Unit)?,
    contentColor: Color,
) {
    with(scope) {
        content?.let { decor ->
            Box(
                modifier = Modifier
                    .align(alignment)
                    .padding(trailingPadding)
                    .takeIf({ trailingPercent != null }) {
                        weight(trailingPercent!!)
                    }
                    .takeIf({ trailingSize.isSpecified }) {
                        size(trailingSize)
                    }
            ) {
                ListItemIconBox(trailingIconStyle, decor, contentColor)
            }
        }
    }
}

@Composable
private fun ListItemStyle.ListItemIconBox(
    style: ListItemIconStyle = leadingIconStyle,
    content: @Composable () -> Unit,
    contentColor: Color,
) {
    Box(
        modifier = Modifier
            .background(style.backgroundColor, style.shape)
            .takeIf({ style.size.isSpecified }) {
                size(style.size)
            }
    ) {
        Box(modifier = Modifier.align(alignment = Alignment.Center)) {
            ProvideContentColorTextStyle(
                contentColor = contentColor,
                textStyle = style.textStyle,
            ) {
                content()
            }
        }
    }
}

//</editor-fold>
private fun Modifier.zIndexLambda(zIndex: FloatProducer): Modifier =
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        layout(placeable.width, placeable.height) { placeable.place(0, 0, zIndex = zIndex()) }
    }
