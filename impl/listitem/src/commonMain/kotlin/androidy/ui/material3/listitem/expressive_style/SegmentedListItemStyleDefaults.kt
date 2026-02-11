package androidy.ui.material3.listitem.expressive_style

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidy.ui.material3.listitem.interactive.StateShapes
import androidy.ui.material3.listitem.m3_tokens.value
import androidy.ui.material3.listitem.normal_style.ListItemContentAlignment
import androidy.ui.material3.listitem.normal_style.ListItemContentPaddingValues
import androidy.ui.material3.listitem.normal_style.ListItemIconStyle
import androidy.ui.material3.listitem.normal_style.ListItemStyle
import androidy.ui.material3.listitem.normal_style.shapes

object SegmentedListItemStyleDefaults {
    internal var segmentedCached: ListItemStyle? = null
    internal val segmentedDefaultStyle: ListItemStyle
        @Composable
        get() {
            return segmentedCached ?: ExpressiveListItemDefaults.expressiveDefaultStyle.copy(
                containerColor = ExpressiveListItemTokens.ItemSegmentedContainerColor.value,
                disabledContainerColor = ExpressiveListItemTokens.ItemSegmentedContainerColor.value,
            ).also { segmentedCached = it }
        }

    val SegmentedGap: Dp = ExpressiveListItemTokens.SegmentedGap

    internal val defaultShapes
        @Composable
        get() = this.segmentedDefaultStyle.containerShape

    @Composable
    fun shapes() = defaultShapes

    @Composable
    fun style() = segmentedDefaultStyle

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
        val base = segmentedDefaultStyle
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
    fun segmentedShape(
        index: Int,
        count: Int,
        defaultShapes: StateShapes = shapes(),
    ): StateShapes {
        val overrideShape = ExpressiveListItemTokens.ContainerShape.value
        return remember(index, count, defaultShapes, overrideShape) {
            when {
                count == 1 -> defaultShapes

                index == 0 -> {
                    val defaultBaseShape = defaultShapes.shape
                    if (defaultBaseShape is CornerBasedShape && overrideShape is CornerBasedShape) {
                        defaultShapes.copy(
                            shape =
                                defaultBaseShape.copy(
                                    topStart = overrideShape.topStart,
                                    topEnd = overrideShape.topEnd,
                                )
                        )
                    } else {
                        defaultShapes
                    }
                }

                index == count - 1 -> {
                    val defaultBaseShape = defaultShapes.shape
                    if (defaultBaseShape is CornerBasedShape && overrideShape is CornerBasedShape) {
                        defaultShapes.copy(
                            shape =
                                defaultBaseShape.copy(
                                    bottomStart = overrideShape.bottomStart,
                                    bottomEnd = overrideShape.bottomEnd,
                                )
                        )
                    } else {
                        defaultShapes
                    }
                }

                else -> defaultShapes
            }
        }
    }
}
