package androidy.preference.ui.list_item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidy.preference.ui.basic.takeIf
import androidy.preference.ui.list_item.m3_tokens.ProvideContentColorTextStyle
import androidy.preference.ui.list_item.m3_tokens.value
import androidy.preference.ui.list_item.normal_style.ListItemStyle
import androidy.preference.ui.list_item.normal_style.ListItemTokens

private fun PaddingValues.horization(layoutDirection: LayoutDirection): Dp =
    calculateStartPadding(layoutDirection) + calculateEndPadding(layoutDirection)

private fun PaddingValues.vertical(): Dp = calculateTopPadding() + calculateBottomPadding()

@Composable
fun ListItemDivider(paddingValues: PaddingValues = PaddingValues(ListItemTokens.DividerSpace)) {
    HorizontalDivider(modifier = Modifier.padding(paddingValues))
}

@Composable
fun ListItemIconLeading(content: @Composable () -> Unit) {
    val style = LocalListItemStyle.current.leadingIconStyle
    Box(
        modifier = Modifier
            .size(style.iconSize)
    ) {
        content()
    }
}

@Composable
fun ListItemAvatarLeading(content: @Composable () -> Unit) {
    Box(modifier = Modifier) {
        content()
    }
}

@Composable
fun ListItemImageLeading(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .size(ListItemTokens.ItemLeadingImageWidth, ListItemTokens.ItemLeadingImageHeight)
            .background(Color.Transparent, ListItemTokens.ItemLeadingImageShape.value)
    ) {
        content()
    }
}

@Composable
fun ListItemVideoLeading(large: Boolean = false, content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .size(
                ListItemTokens.ItemLeadingVideoWidth,
                if (large)
                    ListItemTokens.ItemLargeLeadingVideoHeight
                else
                    ListItemTokens.ItemSmallLeadingVideoHeight
            )
            .background(Color.Transparent, ListItemTokens.ItemLeadingVideoShape.value)
    ) {
        content()
    }
}

@Composable
fun SealListItem(
    modifier: Modifier = Modifier,
    style: ListItemStyle = LocalListItemStyle.current,
    overlineContent: @Composable (() -> Unit)? = null,
    headlineContent: @Composable () -> Unit,
    supportingContent: @Composable (() -> Unit)? = null,
    leadingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
) {
    val layoutDirection = LocalLayoutDirection.current
    val listItemType = ListItemType(
        overlineContent != null,
        supportingContent != null,
    )
    with(style) {
        val realPadding = contentPadding(listItemType)
        Surface(
            modifier = modifier.heightIn(
                min = containerHeightMin,
            ),
            shape = containerShape,
            tonalElevation = containerTonalElevation,
            color = containerColor,
            shadowElevation = containerShadowElevation,
            border = containerBorder,
        ) {
            Box {
                Row(
                    modifier = Modifier.align(Alignment.CenterStart)
                        .padding(realPadding)
                ) {
                    leadingContent?.let { decor ->
                        Box(
                            modifier = Modifier
                                .align(alignment)
                                .padding(leadingPadding)
                                .takeIf({ leadingPercent != null }) {
                                    weight(leadingPercent!!)
                                }
                                .takeIf({ leadingSize != null }) {
                                    size(leadingSize!!)
                                }
                        ) {
                            ProvideContentColorTextStyle(
                                contentColor = leadingIconColor(enabled),
                                textStyle = leadingIconStyle.textStyle,
                            ) {
                                decor()
                            }
                        }
                    }
                    Column(
                        modifier = Modifier.weight(bodyPercent).align(alignment),
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
                            ProvideContentColorTextStyle(
                                supportingColor(enabled),
                                supportingTextStyle
                            ) {
                                supportingContent()
                            }
                        }
                    }
                    trailingContent?.let { decor ->
                        Box(
                            modifier = Modifier
                                .align(alignment)
                                .padding(trailingPadding)
                                .takeIf({ trailingPercent != null }) {
                                    weight(trailingPercent!!)
                                }
                                .takeIf({ trailingSize != null }) {
                                    size(trailingSize!!)
                                }
                        ) {
                            ProvideContentColorTextStyle(
                                contentColor = trailingIconColor(enabled),
                                textStyle = trailingIconStyle.textStyle,
                            ) {
                                decor()
                            }
                        }
                    }
                }
            }
        }
    }
}
