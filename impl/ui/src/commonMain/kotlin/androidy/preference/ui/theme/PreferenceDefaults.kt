package androidy.preference.ui.theme

import androidx.compose.runtime.Composable
import androidy.preference.ui.theme.model.DefaultCautionCardItemStyleProvider
import androidy.preference.ui.theme.model.DefaultHintCardItemStyleProvider
import androidy.preference.ui.theme.model.DefaultLabelTitleStyleProvider
import androidy.preference.ui.theme.model.DefaultLargeTitleStyleProvider
import androidy.preference.ui.theme.model.PreferenceTitleStyle
import androidy.preference.ui.theme.model.PreferenceItemTheme
import androidy.ui.material3.listitem.LocalListItemStyle
import androidy.ui.material3.listitem.normal_style.ListItemStyle

object PreferenceDefaults {
    private var cached: PreferenceItemTheme? = null

    /**
     * PreferenceItem视图是通过ListItem实现的，因此其样式基础是ListItem样式，
     * 在使用PreferenceItem时，必须先设置ListItem样式，
     */
    internal var itemStyle: ListItemStyle? = null

    val defaultTheme: PreferenceItemTheme
        @Composable
        get() {
            return cached ?: PreferenceItemTheme.instance(
                itemStyle = this.itemStyle ?: LocalListItemStyle.current,
                titleStyle = PreferenceTitleStyle.style(),
            ).also {
                cached = it
            }
        }

    @Composable
    fun style(): PreferenceItemTheme {
        return defaultTheme
    }

    @Composable
    fun style(
        itemStyle: ListItemStyle? = null,
        titleStyle: PreferenceTitleStyle? = null,
        largeTitleStyleProvider: PreferenceItemTheme.StyleModifiedProvider<PreferenceTitleStyle> = DefaultLargeTitleStyleProvider,
        labelTitleStyleProvider: PreferenceItemTheme.StyleModifiedProvider<PreferenceTitleStyle> = DefaultLabelTitleStyleProvider,
        hintCardItemStyleProvider: PreferenceItemTheme.StyleModifiedProvider<ListItemStyle> = DefaultHintCardItemStyleProvider,
        cautionCardItemStyleProvider: PreferenceItemTheme.StyleModifiedProvider<ListItemStyle> = DefaultCautionCardItemStyleProvider,
    ): PreferenceItemTheme {
        return defaultTheme.copy(
            itemStyle = itemStyle,
            titleStyle = titleStyle,
            hintCardItemStyle = hintCardItemStyleProvider.provide((itemStyle ?: defaultTheme.itemStyle).copy()),
            cautionCardItemStyle = cautionCardItemStyleProvider.provide((itemStyle ?: defaultTheme.itemStyle).copy()),
            largeTitleStyle = largeTitleStyleProvider.provide((titleStyle ?: defaultTheme.titleStyle).copy()),
            labelTitleStyle = labelTitleStyleProvider.provide((titleStyle ?: defaultTheme.titleStyle).copy()),
        )
    }

    @Composable
    fun style(
        itemStyle: ListItemStyle? = null,
        titleStyle: PreferenceTitleStyle? = null,
        hintCardItemStyle: ListItemStyle? = null,
        cautionCardItemStyle: ListItemStyle? = null,
        largeTitleStyle: PreferenceTitleStyle? = null,
        labelTitleStyle: PreferenceTitleStyle? = null,
    ): PreferenceItemTheme {
        val cache = defaultTheme
        return cache.copy(
            itemStyle = itemStyle,
            titleStyle = titleStyle,
            hintCardItemStyle = hintCardItemStyle,
            cautionCardItemStyle = cautionCardItemStyle,
            largeTitleStyle = largeTitleStyle,
            labelTitleStyle = labelTitleStyle,
        )
    }
}
