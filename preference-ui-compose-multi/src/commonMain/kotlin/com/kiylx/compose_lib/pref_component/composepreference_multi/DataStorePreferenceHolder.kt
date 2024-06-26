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

package com.kiylx.compose_lib.pref_component.composepreference_multi;

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.kiylx.libx.pref_component.core.IPreferenceEditor
import com.kiylx.libx.pref_component.core.PreferenceHolder

/** 向界面提供、管理PreferenceProvider */
class DataStorePreferenceHolder internal constructor(
    private val datastore: DataStore<Preferences>
) : PreferenceHolder() {
    private fun dataStore() = datastore

    override fun <T : Any> getSingleDataEditor(
        keyName: String,
        defaultValue: T,
    ): IPreferenceEditor<T> {
        return hashMap[keyName]?.let {
            it as IPreferenceEditor<T>
        } ?: let {
            val tmp = DataStoreEditor(keyName, defaultValue, dataStore())
            hashMap[keyName] = tmp
            tmp
        }
    }

    companion object {
        @Volatile
        var ps: DataStorePreferenceHolder? = null
        fun instance(
            datastore: DataStore<Preferences>
        ): DataStorePreferenceHolder {
            return ps ?: synchronized(this) {
                ps ?: DataStorePreferenceHolder(datastore).also { ps = it }
            }
        }
    }
}