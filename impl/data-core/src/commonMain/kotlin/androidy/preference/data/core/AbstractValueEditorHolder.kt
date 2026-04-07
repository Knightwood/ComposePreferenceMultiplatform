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
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import kotlin.reflect.KClass

abstract class
AbstractValueEditorHolder(val scope: CoroutineScope) {
    constructor() : this(CoroutineScope(Dispatchers.IO) + SupervisorJob())

    abstract val editorProvider: ISinglePrefValueEditorProvider

    //记录每个偏好值的key与其对应的编辑工具
    val prefEditorHashMap: HashMap<String, ISingleValueEditor<*>> = hashMapOf()
    val dependenceTree = DependenceTree(this)

    //<editor-fold desc="偏好值读写">
    /**
     * 获取编辑单个偏好值的读写工具，其持有某个key对应的偏好值
     *
     * @param keyName String  key
     */
    inline fun <reified T : Any> getOnePrefEditor(
        keyName: String,
    ): ISingleValueEditor<T> {
        return prefEditorHashMap.getOrPut(keyName, {
            editorProvider.createOnePrefEditor<T>(keyName, T::class)
        }) as ISingleValueEditor<T>
    }
    //</editor-fold>

    /**
     * 用于创建用于读写指定偏好值工具类实例
     */
    interface ISinglePrefValueEditorProvider {
        fun <T : Any> createOnePrefEditor(
            keyName: String,
            cls: KClass<T>,
        ): ISingleValueEditor<T>
    }

}
