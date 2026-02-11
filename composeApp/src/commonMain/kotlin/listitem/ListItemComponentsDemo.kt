package listitem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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
import androidy.ui.material3.listitem.BaselineListItem
import leading
import longText

@Composable
fun ColumnScope.ListItemComponentsDemo() {
    Text("三行")
    BaselineListItem(
        headlineContent = { Text("Headline") },
        overlineContent = { Text("Overline") },
        supportingContent = { Text(longText) },
        leadingContent = { leading() },
        trailingContent = { Text("Trailing") },
    )
    BaselineListItem(
        enabled = false,
        headlineContent = { Text("禁用") },
        overlineContent = { Text("Overline") },
        supportingContent = { Text(longText) },
        leadingContent = { leading() },
        trailingContent = { Text("Trailing") },
    )
    Text("两行")
    var isCheckEnter by remember { mutableStateOf(false) }
    BaselineListItem(
        headlineContent = { Text("Headline") },
        supportingContent = { Text(if (isCheckEnter) longText else "Supporting") },
        leadingContent = { leading() },
        trailingContent = { Text("Trailing") },
    )
    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("更长的文本")
        Switch(checked = isCheckEnter, onCheckedChange = { isCheckEnter = it })
    }
    Text("单行")
    BaselineListItem(
        headlineContent = { Text("Headline") },
        leadingContent = {
            leading()
        },
        trailingContent = { Text("Trailing") },
    )
}
