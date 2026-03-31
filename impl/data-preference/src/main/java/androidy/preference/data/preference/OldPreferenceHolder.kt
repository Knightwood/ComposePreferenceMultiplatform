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

package androidy.preference.data.preference

import android.content.SharedPreferences
import androidy.preference.data.core.ISinglePrefValueEditor
import androidy.preference.data.core.AbstractPreferenceHolder
import kotlin.reflect.KClass

/**
 * 向界面提供、管理PreferenceProvider
 */
class OldPreferenceHolder internal constructor(
    private val sp: SharedPreferences,
) : AbstractPreferenceHolder() {

    override val editorProvider: ISinglePrefValueEditorProvider = object : ISinglePrefValueEditorProvider {
        override fun <T : Any> createOnePrefEditor(
            keyName: String,
            defaultValue: T,
            cls: KClass<T>,
        ): ISinglePrefValueEditor<T> {
            return SPEditor(sp, keyName, defaultValue)
        }
    }

    companion object {
        @Volatile
        var ps: AbstractPreferenceHolder? = null
        fun instance(
            sp: SharedPreferences,
        ): AbstractPreferenceHolder {
            return ps ?: synchronized(this) {
                ps ?: OldPreferenceHolder(sp)
                    .also { ps = it }
            }
        }
    }
}
