@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidy.ui.material3.listitem.LocalListItemStyle
import androidy.ui.material3.listitem.SegmentedListItem
import androidy.ui.material3.listitem.expressive_style.SegmentedListItemStyleDefaults
import androidy.ui.material3.listitem.normal_style.colors
import kotlin.collections.set

@Composable
fun ColumnScope.SegmentedListItemComponentsDemo() {
    Text("normal")
    SegmentedListItem(
        content = { Text("普通可点击的ListItem") },
        supportingContent = { Text(longText) },
        leadingContent = { leading() },
        trailingContent = { OutlinedButton(onClick = {}) { Text("Button") } },
        onClick = {}
    )
    SegmentedListItem(
        enabled = false,
        content = { Text("Headline") },
        supportingContent = { Text("禁用的ListItem") },
        leadingContent = { leading() },
        trailingContent = { Text("Trailing") },
        onClick = {}
    )
    SegmentedListItem(
        selected = true,
        content = { Text("Headline") },
        supportingContent = { Text("被选择的ListItem") },
        leadingContent = { leading() },
        trailingContent = { Text("Trailing") },
        onClick = {}
    )
    Text("Check-Role")
    val checkedIndex2 = remember { mutableStateMapOf<Int, Boolean>() }
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        repeat(3) { index: Int ->
            SegmentedListItemCheckBoxGroup(index, checkedIndex2[index] ?: false, { checkedIndex2[index] = it })
        }
    }
    Text("Radio-Role")
    var selectedIndex2 by remember { mutableStateOf(0) }
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        repeat(3) {
            SegmentedListItemRadioGroup(it, selectedIndex2, { selectedIndex2 = it })
        }
    }
}

@Composable
private fun SegmentedListItemRadioGroup(
    index: Int,
    selectedIndex: Int,
    onSelected: (Int) -> Unit,
) {
    SegmentedListItem(
        content = { Text("Option $index") },
        supportingContent = { Text("this is option $index") },
        leadingContent = { RadioButton(selected = selectedIndex == index, onClick = { onSelected(index) }) },
        trailingContent = { Text("Trailing") },
//        style = LocalListItemStyle.currentSegmented.copy(containerColor = MaterialTheme.colorScheme.surfaceContainer),
        colors = LocalListItemStyle.currentSegmented.colors()
            .copy(containerColor = MaterialTheme.colorScheme.surfaceContainer),
        shapes = SegmentedListItemStyleDefaults.segmentedShape(index, 3),
        selected = selectedIndex == index,
        onClick = { onSelected(index) }
    )
}

@Composable
private fun SegmentedListItemCheckBoxGroup(
    index: Int,
    checked: Boolean,
    onChecked: (Boolean) -> Unit,
) {
    SegmentedListItem(
        content = { Text("Option $index") },
        supportingContent = { Text("this is option $index") },
        leadingContent = { Checkbox(checked = checked, onCheckedChange = { onChecked(it) }) },
        trailingContent = { Text("Trailing") },
        checked = checked,
//        style = LocalListItemStyle.currentSegmented.copy(containerColor = MaterialTheme.colorScheme.surfaceContainer),
        colors = LocalListItemStyle.currentSegmented.colors()
            .copy(containerColor = MaterialTheme.colorScheme.surfaceContainer),
        shapes = SegmentedListItemStyleDefaults.segmentedShape(index, 3),
        onCheckedChange = { onChecked(it) }
    )
//
//    ListItem(
//        content = { Text("Option $index") },
//        supportingContent = { Text("this is option $index") },
//        leadingContent = { Checkbox(checked = checked, onCheckedChange = { onChecked(it) }) },
//        trailingContent = { Text("Trailing") },
//        checked = checked,
//        colors = ListItemDefaults.segmentedColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
//        shapes = ListItemDefaults.segmentedShapes(index, 3),
//        onCheckedChange = { onChecked(it) }
//    )
}
