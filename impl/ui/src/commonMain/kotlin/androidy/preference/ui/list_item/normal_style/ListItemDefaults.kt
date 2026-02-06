package androidy.preference.ui.list_item.normal_style

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Vertical
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidy.preference.ui.list_item.m3_tokens.value

object ListItemDefaults {
    internal var cached: ListItemStyle? = null

    val defaultStyle: ListItemStyle
        @Composable
        get() {
            return cached ?: ListItemStyle(
                containerShape = ListItemTokens.ItemContainerShape.value,
                containerColor = ListItemTokens.ItemContainerColor.value,
                overlineTextStyle = ListItemTokens.ItemOverlineFont.value,
                overlineColor = ListItemTokens.ItemOverlineColor.value,
                disabledOverlineColor = ListItemTokens.ItemDisabledOverlineColor.value.copy(
                    alpha = ListItemTokens.ItemDisabledOverlineOpacity
                ),
                headlineTextStyle = ListItemTokens.ItemLabelTextFont.value,
                headlineColor = ListItemTokens.ItemLabelTextColor.value,
                disabledHeadlineColor = ListItemTokens.ItemDisabledLabelTextColor.value.copy(
                    alpha = ListItemTokens.ItemDisabledLabelTextOpacity
                ),
                supportingTextStyle = ListItemTokens.ItemSupportingTextFont.value,
                supportingTextColor = ListItemTokens.ItemSupportingTextColor.value,
                disabledSupportingTextColor = ListItemTokens.ItemSupportingTextColor.value.copy(
                    alpha = ListItemTokens.ItemDisabledSupportingTextOpacity
                ),
                leadingIconStyle = ListItemIconStyle.Companion.leadingIconStyle(),
                trailingIconStyle = ListItemIconStyle.Companion.trailingIconStyle(),
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
            bodyItemSpace = bodyItemSpace,
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
            disabledSupportingTextColor = disabledSupportingTextColor,
            leadingIconStyle = leadingIconStyle,
            trailingIconStyle = trailingIconStyle,
        )
    }
}

