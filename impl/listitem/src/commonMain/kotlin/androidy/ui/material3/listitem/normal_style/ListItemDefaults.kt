package androidy.ui.material3.listitem.normal_style

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
import androidy.ui.material3.listitem.m3_tokens.value

object ListItemDefaults {
    internal var cached: ListItemStyle? = null

    internal val defaultStyle: ListItemStyle
        @Composable
        get() {
            return cached ?: ListItemStyle(
                containerShape = ListItemTokens.ItemContainerShape.value,
                containerColor = ListItemTokens.ItemContainerColor.value,
                disabledContainerColor = ListItemTokens.ItemContainerColor.value,
                containerTonalElevation = ListItemTokens.ItemContainerElevation,
                containerShadowElevation = ListItemTokens.ItemContainerShadowElevation,
                containerBorder = null,
                containerHeightMin = ListItemTokens.ItemContainerHeightMin,
                containerHeightMax = ListItemTokens.ItemContainerHeightMax,
                alignment = ListItemContentAlignment(
                    oneline = Alignment.CenterVertically,
                    threeline = Alignment.Top,
                ),
                contentPadding = ListItemContentPaddingValues.default(),
                leadingPadding = PaddingValues(end = ListItemTokens.LeadingContentEndPadding),
                leadingSize = DpSize.Unspecified,
                leadingPercent = null,
                bodyPadding = PaddingValues(0.dp),
                bodyItemSpace = null,
                bodyPercent = 1f,
                trailingPadding = PaddingValues(start = ListItemTokens.TrailingContentStartPadding),
                trailingSize = DpSize.Unspecified,
                trailingPercent = null,
                overlineTextStyle = ListItemTokens.ItemOverlineFont.value,
                overlineContentColor = ListItemTokens.ItemOverlineColor.value,
                disabledOverlineContentColor = ListItemTokens.ItemDisabledOverlineColor.value.copy(
                    alpha = ListItemTokens.ItemDisabledOverlineOpacity
                ),
                headlineTextStyle = ListItemTokens.ItemLabelTextFont.value,
                headlineContentColor = ListItemTokens.ItemLabelTextColor.value,
                disabledHeadlineContentColor = ListItemTokens.ItemDisabledLabelTextColor.value.copy(
                    alpha = ListItemTokens.ItemDisabledLabelTextOpacity
                ),
                supportingTextStyle = ListItemTokens.ItemSupportingTextFont.value,
                supportingContentColor = ListItemTokens.ItemSupportingTextColor.value,
                disabledSupportingContentColor = ListItemTokens.ItemDisabledSupportingTextColor.value.copy(
                    alpha = ListItemTokens.ItemDisabledSupportingTextOpacity
                ),
                leadingIconStyle = ListItemIconStyle.Companion.leadingIconStyle(),
                trailingIconStyle = ListItemIconStyle.Companion.trailingIconStyle(),
            ).also { cached = it }
        }

    @Composable
    fun style() = defaultStyle

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

        headlineTextStyle: TextStyle? = null,
        headlineContentColor: Color? = null,
        disabledHeadlineContentColor: Color? = null,

        supportingTextStyle: TextStyle? = null,
        supportingContentColor: Color? = null,
        disabledSupportingContentColor: Color? = null,
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
            overlineContentColor = overlineContentColor,
            headlineTextStyle = headlineTextStyle,
            headlineContentColor = headlineContentColor,
            disabledHeadlineContentColor = disabledHeadlineContentColor,
            supportingTextStyle = supportingTextStyle,
            supportingContentColor = supportingContentColor,
            disabledSupportingContentColor = disabledSupportingContentColor,
            leadingIconStyle = leadingIconStyle,
            trailingIconStyle = trailingIconStyle,
        )
    }
}

