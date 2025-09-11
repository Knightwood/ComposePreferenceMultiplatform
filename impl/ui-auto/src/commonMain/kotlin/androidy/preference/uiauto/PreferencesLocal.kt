package androidy.preference.uiauto

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidy.preference.ui.theme.PreferenceBoxStyle
import androidy.preference.ui.theme.PreferenceDimen
import androidy.preference.ui.theme.PreferenceIconStyle
import androidy.preference.ui.theme.PreferenceStyle
import androidy.preference.ui.theme.PreferenceTextStyle
import androidy.preference.ui.theme.Preferences
import androidy.preference.ui.theme.defaultIconStyle
import androidy.preference.ui.theme.defaultPreferenceBoxStyle
import androidy.preference.data.core.DefaultPreferenceHolder
import androidy.preference.data.core.PreferenceHolder
import androidy.preference.ui.theme.LocalPreferenceDimens
import androidy.preference.ui.theme.LocalPreferenceStyle
import androidy.preference.ui.theme.LocalPreferenceTextStyle
import androidy.preference.ui.theme.defaultPreferenceTextStyle


//持有偏好值
val LocalPrefs = compositionLocalOf<PreferenceHolder> {
    DefaultPreferenceHolder.instance()
}


/**
 * 快速修改当前使用的preference各个样式
 *
 * @param boxStyleProvider
 * @param iconStyleProvider
 * @param textStyleProvider
 * @param dimenProvider
 * @param content
 */
@Composable
fun Preferences.CopyTheme(
    titleMaxLine: Int = 1,
    descMaxLine: Int = 2,
    boxStyleProvider: @Composable PreferenceBoxStyle.() -> PreferenceBoxStyle = { this },
    iconStyleProvider: @Composable PreferenceIconStyle.() -> PreferenceIconStyle = { this },
    textStyleProvider: @Composable PreferenceTextStyle.() -> PreferenceTextStyle = { this },
    dimenProvider: @Composable PreferenceDimen.() -> PreferenceDimen = { this },
    holder: PreferenceHolder = LocalPrefs.current,
    content: @Composable () -> Unit,
) {
    SetTheme(
        boxStyle = LocalPreferenceStyle.current.boxStyle.boxStyleProvider(),
        iconStyle = LocalPreferenceStyle.current.iconStyle.iconStyleProvider(),
        textStyle = LocalPreferenceTextStyle.current.textStyleProvider(),
        dimen = LocalPreferenceDimens.current.dimenProvider(),
        holder = holder,
        content = content, titleMaxLine = titleMaxLine, descMaxLine = descMaxLine
    )
}

/**
 * 设置preference样式
 *
 * @param boxStyle
 * @param iconStyle
 * @param textStyle
 * @param dimen
 * @param content
 */
@Composable
fun Preferences.SetTheme(
    titleMaxLine: Int = 1,
    descMaxLine: Int = 2,
    boxStyle: PreferenceBoxStyle = defaultPreferenceBoxStyle,
    iconStyle: PreferenceIconStyle = defaultIconStyle,
    textStyle: PreferenceTextStyle = defaultPreferenceTextStyle,
    dimen: PreferenceDimen = PreferenceDimen(),
    holder: PreferenceHolder = remember {
        DefaultPreferenceHolder.instance()
    },
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalPreferenceDimens provides dimen,
        LocalPreferenceTextStyle provides textStyle,
        LocalPreferenceStyle provides PreferenceStyle(
            boxStyle,
            iconStyle,
            titleMaxLine,
            descMaxLine
        ),
        LocalPrefs provides holder,
        content = content
    )
}
