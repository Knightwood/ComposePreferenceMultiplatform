package com.github.knight.composepreference_multi

import App
import PreferencePage
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kiylx.compose_lib.pref_component.composepreference_multi.DataStorePreferenceHolder
import com.kiylx.compose_lib.pref_component.datastore.getDataStore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataStore = getDataStore(applicationContext,"ee.preferences_pb")
        val holder = DataStorePreferenceHolder.instance(dataStore)

        setContent {
            PreferencePage(holder)
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}