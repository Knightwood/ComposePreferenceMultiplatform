package androidy.ui.material3.listitem.expressive_style

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidy.ui.material3.listitem.m3_tokens.value
import androidy.ui.material3.listitem.normal_style.ListItemColors
import androidy.ui.material3.listitem.normal_style.ListItemContentAlignment
import androidy.ui.material3.listitem.normal_style.ListItemContentPaddingValues
import androidy.ui.material3.listitem.normal_style.ListItemIconStyle
import androidy.ui.material3.listitem.normal_style.ListItemStyle
import androidy.ui.material3.listitem.normal_style.colors

object ExpressiveListItemDefaults {
    internal var cached: ListItemStyle? = null
    internal var colorsCache: ListItemColors? = null

    /** The default padding applied to all content within a list item. */
    val ContentPadding: PaddingValues = ListItemContentPaddingValues.expressiveDefaults()()

    /** The default elevation of a list item */
    val Elevation: Dp = ExpressiveListItemTokens.ItemContainerElevation

    /** The default shape of a list item */
    val shape: Shape
        @Composable @ReadOnlyComposable get() = ExpressiveListItemTokens.ItemContainerShape.value

    /** The container color of a list item */
    val containerColor: Color
        @Composable @ReadOnlyComposable get() = ExpressiveListItemTokens.ItemContainerColor.value

    /** The content color of a list item */
    val contentColor: Color
        @Composable @ReadOnlyComposable get() = ExpressiveListItemTokens.ItemLabelTextColor.value

    internal val expressiveDefaultStyle: ListItemStyle
        @Composable
        get() {
            return cached ?: ListItemStyle(
                containerShape = ExpressiveListItemTokens.ItemContainerExpressiveShape.value,
                containerSelectedShape = ExpressiveListItemTokens.ItemSelectedContainerExpressiveShape.value,
                containerPressedShape = ExpressiveListItemTokens.ItemPressedContainerExpressiveShape.value,
                containerFocusedShape = ExpressiveListItemTokens.ItemFocusedContainerExpressiveShape.value,
                containerHoveredShape = ExpressiveListItemTokens.ItemHoveredContainerExpressiveShape.value,
                containerDraggedShape = ExpressiveListItemTokens.ItemDraggedContainerExpressiveShape.value,

                containerColor = ExpressiveListItemTokens.ItemContainerColor.value,
                disabledContainerColor = ExpressiveListItemTokens.ItemContainerColor.value,
                selectedContainerColor = ExpressiveListItemTokens.ItemSelectedContainerColor.value,
                draggedContainerColor = ExpressiveListItemTokens.ItemContainerColor.value,

                containerTonalElevation = ExpressiveListItemTokens.ItemContainerElevation,
                containerTonalDraggedElevation = ExpressiveListItemTokens.ItemDraggedContainerElevation,

                containerShadowElevation = ExpressiveListItemTokens.ItemContainerElevation,
                containerShadowDraggedElevation = ExpressiveListItemTokens.ItemDraggedContainerElevation,

                containerBorder = null,
                containerHeightMin = ExpressiveListItemTokens.ItemOneLineContainerHeight,
                containerHeightMax = ExpressiveListItemTokens.ItemThreeLineContainerHeight,
                alignment = ListItemContentAlignment(
                    oneline = Alignment.CenterVertically,
                    threeline = Alignment.Top,
                ),
                contentPadding = ListItemContentPaddingValues.expressiveDefaults(),
                leadingPadding = PaddingValues(end = InteractiveListInternalSpacing),

                leadingSize = DpSize.Unspecified,
                leadingPercent = null,
                bodyPadding = PaddingValues(0.dp),
                bodyItemSpace = null,
                bodyPercent = 1f,
                trailingPadding = PaddingValues(start = InteractiveListInternalSpacing),
                trailingSize = DpSize.Unspecified,
                trailingPercent = null,

                overlineTextStyle = ExpressiveListItemTokens.ItemOverlineFont.value,
                overlineContentColor = ExpressiveListItemTokens.ItemOverlineColor.value,
                disabledOverlineContentColor = ExpressiveListItemTokens.ItemDisabledOverlineColor.value.copy(
                    alpha = ExpressiveListItemTokens.ItemDisabledOverlineOpacity
                ),
                selectedOverlineContentColor = ExpressiveListItemTokens.ItemSelectedOverlineColor.value,
                draggedOverlineContentColor = ExpressiveListItemTokens.ItemDraggedOverlineColor.value,

                headlineTextStyle = ExpressiveListItemTokens.ItemLabelTextFont.value,
                headlineContentColor = ExpressiveListItemTokens.ItemLabelTextColor.value,
                disabledHeadlineContentColor = ExpressiveListItemTokens.ItemDisabledLabelTextColor.value.copy(
                    alpha = ExpressiveListItemTokens.ItemDisabledLabelTextOpacity
                ),
                selectedHeadlineContentColor = ExpressiveListItemTokens.ItemSelectedLabelTextColor.value,
                draggedHeadlineContentColor = ExpressiveListItemTokens.ItemDraggedLabelTextColor.value,

                supportingTextStyle = ExpressiveListItemTokens.ItemSupportingTextFont.value,
                supportingContentColor = ExpressiveListItemTokens.ItemSupportingTextColor.value,
                disabledSupportingContentColor = ExpressiveListItemTokens.ItemDisabledSupportingTextColor.value.copy(
                    alpha = ExpressiveListItemTokens.ItemDisabledSupportingTextOpacity
                ),
                selectedSupportingContentColor = ExpressiveListItemTokens.ItemSelectedSupportingTextColor.value,
                draggedSupportingContentColor = ExpressiveListItemTokens.ItemDraggedSupportingTextColor.value,

                leadingIconStyle = ListItemIconStyle.Companion.expressiveLeadingIconStyle(),
                trailingIconStyle = ListItemIconStyle.Companion.expressiveTrailingIconStyle(),
            ).also { cached = it }
        }

    internal val expressiveDefaultShapes
        @Composable
        get() = this.expressiveDefaultStyle.containerShape

    internal val expressiveDefaultColors: ListItemColors
        @Composable
        get() {
            return colorsCache ?: expressiveDefaultStyle.colors().also {
                colorsCache = it
            }
        }

    @Composable
    fun style() = expressiveDefaultStyle

    @Composable
    fun style(
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
        val base = expressiveDefaultStyle
        return base.copy(
            containerShape = containerShape,
            containerSelectedShape = containerSelectedShape,
            containerPressedShape = containerPressedShape,
            containerFocusedShape = containerFocusedShape,
            containerHoveredShape = containerHoveredShape,
            containerDraggedShape = containerDraggedShape,

            containerColor = containerColor,
            disabledContainerColor = disabledContainerColor,
            selectedContainerColor = selectedContainerColor,
            draggedContainerColor = draggedContainerColor,

            containerTonalElevation = containerTonalElevation,
            containerTonalDraggedElevation = containerTonalDraggedElevation,

            containerShadowElevation = containerShadowElevation,
            containerShadowDraggedElevation = containerShadowDraggedElevation,

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
            overlineContentColor = overlineContentColor,
            disabledOverlineContentColor = disabledOverlineContentColor,
            selectedOverlineContentColor = selectedOverlineContentColor,
            draggedOverlineContentColor = draggedOverlineContentColor,

            headlineTextStyle = headlineTextStyle,
            headlineContentColor = headlineContentColor,
            disabledHeadlineContentColor = disabledHeadlineContentColor,
            selectedHeadlineContentColor = selectedHeadlineContentColor,
            draggedHeadlineContentColor = draggedHeadlineContentColor,

            supportingTextStyle = supportingTextStyle,
            supportingContentColor = supportingContentColor,
            disabledSupportingContentColor = disabledSupportingContentColor,
            selectedSupportingContentColor = selectedSupportingTextColor,
            draggedSupportingContentColor = draggedSupportingTextColor,

            leadingIconStyle = leadingIconStyle,
            trailingIconStyle = trailingIconStyle,
        )
    }


    @Composable
    fun shapes() = expressiveDefaultShapes

    @Composable
    fun shapes(
        shape: Shape? = null,
        selectedShape: Shape? = null,
        pressedShape: Shape? = null,
        focusedShape: Shape? = null,
        hoveredShape: Shape? = null,
        draggedShape: Shape? = null,
    ) = expressiveDefaultShapes.copy(
        shape = shape,
        selectedShape = selectedShape,
        pressedShape = pressedShape,
        focusedShape = focusedShape,
        hoveredShape = hoveredShape,
        draggedShape = draggedShape,
    )

    @Composable
    fun colors() = this.expressiveDefaultColors

    @Composable
    fun colors(
        // default
        containerColor: Color = Color.Unspecified,
        contentColor: Color = Color.Unspecified,
        leadingContentColor: Color = Color.Unspecified,
        trailingContentColor: Color = Color.Unspecified,
        overlineContentColor: Color = Color.Unspecified,
        supportingContentColor: Color = Color.Unspecified,
        // disabled
        disabledContainerColor: Color = Color.Unspecified,
        disabledContentColor: Color = Color.Unspecified,
        disabledLeadingContentColor: Color = Color.Unspecified,
        disabledTrailingContentColor: Color = Color.Unspecified,
        disabledOverlineContentColor: Color = Color.Unspecified,
        disabledSupportingContentColor: Color = Color.Unspecified,
        // selected
        selectedContainerColor: Color = Color.Unspecified,
        selectedContentColor: Color = Color.Unspecified,
        selectedLeadingContentColor: Color = Color.Unspecified,
        selectedTrailingContentColor: Color = Color.Unspecified,
        selectedOverlineContentColor: Color = Color.Unspecified,
        selectedSupportingContentColor: Color = Color.Unspecified,
        // dragged
        draggedContainerColor: Color = Color.Unspecified,
        draggedContentColor: Color = Color.Unspecified,
        draggedLeadingContentColor: Color = Color.Unspecified,
        draggedTrailingContentColor: Color = Color.Unspecified,
        draggedOverlineContentColor: Color = Color.Unspecified,
        draggedSupportingContentColor: Color = Color.Unspecified,
    ): ListItemColors {
        return this.expressiveDefaultColors.copy(
            containerColor = containerColor,
            contentColor = contentColor,
            leadingContentColor = leadingContentColor,
            trailingContentColor = trailingContentColor,
            overlineContentColor = overlineContentColor,
            supportingContentColor = supportingContentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor,
            disabledLeadingContentColor = disabledLeadingContentColor,
            disabledTrailingContentColor = disabledTrailingContentColor,
            disabledOverlineContentColor = disabledOverlineContentColor,
            disabledSupportingContentColor = disabledSupportingContentColor,
            selectedContainerColor = selectedContainerColor,
            selectedContentColor = selectedContentColor,
            selectedLeadingContentColor = selectedLeadingContentColor,
            selectedTrailingContentColor = selectedTrailingContentColor,
            selectedOverlineContentColor = selectedOverlineContentColor,
            selectedSupportingContentColor = selectedSupportingContentColor,
            draggedContainerColor = draggedContainerColor,
            draggedContentColor = draggedContentColor,
            draggedLeadingContentColor = draggedLeadingContentColor,
            draggedTrailingContentColor = draggedTrailingContentColor,
            draggedOverlineContentColor = draggedOverlineContentColor,
            draggedSupportingContentColor = draggedSupportingContentColor,
        )
    }
}
