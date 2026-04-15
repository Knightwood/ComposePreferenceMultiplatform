package androidy.preference.data.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

/**
 * 自动偏好作用域 Composable 函数
 *
 * 这是一个高级声明式API，用于在Compose UI中方便地访问和操作偏好值及其依赖关系。
 * 它封装了编辑器获取、依赖树访问和状态管理的样板代码，提供更简洁的使用方式。
 *
 * 核心功能：
 * 1. 自动从LocalAutoPreference获取当前的偏好持有者
 * 2. 根据key获取对应的单值编辑器（ISingleValueEditor）
 * 3. 提供对依赖关系树（DependenceTree）的访问
 * 4. 在闭包中暴露编辑器和依赖树，方便进行状态观察和依赖配置
 *
 * @param T 偏好值的类型，必须是Any的子类且可具体化（reified）
 * @param key 偏好值的唯一标识键名
 * @param content 内容闭包，接收编辑器和依赖树作为参数
 *        - ISingleValueEditor<T>: 用于读写偏好值
 *        - DependenceTree: 用于配置和管理依赖关系
 *
 * 完整使用示例：
 * ```kotlin
 * @Composable
 * fun AutoSyncPreference() {
 *     IAutoScope<Boolean>("auto_sync") { editor, tree ->
 *         // 1. 观察偏好值状态
 *         val isChecked = editor.flow.collectAsState(false)
 *
 *         // 2. 配置依赖关系：仅当"network_enabled"为true时启用
 *         val enabled = tree.get("auto_sync") { holder ->
 *             dependenceValueFrom<Boolean>("network_enabled") { networkEnabled ->
 *                 networkEnabled ?: false
 *             }
 *         }.state()
 *
 *         // 3. 渲染UI
 *         PreferenceSwitchItem(
 *             title = { Text("自动同步") },
 *             subtitle = { Text("在后台自动同步数据") },
 *             checked = isChecked.value ?: false,
 *             onCheckedChange = { newValue ->
 *                 // 写入新值
 *                 editor.writeAsync(newValue)
 *
 *                 // 根据开关状态控制其他选项的启用/禁用
 *                 if (newValue) {
 *                     tree.enable("sync_interval", "sync_wifi_only")
 *                 } else {
 *                     tree.disable("sync_interval", "sync_wifi_only")
 *                 }
 *             },
 *             enabled = enabled
 *         )
 *     }
 * }
 * ```
 *
 * 复杂依赖场景示例：
 * ```kotlin
 * @Composable
 * fun AdvancedOptions() {
 *     IAutoScope<String>("display_mode") { editor, tree ->
 *         val mode = editor.flow.collectAsState("standard")
 *
 *         // 仅在"advanced"模式下启用高级选项
 *         val advancedEnabled = tree.get("advanced_option") { holder ->
 *             dependenceValueFrom<String>("display_mode") { currentMode ->
 *                 currentMode == "advanced"
 *             }
 *         }.state()
 *
 *         Column {
 *             // 显示模式选择器
 *             PreferenceRadioGroup(
 *                 selectedValue = mode.value ?: "standard",
 *                 onValueChange = { editor.writeAsync(it) },
 *                 options = listOf("standard", "advanced", "expert")
 *             )
 *
 *             // 高级选项（受依赖控制）
 *             PreferenceSwitchItem(
 *                 title = { Text("启用实验性功能") },
 *                 checked = false,
 *                 onCheckedChange = { /* ... */ },
 *                 enabled = advancedEnabled  // 仅在高级模式时可用
 *             )
 *         }
 *     }
 * }
 * ```
 *
 * 架构优势：
 * - **声明式**: 通过闭包清晰地表达偏好项的逻辑
 * - **类型安全**: 利用Kotlin的reified类型参数保证类型安全
 * - **响应式**: 自动响应偏好值和依赖状态的变化
 * - **解耦**: 将状态管理、依赖逻辑和UI渲染分离
 *
 * 注意事项：
 * - 必须在@Composable作用域中调用
 * - 需要在LocalAutoPreference.Provide提供的上下文中使用
 * - content闭包中的editor和tree可以直接使用，无需手动管理生命周期
 * - 建议在content中使用collectAsState观察editor.flow，而非直接调用readValue()
 *
 * @see LocalAutoPreference
 * @see ISingleValueEditor
 * @see EnableStateNodeTree
 */
@Composable
inline fun <reified T : Any> IAutoScope(
    key: String,
    content: @Composable (ISingleValueEditor<T>, EnableStateNodeTree) -> Unit,
) {
    val holder = LocalAutoPreference.current
    val editor = holder.getOnePrefEditor<T>(key)
    val dependenceTree = holder.dependenceTree
    content(editor, dependenceTree)
}

/**
 * 使用示例：完整的偏好项实现
 *
 * 此示例展示了如何结合IAutoScope、编辑器和依赖树创建一个功能完整的偏好开关项。
 *
 * 场景描述：
 * - 一个主开关"key1"，控制某个功能的启用/禁用
 * - 该开关的可用性依赖于"key2"节点的启用状态
 * - 该开关的可用性还依赖于"key3"偏好值的条件判断
 * - 当开关状态改变时，需要更新相关依赖项的状态
 *
 * 注意：此为私有示例函数，仅用于文档说明，不应在实际代码中使用。
 */
@Composable
private fun Example() {
    LocalAutoPreference.Provide(holder = EmptyValueEditorHodler()) {
        IAutoScope<Boolean>("key1") { editor, tree ->
            // 1. 观察"key1"的偏好值状态
            val checked = editor.flow.collectAsState(false)

            // 2. 配置"key1"节点的依赖关系
            val enabled = tree.get("key1") { holder ->
                // 方式A: 依赖于另一个节点（"key2"）的启用状态
                dependenceStateFrom("key2") { key2Enabled ->
                    key2Enabled ?: false  // 如果key2节点不存在或为null，则禁用
                }

                // 方式B: 依赖于另一个偏好值（"key3"）的值
                // 注意：dependenceStateFrom和dependenceValueFrom二选一
                // 使用依赖状态后，节点不再响应自身的enable/disable调用
                dependenceValueFrom<String>("key3") { key3Value ->
                    // 根据key3的值决定是否启用
                    // 这里仅作示例，实际逻辑应根据业务需求定制
                    true
                }
            }.state()  // 获取最终的启用状态

            // 3. 渲染偏好开关项
            PreferenceSwitchItem(
                checked = checked.value ?: false,  // 当前开关状态
                onCheckedChange = { newValue ->
                    // 写入新的偏好值
                    editor.write(newValue)

                    // 根据新状态控制相关依赖项
                    // 例如：当key1启用时，启用key2和key3相关的选项
                    tree.enable("key2", "key3")
                },
                enabled = enabled,  // 应用依赖关系计算出的启用状态
                title = {}  // 标题Composable（此处为空示例）
            )
        }
    }
}

/**
 * 偏好开关项组件（占位符）
 *
 * 此为示例中的UI组件签名，实际项目中应替换为真实的实现。
 *
 * @param checked 当前选中状态
 * @param onCheckedChange 状态改变回调
 * @param enabled 是否启用（影响UI的可交互性和视觉表现）
 * @param title 标题内容的Composable
 */
@Composable
private fun PreferenceSwitchItem(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    enabled: Boolean,
    title: () -> Unit
) {
    // 实际实现应包含Switch或其他UI组件
}
