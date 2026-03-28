package androidy.preference.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidy.preference.ui.theme.LocalPreferenceTheme
import androidy.preference.ui.theme.model.PreferenceTitleStyle
import androidy.ui.material3.listitem.m3_tokens.ProvideContentColorTextStyle

/**
 * 标题不应与preference item使用相同布局结构，
 * 标题只需显示一行简短文本，使用特定的文本样式、边距尺寸，
 * 标题有不同样式，比如大标题、小标题等。
 */
@Composable
fun PreferenceTitle(
    modifier: Modifier = Modifier,
    style: PreferenceTitleStyle = LocalPreferenceTheme.current.titleStyle,
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = modifier.padding(style.padding),
        color = style.containerColor,
        contentColor = style.contentColor,
        shape = style.shape,
        tonalElevation = style.tonalElevation,
        shadowElevation = style.shadowElevation,
        border = style.border
    ) {
        ProvideContentColorTextStyle(
            contentColor = style.contentColor,
            textStyle = style.textStyle
        ) {
            content()
        }
    }
}

@Composable
fun PreferenceSubTitle(
    modifier: Modifier = Modifier,
    style: PreferenceTitleStyle = LocalPreferenceTheme.current.labelTitleStyle,
    content: @Composable () -> Unit,
) = PreferenceTitle(modifier, style, content)

@Composable
fun PreferenceLargeTitle(
    modifier: Modifier = Modifier,
    style: PreferenceTitleStyle = LocalPreferenceTheme.current.largeTitleStyle,
    content: @Composable () -> Unit,
) = PreferenceTitle(modifier, style, content)
