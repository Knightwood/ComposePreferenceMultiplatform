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

package com.github.knight.composepreference_multi

import MainScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import NewComponents2
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.datastore.dataStore
import androidy.preference.data.datastore.DataStorePreferenceHolder
import androidy.preference.data.mmkv.MMKVPreferenceHolder
import androidy.preference.helper.datastore.getDataStore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val holder = getHolder()
        setContent {
            MaterialTheme {
                MainScreen(holder)
            }
        }
    }

    fun getHolder(): DataStorePreferenceHolder {
        //1. 使用dataStore存储偏好值
        val ds = getDataStore(applicationContext, "test.pb")
        val holder = DataStorePreferenceHolder.instance(ds)

        //2. 使用mmkv存储偏好值
//        val holder = MMKVPreferenceHolder.instance(MMKV.defaultMMKV())

        //3. 使用sharedprefrence存储偏好值
//        val holder =
//            OldPreferenceHolder.instance(
//                AppCtx.instance.getSharedPreferences(
//                    "ddd",
//                    Context.MODE_PRIVATE
//                )
//            )
        return holder
    }
}
