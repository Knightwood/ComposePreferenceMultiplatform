import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Usb
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidy.preference.ui.basic.ListItem
import androidy.preference.ui.style.ListItemDefaults

@Composable
fun ListItemTest(modifier: Modifier = Modifier) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        ListItem(
            headlineContent = { Text("Headline") },
            overlineContent = { Text("Overline") },
            supportingContent = { Text("Supporting，SupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupportingSupporting") },
            leadingContent = { Icon(imageVector = Icons.Default.Usb, contentDescription = "one") },
            trailingContent = { Text("Trailing") },
        )
        ListItem(
            style = ListItemDefaults.style(alignment = Alignment.CenterVertically),
            headlineContent = { Text("Headline") },
            supportingContent = { Text("Supporting") },
            leadingContent = { Icon(imageVector = Icons.Default.Usb, contentDescription = "one") },
            trailingContent = { Text("Trailing") },
        )
        ListItem(
            headlineContent = { Text("Headline") },
            leadingContent = { Icon(imageVector = Icons.Default.Usb, contentDescription = "one") },
            trailingContent = { Text("Trailing") },
        )
        ListItem(
            headlineContent = { Text("Headline") },
            leadingContent = { Icon(imageVector = Icons.Default.Usb, contentDescription = "one") },
        )
        ListItem(
            headlineContent = { Text("Headline") },
            trailingContent = { Text("Trailing") },
        )
    }
}
