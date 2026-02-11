import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidy.ui.material3.listitem.LocalListItemStyle
import listitem.ListItemComponentsDemo


@Composable
fun ListItemTestScreen() {
    LocalListItemStyle.provide() {
        Box() {
            val scrollstate = rememberScrollState()
            ListItemInternal(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(scrollstate)
            )
            LocalVerticalScrollbar(
                modifier = Modifier.align(Alignment.CenterEnd),
                scrollState = scrollstate,
            )
        }
    }
}

@Composable
private fun ListItemInternal(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("普通主题")
        ListItemComponentsDemo()

        HorizontalDivider(modifier = Modifier.padding(bottom = 36.dp))
        Text("Expressive主题")
        ExpressiveListItemComponentsDemo()

        HorizontalDivider(modifier = Modifier.padding(bottom = 36.dp))
        Text("SegmentedExpressive主题")
        SegmentedListItemComponentsDemo()
    }
}

@Composable
fun leading() {
    Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "one")
}

const val longText =
    "Support,Support,Support,Support,Support,Support,Support,Support,Support,Support,Support,Support,"
