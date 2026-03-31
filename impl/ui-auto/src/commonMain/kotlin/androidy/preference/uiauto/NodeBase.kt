package androidy.preference.uiauto

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidy.preference.data.core.AbstractPreferenceHolder
import androidy.preference.uiauto.domain.LocalAutoPreference
import kotlinx.coroutines.CoroutineScope


@Composable
inline fun <reified T : Any> AutoReadWriteScope(
    content: @Composable AbstractPreferenceHolder.() -> Unit,
) {
    val scope = LocalAutoPreference.current
    scope.content()
}

//@Composable
//fun PreferenceNodeBase(
//    enabled: Boolean,
//    dependenceKey: String?,
//    content: @Composable (
//        scope: CoroutineScope,
//        depState: Boolean,
//    ) -> Unit,
//) {
//    val scope = rememberCoroutineScope()
//    val prefStoreHolder = LocalAutoPreference.current
//    //不注册自身节点，仅获取目标节点的状态
//    val dependenceState =
//        prefStoreHolder.getDependenceNotEmpty(
//            dependenceKey,
//            enabled
//        ).enableStateFlow.collectAsState()
//
//    content(
//        scope,
//        dependenceState.value,
//    )
//
//
//}
