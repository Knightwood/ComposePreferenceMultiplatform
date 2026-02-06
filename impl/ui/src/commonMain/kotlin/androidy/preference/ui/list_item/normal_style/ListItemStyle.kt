package androidy.preference.ui.list_item.normal_style

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ListItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Vertical
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidy.preference.ui.basic.invalidUse
import androidy.preference.ui.list_item.ListItemType
import androidy.preference.ui.list_item.m3_tokens.value


@Immutable
class ListItemStyle
constructor(
    /* 整体样式 */
    /*----------------------------------------------*/
    val containerShape: Shape,
    val containerColor: Color,
    val containerTonalElevation: Dp = ListItemTokens.ItemContainerElevation,
    val containerShadowElevation: Dp = ListItemTokens.ItemContainerShadowElevation,
    val containerBorder: BorderStroke? = null,
    val containerHeightMin: Dp = ListItemTokens.ItemContainerHeightMin,
    val containerHeightMax: Dp = ListItemTokens.ItemContainerHeightMax,

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

    /**
     * 我们只设置内边距。外边距效果让用户自己来实现。
     * 在单行时垂直边距应为8dp
     * 当多行或者item比较臃肿时，垂直边距应为12dp
     * 默认为10dp
     */
    val contentPadding: ListItemContentPaddingValues = ListItemContentPaddingValues(
        oneline = PaddingValues(
            top = ListItemTokens.ItemVerticalPadding,
            bottom = ListItemTokens.ItemVerticalPadding,
            start = ListItemTokens.ItemStartPadding,
            end = ListItemTokens.ItemEndPadding
        ),
        twoline = PaddingValues(
            top = ListItemTokens.ItemVerticalPadding,
            bottom = ListItemTokens.ItemVerticalPadding,
            start = ListItemTokens.ItemStartPadding,
            end = ListItemTokens.ItemEndPadding
        ),
        threeline = PaddingValues(
            top = ListItemTokens.ItemThreeLineVerticalPadding,
            bottom = ListItemTokens.ItemThreeLineVerticalPadding,
            start = ListItemTokens.ItemStartPadding,
            end = ListItemTokens.ItemEndPadding
        )
    ),

    val leadingPadding: PaddingValues = PaddingValues(end = ListItemTokens.LeadingContentEndPadding),
    /* 可以给头部视图设置确切的尺寸，也可以使用leadingPercent设定占宽度的比例 */
    val leadingSize: DpSize? = null,
    val leadingPercent: Float? = null,

    val bodyPadding: PaddingValues = PaddingValues(0.dp),
    val bodyItemSpace: Dp? = null, /* overline,headline,supporting的间隔 */
    val bodyPercent: Float = 1f,

    val trailingPadding: PaddingValues = PaddingValues(start = ListItemTokens.TrailingContentStartPadding),
    val trailingSize: DpSize? = null,
    val trailingPercent: Float? = null,
    /* 各要素尺寸 */

    /* overline,headline,supporting的文本样式 */
    val overlineTextStyle: TextStyle,
    val overlineColor: Color,
    val disabledOverlineColor: Color,

    val headlineTextStyle: TextStyle,
    val headlineColor: Color,
    val disabledHeadlineColor: Color,

    val supportingTextStyle: TextStyle,
    val supportingTextColor: Color,
    val disabledSupportingTextColor: Color,
    /* overline,headline,supporting的文本样式 */

    /* Icon的默认样式 */
    val leadingIconStyle: ListItemIconStyle,
    val trailingIconStyle: ListItemIconStyle,

    ) {

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
        contentPadding: ListItemContentPaddingValues? = null,

        leadingPadding: PaddingValues? = null,
        /* 可以给头部视图设置确切的尺寸，也可以使用leadingPercent设定占宽度的比例 */
        leadingSize: DpSize? = null,
        leadingPercent: Float? = null,

        bodyPadding: PaddingValues? = null,
        bodyItemSpace: Dp? = null, /* overline,headline,supporting的间隔 */
        bodyPercent: Float? = null,

        trailingPadding: PaddingValues? = null,
        trailingSize: DpSize? = null,
        trailingPercent: Float? = null,
        /* 各要素尺寸 */

        /* overline,headline,supporting的文本样式 */
        overlineTextStyle: TextStyle? = null,
        overlineColor: Color? = null,
        disabledOverlineColor: Color? = null,

        headlineTextStyle: TextStyle? = null,
        headlineColor: Color? = null,
        disabledHeadlineColor: Color? = null,

        supportingTextStyle: TextStyle? = null,
        supportingTextColor: Color? = null,
        disabledSupportingTextColor: Color? = null,
        /* overline,headline,supporting的文本样式 */

        /* Icon的默认样式 */
        leadingIconStyle: ListItemIconStyle? = null,
        trailingIconStyle: ListItemIconStyle? = null,
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
            bodyItemSpace = bodyItemSpace ?: this.bodyItemSpace,
            bodyPercent = bodyPercent ?: this.bodyPercent,
            trailingPadding = trailingPadding ?: this.trailingPadding,
            trailingSize = trailingSize ?: this.trailingSize,
            trailingPercent = trailingPercent ?: this.trailingPercent,
            overlineTextStyle = overlineTextStyle ?: this.overlineTextStyle,
            overlineColor = overlineColor invalidUse { this.overlineColor },
            disabledOverlineColor = disabledOverlineColor invalidUse { this.disabledOverlineColor },
            headlineTextStyle = headlineTextStyle ?: this.headlineTextStyle,
            headlineColor = headlineColor invalidUse { this.headlineColor },
            disabledHeadlineColor = disabledHeadlineColor invalidUse { this.disabledHeadlineColor },
            supportingTextStyle = supportingTextStyle ?: this.supportingTextStyle,
            supportingTextColor = supportingTextColor invalidUse { this.supportingTextColor },
            disabledSupportingTextColor = disabledSupportingTextColor invalidUse { this.disabledSupportingTextColor },
            leadingIconStyle = leadingIconStyle ?: this.leadingIconStyle,
            trailingIconStyle = trailingIconStyle ?: this.trailingIconStyle,
        )
    }

    internal fun contentPadding(
        listItemType: ListItemType = ListItemType.Companion.OneLine,
    ): PaddingValues {
        return when (listItemType) {
            ListItemType.Companion.OneLine -> contentPadding.oneline
            ListItemType.Companion.TwoLine -> contentPadding.twoline
            ListItemType.Companion.ThreeLine -> contentPadding.threeline
            else -> contentPadding.oneline
        }
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
    internal fun overlineColor(enabled: Boolean): Color =
        if (enabled) overlineColor else disabledOverlineColor

    /** The color of this [ListItem]'s supporting text based on enabled state */
    @Stable
    internal fun supportingColor(enabled: Boolean): Color =
        if (enabled) supportingTextColor else disabledSupportingTextColor

    /** The color of this [ListItem]'s trailing content based on enabled state */
    @Stable
    internal fun trailingIconColor(enabled: Boolean): Color =
        if (enabled) trailingIconStyle.contentColor else trailingIconStyle.disabledContentColor
}

/**
 * icon、text都默认使用ContentColor
 */
data class ListItemIconStyle(
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
            disabledContentColor: Color = ListItemTokens.ItemLeadingAvatarColor.value.copy(
                alpha = ListItemTokens.ItemDisabledLeadingIconOpacity
            ),
            shape: Shape = ListItemTokens.ItemLeadingAvatarShape.value,
            iconSize: Dp = ListItemTokens.ItemLeadingAvatarSize,
            contentColor: Color = ListItemTokens.ItemLeadingAvatarLabelColor.value,
            backgroundColor: Color = ListItemTokens.ItemLeadingAvatarColor.value,
            textStyle: TextStyle = ListItemTokens.ItemLeadingAvatarLabelFont.value,
        ): ListItemIconStyle {
            return ListItemIconStyle(
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
            contentColor: Color = ListItemTokens.ItemLeadingIconColor.value,
            backgroundColor: Color = Color.Transparent,
            textStyle: TextStyle = ListItemTokens.ItemLeadingAvatarLabelFont.value,
            iconSize: Dp = ListItemTokens.ItemLeadingIconSize,
            disabledIconColor: Color = ListItemTokens.ItemDisabledLeadingIconColor.value.copy(
                alpha = ListItemTokens.ItemDisabledLeadingIconOpacity
            ),
        ): ListItemIconStyle {
            return ListItemIconStyle(
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
            contentColor: Color = ListItemTokens.ItemTrailingIconColor.value,
            backgroundColor: Color = Color.Transparent,
            textStyle: TextStyle = ListItemTokens.ItemTrailingSupportingTextFont.value,
            iconSize: Dp = ListItemTokens.ItemTrailingIconSize,
            disabledIconColor: Color = ListItemTokens.ItemDisabledTrailingIconColor.value.copy(
                alpha = ListItemTokens.ItemDisabledTrailingIconOpacity
            ),
        ): ListItemIconStyle {
            return ListItemIconStyle(
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

/**
 * 如果只希望使用一种边距，则只设置oneline即可，其他两个将使用oneline的设置
 */
@Immutable
data class ListItemContentPaddingValues(
    val oneline: PaddingValues,
    val twoline: PaddingValues = oneline,
    val threeline: PaddingValues = oneline,
)
