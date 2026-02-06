import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Usb
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidy.preference.ui.list_item.SealListItem
import androidy.preference.ui.list_item.normal_style.ListItemDefaults

private const val longText = "Support,Support,Support,Support,Support,Support,Support,Support,Support,Support,Support,Support,"
@Composable
fun ListItemTest(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SealListItem(
            headlineContent = { Text("Headline") },
            overlineContent = { Text("Overline") },
            supportingContent = { Text(longText) },
            leadingContent = { Icon(imageVector = Icons.Default.Usb, contentDescription = "one") },
            trailingContent = { Text("Trailing") },
        )
        ListItem(
            headlineContent = { Text("ListItem---Headline") },
            overlineContent = { Text("Overline") },
            supportingContent = { Text(longText) },
            leadingContent = { Icon(imageVector = Icons.Default.Usb, contentDescription = "one") },
            trailingContent = { Text("Trailing") },
        )
        SealListItem(
            enabled = false,
            headlineContent = { Text("Headline") },
            overlineContent = { Text("Overline") },
            supportingContent = { Text(longText) },
            leadingContent = { Icon(imageVector = Icons.Default.Usb, contentDescription = "one") },
            trailingContent = { Text("Trailing") },
        )
        SealListItem(
            style = ListItemDefaults.style(alignment = Alignment.CenterVertically),
            headlineContent = { Text("Headline") },
            supportingContent = { Text("Supporting") },
            leadingContent = { Icon(imageVector = Icons.Default.Usb, contentDescription = "one") },
            trailingContent = { Text("Trailing") },
        )
        SealListItem(
            headlineContent = { Text("Headline") },
            leadingContent = { Icon(imageVector = Icons.Default.Usb, contentDescription = "one") },
            trailingContent = { Text("Trailing") },
        )
        ListItem(
            headlineContent = { Text("ListItem---Headline") },
            leadingContent = { Icon(imageVector = Icons.Default.Usb, contentDescription = "one") },
            trailingContent = { Text("Trailing") },
        )
        SealListItem(
            headlineContent = { Text("Headline") },
            leadingContent = { Icon(imageVector = Icons.Default.Usb, contentDescription = "one") },
        )
        SealListItem(
            headlineContent = { Text("Headline") },
            trailingContent = { Text("Trailing") },
        )
    }
}
