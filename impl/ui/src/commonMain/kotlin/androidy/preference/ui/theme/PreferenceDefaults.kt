package androidy.preference.ui.theme

import androidx.compose.runtime.Composable
import androidy.preference.ui.theme.model.DefaultCautionCardItemStyleProvider
import androidy.preference.ui.theme.model.DefaultHintCardItemStyleProvider
import androidy.preference.ui.theme.model.DefaultLabelTitleStyleProvider
import androidy.preference.ui.theme.model.DefaultLargeTitleStyleProvider
import androidy.preference.ui.theme.model.PreferenceTitleStyle
import androidy.preference.ui.theme.model.PreferenceItemTheme
import androidy.ui.material3.listitem.expressive_style.ExpressiveListItemDefaults
import androidy.ui.material3.listitem.normal_style.ListItemStyle

object PreferenceDefaults {
    private var cached: PreferenceItemTheme? = null

    /**
     * 默认主题基于 ExpressiveListItem
     */
    val defaultTheme: PreferenceItemTheme
        @Composable
        get() {
            return cached ?: PreferenceItemTheme.instance(
                itemStyle = ExpressiveListItemDefaults.style(),
                titleStyle = PreferenceTitleStyle.style(),
            ).also {
                cached = it
            }
        }

    /**
     * 获取默认的 PreferenceItemTheme
     */
    @Composable
    fun style(): PreferenceItemTheme {
        return defaultTheme
    }

    /**
     * 指定基础的 item样式、标题样式，使用provider创建其余样式 并返回基于默认值创建的 PreferenceItemTheme
     */
    @Composable
    fun styleFromProvider(
        itemStyle: ListItemStyle = defaultTheme.itemStyle,
        titleStyle: PreferenceTitleStyle = defaultTheme.titleStyle,
        largeTitleStyleProvider: PreferenceItemTheme.StyleModifiedProvider<PreferenceTitleStyle> = DefaultLargeTitleStyleProvider,
        labelTitleStyleProvider: PreferenceItemTheme.StyleModifiedProvider<PreferenceTitleStyle> = DefaultLabelTitleStyleProvider,
        hintCardItemStyleProvider: PreferenceItemTheme.StyleModifiedProvider<ListItemStyle> = DefaultHintCardItemStyleProvider,
        cautionCardItemStyleProvider: PreferenceItemTheme.StyleModifiedProvider<ListItemStyle> = DefaultCautionCardItemStyleProvider,
    ): PreferenceItemTheme {
        return defaultTheme.copy(
            itemStyle = itemStyle,
            titleStyle = titleStyle,
            hintCardItemStyle = hintCardItemStyleProvider.provide(itemStyle.copy()),
            cautionCardItemStyle = cautionCardItemStyleProvider.provide(itemStyle.copy()),
            largeTitleStyle = largeTitleStyleProvider.provide(titleStyle.copy()),
            labelTitleStyle = labelTitleStyleProvider.provide(titleStyle.copy()),
        )
    }

    /**
     * 指定不同子组件样式，返回基于默认值创建的 PreferenceItemTheme
     *
     * 如果你希望提供itemStyle之后，后续的子组件主题是根据提供的itemStyle创建的，请使用[styleFromProvider]
     *
     * 此方法在没有指定具体子组件样式时将使用[defaultTheme]中的值，
     * 可能会造成主题视觉割裂：某些子组件样式基于你传入的值，而另一些子组件样式基于[defaultTheme]中的值
     */
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
