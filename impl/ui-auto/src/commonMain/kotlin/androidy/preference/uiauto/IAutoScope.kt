package androidy.preference.uiauto

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidy.preference.data.core.DependenceNode
import androidy.preference.data.core.DependenceTree
import androidy.preference.data.core.EmptyValueEditorHodler
import androidy.preference.data.core.ISingleValueEditor
import androidy.preference.ui.component.PreferenceSwitchItem

@Composable
inline fun <reified T : Any> IAutoScope(
    key: String,
    content: @Composable (ISingleValueEditor<T>, DependenceTree) -> Unit,
) {
    val holder = LocalAutoPreference.current
    val scope = holder.getOnePrefEditor<T>(key)
    val dependenceTree = holder.dependenceTree
    DisposableEffect(key) {
        onDispose {
            dependenceTree.unRegister(key)
        }
    }
    content(scope, dependenceTree)
}

@Composable
private fun Example() {
    LocalAutoPreference.Provide(holder = EmptyValueEditorHodler()) {
        IAutoScope<Boolean>("key1") { editor, tree ->
            val checked = editor.flow.collectAsState(false)
            val enabled = tree.get("key1") { holder ->
                dependenceStateFrom("key2") { key2Enabled -> key2Enabled ?: false }
                //与dependenceStateFrom二选一，且使用依赖状态后不再响应节点自身enable
                dependenceValueFrom<String>("key3") { key3Value -> true }
            }.state()

            PreferenceSwitchItem(
                checked = checked.value ?: false,
                onCheckedChange = {
                    editor.write(it)
                    tree.enable("key2","key3")
                },
                enabled = enabled,
                title = {}
            )
        }
    }
}

/**
 * @param enabled 组件的enabled状态
 * @return 若节点存在，则返回节点的enabled状态，否则返回组件输入的enabled状态
 */
@Composable
fun DependenceNode?.state(enabled: Boolean = true): Boolean {
    if (this == null) {
        return enabled
    }
    return this.flow.collectAsState(enabled).value ?: enabled
}
