package androidy.preference.uiauto.domain

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidy.preference.data.core.DefaultPreferenceHolder
import androidy.preference.data.core.PreferenceHolder

object LocalAutoPreference {

    //持有偏好值
    private val LocalPrefs = compositionLocalOf<PreferenceHolder> {
        DefaultPreferenceHolder.instance()
    }

    val current: PreferenceHolder
        @Composable
        get() = LocalPrefs.current


    /**
     * 设置preference需要的数据读写工具
     * @param content
     */
    @Composable
    fun Provide(
        holder: PreferenceHolder = remember {
            DefaultPreferenceHolder.instance()
        },
        content: @Composable () -> Unit,
    ) {
        CompositionLocalProvider(
            LocalPrefs provides holder,
            content = content
        )
    }
}
