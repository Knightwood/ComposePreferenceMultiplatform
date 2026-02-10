import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExposurePlus1
import androidx.compose.material.icons.filled.ExposurePlus2
import androidx.compose.material.icons.filled.Task
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidy.preference.data.core.PreferenceHolder

@Composable
fun MainScreen(holder : PreferenceHolder) {
    var selected by remember { mutableIntStateOf(0) }
    CompositionLocalProvider() {
        Scaffold(bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selected == 0,
                    onClick = { selected = 0 },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.ExposurePlus1,
                            contentDescription = "one"
                        )
                    },
                    label = { Text("仅组件") }
                )

                NavigationBarItem(
                    selected = selected == 1,
                    onClick = { selected = 1 },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.ExposurePlus2,
                            contentDescription = "two"
                        )
                    },
                    label = { Text("自动读写") }
                )

                NavigationBarItem(
                    selected = selected == 2,
                    onClick = { selected = 2 },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Task,
                            contentDescription = "listitem"
                        )
                    },
                    label = { Text("ListItem") }
                )
            }
        }) {
            Surface(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(it)
            ) {
                if (selected == 0) {
                    CrossComponentsTestScreen()
                } else if (selected == 1) {
                    AutoComponentsTestScreen(holder)
                }else if (selected == 2) {
                    ListItemTestScreen()
                }
            }
        }
    }
}

