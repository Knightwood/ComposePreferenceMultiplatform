package androidy.ui.material3.listitem.normal_style

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
import androidy.ui.material3.listitem.basic.invalidUse
import androidy.ui.material3.listitem.interactive.StateColors
import androidy.ui.material3.listitem.interactive.StateElevation
import androidy.ui.material3.listitem.interactive.StateShapes
import androidy.ui.material3.listitem.ListItemType
import androidy.ui.material3.listitem.expressive_style.BasicExpressiveListItemTokens
import androidy.ui.material3.listitem.expressive_style.ExpressiveListItemTokens
import androidy.ui.material3.listitem.expressive_style.InteractiveListBottomPadding
import androidy.ui.material3.listitem.expressive_style.InteractiveListEndPadding
import androidy.ui.material3.listitem.expressive_style.InteractiveListStartPadding
import androidy.ui.material3.listitem.expressive_style.InteractiveListTopPadding
import androidy.ui.material3.listitem.m3_tokens.value


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

    //全参数平展开的构造函数
    constructor (
        containerShape: Shape,
        containerSelectedShape: Shape = containerShape,
        containerPressedShape: Shape = containerShape,
        containerFocusedShape: Shape = containerShape,
        containerHoveredShape: Shape = containerShape,
        containerDraggedShape: Shape = containerShape,

        containerColor: Color,
        disabledContainerColor: Color,
        selectedContainerColor: Color = containerColor,
        draggedContainerColor: Color = containerColor,

        containerTonalElevation: Dp,
        containerTonalDraggedElevation: Dp = containerTonalElevation,

        containerShadowElevation: Dp,
        containerShadowDraggedElevation: Dp = containerShadowElevation,

        containerBorder: BorderStroke?,
        containerHeightMin: Dp,
        containerHeightMax: Dp,
        alignment: ListItemContentAlignment,
        contentPadding: ListItemContentPaddingValues,
        leadingPadding: PaddingValues,
        leadingSize: DpSize = DpSize.Unspecified,
        leadingPercent: Float? = null,
        bodyPadding: PaddingValues,
        bodyItemSpace: Dp? = null,
        bodyPercent: Float = 1f,
        trailingPadding: PaddingValues,
        trailingSize: DpSize = DpSize.Unspecified,
        trailingPercent: Float? = null,

        overlineTextStyle: TextStyle,
        overlineContentColor: Color,
        disabledOverlineContentColor: Color,
        selectedOverlineContentColor: Color = overlineContentColor,
        draggedOverlineContentColor: Color = overlineContentColor,

        headlineTextStyle: TextStyle,
        headlineContentColor: Color,
        disabledHeadlineContentColor: Color,
        selectedHeadlineContentColor: Color = headlineContentColor,
        draggedHeadlineContentColor: Color = headlineContentColor,

        supportingTextStyle: TextStyle,
        supportingContentColor: Color,
        disabledSupportingContentColor: Color,
        selectedSupportingContentColor: Color = supportingContentColor,
        draggedSupportingContentColor: Color = supportingContentColor,

        leadingIconStyle: ListItemIconStyle,
        trailingIconStyle: ListItemIconStyle,
    ) : this(
        containerShape = StateShapes(
            containerShape,
            containerSelectedShape,
            containerPressedShape,
            containerFocusedShape,
            containerHoveredShape,
            containerDraggedShape
        ),
        containerColor = StateColors(
            containerColor,
            disabledColor = disabledContainerColor,
            selectedColor = selectedContainerColor,
            draggedColor = draggedContainerColor
        ),
        containerTonalElevation = StateElevation(containerTonalElevation, containerTonalDraggedElevation),
        containerShadowElevation = StateElevation(containerShadowElevation, containerShadowDraggedElevation),
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
        overlineColor = StateColors(
            overlineContentColor, disabledOverlineContentColor,
            selectedOverlineContentColor, draggedOverlineContentColor
        ),
        headlineTextStyle = headlineTextStyle,
        headlineColor = StateColors(
            headlineContentColor, disabledHeadlineContentColor,
            selectedHeadlineContentColor, draggedHeadlineContentColor
        ),
        supportingTextStyle = supportingTextStyle,
        supportingTextColor = StateColors(
            supportingContentColor, disabledSupportingContentColor,
            selectedSupportingContentColor, draggedSupportingContentColor
        ),
        leadingIconStyle = leadingIconStyle,
        trailingIconStyle = trailingIconStyle,
    )

    //全参数平展开的copy函数
    fun copy(
        /* 整体样式 */
        /*----------------------------------------------*/
        containerShape: Shape? = null,
        containerSelectedShape: Shape? = null,
        containerPressedShape: Shape? = null,
        containerFocusedShape: Shape? = null,
        containerHoveredShape: Shape? = null,
        containerDraggedShape: Shape? = null,

        containerColor: Color? = null,
        disabledContainerColor: Color? = null,
        selectedContainerColor: Color? = null,
        draggedContainerColor: Color? = null,

        containerTonalElevation: Dp? = null,
        containerTonalDraggedElevation: Dp? = null,

        containerShadowElevation: Dp? = null,
        containerShadowDraggedElevation: Dp? = null,

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
        overlineContentColor: Color? = null,
        disabledOverlineContentColor: Color? = null,
        selectedOverlineContentColor: Color? = null,
        draggedOverlineContentColor: Color? = null,

        headlineTextStyle: TextStyle? = null,
        headlineContentColor: Color? = null,
        disabledHeadlineContentColor: Color? = null,
        selectedHeadlineContentColor: Color? = null,
        draggedHeadlineContentColor: Color? = null,

        supportingTextStyle: TextStyle? = null,
        supportingContentColor: Color? = null,
        disabledSupportingContentColor: Color? = null,
        selectedSupportingContentColor: Color? = null,
        draggedSupportingContentColor: Color? = null,
        /* overline,headline,supporting的文本样式 */

        /* Icon的默认样式 */
        leadingIconStyle: ListItemIconStyle? = null,
        trailingIconStyle: ListItemIconStyle? = null,
    ): ListItemStyle {
        return ListItemStyle(
            containerShape = containerShape ?: this.containerShape.shape,
            containerSelectedShape = containerSelectedShape ?: this.containerShape.selectedShape,
            containerPressedShape = containerPressedShape ?: this.containerShape.pressedShape,
            containerFocusedShape = containerFocusedShape ?: this.containerShape.focusedShape,
            containerHoveredShape = containerHoveredShape ?: this.containerShape.hoveredShape,
            containerDraggedShape = containerDraggedShape ?: this.containerShape.draggedShape,

            containerColor = containerColor invalidUse { this.containerColor.enabledColor },
            disabledContainerColor = disabledContainerColor invalidUse { this.containerColor.disabledColor },
            selectedContainerColor = selectedContainerColor invalidUse { this.containerColor.selectedColor },
            draggedContainerColor = draggedContainerColor invalidUse { this.containerColor.draggedColor },

            containerTonalElevation = containerTonalElevation ?: this.containerTonalElevation.elevation,
            containerTonalDraggedElevation = containerTonalDraggedElevation
                ?: this.containerTonalElevation.draggedElevation,

            containerShadowElevation = containerShadowElevation ?: this.containerShadowElevation.elevation,
            containerShadowDraggedElevation = containerShadowDraggedElevation
                ?: this.containerShadowElevation.draggedElevation,

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
            overlineContentColor = overlineContentColor invalidUse { this.overlineColor.enabledColor },
            disabledOverlineContentColor = disabledOverlineContentColor invalidUse { this.overlineColor.disabledColor },
            selectedOverlineContentColor = selectedOverlineContentColor invalidUse { this.overlineColor.selectedColor },
            draggedOverlineContentColor = draggedOverlineContentColor invalidUse { this.overlineColor.draggedColor },

            headlineTextStyle = headlineTextStyle ?: this.headlineTextStyle,
            headlineContentColor = headlineContentColor invalidUse { this.headlineColor.enabledColor },
            disabledHeadlineContentColor = disabledHeadlineContentColor invalidUse { this.headlineColor.disabledColor },
            selectedHeadlineContentColor = selectedHeadlineContentColor invalidUse { this.headlineColor.selectedColor },
            draggedHeadlineContentColor = draggedHeadlineContentColor invalidUse { this.headlineColor.draggedColor },

            supportingTextStyle = supportingTextStyle ?: this.supportingTextStyle,
            supportingContentColor = supportingContentColor invalidUse { this.supportingTextColor.enabledColor },
            disabledSupportingContentColor = disabledSupportingContentColor invalidUse { this.supportingTextColor.disabledColor },
            selectedSupportingContentColor = selectedSupportingContentColor invalidUse { this.supportingTextColor.selectedColor },
            draggedSupportingContentColor = draggedSupportingContentColor invalidUse { this.supportingTextColor.draggedColor },

            leadingIconStyle = leadingIconStyle ?: this.leadingIconStyle,
            trailingIconStyle = trailingIconStyle ?: this.trailingIconStyle,
        )
    }

    /**
     * 非平展参数的copy函数
     */
    fun copy(
        /* 整体样式 */
        /*----------------------------------------------*/
        containerShape: StateShapes? = null,
        containerColor: StateColors? = null,
        containerTonalElevation: StateElevation? = null,
        containerShadowElevation: StateElevation? = null,
        containerBorder: BorderStroke? = null,
        containerHeightMin: Dp? = null,
        containerHeightMax: Dp? = null,
        alignment: ListItemContentAlignment? = null,
        contentPadding: ListItemContentPaddingValues? = null,

        leadingPadding: PaddingValues? = null,
        leadingSize: DpSize? = null,
        leadingPercent: Float? = null,
        bodyPadding: PaddingValues? = null,
        bodyItemSpace: Dp? = null, /* overline,headline,supporting的间隔 */
        bodyPercent: Float? = null,
        trailingPadding: PaddingValues? = null,
        trailingSize: DpSize? = null,
        trailingPercent: Float? = null,

        overlineTextStyle: TextStyle? = null,
        overlineColor: StateColors? = null,

        headlineTextStyle: TextStyle? = null,
        headlineColor: StateColors? = null,

        supportingTextStyle: TextStyle? = null,
        supportingColor: StateColors? = null,

        leadingIconStyle: ListItemIconStyle? = null,
        trailingIconStyle: ListItemIconStyle? = null,
    ): ListItemStyle {
        return ListItemStyle(
            containerShape = containerShape ?: this.containerShape,
            containerColor = containerColor ?: this.containerColor,
            containerTonalElevation = containerTonalElevation ?: this.containerTonalElevation,
            containerShadowElevation = containerShadowElevation ?: this.containerShadowElevation,
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
            overlineColor = overlineColor ?: this.overlineColor,
            headlineTextStyle = headlineTextStyle ?: this.headlineTextStyle,
            headlineColor = headlineColor ?: this.headlineColor,
            supportingTextStyle = supportingTextStyle ?: this.supportingTextStyle,
            supportingTextColor = supportingColor ?: this.supportingTextColor,
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
    internal fun containerColor(enabled: Boolean): Color {
        return containerColor.get(enabled)
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
        //<editor-fold desc="普通样式">


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
        //</editor-fold>

        //<editor-fold desc="expressive样式 "> // 实际上与普通样式相比除了形状改成圆角外没有其他变化
        @Composable
        fun expressiveLeadingAvatarStyle(
            contentColor: Color = ExpressiveListItemTokens.ItemLeadingAvatarLabelColor.value,
            disabledContentColor: Color = ExpressiveListItemTokens.ItemLeadingAvatarLabelColor.value.copy(
                alpha = ExpressiveListItemTokens.ItemDisabledLeadingIconOpacity
            ),
            backgroundColor: Color = ExpressiveListItemTokens.ItemLeadingAvatarColor.value,
            shape: Shape = ExpressiveListItemTokens.ItemLeadingAvatarShape.value,
            size: DpSize = DpSize(ExpressiveListItemTokens.ItemLeadingAvatarSize),
            textStyle: TextStyle = ExpressiveListItemTokens.ItemLeadingAvatarLabelFont.value,
            selectedContentColor: Color = contentColor,
            draggedContentColor: Color = contentColor,
        ) = ListItemIconStyle(
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

        @Composable
        fun expressiveLeadingImageStyle(
            contentColor: Color = ExpressiveListItemTokens.ItemLeadingAvatarLabelColor.value,
            disabledContentColor: Color = ExpressiveListItemTokens.ItemLeadingAvatarLabelColor.value.copy(
                alpha = ExpressiveListItemTokens.ItemDisabledLeadingIconOpacity
            ),
            backgroundColor: Color = Color.Transparent,
            shape: Shape = ExpressiveListItemTokens.ItemLeadingImageExpressiveShape.value,
            size: DpSize = DpSize(
                ExpressiveListItemTokens.ItemLeadingImageWidth,
                ExpressiveListItemTokens.ItemLeadingImageHeight,
            ),
            textStyle: TextStyle = ExpressiveListItemTokens.ItemLeadingAvatarLabelFont.value,
            selectedContentColor: Color = contentColor,
            draggedContentColor: Color = contentColor,
        ) = ListItemIconStyle(
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

        @Composable
        fun expressiveLeadingVideoStyle(
            small: Boolean = true,
            contentColor: Color = ExpressiveListItemTokens.ItemLeadingAvatarLabelColor.value,
            disabledContentColor: Color = ExpressiveListItemTokens.ItemLeadingAvatarLabelColor.value.copy(
                alpha = ExpressiveListItemTokens.ItemDisabledLeadingIconOpacity
            ),
            backgroundColor: Color = Color.Transparent,
            shape: Shape = ExpressiveListItemTokens.ItemLeadingVideoShape.value,
            size: DpSize = if (small) {
                DpSize(
                    ExpressiveListItemTokens.ItemSmallLeadingVideoWidth,
                    ExpressiveListItemTokens.ItemSmallLeadingVideoHeight,
                )
            } else {
                DpSize(
                    ExpressiveListItemTokens.ItemLargeLeadingVideoWidth,
                    ExpressiveListItemTokens.ItemLargeLeadingVideoHeight,
                )
            },
            textStyle: TextStyle = ExpressiveListItemTokens.ItemLeadingAvatarLabelFont.value,
            selectedContentColor: Color = contentColor,
            draggedContentColor: Color = contentColor,
        ) = ListItemIconStyle(
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
        @Composable
        fun expressiveLeadingIconStyle(
            contentColor: Color = ExpressiveListItemTokens.ItemLeadingIconColor.value,
            disabledContentColor: Color = ExpressiveListItemTokens.ItemDisabledLeadingIconColor.value.copy(
                alpha = ExpressiveListItemTokens.ItemDisabledLeadingIconOpacity
            ),
            backgroundColor: Color = Color.Transparent,
            shape: Shape = RectangleShape,
            size: DpSize = DpSize(ExpressiveListItemTokens.ItemLeadingIconExpressiveSize),
            textStyle: TextStyle = ExpressiveListItemTokens.ItemLeadingAvatarLabelFont.value,
            selectedContentColor: Color = contentColor,
            draggedContentColor: Color = contentColor,
        ) = ListItemIconStyle(
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

        /**
         * @param size 通常不给trailIcon限制尺寸，避免无法放下Button、text等
         * 若需要设置size，尺寸可以参考[BasicExpressiveListItemTokens.ItemTrailingIconExpressiveSize]
         */
        @Composable
        fun expressiveTrailingIconStyle(
            contentColor: Color = ExpressiveListItemTokens.ItemTrailingIconColor.value,
            disabledContentColor: Color = ExpressiveListItemTokens.ItemDisabledTrailingIconColor.value.copy(
                alpha = ExpressiveListItemTokens.ItemDisabledTrailingIconOpacity
            ),
            backgroundColor: Color = Color.Transparent,
            shape: Shape = RectangleShape,
            size: DpSize = DpSize.Unspecified,
            textStyle: TextStyle = ExpressiveListItemTokens.ItemTrailingSupportingTextFont.value,
            selectedContentColor: Color = contentColor,
            draggedContentColor: Color = contentColor,
        ) = ListItemIconStyle(
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
        //</editor-fold>

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
