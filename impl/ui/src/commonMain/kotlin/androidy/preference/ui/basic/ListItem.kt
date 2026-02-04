package androidy.preference.ui.basic

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidy.preference.ui.style.ListItemStyleHolder
import androidy.preference.ui.style.ListItemTokens

@Composable
fun ListItemDivider(paddingValues: PaddingValues = PaddingValues(ListItemTokens.DividerSpace)) {
    HorizontalDivider(modifier = Modifier.padding(paddingValues))
}

@Composable
fun ListItem() {
    with(ListItemStyleHolder.get()) {
        BadgedBox(
            badge = {

            },
        ) {
            Surface {
                Row {
                    Box(modifier = Modifier.align(alignment)) {

                    }
                    Column {

                    }
                    Box {

                    }
                }
            }
        }
    }
}
