package androidy.preference.uiauto

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidy.preference.data.core.AbstractValueEditorHolder
import java.lang.NullPointerException

object LocalAutoPreference {

    //持有偏好值
    private val LocalPrefs = compositionLocalOf<AbstractValueEditorHolder> {
        error(NullPointerException("LocalPrefs is not initialized"))
    }

    val current: AbstractValueEditorHolder
        @Composable
        get() = LocalPrefs.current


    /**
     * 设置preference需要的数据读写工具
     * @param content
     */
    @Composable
    fun Provide(
        holder: AbstractValueEditorHolder,
        content: @Composable () -> Unit,
    ) {
        CompositionLocalProvider(
            LocalPrefs provides holder,
            content = content
        )
    }
}
