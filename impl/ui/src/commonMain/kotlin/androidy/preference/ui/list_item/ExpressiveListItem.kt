package androidy.preference.ui.list_item
//
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.RowScope
//import androidx.compose.foundation.layout.padding
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.ProvidedValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.TextStyle
//import androidy.preference.ui.list_item.m3_tokens.ProvideContentColorTextStyle
//import androidy.preference.ui.list_item.normal_style.ListItemStyle
//
//@Composable
//context(scope: RowScope)
//private fun ListItemStyle.LeadingContentWrapper(
//    enabled: Boolean = true,
//    content: @Composable (() -> Unit),
//) {
//    with(scope) {
//        ContentWrapper(
//            alignment = alignment,
//            padding = leadingPadding,
//            contentColor = leadingIconColor(enabled),
//            textStyle = leadingIconStyle.textStyle,
//            content = content
//        )
//    }
//}
//
//@Composable
//context(scope: RowScope)
//private fun ListItemStyle.TrialingContentWrapper(
//    enabled: Boolean = true,
//    content: @Composable (() -> Unit),
//) {
//    with(scope) {
//        ContentWrapper(
//            alignment = alignment,
//            padding = trailingPadding,
//            contentColor = trailingIconColor(enabled),
//            textStyle = trailingIconStyle.textStyle,
//            content = content
//        )
//    }
//}
//
///**
// * 通用的内容包装
// */
//@Composable
//internal fun RowScope.ContentWrapper(
//    alignment: Alignment.Vertical,
//    padding: PaddingValues,
//    contentColor: Color,
//    textStyle: TextStyle,
//    vararg values: ProvidedValue<*>,
//    content: @Composable (() -> Unit),
//) {
//    Box(
//        modifier = Modifier
//            .align(alignment)
//            .padding(padding)
//    ) {
//        ProvideContentColorTextStyle(
//            contentColor = contentColor,
//            textStyle = textStyle,
//            values = *values
//        ) {
//            content()
//        }
//    }
//}
