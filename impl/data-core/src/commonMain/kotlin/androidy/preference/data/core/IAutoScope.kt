package androidy.preference.data.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

@Composable
inline fun <reified T : Any> IAutoScope(
    key: String,
    content: @Composable (ISingleValueEditor<T>, DependenceTree) -> Unit,
) {
    val holder = LocalAutoPreference.current
    val editor = holder.getOnePrefEditor<T>(key)
    val dependenceTree = holder.dependenceTree
    content(editor, dependenceTree)
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

@Composable
private fun PreferenceSwitchItem(checked: Boolean, onCheckedChange: (Boolean) -> Unit, enabled: Boolean, title: () -> Unit) {}
