package androidy.preference.data.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map

class DependenceTree(
    val editorHolder: AbstractValueEditorHolder,
) {
    val cache = HashMap<String, DependenceNode>()

    fun unRegister(key: String) {
        cache.remove(key)
    }

    fun disable(vararg key: String) {
        key.forEach {
            cache[it]?.disable()
        }
    }

    fun enable(vararg key: String) {
        key.forEach {
            cache[it]?.enable()
        }
    }

    fun get(key: String): DependenceNode {
        return cache.getOrPut(key) { DependenceNode(key, this) }
    }

    @Composable
    fun get(
        key: String,
        action: DependenceNode.(preferenceHolder: AbstractValueEditorHolder) -> Unit,
    ): DependenceNode {
        return remember(key) {
            get(key).apply { action(editorHolder) }
        }
    }
}

/**
 * 1. 组件自身的enable状态、节点的值状态
 * 2. 组件依赖的其他组件的enable状态、节点的值状态、偏好值状态
 * 组件只应响应一个状态
 * 组件应优先响应自身节点值状态，enable状态应当合并进值状态
 * 组件应当可以监听其他组件的节点值状态、偏好值->修改自身节点值状态
 *
 * 方案1 状态节点组织成树结构，B,C,D依赖A节点状态，A修改自身状态，B,C,D观察
 * 方案2 状态节点分散，B,C,D各自有状态节点，A直接修改B,C,D状态
 *
 * 方案3 状态节点分散，B,C,D各自有状态节点，并在scope中collectA的状态或偏好值，以此根据条件修改自身状态
 *       B\C\D collect A的状态值和偏好值只能二选一，且此时B\C\D自身状态值不可手动修改
 */
class DependenceNode(val key: String, val tree: DependenceTree) {
    private val stateFlow = MutableStateFlow<Boolean>(true)
    private val defaultProducer = DependenceStateProducer { stateFlow }
    private var conditionFlow = MutableStateFlow<DependenceStateProducer>(value = defaultProducer)
    var flow = conditionFlow.flatMapLatest { it.provide() }

    fun enable() {
        stateFlow.value = true
    }

    fun disable() {
        stateFlow.value = false
    }

    fun setProducer(producer: DependenceStateProducer) {
        conditionFlow.value = producer
    }

    fun clearProducer() {
        conditionFlow.value = defaultProducer
    }

    /**
     *
     */
    fun dependenceStateFrom(string: String, function: (Boolean?) -> Boolean) {
        val t = tree.cache.get(string)?.flow?.map { value -> function(value) }
        if (t != null) {
            setProducer { t }
        }
    }

    /**
     *
     */
    inline fun <reified T : Any> dependenceValueFrom(keyName: String, crossinline function: (T?) -> Boolean) {
        val t = tree.editorHolder.getOnePrefEditor<T>(keyName).flow.map { value -> function(value) }
        setProducer { t }
    }
}

fun interface DependenceStateProducer {
    fun provide(): Flow<Boolean>
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
