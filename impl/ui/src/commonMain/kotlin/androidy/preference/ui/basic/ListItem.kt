package androidy.preference.ui.basic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidy.preference.ui.m3_tokens.ProvideContentColorTextStyle
import androidy.preference.ui.m3_tokens.value
import androidy.preference.ui.style.ListItemStyle
import androidy.preference.ui.style.ListItemStyleHolder
import androidy.preference.ui.style.ListItemTokens
import androidy.preference.ui.style.ListItemType
import kotlin.math.sin

@Composable
fun ListItemDivider(paddingValues: PaddingValues = PaddingValues(ListItemTokens.DividerSpace)) {
    HorizontalDivider(modifier = Modifier.padding(paddingValues))
}

@Composable
fun ListItemIconLeading(content: @Composable () -> Unit) {
    Box(modifier = Modifier) {
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
            .size(ListItemTokens.ListItemLeadingImageWidth, ListItemTokens.ListItemLeadingImageHeight)
            .background(Color.Transparent, ListItemTokens.ListItemLeadingImageShape.value)
    ) {
        content()
    }
}

@Composable
fun ListItemVideoLeading(large: Boolean = false, content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .size(
                ListItemTokens.ListItemLeadingVideoWidth,
                if (large)
                    ListItemTokens.ListItemLargeLeadingVideoHeight
                else
                    ListItemTokens.ListItemSmallLeadingVideoHeight
            )
            .background(Color.Transparent, ListItemTokens.ListItemLeadingVideoShape.value)
    ) {
        content()
    }
}

@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    style: ListItemStyle= ListItemStyleHolder.get(),
    overlineContent: @Composable (() -> Unit)? = null,
    headlineContent: @Composable () -> Unit,
    supportingContent: @Composable (() -> Unit)? = null,
    leadingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    singleLine: Boolean = true,
) {
    val listItemType = ListItemType.invoke(
        overlineContent != null,
        supportingContent != null,
        !singleLine
    )
    with(style) {
        Surface(
            modifier = modifier.heightIn(
                min = containerHeightMin,
                max = containerHeightMax(listItemType)
            ),
            shape = containerShape,
            tonalElevation = containerTonalElevation,
            color = containerColor,
            shadowElevation = containerShadowElevation,
            border = containerBorder,
        ) {
            Box {
                Row(modifier = Modifier.align(Alignment.CenterStart).padding(contentPadding(listItemType))) {
                    leadingContent?.let { decor ->
                        Box(modifier = Modifier.align(alignment).padding(leadingPadding)) {
                            ProvideContentColorTextStyle(
                                leadingIconStyle.contentColor,
                                leadingIconStyle.textStyle
                            ) {
                                decor()
                            }
                        }
                    }
                    Column(
                        modifier = Modifier.weight(1f).align(alignment),
                        verticalArrangement = Arrangement.spacedBy(bodySpace)
                    ) {
                        if (overlineContent != null) {
                            ProvideContentColorTextStyle(
                                overlineColor(),
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
                                supportingColor(),
                                supportingTextStyle
                            ) {
                                supportingContent()
                            }
                        }
                    }
                    trailingContent?.let { decor ->
                        Box(modifier = Modifier.align(alignment).padding(trailingPadding)) {
                            ProvideContentColorTextStyle(
                                trailingIconStyle.contentColor,
                                trailingIconStyle.textStyle
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
