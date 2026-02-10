import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidy.ui.material3.listitem.ExpressiveListItem
import androidy.ui.material3.listitem.BaselineListItem
import androidy.ui.material3.listitem.LocalListItemStyle
import kotlin.collections.getValue
import kotlin.collections.setValue

private const val longText =
    "Support,Support,Support,Support,Support,Support,Support,Support,Support,Support,Support,Support,"

@Composable
fun ListItemTestScreen() {
    Column {
        LocalListItemStyle.provide() {
            ListItemTest()
        }
    }
}

@Composable
fun ListItemTest() {
    Box() {
        val scrollstate = rememberScrollState()
        ListItemInternal(
            modifier = Modifier
                .padding(16.dp)
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
        HorizontalDivider()
        Text("Expressive主题")
        Text("normal")
        ExpressiveListItem(
            content = { Text("普通可点击的ListItem") },
            supportingContent = { Text(longText) },
            leadingContent = { leading() },
            trailingContent = { OutlinedButton(onClick = {}){Text("Button")} },
            onClick = {}
        )
        ExpressiveListItem(
            enabled = false,
            content = { Text("Headline") },
            supportingContent = { Text("禁用的ListItem") },
            leadingContent = { leading() },
            trailingContent = { Text("Trailing") },
            onClick = {}
        )
        ExpressiveListItem(
            selected = true,
            content = { Text("Headline") },
            supportingContent = { Text("被选择的ListItem") },
            leadingContent = { leading() },
            trailingContent = { Text("Trailing") },
            onClick = {}
        )
        Text("Check-Role")
        val checkedIndex = remember { mutableStateMapOf<Int, Boolean>() }
        repeat(3) { index: Int ->
            ExpressiveListItemCheckBoxGroup(index, checkedIndex[index] ?: false, { checkedIndex[index] = it })
        }
        Text("Radio-Role")
        var selectedIndex by remember { mutableStateOf(0) }
        repeat(3) {
            ExpressiveListItemRadioGroup(it, selectedIndex, { selectedIndex = it })
        }
    }
}

@Composable
private fun ExpressiveListItemRadioGroup(
    index: Int,
    selectedIndex: Int,
    onSelected: (Int) -> Unit,
) {
    ExpressiveListItem(
        content = { Text("Option $index") },
        supportingContent = { Text("this is option $index") },
        leadingContent = { RadioButton(selected = selectedIndex == index, onClick = { onSelected(index) }) },
        trailingContent = { Text("Trailing") },
        selected = selectedIndex == index,
        onClick = { onSelected(index) }
    )
}

@Composable
private fun ExpressiveListItemCheckBoxGroup(
    index: Int,
    checked: Boolean,
    onChecked: (Boolean) -> Unit,
) {
    ExpressiveListItem(
        content = { Text("Option $index") },
        supportingContent = { Text("this is option $index") },
        leadingContent = { Checkbox(checked = checked, onCheckedChange = { onChecked(it) }) },
        trailingContent = { Text("Trailing") },
        checked = checked,
        onCheckedChange = { onChecked(it) }
    )
}

@Composable
private fun leading() {
    Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "one")
}
