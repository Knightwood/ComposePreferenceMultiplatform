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
import androidx.datastore.preferences.core.edit
import androidy.preference.data.core.ISingleValueEditor
import androidy.preference.helper.datastore.DataStoreKeyUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.reflect.KClass

private const val TAG = "DataStoreEditor"

/**
 * 提供偏好值的读写，datastore实现功能版本
 */
class DataStoreSingleValueEditor<T : Any>(
    val keyName: String,
    val cls: KClass<T>,
    val dataStore: DataStore<Preferences>,
    val scope: CoroutineScope,
) : ISingleValueEditor<T> {
    var key: Preferences.Key<T> = DataStoreKeyUtils.getKey<T>(keyName, cls)

    override val flow: Flow<T?> = dataStore.data.map { preferences -> preferences[key] }

    override suspend fun readValueAsync(): T? {
        return flow.last()
    }

    override suspend fun writeAsync(data: T?) {
        dataStore.edit {
            if (data == null) {
                it.remove(key)
            } else {
                it[key] = data
            }
        }
    }

    override fun write(data: T?) {
        scope.launch {
            withContext(Dispatchers.IO) {
                writeAsync(data)
            }
        }
    }

}
