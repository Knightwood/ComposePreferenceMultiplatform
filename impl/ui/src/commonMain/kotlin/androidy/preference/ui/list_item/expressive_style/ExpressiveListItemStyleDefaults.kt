package androidy.preference.ui.list_item.expressive_style

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidy.preference.ui.list_item.m3_tokens.value
import androidy.preference.ui.list_item.normal_style.ListItemContentAlignment
import androidy.preference.ui.list_item.normal_style.ListItemContentPaddingValues
import androidy.preference.ui.list_item.normal_style.ListItemIconStyle
import androidy.preference.ui.list_item.normal_style.ListItemStyle

object ExpressiveListItemDefaults {
    internal var cached: ListItemStyle? = null

    val defaultStyle: ListItemStyle
        @Composable
        get() {
            return cached ?: ExpressiveListItemStyle(
                containerShape = ExpressiveListItemTokens.ContainerShape.value,
                containerSelectedShape = ExpressiveListItemTokens.ItemSelectedContainerExpressiveShape.value,
                containerPressedShape = ExpressiveListItemTokens.ItemPressedContainerExpressiveShape.value,
                containerFocusedShape = ExpressiveListItemTokens.ItemFocusedContainerExpressiveShape.value,
                containerHoveredShape = ExpressiveListItemTokens.ItemHoveredContainerExpressiveShape.value,
                containerDraggedShape = ExpressiveListItemTokens.ItemDraggedContainerExpressiveShape.value,

                containerColor = ExpressiveListItemTokens.ItemContainerColor.value,
                disabledContainerColor = ExpressiveListItemTokens.ItemContainerColor.value.copy(
                    alpha = ExpressiveListItemTokens.ItemDisabledLabelTextOpacity
                ),
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

                contentPadding = ListItemContentPaddingValues(
                    oneline = PaddingValues(
                        start = InteractiveListStartPadding,
                        end = InteractiveListEndPadding,
                        top = InteractiveListTopPadding,
                        bottom = InteractiveListBottomPadding
                    )
                ),
                leadingPadding = PaddingValues(end = ExpressiveListItemTokens.ItemBetweenSpace),
                leadingSize = DpSize.Unspecified,
                leadingPercent = null,
                bodyPadding = PaddingValues(0.dp),
                bodyItemSpace = null,
                bodyPercent = 1f,
                trailingPadding = PaddingValues(start = ExpressiveListItemTokens.ItemBetweenSpace),
                trailingSize = DpSize.Unspecified,
                trailingPercent = null,

                overlineTextStyle = ExpressiveListItemTokens.ItemOverlineFont.value,
                overlineContentColor = ExpressiveListItemTokens.ItemOverlineColor.value,
                disabledOverlineContentColor = ExpressiveListItemTokens.ItemDisabledOverlineColor.value.copy(
                    alpha = ExpressiveListItemTokens.ItemDisabledOverlineOpacity
                ),
                selectedOverlineContentColor = ExpressiveListItemTokens.ItemSelectedOverlineColor.value,
                draggedOverlineContentColor = ExpressiveListItemTokens.ItemOverlineColor.value,

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
                selectedSupportingTextColor = ExpressiveListItemTokens.ItemSelectedSupportingTextColor.value,
                draggedSupportingTextColor = ExpressiveListItemTokens.ItemSupportingTextColor.value,

                leadingIconStyle = ListItemIconStyle.Companion.leadingIconStyle(
                    contentColor = ExpressiveListItemTokens.ItemLeadingIconColor.value,
                    disabledIconColor = ExpressiveListItemTokens.ItemDisabledLeadingIconColor.value.copy(
                        alpha = ExpressiveListItemTokens.ItemDisabledLeadingIconOpacity
                    ),
                    size = DpSize(
                        ExpressiveListItemTokens.ItemLeadingIconExpressiveSize,
                        ExpressiveListItemTokens.ItemLeadingIconExpressiveSize
                    )
                ),
                trailingIconStyle = ListItemIconStyle.Companion.trailingIconStyle(
                    contentColor = ExpressiveListItemTokens.ItemTrailingIconColor.value,
                    disabledIconColor = ExpressiveListItemTokens.ItemDisabledTrailingIconColor.value.copy(
                        alpha = ExpressiveListItemTokens.ItemDisabledTrailingIconOpacity
                    ),
                    size = DpSize(
                        ExpressiveListItemTokens.ItemTrailingIconExpressiveSize,
                        ExpressiveListItemTokens.ItemTrailingIconExpressiveSize
                    )
                ),
            ).also { cached = it }
        }

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
        return defaultStyle.copy(
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
            selectedSupportingTextColor = selectedSupportingTextColor,
            draggedSupportingTextColor = draggedSupportingTextColor,

            leadingIconStyle = leadingIconStyle,
            trailingIconStyle = trailingIconStyle,
        )
    }
}
