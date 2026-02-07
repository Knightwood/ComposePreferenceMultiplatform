package androidy.preference.ui.list_item.expressive_style

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidy.preference.ui.basic.invalidUse
import androidy.preference.ui.list_item.normal_style.ListItemContentAlignment
import androidy.preference.ui.list_item.normal_style.ListItemContentPaddingValues
import androidy.preference.ui.list_item.normal_style.ListItemIconStyle
import androidy.preference.ui.list_item.normal_style.ListItemStyle
import androidy.preference.ui.list_item.normal_style.ListItemTokens
import androidy.preference.ui.list_item.normal_style.StateColors
import androidy.preference.ui.list_item.normal_style.StateElevation
import androidy.preference.ui.list_item.normal_style.StateShapes

/**
 * 创建Expressive主题
 */
fun ExpressiveListItemStyle(
    containerShape: Shape,
    containerSelectedShape: Shape = RectangleShape,
    containerPressedShape: Shape = RectangleShape,
    containerFocusedShape: Shape = RectangleShape,
    containerHoveredShape: Shape = RectangleShape,
    containerDraggedShape: Shape = RectangleShape,

    containerColor: Color,
    disabledContainerColor: Color,
    selectedContainerColor: Color,
    draggedContainerColor: Color,

    containerTonalElevation: Dp = ListItemTokens.ItemContainerElevation,
    containerTonalDraggedElevation: Dp = ListItemTokens.ItemContainerElevation,

    containerShadowElevation: Dp = ListItemTokens.ItemContainerShadowElevation,
    containerShadowDraggedElevation: Dp = ListItemTokens.ItemContainerShadowElevation,

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
    overlineContentColor: Color,
    disabledOverlineContentColor: Color,
    selectedOverlineContentColor: Color,
    draggedOverlineContentColor: Color,

    headlineTextStyle: TextStyle,
    headlineContentColor: Color,
    disabledHeadlineContentColor: Color,
    selectedHeadlineContentColor: Color,
    draggedHeadlineContentColor: Color,

    supportingTextStyle: TextStyle,
    supportingContentColor: Color,
    disabledSupportingContentColor: Color,
    selectedSupportingTextColor: Color,
    draggedSupportingTextColor: Color,

    leadingIconStyle: ListItemIconStyle,
    trailingIconStyle: ListItemIconStyle,
) = ListItemStyle(
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
        selectedSupportingTextColor, draggedSupportingTextColor
    ),
    leadingIconStyle = leadingIconStyle,
    trailingIconStyle = trailingIconStyle,
)

fun ExpressiveListItemIconStyle(
    shape: Shape = RectangleShape,
    backgroundColor: Color = Color.Transparent,
    textStyle: TextStyle,
    size: DpSize = DpSize.Unspecified,
    contentColor: Color,
    disabledContentColor: Color = contentColor,
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
 * Expressive主题复制
 */
fun ListItemStyle.copy(
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
    selectedSupportingTextColor: Color? = null,
    draggedSupportingTextColor: Color? = null,
    /* overline,headline,supporting的文本样式 */

    /* Icon的默认样式 */
    leadingIconStyle: ListItemIconStyle? = null,
    trailingIconStyle: ListItemIconStyle? = null,
): ListItemStyle {
    return ExpressiveListItemStyle(
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
        containerTonalDraggedElevation = containerTonalDraggedElevation ?: this.containerTonalElevation.draggedElevation,
        
        containerShadowElevation = containerShadowElevation ?: this.containerShadowElevation.elevation,
        containerShadowDraggedElevation = containerShadowDraggedElevation ?: this.containerShadowElevation.draggedElevation,
        
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
        selectedSupportingTextColor = selectedSupportingTextColor invalidUse { this.supportingTextColor.selectedColor },
        draggedSupportingTextColor = draggedSupportingTextColor invalidUse { this.supportingTextColor.draggedColor },
        
        leadingIconStyle = leadingIconStyle ?: this.leadingIconStyle,
        trailingIconStyle = trailingIconStyle ?: this.trailingIconStyle,
    )
}
