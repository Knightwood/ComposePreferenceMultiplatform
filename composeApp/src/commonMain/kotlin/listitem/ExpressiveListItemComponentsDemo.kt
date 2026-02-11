import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidy.ui.material3.listitem.ExpressiveListItem

@Composable
fun ColumnScope.ExpressiveListItemComponentsDemo() {
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
