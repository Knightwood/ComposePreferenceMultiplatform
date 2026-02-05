package androidy.preference.ui.style

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.material3.ListItem
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Vertical
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidy.preference.ui.basic.invalidUse
import androidy.preference.ui.m3_tokens.value
import androidy.preference.ui.style.ListItemStyle.IconStyle

object ListItemDefaults {
    internal var cached: ListItemStyle? = null

    val defaultStyle: ListItemStyle
        @Composable
        get() {
            return cached ?: ListItemStyle(
                containerShape = ListItemTokens.ListItemContainerShape.value,
                containerColor = ListItemTokens.ListItemContainerColor.value,
                overlineTextStyle = ListItemTokens.ListItemOverlineFont.value,
                overlineColor = ListItemTokens.ListItemOverlineColor.value,
                headlineTextStyle = ListItemTokens.ListItemLabelTextFont.value,
                headlineColor = ListItemTokens.ListItemLabelTextColor.value,
                disabledHeadlineColor = ListItemTokens.ListItemDisabledLabelTextColor.value.copy(
                    alpha = ListItemTokens.ListItemDisabledLabelTextOpacity
                ),
                supportingTextStyle = ListItemTokens.ListItemSupportingTextFont.value,
                supportingTextColor = ListItemTokens.ListItemSupportingTextColor.value,
                leadingIconStyle = ListItemStyle.IconStyle.leadingIconStyle(),
                trailingIconStyle = ListItemStyle.IconStyle.leadingIconStyle(),
            ).also { cached = it }
        }

    @Composable
    fun style(
        /* 整体样式 */
        /*----------------------------------------------*/
        containerShape: Shape? = null,
        containerColor: Color? = null,
        containerTonalElevation: Dp? = null,
        containerShadowElevation: Dp? = null,
        containerBorder: BorderStroke? = null,
        containerHeightMin: Dp? = null,
        containerHeightMax: Dp? = null,

        /**
         * 主轴上有leading,body,tailing 等元素, 设置他们的对齐方式.
         * Alignment.Top的效果将与ListItem一样
         */
        alignment: Vertical? = null,
        /* 整体样式 */

        /* 各要素尺寸，我们只设置内边距。外边距效果让用户自己来实现。 */
        contentPadding: PaddingValues? = null,

        leadingPadding: PaddingValues? = null,
        /* 可以给头部视图设置确切的尺寸，也可以使用leadingPercent设定占宽度的比例 */
        leadingSize: IntSize? = null,
        leadingPercent: Float? = null,

        bodyPadding: PaddingValues? = null,
        bodySpace: Dp? = null, /* overline,headline,supporting的间隔 */
        bodyPercent: Float? = null,

        trailingPadding: PaddingValues? = null,
        trailingSize: IntSize? = null,
        trailingPercent: Float? = null,
        /* 各要素尺寸 */

        /* overline,headline,supporting的文本样式 */
        overlineTextStyle: TextStyle? = null,
        overlineColor: Color? = null,

        headlineTextStyle: TextStyle? = null,
        headlineColor: Color? = null,
        disabledHeadlineColor: Color? = null,

        supportingTextStyle: TextStyle? = null,
        supportingTextColor: Color? = null,
        /* overline,headline,supporting的文本样式 */

        /* Icon的默认样式 */
        leadingIconStyle: IconStyle? = null,
        trailingIconStyle: IconStyle? = null,
    ): ListItemStyle {
        return defaultStyle.copy(
            containerShape = containerShape,
            containerColor = containerColor,
            containerTonalElevation = containerTonalElevation,
            containerShadowElevation = containerShadowElevation,
            containerBorder = containerBorder,
            containerHeightMin = containerHeightMin,
            containerHeightMax = containerHeightMax,
            alignment = alignment,
            contentPadding = contentPadding,
            leadingPadding = leadingPadding,
            leadingSize = leadingSize,
            leadingPercent = leadingPercent,
            bodyPadding = bodyPadding,
            bodySpace = bodySpace,
            bodyPercent = bodyPercent,
            trailingPadding = trailingPadding,
            trailingSize = trailingSize,
            trailingPercent = trailingPercent,
            overlineTextStyle = overlineTextStyle,
            overlineColor = overlineColor,
            headlineTextStyle = headlineTextStyle,
            headlineColor = headlineColor,
            disabledHeadlineColor = disabledHeadlineColor,
            supportingTextStyle = supportingTextStyle,
            supportingTextColor = supportingTextColor,
            leadingIconStyle = leadingIconStyle,
            trailingIconStyle = trailingIconStyle,
        )
    }
}

@Immutable
class ListItemStyle
constructor(
    /* 整体样式 */
    /*----------------------------------------------*/
    val containerShape: Shape,
    val containerColor: Color,
    val containerTonalElevation: Dp = ListItemTokens.ListItemContainerElevation,
    val containerShadowElevation: Dp = ListItemTokens.ListItemContainerShadowElevation,
    val containerBorder: BorderStroke? = null,
    val containerHeightMin: Dp = ListItemTokens.ListItemContainerHeightMin,
    val containerHeightMax: Dp = ListItemTokens.ListItemContainerHeightMax,

    /**
     * 主轴上有leading,body,tailing 等元素, 设置他们的对齐方式.
     * Alignment.Top的效果将与ListItem一样
     *
     * 原版ListItem：
     * In most cases, elements in a list item are middle-aligned
     * If a list is 88dp or larger, or contains three or more lines of text, elements are top-aligned
     */
    val alignment: Vertical = Alignment.Top,
    /* 整体样式 */

    /* 各要素尺寸，我们只设置内边距。外边距效果让用户自己来实现。 */
    val contentPadding: PaddingValues = PaddingValues(
        top = ListItemTokens.ListItemVerticalPadding,
        bottom = ListItemTokens.ListItemVerticalPadding,
        start = ListItemTokens.ListItemStartPadding,
        end = ListItemTokens.ListItemEndPadding
    ),

    val leadingPadding: PaddingValues = PaddingValues(end = ListItemTokens.LeadingContentEndPadding),
    /* 可以给头部视图设置确切的尺寸，也可以使用leadingPercent设定占宽度的比例 */
    val leadingSize: IntSize? = null,
    val leadingPercent: Float? = null,

    val bodyPadding: PaddingValues = PaddingValues(0.dp),
    val bodySpace: Dp = 4.dp, /* overline,headline,supporting的间隔 */
    val bodyPercent: Float? = null,

    val trailingPadding: PaddingValues = PaddingValues(start = ListItemTokens.TrailingContentStartPadding),
    val trailingSize: IntSize? = null,
    val trailingPercent: Float? = null,
    /* 各要素尺寸 */

    /* overline,headline,supporting的文本样式 */
    val overlineTextStyle: TextStyle,
    val overlineColor: Color,

    val headlineTextStyle: TextStyle,
    val headlineColor: Color,
    val disabledHeadlineColor: Color,

    val supportingTextStyle: TextStyle,
    val supportingTextColor: Color,
    /* overline,headline,supporting的文本样式 */

    /* Icon的默认样式 */
    val leadingIconStyle: IconStyle,
    val trailingIconStyle: IconStyle,

    ) {
    /**
     * icon、text都默认使用ContentColor
     */
    data class IconStyle(
        val shape: Shape?,
        val backgroundColor: Color = Color.Transparent,
        val paddingValues: PaddingValues = PaddingValues(0.dp),
        val textStyle: TextStyle,
        val iconSize: Dp,
        val contentColor: Color,
        val disabledContentColor: Color,
    ) {
        companion object {
            @Composable
            fun leadingAvatarStyle(
                paddingValues: PaddingValues = PaddingValues(0.dp),
                disabledContentColor: Color = ListItemTokens.ListItemLeadingAvatarColor.value.copy(
                    alpha = ListItemTokens.ListItemDisabledLeadingIconOpacity
                ),
                shape: Shape = ListItemTokens.ListItemLeadingAvatarShape.value,
                iconSize: Dp = ListItemTokens.ListItemLeadingAvatarSize,
                contentColor: Color = ListItemTokens.ListItemLeadingAvatarLabelColor.value,
                backgroundColor: Color = ListItemTokens.ListItemLeadingAvatarColor.value,
                textStyle: TextStyle = ListItemTokens.ListItemLeadingAvatarLabelFont.value,
            ): IconStyle {
                return IconStyle(
                    shape = shape,
                    backgroundColor = backgroundColor,
                    paddingValues = paddingValues,
                    textStyle = textStyle,
                    iconSize = iconSize,
                    contentColor = contentColor,
                    disabledContentColor = disabledContentColor
                )
            }

            @Composable
            fun leadingIconStyle(
                shape: Shape? = null,
                paddingValues: PaddingValues = PaddingValues(0.dp),
                contentColor: Color = ListItemTokens.ListItemLeadingIconColor.value,
                backgroundColor: Color = Color.Transparent,
                textStyle: TextStyle = ListItemTokens.ListItemLeadingAvatarLabelFont.value,
                iconSize: Dp = ListItemTokens.ListItemLeadingIconSize,
                disabledIconColor: Color = ListItemTokens.ListItemDisabledLeadingIconColor.value.copy(
                    alpha = ListItemTokens.ListItemDisabledLeadingIconOpacity
                ),
            ): IconStyle {
                return IconStyle(
                    shape = shape,
                    paddingValues = paddingValues,
                    backgroundColor = backgroundColor,
                    iconSize = iconSize,
                    contentColor = contentColor,
                    textStyle = textStyle,
                    disabledContentColor = disabledIconColor,
                )
            }

            @Composable
            fun trailingIconStyle(
                shape: Shape? = null,
                paddingValues: PaddingValues = PaddingValues(0.dp),
                contentColor: Color = ListItemTokens.ListItemTrailingIconColor.value,
                backgroundColor: Color = Color.Transparent,
                textStyle: TextStyle = ListItemTokens.ListItemTrailingSupportingTextFont.value,
                iconSize: Dp = ListItemTokens.ListItemTrailingIconSize,
                disabledIconColor: Color = ListItemTokens.ListItemDisabledTrailingIconColor.value.copy(
                    alpha = ListItemTokens.ListItemDisabledTrailingIconOpacity
                ),
            ): IconStyle {
                return IconStyle(
                    shape = shape,
                    paddingValues = paddingValues,
                    backgroundColor = backgroundColor,
                    iconSize = iconSize,
                    contentColor = contentColor,
                    textStyle = textStyle,
                    disabledContentColor = disabledIconColor,
                )
            }
        }
    }

    fun copy(
        /* 整体样式 */
        /*----------------------------------------------*/
        containerShape: Shape? = null,
        containerColor: Color? = null,
        containerTonalElevation: Dp? = null,
        containerShadowElevation: Dp? = null,
        containerBorder: BorderStroke? = null,
        containerHeightMin: Dp? = null,
        containerHeightMax: Dp? = null,

        /**
         * 主轴上有leading,body,tailing 等元素, 设置他们的对齐方式.
         * Alignment.Top的效果将与ListItem一样
         */
        alignment: Vertical? = null,
        /* 整体样式 */

        /* 各要素尺寸，我们只设置内边距。外边距效果让用户自己来实现。 */
        contentPadding: PaddingValues? = null,

        leadingPadding: PaddingValues? = null,
        /* 可以给头部视图设置确切的尺寸，也可以使用leadingPercent设定占宽度的比例 */
        leadingSize: IntSize? = null,
        leadingPercent: Float? = null,

        bodyPadding: PaddingValues? = null,
        bodySpace: Dp? = null, /* overline,headline,supporting的间隔 */
        bodyPercent: Float? = null,

        trailingPadding: PaddingValues? = null,
        trailingSize: IntSize? = null,
        trailingPercent: Float? = null,
        /* 各要素尺寸 */

        /* overline,headline,supporting的文本样式 */
        overlineTextStyle: TextStyle? = null,
        overlineColor: Color? = null,

        headlineTextStyle: TextStyle? = null,
        headlineColor: Color? = null,
        disabledHeadlineColor: Color? = null,

        supportingTextStyle: TextStyle? = null,
        supportingTextColor: Color? = null,
        /* overline,headline,supporting的文本样式 */

        /* Icon的默认样式 */
        leadingIconStyle: IconStyle? = null,
        trailingIconStyle: IconStyle? = null,
    ): ListItemStyle {
        return ListItemStyle(
            containerShape = containerShape ?: this.containerShape,
            containerColor = containerColor invalidUse { this.containerColor },
            containerTonalElevation = containerTonalElevation ?: this.containerTonalElevation,
            containerShadowElevation = containerShadowElevation ?: this.containerShadowElevation,
            containerBorder = containerBorder ?: this.containerBorder,
            containerHeightMin = containerHeightMin ?: this.containerHeightMin,
            containerHeightMax = containerHeightMax ?: this.containerHeightMax,
            alignment = alignment ?: this.alignment,
            contentPadding = contentPadding ?: this.contentPadding,
            leadingPadding = leadingPadding ?: this.leadingPadding,
            leadingSize = leadingSize ?: this.leadingSize,
            leadingPercent = leadingPercent ?: this.leadingPercent,
            bodyPadding = bodyPadding ?: this.bodyPadding,
            bodySpace = bodySpace ?: this.bodySpace,
            bodyPercent = bodyPercent ?: this.bodyPercent,
            trailingPadding = trailingPadding ?: this.trailingPadding,
            trailingSize = trailingSize ?: this.trailingSize,
            trailingPercent = trailingPercent ?: this.trailingPercent,
            overlineTextStyle = overlineTextStyle ?: this.overlineTextStyle,
            overlineColor = overlineColor invalidUse { this.overlineColor },
            headlineTextStyle = headlineTextStyle ?: this.headlineTextStyle,
            headlineColor = headlineColor invalidUse { this.headlineColor },
            disabledHeadlineColor = disabledHeadlineColor invalidUse { this.disabledHeadlineColor },
            supportingTextStyle = supportingTextStyle ?: this.supportingTextStyle,
            supportingTextColor = supportingTextColor invalidUse { this.supportingTextColor },
            leadingIconStyle = leadingIconStyle ?: this.leadingIconStyle,
            trailingIconStyle = trailingIconStyle ?: this.trailingIconStyle,
        )
    }

    internal fun contentPadding(listItemType: ListItemType = ListItemType.OneLine): PaddingValues {
        return when (listItemType) {
            ListItemType.OneLine -> contentPadding
            ListItemType.TwoLine -> contentPadding
            ListItemType.ThreeLine -> contentPadding.copy(
                top = ListItemTokens.ListItemThreeLineVerticalPadding,
                bottom = ListItemTokens.ListItemThreeLineVerticalPadding,
            )

            else -> contentPadding
        }
    }

    internal fun containerHeightMax(listItemType: ListItemType): Dp {
        val padding = contentPadding(listItemType)
        return containerHeightMax + padding.calculateTopPadding() + padding.calculateBottomPadding()
    }

    /** The container color of this [ListItem] based on enabled state */
    internal fun containerColor(): Color {
        return containerColor
    }

    /** The color of this [ListItem]'s headline text based on enabled state */
    @Stable
    internal fun headlineColor(enabled: Boolean): Color {
        return if (enabled) headlineColor else disabledHeadlineColor
    }

    /** The color of this [ListItem]'s leading content based on enabled state */
    @Stable
    internal fun leadingIconColor(enabled: Boolean): Color =
        if (enabled) leadingIconStyle.contentColor else leadingIconStyle.disabledContentColor

    /** The color of this [ListItem]'s overline text based on enabled state */
    @Stable
    internal fun overlineColor(): Color = overlineColor

    /** The color of this [ListItem]'s supporting text based on enabled state */
    @Stable
    internal fun supportingColor(): Color = supportingTextColor

    /** The color of this [ListItem]'s trailing content based on enabled state */
    @Stable
    internal fun trailingIconColor(enabled: Boolean): Color =
        if (enabled) trailingIconStyle.contentColor else trailingIconStyle.disabledContentColor
}

private fun PaddingValues.copy(
    top: Dp = this.calculateTopPadding(),
    bottom: Dp = this.calculateBottomPadding(),
    layoutDirection: LayoutDirection = LayoutDirection.Ltr,
    start: Dp = this.calculateStartPadding(layoutDirection),
    end: Dp = this.calculateEndPadding(layoutDirection),
): PaddingValues {
    return PaddingValues(top = top, bottom = bottom, start = start, end = end)
}
