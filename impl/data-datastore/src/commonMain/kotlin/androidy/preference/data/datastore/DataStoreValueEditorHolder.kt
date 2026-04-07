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

package androidy.preference.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidy.preference.data.core.AbstractValueEditorHolder
import androidy.preference.data.core.ISingleValueEditor
import kotlin.reflect.KClass

/**
 * 向界面提供、管理PreferenceProvider
 * ```
 * val Context.store by preferencesDataStore(name = "test")
 *
 * fun example(context: Context){
 *     val holder = DataStorePreferenceHolder.instance(context.store)
 * }
 *
 * ```
 */
class DataStoreValueEditorHolder internal constructor(
    private val dataStore: DataStore<Preferences>,
) : AbstractValueEditorHolder() {
    override val editorProvider: ISinglePrefValueEditorProvider = object : ISinglePrefValueEditorProvider {
        override fun <T : Any> createOnePrefEditor(
            keyName: String,
            cls: KClass<T>,
        ): ISingleValueEditor<T> {
            return DataStoreSingleValueEditor(keyName, cls, dataStore, scope)
        }
    }

    companion object {

        @Volatile
        var ps: DataStoreValueEditorHolder? = null

        fun instance(
            dataStore: DataStore<Preferences>,
        ): DataStoreValueEditorHolder {
            return ps ?: synchronized(this) {
                ps ?: DataStoreValueEditorHolder(dataStore).also {
                    ps = it
                }
            }
        }
    }
}
