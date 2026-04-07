package androidy.preference.data.core

class DependenceTree(
    val editorHolder: AbstractValueEditorHolder,
) {
    val tree = HashMap<String, DependenceNode>()

    fun register(key: String) {

    }
    fun register(key: String, state: Boolean) {}
    fun unRegister(key: String) {

    }
}

class DependenceNode {
}
