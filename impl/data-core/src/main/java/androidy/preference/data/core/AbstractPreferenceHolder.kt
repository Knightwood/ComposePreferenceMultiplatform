/*
 * Copyright 2024 [KnightWood]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package androidy.preference.data.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import kotlin.collections.get
import kotlin.reflect.KClass

abstract class
AbstractPreferenceHolder(val scope: CoroutineScope) {
    constructor() : this(CoroutineScope(Dispatchers.IO) + SupervisorJob())

    /**
     * 用于创建用于读写指定偏好值工具类实例
     */
    interface ISinglePrefValueEditorProvider {
        fun <T : Any> createOnePrefEditor(
            keyName: String,
            defaultValue: T,
            cls: KClass<T>,
        ): ISinglePrefValueEditor<T>
    }

    abstract val editorProvider: ISinglePrefValueEditorProvider

    //记录每个偏好值的key与其对应的编辑工具
    val hashMap: HashMap<String, ISinglePrefValueEditor<*>> = hashMapOf()

    //<editor-fold desc="偏好值读写">
    /**
     * 获取编辑单个偏好值的读写工具，其持有某个key对应的偏好值
     *
     * @param keyName String  key
     * @param defaultValue T? 默认值
     *
     */
    @PublishedApi
    internal inline fun <reified T : Any> getOnePrefEditor(
        keyName: String,
        defaultValue: T,
    ): ISinglePrefValueEditor<T> {
        return hashMap.getOrPut(keyName, {
            editorProvider.createOnePrefEditor<T>(keyName, defaultValue, T::class)
        }) as ISinglePrefValueEditor<T>
    }

    inline fun <reified T : Any> write(keyName: String, data: T?) {
        val editor = (hashMap[keyName] as? ISinglePrefValueEditor<T>) ?: return
        scope.launch {
            editor.write(data)
        }
    }

    inline fun <reified T : Any> observe(keyName: String, defaultValue: T): Flow<T> {
        val editor = getOnePrefEditor(keyName, defaultValue)
        return editor.flow()
    }
    //</editor-fold>


    //<editor-fold desc="依赖树">
//    //记录每个key对应的enable状态
//    val dependenceTree: HashMap<String, DependenceNode> = hashMapOf()
//
//    init {
//        //放入默认的公共依赖的根节点
//        dependenceTree[DependenceNode.rootName] = DependenceNode(
//            enable = true,
//            keyName = DependenceNode.rootName
//        )
//    }
//    /**
//     * 将注册者自身(preference compose function)的状态记录下来，并返回注册者依赖的节点状态
//     *
//     * @param currentKey 注册者自身的key
//     * @param currentState 注册者自身的状态
//     * @param targetKey 注册者要依赖于哪个节点的key，如果为null，则依赖于根节点状态
//     * @return 返回依赖的节点的状态,若targetKey为null，返回自身节点状态
//     */
//    fun getDependence(
//        currentKey: String,
//        currentState: Boolean,
//        targetKey: String? = null,
//    ): DependenceNode {
//        if (!dependenceTree.contains(currentKey)) {
//            val node = DependenceNode(currentState, currentKey)
//            dependenceTree.putIfAbsent(currentKey, node)
//        }
//        return targetKey?.let {
//            dependenceTree[it]
//        } ?: dependenceTree[currentKey]!!
//    }
//
//    /**
//     * 注册并返回key的状态
//     */
//    fun registerDependence(
//        key: String,
//        state: Boolean,
//    ): DependenceNode {
//        if (!dependenceTree.contains(key)) {
//            val node = DependenceNode(state, key)
//            dependenceTree.putIfAbsent(key, node)
//        }
//        return dependenceTree[key]!!
//    }
//
//    /**
//     * 获取某个key对应的状态
//     */
//    fun getDependence(
//        key: String,
//    ): DependenceNode? {
//        return dependenceTree[key]
//    }
//
//    /**
//     * 获取某个key对应的状态,如不存在，返回默认启用状态
//     */
//    fun getDependenceNotEmpty(
//        key: String?,
//        enable: Boolean = true,
//    ): DependenceNode {
//        return dependenceTree[key] ?: DependenceNode(enable, "")
//    }


    //</editor-fold>
}

//class DependenceNode(
//    enable: Boolean,
//    val keyName: String,
//) {
//    val enableStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(enable)
//
//    //    val enableState = mutableStateOf(enable)
//    fun setEnabled(enable: Boolean) {
//        enableStateFlow.value = enable
//    }
//
//    companion object {
//        const val rootName = "Pref_Dependence_Node_Root"
//    }
//}
