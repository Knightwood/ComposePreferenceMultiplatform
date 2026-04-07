package androidy.preference.uiauto

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
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
        dependenceTree.register(key)
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

            PreferenceSwitchItem(
                checked = checked.value ?: false,
                onCheckedChange = {
                    editor.write(it)
                },
                title = {}
            )
        }
    }
}

