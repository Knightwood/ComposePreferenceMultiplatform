package androidy.preference.data.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

/**
 * 此类管理所有偏好项的依赖关系节点，提供统一的节点注册、查询和控制接口。
 * 主要用于实现偏好项之间的启用/禁用依赖逻辑。
 *
 * 使用场景：
 * - 当某项的启用状态、偏好值发生变化时，所有观察它的子项将禁用或启用
 * - 批量启用或禁用未依赖其他偏好项启用状态、偏好值的偏好项
 *
 * @param editorHolder 偏好编辑器持有者，用于访问偏好值和创建节点
 *
 * 使用示例：
 * ```kotlin
 *
 * // 批量启用/禁用
 * // 若节点依赖了其他节点的状态或偏好值，批量启用/禁用将无效
 * tree.enable("option1", "option2", "option3")
 * tree.disable("option1", "option2")
 *
 * // 在Composable中配置依赖
 * @Composable
 * fun MyOption() {
 *     // IAutoScope函数参数中的key是偏好值键值对的key
 *     IAutoScope<Boolean>(key = "auto_sync") { editor, tree ->
 *          //注册一个状态节点，名为"option1"
 *          val enabled = tree.get("option1") { holder ->
 *              // 观察名为option2的状态节点的enable状态，在状态为enable状态时将自己禁用
 *              dependenceStateFrom("option2") { it == false }
 *          }.state()
 *     }
 * }
 * ```
 */
class EnableStateNodeTree(
    val editorHolder: AbstractValueEditorHolder,
) {
    /**
     * 状态节点缓存表
     *
     * Key: 状态节点名，可以于偏好值的键名相同
     * Value: 对应的状态节点实例
     *
     * 保证每个key只有一个EnableStateNode实例，确保状态一致性。
     */
    val nodeCache = HashMap<String, EnableStateNode>()

    /**
     * 删除指定key的状态节点
     *
     * 从缓存中移除节点，释放相关资源。
     * 注意：此操作不会清除节点上配置的依赖关系提供者。
     *
     * @param key 要注销的状态节点名
     */
    fun unRegister(key: String) {
        nodeCache.remove(key)
    }

    /**
     * 批量禁用指定的状态节点
     *
     * 将指定key对应的节点状态设置为false（禁用）。
     * 如果节点不存在，则忽略该key。
     *
     * 若节点已使用[EnableStateNode.setProducer] 更新了"启用状态"来源，此方法将失效
     *
     * @param key 要禁用的状态节点的名
     *
     * 使用示例：
     * ```kotlin
     * // 当主开关关闭时，禁用所有子选项
     * tree.disable("sub_option1", "sub_option2", "sub_option3")
     * ```
     */
    fun disable(vararg key: String) {
        key.forEach {
            nodeCache[it]?.disable()
        }
    }

    /**
     * 批量启用指定的状态节点
     *
     * 将指定key对应的节点状态设置为true（启用）。
     * 如果节点不存在，则忽略该key。
     *
     * 若节点已使用[EnableStateNode.setProducer] 更新了"启用状态"来源，此方法将失效
     *
     * @param key 要启用的状态节点的名
     *
     * 使用示例：
     * ```kotlin
     * // 当主开关打开时，启用所有子选项
     * tree.enable("sub_option1", "sub_option2", "sub_option3")
     * ```
     */
    fun enable(vararg key: String) {
        key.forEach {
            nodeCache[it]?.enable()
        }
    }

    /**
     * 获取已注册的状态节点或注册一个新的状态节点
     *
     * 如果节点已存在，返回缓存的实例；否则创建新节点并缓存。
     *
     * @param key 状态节点名
     * @return EnableStateNode 对应的状态节点实例
     */
    fun get(key: String): EnableStateNode {
        return nodeCache.getOrPut(key) { EnableStateNode(key, this) }
    }

    /**
     *
     * 用于在compose函数中注册、获取节点并配置状态节点，确保同一个状态节点只在首次组合时注册，避免重复配置。
     * 如果不需要配置状态节点，则可以使用上面的[get]函数
     *
     * @param key 状态节点的名
     *
     * @param action 配置状态节点，可在闭包中调用dependenceStateFrom或dependenceValueFrom设置依赖逻辑
     * @return EnableStateNode 配置后的状态节点实例
     *
     * 使用示例：
     * ```kotlin
     * @Composable
     * fun DependentOption() {
     *     val enabled = tree.get("my_option") { holder ->
     *         // 依赖于"main_switch"节点的状态
     *         dependenceStateFrom("main_switch") { mainEnabled ->
     *             mainEnabled ?: false  // 如果main_switch为null，则禁用
     *         }
     *     }.state()
     *
     *     Switch(enabled = enabled, ...)
     * }
     * ```
     *
     * 注意事项：
     * - action只在首次组合时执行一次
     * - 如果传入的key发生变化，则重新获取节点实例并调用action进行配置
     * - 在action中只能调用dependenceStateFrom或dependenceValueFrom中的一个
     * - 配置依赖后，节点不再响应自身的enable/disable调用
     */
    @Composable
    fun get(
        key: String,
        action: EnableStateNode.(preferenceHolder: AbstractValueEditorHolder) -> Unit,
    ): EnableStateNode {
        return remember(key) {
            get(key).apply { action(editorHolder) }
        }
    }
}

/**
 * enable状态节点
 *
 * 功能：
 * 1. 管理、分发enable状态
 * 2. 观察其他节点的启用状态
 * 3. 观察其他节点或偏好值的变化
 *
 *
 * 核心设计理念：
 * 1. 每个节点维护一个布尔状态流（stateFlow），表示当前的启用/禁用状态
 * 2. 节点可以通过两种方式确定最终状态：
 *    a) 直接通过enable()/disable()手动控制
 *    b) 通过DependenceStateProducer从其他节点或偏好值派生状态
 * 3. 一旦配置了状态来源（producer），节点将不再响应手动的enable/disable调用
 *
 * 三种依赖方案对比（见代码注释）：
 * - 方案1：树形结构，父节点变化通知所有子节点（当前采用的改进版）
 * - 方案2：分散节点，父节点直接修改子节点状态
 * - 方案3：分散节点，子节点collect父节点状态并自行更新（当前实现的核心思路）
 *
 * @param key 用于标识词状态节点的key
 * @param tree 所属的启用状态关系树，用于访问其他节点
 *
 * 状态流转：
 * ```
 * 初始状态: stateFlow = true (启用)
 *          |
 *          |-- enable() --> stateFlow = true
 *          |-- disable() --> stateFlow = false
 *          |-- setProducer(producer) --> stateFlow中value由producer提供的flow决定
 *          |-- clearProducer() --> stateFlow恢复为默认flow
 * ```
 *
 * 使用示例：
 * ```kotlin
 * val node = tree.get("my_option")
 *
 * // 方式1: 手动控制
 * node.enable()
 * node.disable()
 *
 * // 方式2: 依赖于其他节点的状态
 * node.dependenceStateFrom("main_switch") { mainEnabled ->
 *     mainEnabled ?: false
 * }
 *
 * // 方式3: 依赖于其他偏好值的值
 * node.dependenceValueFrom<String>("mode") { mode ->
 *     mode == "advanced"  // 仅在高级模式时启用
 * }
 *
 * // 在Compose中观察状态
 * @Composable
 * fun MyUI() {
 *     val enabled = node.state()
 *     Button(enabled = enabled) { ... }
 * }
 * ```
 */
class EnableStateNode(val key: String, val tree: EnableStateNodeTree) {
    /**
     * 状态默认来源
     *
     * 存储节点的原始启用/禁用状态，默认为true（启用）。
     * 当没有使用[setProducer]设置状态来源时，此flow将作为状态节点的默认状态来源，可使用[enable]、[disable]手动控制状态。
     */
    private val defaultStateSource = MutableStateFlow<Boolean>(true)

    /**
     * 默认的状态来源提供者
     * 专用于提供默认状态来源[defaultStateSource]
     */
    private val defaultProducer = EnableStateProducer(defaultStateSource)

    /**
     * 下发最新“状态来源提供者”。
     * “状态来源提供者”包装了一个用手提供状态的stateFlow
     * 默认的状态来源提供者是[defaultProducer]
     *
     * [flow]通过flatMapLatest监听此Flow，当提供者改变时，自动切换到新的状态流。
     */
    private var stateProducerFlow = MutableStateFlow<EnableStateProducer>(value = defaultProducer)

    /**
     * 提供此状态节点enable状态的flow
     *
     * 此Flow会根据conditionFlow的变化动态切换状态源：
     * - 默认情况下，发射stateFlow的值（手动控制）
     * - 调用dependenceStateFrom后，flow将提供从其他状态节点flow派生的状态，调用enable/disable将不再生效
     * - 调用dependenceValueFrom后，flow将提供基于其他偏好值派生的状态，调用enable/disable将不再生效
     *
     * 使用flatMapLatest确保当提供者改变时，立即切换到新的状态流。
     */
    var flow = stateProducerFlow.flatMapLatest { it.stateSources }

    /**
     * 启用此节点，将内部状态设置为true。
     * 如果当前没有配置状态来源提供者，
     * flow将发射true；如果已配置提供者，此调用无效。
     */
    fun enable() {
        defaultStateSource.value = true
    }

    /**
     * 禁用此节点，将内部状态设置为false。
     * 如果当前没有配置状态来源提供者，
     * flow将发射false；如果已配置提供者，此调用无效。
     */
    fun disable() {
        defaultStateSource.value = false
    }

    /**
     * 设置自定义状态来源提供者
     *
     * 替换当前的状态来源提供者提供者，flow将开始发射新提供者提供的状态。
     * 设置提供者后，enable()/disable()调用将不再生效，直到调用clearProducer()。
     *
     * @param producer 新的状态来源提供者，提供动态计算的启用状态流
     *
     * 使用场景：
     * - 需要根据复杂逻辑计算启用状态
     * - 需要组合多个数据源的状态
     * - 需要自定义状态转换逻辑
     */
    fun setProducer(producer: EnableStateProducer) {
        stateProducerFlow.value = producer
    }

    /**
     * 清除自定义状态来源提供者
     *
     * 恢复到默认提供者，flow将重新发射stateFlow的值。
     * 调用此方法后，enable()/disable()调用将再次生效。
     */
    fun clearProducer() {
        stateProducerFlow.value = defaultProducer
    }

    /**
     * 依赖于另一个节点的状态
     *
     * 配置此节点的状态来源于另一个EnableStateNode的状态。
     * 当被观察状态节点的状态变化时，此节点状态自动更新。
     *
     * @param string 被状态节点的key
     * @param function 状态转换函数，将被状态节点的状态（Boolean?）转换为此节点的状态（Boolean）
     *
     * 使用示例：
     * ```kotlin
     * // 依赖于"main_switch"节点，仅当其为true时启用
     * node.dependenceStateFrom("main_switch") { mainEnabled ->
     *     mainEnabled ?: false  // null视为false
     * }
     *
     * // 取反依赖：当"night_mode"启用时，禁用当前节点
     * node.dependenceStateFrom("night_mode") { nightMode ->
     *     !(nightMode ?: false)
     * }
     * ```
     *
     * 注意事项：
     * - 如果被状态节点不存在，function会收到null值
     * - 调用此方法后，enable()/disable()将不再生效
     * - 与dependenceValueFrom二选一，多次调用会覆盖之前的配置
     */
    fun dependenceStateFrom(string: String, function: (Boolean?) -> Boolean) {
        val t = tree.nodeCache.get(string)?.flow?.map { value -> function(value) }
        if (t != null) {
            setProducer(EnableStateProducer( t))
        }
    }

    /**
     * 依赖于另一个偏好值的值
     *
     * 配置此节点的状态来源于另一个偏好值的实际数据。
     * 当被依赖的偏好值变化时，此节点会通过function重新计算状态。
     *
     * @param T 被依赖偏好值的类型，必须是Any的子类且可具体化（reified）
     * @param keyName 被依赖偏好值的键名
     * @param function 值转换函数，将被依赖偏好值（T?）转换为此节点的状态（Boolean）
     *
     * 使用示例：
     * ```kotlin
     * // 仅在用户等级为"premium"时启用
     * node.dependenceValueFrom<String>("user_level") { level ->
     *     level == "premium"
     * }
     *
     * // 当音量大于50时启用高级音效
     * node.dependenceValueFrom<Int>("volume") { volume ->
     *     (volume ?: 0) > 50
     * }
     *
     * // 多条件判断
     * node.dependenceValueFrom<String>("theme") { theme ->
     *     theme in listOf("dark", "amoled")  // 仅在深色主题下启用
     * }
     * ```
     *
     * 注意事项：
     * - 如果偏好值不存在，function会收到null值，需要妥善处理
     * - 调用此方法后，enable()/disable()将不再生效
     * - 与dependenceStateFrom二选一，多次调用会覆盖之前的配置
     * - 类型T必须与实际存储的类型匹配
     */
    inline fun <reified T : Any> dependenceValueFrom(keyName: String, crossinline function: (T?) -> Boolean) {
        val t = tree.editorHolder.getOnePrefEditor<T>(keyName).flow.map { value -> function(value) }
        setProducer (EnableStateProducer(t))
    }
}

/**
 * 依赖状态来源函数式接口
 *
 * 此接口定义了如何动态生成启用状态的Flow。
 * 实现类可以提供任意复杂的逻辑来计算状态，包括：
 * - 监听其他节点的状态变化
 * - 监听偏好值的变化
 * - 组合多个数据源
 * - 执行异步计算
 *
 * 典型使用场景：
 * ```kotlin
 * // 简单示例：直接返回一个Flow
 * val producer = DependenceStateProducer { someStateFlow }
 *
 * // 复杂示例：组合多个Flow
 * val producer = DependenceStateProducer {
 *     combine(flow1, flow2) { value1, value2 ->
 *         value1 == true && value2 > 10
 *     }
 * }
 * ```
 */
@JvmInline
value class EnableStateProducer(
    val stateSources : Flow<Boolean>
)

/**
 * Composable函数：获取状态节点的启用状态
 *
 * 这是一个便捷的扩展函数，用于在Compose UI中观察状态节点的启用状态。
 * 如果节点为null，则返回传入的默认enabled值。
 *
 * @param enabled 当节点为null时的默认返回值，默认为true（启用）
 * @return Boolean 节点的当前启用状态，如果节点为null则返回enabled参数
 *
 * 使用示例：
 * ```kotlin
 * @Composable
 * fun MyPreferenceItem() {
 *     val tree = LocalAutoPreference.current.dependenceTree
 *
 *     // 获取节点状态，如果节点不存在则默认启用
 *     val enabled = tree.get("my_option").state()
 *
 *     // 或者显式指定默认值
 *     val enabled2 = tree.get("another_option").state(enabled = false)
 *
 *     Switch(
 *         enabled = enabled,
 *         checked = ...,
 *         onCheckedChange = { ... }
 *     )
 * }
 * ```
 *
 * 注意事项：
 * - 此函数必须在@Composable作用域中调用
 * - 当节点状态变化时，会自动重组使用该状态的Composable
 * - 返回的状态已经考虑了所有依赖关系和提供者逻辑
 */
@Composable
fun EnableStateNode?.state(enabled: Boolean = true): Boolean {
    if (this == null) {
        return enabled
    }
    return this.flow.collectAsState(enabled).value ?: enabled
}
