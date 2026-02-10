package androidy.ui.material3.listitem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.isSpecified
import androidx.compose.ui.unit.sp
import androidy.ui.material3.listitem.basic.takeIf
import androidy.ui.material3.listitem.m3_tokens.ProvideContentColorTextStyle
import androidy.ui.material3.listitem.normal_style.ListItemIconStyle
import androidy.ui.material3.listitem.normal_style.ListItemStyle
import androidy.ui.material3.listitem.normal_style.ListItemTokens

private val logger = org.slf4j.LoggerFactory.getLogger("ListItem")

/**
 */
@Composable
fun BaselineListItem(
    modifier: Modifier = Modifier,
    headlineContent: @Composable () -> Unit,
    overlineContent: @Composable (() -> Unit)? = null,
    supportingContent: @Composable (() -> Unit)? = null,
    leadingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    style: ListItemStyle = LocalListItemStyle.current,
) {
    with(style) {
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
        Surface(
            modifier = modifier.heightIn(
                min = containerHeightMin,
            ),
            shape = containerShape.get(),
            tonalElevation = containerTonalElevation.get(),
            color = containerColor(),
            shadowElevation = containerShadowElevation.get(),
            border = containerBorder,
        ) {
            Box {
                Row(
                    modifier = Modifier.align(Alignment.CenterStart)
                        .padding(realPadding)
                ) {
                    ListItemLeading(realAlignment, enabled, leadingContent)
                    Column(
                        modifier = Modifier.weight(bodyPercent).align(realAlignment),
                        verticalArrangement = if (bodyItemSpace != null && bodyItemSpace.value > 0)
                            Arrangement.spacedBy(bodyItemSpace) else Arrangement.Top
                    ) {
                        if (overlineContent != null) {
                            ProvideContentColorTextStyle(
                                overlineColor(enabled),
                                overlineTextStyle
                            ) {
                                overlineContent()
                            }
                        }
                        ProvideContentColorTextStyle(
                            headlineColor(enabled),
                            headlineTextStyle
                        ) {
                            headlineContent()
                        }
                        if (supportingContent != null) {
                            Box(modifier = Modifier.onSizeChanged { size ->
                                supportingContentHeight = size.height
                            }) {
                                ProvideContentColorTextStyle(
                                    supportingColor(enabled),
                                    supportingTextStyle
                                ) {
                                    supportingContent()
                                }
                            }
                        }
                    }
                    ListItemTrailing(realAlignment, enabled, trailingContent)
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
 * 此方法用于配置第一部分样式，第二部分样式需要[androidy.ui.material3.listitem.ListItemIconBox]进行配置
 */
@Composable
context(scope: RowScope)
private fun ListItemStyle.ListItemLeading(
    alignment: Alignment.Vertical,
    enabled: Boolean,
    content: @Composable (() -> Unit)?,
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
                    .takeIf({  leadingSize.isSpecified  }) {
                        size(leadingSize)
                    }
            ) {
                ListItemIconBox(enabled, leadingIconStyle, decor)
            }
        }
    }
}

@Composable
context(scope: RowScope)
private fun ListItemStyle.ListItemTrailing(
    alignment: Alignment.Vertical,
    enabled: Boolean,
    content: @Composable (() -> Unit)?,
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
                ListItemIconBox(enabled, trailingIconStyle, decor)
            }
        }
    }
}

@Composable
private fun ListItemStyle.ListItemIconBox(
    enabled: Boolean,
    style: ListItemIconStyle = leadingIconStyle,
    content: @Composable () -> Unit,
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
                contentColor = style.stateColors.get(enabled),
                textStyle = style.textStyle,
            ) {
                content()
            }
        }
    }
}
//</editor-fold>

@Composable
internal fun TextUnit.roundToPx(): Int {
    val localDensity = LocalDensity.current
    return localDensity.run {
        roundToPx()
    }
}

internal fun PaddingValues.horization(layoutDirection: LayoutDirection): Dp =
    calculateStartPadding(layoutDirection) + calculateEndPadding(layoutDirection)

internal fun PaddingValues.vertical(): Dp = calculateTopPadding() + calculateBottomPadding()

@Composable
fun ListItemDivider(paddingValues: PaddingValues = PaddingValues(ListItemTokens.DividerSpace)) {
    HorizontalDivider(modifier = Modifier.padding(paddingValues))
}
