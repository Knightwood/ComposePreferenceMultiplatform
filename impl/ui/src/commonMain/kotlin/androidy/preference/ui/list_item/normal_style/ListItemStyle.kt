package androidy.preference.ui.list_item.normal_style

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ListItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidy.preference.ui.basic.invalidUse
import androidy.preference.ui.interactive.StateColors
import androidy.preference.ui.interactive.StateElevation
import androidy.preference.ui.interactive.StateShapes
import androidy.preference.ui.list_item.ListItemType
import androidy.preference.ui.list_item.expressive_style.InteractiveListBottomPadding
import androidy.preference.ui.list_item.expressive_style.InteractiveListEndPadding
import androidy.preference.ui.list_item.expressive_style.InteractiveListStartPadding
import androidy.preference.ui.list_item.expressive_style.InteractiveListTopPadding
import androidy.preference.ui.list_item.m3_tokens.value


@Immutable
class ListItemStyle
constructor(
    /* 整体样式 */
    /*----------------------------------------------*/
    val containerShape: StateShapes,
    val containerColor: StateColors,
    val containerTonalElevation: StateElevation = StateElevation(ListItemTokens.ItemContainerElevation),
    val containerShadowElevation: StateElevation = StateElevation(ListItemTokens.ItemContainerShadowElevation),

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
    val alignment: ListItemContentAlignment = ListItemContentAlignment(
        oneline = Alignment.CenterVertically,
        threeline = Alignment.Top,
    ),
    /* 整体样式 */

    /**
     * 我们只设置内边距。外边距效果让用户自己来实现。
     * 在单行时垂直边距应为8dp
     * 当多行或者item比较臃肿时，垂直边距应为12dp
     * 默认为10dp
     */
    val contentPadding: ListItemContentPaddingValues = ListItemContentPaddingValues.default(),

    val leadingPadding: PaddingValues = PaddingValues(end = ListItemTokens.LeadingContentEndPadding),
    /**
     * 可以给LeadingIcon slot设置确切的尺寸，也可以使用leadingPercent设定占宽度的比例
     *
     * 此项配置与ListItemIconStyle中的size并不等同。
     */
    val leadingSize: DpSize = DpSize.Unspecified,
    val leadingPercent: Float? = null,

    val bodyPadding: PaddingValues = PaddingValues(0.dp),
    val bodyItemSpace: Dp? = null, /* overline,headline,supporting的间隔 */
    val bodyPercent: Float = 1f,

    val trailingPadding: PaddingValues = PaddingValues(start = ListItemTokens.TrailingContentStartPadding),
    /**
     * 参考leadingSize
     */
    val trailingSize: DpSize = DpSize.Unspecified,
    val trailingPercent: Float? = null,
    /* 各要素尺寸 */

    /* overline,headline,supporting的文本样式 */
    val overlineTextStyle: TextStyle,
    val overlineColor: StateColors,

    val headlineTextStyle: TextStyle,
    val headlineColor: StateColors,//作为默认颜色，即contentColor

    val supportingTextStyle: TextStyle,
    val supportingTextColor: StateColors,
    /* overline,headline,supporting的文本样式 */

    /* Icon的默认样式 */
    val leadingIconStyle: ListItemIconStyle,
    val trailingIconStyle: ListItemIconStyle,

    ) {
    constructor(
        containerShape: Shape,
        containerColor: Color,
        containerTonalElevation: Dp = ListItemTokens.ItemContainerElevation,
        containerShadowElevation: Dp = ListItemTokens.ItemContainerShadowElevation,
        containerBorder: BorderStroke? = null,
        containerHeightMin: Dp = ListItemTokens.ItemContainerHeightMin,
        containerHeightMax: Dp = ListItemTokens.ItemContainerHeightMax,
        alignment: ListItemContentAlignment = ListItemContentAlignment(
            oneline = Alignment.CenterVertically,
            threeline = Alignment.Top,
        ),
        contentPadding: ListItemContentPaddingValues = ListItemContentPaddingValues.default(),
        leadingPadding: PaddingValues = PaddingValues(end = ListItemTokens.LeadingContentEndPadding),
        leadingSize: DpSize = DpSize.Unspecified,
        leadingPercent: Float? = null,
        bodyPadding: PaddingValues = PaddingValues(0.dp),
        bodyItemSpace: Dp? = null,
        bodyPercent: Float = 1f,
        trailingPadding: PaddingValues = PaddingValues(start = ListItemTokens.TrailingContentStartPadding),
        trailingSize: DpSize = DpSize.Unspecified,
        trailingPercent: Float? = null,
        overlineTextStyle: TextStyle,
        overlineColor: Color,
        disabledOverlineColor: Color,
        headlineTextStyle: TextStyle,
        headlineColor: Color,
        disabledHeadlineColor: Color,
        supportingTextStyle: TextStyle,
        supportingTextColor: Color,
        disabledSupportingTextColor: Color,
        leadingIconStyle: ListItemIconStyle,
        trailingIconStyle: ListItemIconStyle,
    ) : this(
        containerShape = StateShapes(containerShape),
        containerColor = StateColors(containerColor),
        containerTonalElevation = StateElevation(containerTonalElevation),
        containerShadowElevation = StateElevation(containerShadowElevation),
        containerBorder = containerBorder,
        containerHeightMin = containerHeightMin,
        containerHeightMax = containerHeightMax,
        alignment = alignment,
        contentPadding = contentPadding,
        leadingPadding = leadingPadding,
        leadingSize = leadingSize,
        leadingPercent = leadingPercent,
        bodyPadding = bodyPadding,
        bodyItemSpace = bodyItemSpace,
        bodyPercent = bodyPercent,
        trailingPadding = trailingPadding,
        trailingSize = trailingSize,
        trailingPercent = trailingPercent,
        overlineTextStyle = overlineTextStyle,
        overlineColor = StateColors(overlineColor, disabledOverlineColor),
        headlineTextStyle = headlineTextStyle,
        headlineColor = StateColors(headlineColor, disabledHeadlineColor),
        supportingTextStyle = supportingTextStyle,
        supportingTextColor = StateColors(supportingTextColor, disabledSupportingTextColor),
        leadingIconStyle = leadingIconStyle,
        trailingIconStyle = trailingIconStyle,
    )

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
        alignment: ListItemContentAlignment? = null,
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
            containerShape = containerShape ?: this.containerShape.shape,
            containerColor = containerColor invalidUse { this.containerColor.enabledColor },
            containerTonalElevation = containerTonalElevation ?: this.containerTonalElevation.elevation,
            containerShadowElevation = containerShadowElevation ?: this.containerShadowElevation.elevation,
            containerBorder = containerBorder ?: this.containerBorder,
            containerHeightMin = containerHeightMin ?: this.containerHeightMin,
            containerHeightMax = containerHeightMax ?: this.containerHeightMax,
            alignment = alignment ?: this.alignment,
            contentPadding = contentPadding ?: this.contentPadding,
            leadingPadding = leadingPadding ?: this.leadingPadding,
            leadingSize = leadingSize invalidUse { this.leadingSize },
            leadingPercent = leadingPercent ?: this.leadingPercent,
            bodyPadding = bodyPadding ?: this.bodyPadding,
            bodyItemSpace = bodyItemSpace ?: this.bodyItemSpace,
            bodyPercent = bodyPercent ?: this.bodyPercent,
            trailingPadding = trailingPadding ?: this.trailingPadding,
            trailingSize = trailingSize invalidUse { this.trailingSize },
            trailingPercent = trailingPercent ?: this.trailingPercent,
            overlineTextStyle = overlineTextStyle ?: this.overlineTextStyle,
            overlineColor = overlineColor invalidUse { this.overlineColor.enabledColor },
            disabledOverlineColor = disabledOverlineColor invalidUse { this.overlineColor.disabledColor },
            headlineTextStyle = headlineTextStyle ?: this.headlineTextStyle,
            headlineColor = headlineColor invalidUse { this.headlineColor.enabledColor },
            disabledHeadlineColor = disabledHeadlineColor invalidUse { this.headlineColor.disabledColor },
            supportingTextStyle = supportingTextStyle ?: this.supportingTextStyle,
            supportingTextColor = supportingTextColor invalidUse { this.supportingTextColor.enabledColor },
            disabledSupportingTextColor = disabledSupportingTextColor invalidUse { this.supportingTextColor.disabledColor },
            leadingIconStyle = leadingIconStyle ?: this.leadingIconStyle,
            trailingIconStyle = trailingIconStyle ?: this.trailingIconStyle,
        )
    }

    internal fun alignment(listItemType: ListItemType): Alignment.Vertical {
        return when (listItemType) {
            ListItemType.Companion.OneLine -> alignment.oneline
            ListItemType.Companion.TwoLine -> alignment.twoline
            ListItemType.Companion.ThreeLine -> alignment.threeline
            else -> alignment.oneline
        }
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
        return containerColor.enabledColor
    }

    /** The color of this [ListItem]'s headline text based on enabled state */
    @Stable
    internal fun headlineColor(enabled: Boolean): Color {
        return headlineColor.get(enabled)
    }

    /** The color of this [ListItem]'s leading content based on enabled state */
    @Stable
    internal fun leadingIconColor(enabled: Boolean): Color = leadingIconStyle.stateColors.get(enabled)

    /** The color of this [ListItem]'s overline text based on enabled state */
    @Stable
    internal fun overlineColor(enabled: Boolean): Color = overlineColor.get(enabled)

    /** The color of this [ListItem]'s supporting text based on enabled state */
    @Stable
    internal fun supportingColor(enabled: Boolean): Color = supportingTextColor.get(enabled)

    /** The color of this [ListItem]'s trailing content based on enabled state */
    @Stable
    internal fun trailingIconColor(enabled: Boolean): Color = trailingIconStyle.stateColors.get(enabled)

    companion object
}

/**
 * icon、text都默认使用ContentColor
 */
data class ListItemIconStyle(
    val shape: Shape = RectangleShape,
    val backgroundColor: Color = Color.Transparent,
    val textStyle: TextStyle,
    val size: DpSize,
    val stateColors: StateColors,
) {
    constructor(
        shape: Shape = RectangleShape,
        backgroundColor: Color = Color.Transparent,
        textStyle: TextStyle,
        size: DpSize = DpSize.Unspecified,
        contentColor: Color,
        disabledContentColor: Color = contentColor,
    ) : this(
        shape = shape,
        backgroundColor = backgroundColor,
        textStyle = textStyle,
        size = size,
        stateColors = StateColors(
            contentColor,
            disabledContentColor
        )
    )

    constructor(
        shape: Shape = RectangleShape,
        backgroundColor: Color = Color.Transparent,
        textStyle: TextStyle,
        size: DpSize = DpSize.Unspecified,
        contentColor: Color,
        disabledContentColor: Color = contentColor,
        selectedContentColor: Color = contentColor,
        draggedContentColor: Color = contentColor,
    ) : this(
        shape = shape,
        backgroundColor = backgroundColor,
        textStyle = textStyle,
        size = size,
        stateColors = StateColors(
            contentColor,
            disabledContentColor,
            selectedContentColor,
            draggedContentColor
        )
    )

    companion object {
        @Composable
        fun leadingAvatarStyle(
            contentColor: Color = ListItemTokens.ItemLeadingAvatarLabelColor.value,
            disabledContentColor: Color = ListItemTokens.ItemLeadingAvatarLabelColor.value.copy(
                alpha = ListItemTokens.ItemDisabledLeadingIconOpacity
            ),
            backgroundColor: Color = ListItemTokens.ItemLeadingAvatarColor.value,
            shape: Shape = ListItemTokens.ItemLeadingAvatarShape.value,
            size: DpSize = DpSize(ListItemTokens.ItemLeadingAvatarSize),
            textStyle: TextStyle = ListItemTokens.ItemLeadingAvatarLabelFont.value,
        ): ListItemIconStyle {
            return ListItemIconStyle(
                shape = shape,
                backgroundColor = backgroundColor,
                textStyle = textStyle,
                size = size,
                contentColor = contentColor,
                disabledContentColor = disabledContentColor
            )
        }

        @Composable
        fun leadingImageStyle(
            contentColor: Color = ListItemTokens.ItemLeadingAvatarLabelColor.value,
            disabledContentColor: Color = ListItemTokens.ItemLeadingAvatarLabelColor.value.copy(
                alpha = ListItemTokens.ItemDisabledLeadingIconOpacity
            ),
            backgroundColor: Color = Color.Transparent,
            shape: Shape = ListItemTokens.ItemLeadingImageShape.value,
            size: DpSize = DpSize(
                ListItemTokens.ItemLeadingImageWidth,
                ListItemTokens.ItemLeadingImageHeight,
            ),
            textStyle: TextStyle = ListItemTokens.ItemLeadingAvatarLabelFont.value,
        ): ListItemIconStyle {
            return ListItemIconStyle(
                shape = shape,
                backgroundColor = backgroundColor,
                textStyle = textStyle,
                size = size,
                contentColor = contentColor,
                disabledContentColor = disabledContentColor
            )
        }

        @Composable
        fun leadingVideoStyle(
            small: Boolean = true,
            contentColor: Color = ListItemTokens.ItemLeadingAvatarLabelColor.value,
            disabledContentColor: Color = ListItemTokens.ItemLeadingAvatarLabelColor.value.copy(
                alpha = ListItemTokens.ItemDisabledLeadingIconOpacity
            ),
            backgroundColor: Color = Color.Transparent,
            shape: Shape = ListItemTokens.ItemLeadingVideoShape.value,
            size: DpSize = if (small) {
                DpSize(
                    ListItemTokens.ItemSmallLeadingVideoWidth,
                    ListItemTokens.ItemSmallLeadingVideoHeight,
                )
            } else {
                DpSize(
                    ListItemTokens.ItemLargeLeadingVideoWidth,
                    ListItemTokens.ItemLargeLeadingVideoHeight,
                )
            },
            textStyle: TextStyle = ListItemTokens.ItemLeadingAvatarLabelFont.value,
        ): ListItemIconStyle {
            return ListItemIconStyle(
                shape = shape,
                backgroundColor = backgroundColor,
                textStyle = textStyle,
                size = size,
                contentColor = contentColor,
                disabledContentColor = disabledContentColor
            )
        }

        @Composable
        fun leadingIconStyle(
            contentColor: Color = ListItemTokens.ItemLeadingIconColor.value,
            disabledContentColor: Color = ListItemTokens.ItemDisabledLeadingIconColor.value.copy(
                alpha = ListItemTokens.ItemDisabledLeadingIconOpacity
            ),
            backgroundColor: Color = Color.Transparent,
            shape: Shape = RectangleShape,
            size: DpSize = DpSize(ListItemTokens.ItemLeadingIconSize),
            textStyle: TextStyle = ListItemTokens.ItemLeadingAvatarLabelFont.value,
        ): ListItemIconStyle {
            return ListItemIconStyle(
                shape = shape,
                backgroundColor = backgroundColor,
                size = size,
                contentColor = contentColor,
                textStyle = textStyle,
                disabledContentColor = disabledContentColor,
            )
        }

        /**
         * @param size 通常不给trailIcon限制尺寸，避免无法放下Button、text等
         * 若需要设置size，尺寸可以参考[BasicListItemTokens.ItemTrailingIconSize]
         */
        @Composable
        fun trailingIconStyle(
            contentColor: Color = ListItemTokens.ItemTrailingIconColor.value,
            disabledIconColor: Color = ListItemTokens.ItemDisabledTrailingIconColor.value.copy(
                alpha = ListItemTokens.ItemDisabledTrailingIconOpacity
            ),
            backgroundColor: Color = Color.Transparent,
            shape: Shape = RectangleShape,
            size: DpSize = DpSize.Unspecified,
            textStyle: TextStyle = ListItemTokens.ItemTrailingSupportingTextFont.value,
        ): ListItemIconStyle {
            return ListItemIconStyle(
                shape = shape,
                backgroundColor = backgroundColor,
                size = size,
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
) {
    operator fun invoke(): PaddingValues {
        return oneline
    }

    internal operator fun invoke(type: ListItemType): PaddingValues {
        return when (type) {
            ListItemType.OneLine -> oneline
            ListItemType.TwoLine -> twoline
            ListItemType.ThreeLine -> threeline
            else -> oneline
        }
    }

    companion object {
        fun default() = ListItemContentPaddingValues(
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
        )

        fun expressiveDefaults() = ListItemContentPaddingValues(
            oneline = PaddingValues(
                start = InteractiveListStartPadding,
                end = InteractiveListEndPadding,
                top = InteractiveListTopPadding,
                bottom = InteractiveListBottomPadding
            )
        )
    }
}

/**
 * 主轴对齐方式
 * @param enableAdjustAlignment 是否允许在supporting高度比较高时使用三行的alignment。
 */
@Immutable
data class ListItemContentAlignment(
    val oneline: Alignment.Vertical,
    val twoline: Alignment.Vertical = oneline,
    val threeline: Alignment.Vertical = oneline,
    val enableAdjustAlignment: Boolean = true,
)

@Stable
fun DpSize(wh: Dp): DpSize = DpSize(wh, wh)
