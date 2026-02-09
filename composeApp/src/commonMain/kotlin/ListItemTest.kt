import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidy.preference.ui.list_item.ExpressiveListItem
import androidy.preference.ui.list_item.SealListItem

private const val longText =
    "Support,Support,Support,Support,Support,Support,Support,Support,Support,Support,Support,Support,"

@Composable
fun ListItemTest(modifier: Modifier = Modifier) {
    Box() {
        val scrollstate = rememberScrollState()
        ListItemInternal(
            modifier = modifier
                .padding(8.dp)
                .verticalScroll(scrollstate)
        )
        VerticalScrollbar(
            adapter = rememberScrollbarAdapter(scrollstate),
            modifier = Modifier.align(Alignment.CenterEnd),
        )
    }
}

@Composable
private fun ListItemInternal(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SealListItem(
            headlineContent = { Text("Headline") },
            overlineContent = { Text("Overline") },
            supportingContent = { Text(longText) },
            leadingContent = { leading() },
            trailingContent = { Text("Trailing") },
        )
        SealListItem(
            enabled = false,
            headlineContent = { Text("Headline") },
            overlineContent = { Text("Overline") },
            supportingContent = { Text(longText) },
            leadingContent = { leading() },
            trailingContent = { Text("Trailing") },
        )
        var isCheckEnter by remember { mutableStateOf(false) }
        SealListItem(
            headlineContent = { Text("Headline") },
            supportingContent = { Text(if (isCheckEnter) longText else "Supporting") },
            leadingContent = { leading() },
            trailingContent = { Text("Trailing") },
        )
        Switch(checked = isCheckEnter, onCheckedChange = { isCheckEnter = it })
        SealListItem(
            headlineContent = { Text("Headline") },
            leadingContent = {
                leading()
            },
            trailingContent = { Text("Trailing") },
        )
        SealListItem(
            headlineContent = { Text("Headline") },
            leadingContent = { leading() },
        )
        SealListItem(
            headlineContent = { Text("Headline") },
            trailingContent = { Text("Trailing") },
        )

        HorizontalDivider()

        var isCheck by remember { mutableStateOf(false) }
        ExpressiveListItem(
            content = { Text("Headline") },
            supportingContent = { Text(longText) },
            leadingContent = { leading() },
            trailingContent = { Text("Trailing") },
            checked = isCheck,
            onCheckedChange = {
                isCheck = it
            }
        )
    }
}

@Composable
fun leading() {
    Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "one")
}
