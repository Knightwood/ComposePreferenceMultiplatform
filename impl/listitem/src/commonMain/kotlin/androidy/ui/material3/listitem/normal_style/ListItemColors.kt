package androidy.ui.material3.listitem.normal_style

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidy.ui.material3.listitem.basic.invalidUse
import androidy.ui.material3.listitem.interactive.StateColors
import androidy.ui.material3.listitem.interactive.StateShapes

@Immutable
class ListItemColors(
    // default
    val containerColor: Color,
    val contentColor: Color,
    val leadingContentColor: Color,
    val trailingContentColor: Color,
    val overlineContentColor: Color,
    val supportingContentColor: Color,
    // disabled
    val disabledContainerColor: Color,
    val disabledContentColor: Color,
    val disabledLeadingContentColor: Color,
    val disabledTrailingContentColor: Color,
    val disabledOverlineContentColor: Color,
    val disabledSupportingContentColor: Color,
    // selected
    val selectedContainerColor: Color = containerColor,
    val selectedContentColor: Color = contentColor,
    val selectedLeadingContentColor: Color = leadingContentColor,
    val selectedTrailingContentColor: Color = trailingContentColor,
    val selectedOverlineContentColor: Color = overlineContentColor,
    val selectedSupportingContentColor: Color = supportingContentColor,
    // dragged
    val draggedContainerColor: Color = containerColor,
    val draggedContentColor: Color = contentColor,
    val draggedLeadingContentColor: Color = leadingContentColor,
    val draggedTrailingContentColor: Color = trailingContentColor,
    val draggedOverlineContentColor: Color = overlineContentColor,
    val draggedSupportingContentColor: Color = supportingContentColor,
){
    fun copy(
        // default
        containerColor: Color = this.containerColor,
        contentColor: Color = this.contentColor,
        leadingContentColor: Color = this.leadingContentColor,
        trailingContentColor: Color = this.trailingContentColor,
        overlineContentColor: Color = this.overlineContentColor,
        supportingContentColor: Color = this.supportingContentColor,
        // disabled
        disabledContainerColor: Color = this.disabledContainerColor,
        disabledContentColor: Color = this.disabledContentColor,
        disabledLeadingContentColor: Color = this.disabledLeadingContentColor,
        disabledTrailingContentColor: Color = this.disabledTrailingContentColor,
        disabledOverlineContentColor: Color = this.disabledOverlineContentColor,
        disabledSupportingContentColor: Color = this.disabledSupportingContentColor,
        // selected
        selectedContainerColor: Color = this.selectedContainerColor,
        selectedContentColor: Color = this.selectedContentColor,
        selectedLeadingContentColor: Color = this.selectedLeadingContentColor,
        selectedTrailingContentColor: Color = this.selectedTrailingContentColor,
        selectedOverlineContentColor: Color = this.selectedOverlineContentColor,
        selectedSupportingContentColor: Color = this.selectedSupportingContentColor,
        // dragged
        draggedContainerColor: Color = this.draggedContainerColor,
        draggedContentColor: Color = this.draggedContentColor,
        draggedLeadingContentColor: Color = this.draggedLeadingContentColor,
        draggedTrailingContentColor: Color = this.draggedTrailingContentColor,
        draggedOverlineContentColor: Color = this.draggedOverlineContentColor,
        draggedSupportingContentColor: Color = this.draggedSupportingContentColor,
    ): ListItemColors {
        return ListItemColors(
            containerColor = containerColor.invalidUse { this.containerColor },
            contentColor = contentColor.invalidUse { this.contentColor },
            leadingContentColor = leadingContentColor.invalidUse { this.leadingContentColor },
            trailingContentColor = trailingContentColor.invalidUse { this.trailingContentColor },
            overlineContentColor = overlineContentColor.invalidUse { this.overlineContentColor },
            supportingContentColor =
                supportingContentColor.invalidUse { this.supportingContentColor },
            disabledContainerColor =
                disabledContainerColor.invalidUse { this.disabledContainerColor },
            disabledContentColor = disabledContentColor.invalidUse { this.disabledContentColor },
            disabledLeadingContentColor =
                disabledLeadingContentColor.invalidUse { this.disabledLeadingContentColor },
            disabledTrailingContentColor =
                disabledTrailingContentColor.invalidUse { this.disabledTrailingContentColor },
            disabledOverlineContentColor =
                disabledOverlineContentColor.invalidUse { this.disabledOverlineContentColor },
            disabledSupportingContentColor =
                disabledSupportingContentColor.invalidUse { this.disabledSupportingContentColor },
            selectedContainerColor =
                selectedContainerColor.invalidUse { this.selectedContainerColor },
            selectedContentColor = selectedContentColor.invalidUse { this.selectedContentColor },
            selectedLeadingContentColor =
                selectedLeadingContentColor.invalidUse { this.selectedLeadingContentColor },
            selectedTrailingContentColor =
                selectedTrailingContentColor.invalidUse { this.selectedTrailingContentColor },
            selectedOverlineContentColor =
                selectedOverlineContentColor.invalidUse { this.selectedOverlineContentColor },
            selectedSupportingContentColor =
                selectedSupportingContentColor.invalidUse { this.selectedSupportingContentColor },
            draggedContainerColor = draggedContainerColor.invalidUse { this.draggedContainerColor },
            draggedContentColor = draggedContentColor.invalidUse { this.draggedContentColor },
            draggedLeadingContentColor =
                draggedLeadingContentColor.invalidUse { this.draggedLeadingContentColor },
            draggedTrailingContentColor =
                draggedTrailingContentColor.invalidUse { this.draggedTrailingContentColor },
            draggedOverlineContentColor =
                draggedOverlineContentColor.invalidUse { this.draggedOverlineContentColor },
            draggedSupportingContentColor =
                draggedSupportingContentColor.invalidUse { this.draggedSupportingContentColor },
        )
    }
}

fun ListItemStyle.shapes() = this.containerShape

fun ListItemStyle.colors(): ListItemColors {
    return ListItemColors(
        containerColor = containerColor.enabledColor,
        contentColor = headlineColor.enabledColor,
        leadingContentColor = leadingIconStyle.stateColors.enabledColor,
        trailingContentColor = trailingIconStyle.stateColors.enabledColor,
        overlineContentColor = overlineColor.enabledColor,
        supportingContentColor = supportingTextColor.enabledColor,

        disabledContainerColor = containerColor.disabledColor,
        disabledContentColor = headlineColor.disabledColor,
        disabledLeadingContentColor = leadingIconStyle.stateColors.disabledColor,
        disabledTrailingContentColor = trailingIconStyle.stateColors.disabledColor,
        disabledOverlineContentColor = overlineColor.disabledColor,
        disabledSupportingContentColor = supportingTextColor.disabledColor,

        selectedContainerColor = containerColor.selectedColor,
        selectedContentColor = headlineColor.selectedColor,
        selectedLeadingContentColor = leadingIconStyle.stateColors.selectedColor,
        selectedTrailingContentColor = trailingIconStyle.stateColors.selectedColor,
        selectedOverlineContentColor = overlineColor.selectedColor,
        selectedSupportingContentColor = supportingTextColor.selectedColor,

        draggedContainerColor = containerColor.draggedColor,
        draggedContentColor = headlineColor.draggedColor,
        draggedLeadingContentColor = leadingIconStyle.stateColors.draggedColor,
        draggedTrailingContentColor = trailingIconStyle.stateColors.draggedColor,
        draggedOverlineContentColor = overlineColor.draggedColor,
        draggedSupportingContentColor = supportingTextColor.draggedColor,
    )
}

/**
 *  合并颜色和形状到已有样式
 */
@JvmName("mergeColorShapes")
fun ListItemStyle.merge(colors: ListItemColors? = null, newShapes: StateShapes? = null): ListItemStyle {
    return if (colors != null) {
        merge(colors, newShapes)
    } else {
        merge(newShapes)
    }
}

/**
 *  合并形状到已有样式
 */
@JvmName("mergeShapeNullable")
fun ListItemStyle.merge(newShapes: StateShapes?): ListItemStyle {
    if (newShapes == null) return this
    return this.copy(containerShape = newShapes)
}

/**
 *  合并颜色和形状到已有样式
 */
@JvmName("mergeColorsShapeNullable")
fun ListItemStyle.merge(colors: ListItemColors, newShapes: StateShapes? = null): ListItemStyle {
    return this.copy(
        containerShape = newShapes,
        containerColor = StateColors(
            colors.containerColor,
            colors.disabledContainerColor,
            colors.selectedContainerColor,
            colors.draggedContainerColor
        ),
        headlineColor = StateColors(
            colors.contentColor,
            colors.disabledContentColor,
            colors.selectedContentColor,
            colors.draggedContentColor
        ),
        supportingColor = StateColors(
            colors.supportingContentColor,
            colors.disabledSupportingContentColor,
            colors.selectedSupportingContentColor,
            colors.draggedSupportingContentColor
        ),
        overlineColor = StateColors(
            colors.overlineContentColor,
            colors.disabledOverlineContentColor,
            colors.selectedOverlineContentColor,
            colors.draggedOverlineContentColor
        ),
        leadingIconStyle = this.leadingIconStyle.copy(
            stateColors = StateColors(
                colors.leadingContentColor,
                colors.disabledLeadingContentColor,
                colors.selectedLeadingContentColor,
                colors.draggedLeadingContentColor
            ),
        ),
        trailingIconStyle = this.trailingIconStyle.copy(
            stateColors = StateColors(
                colors.trailingContentColor,
                colors.disabledTrailingContentColor,
                colors.selectedTrailingContentColor,
                colors.draggedTrailingContentColor
            ),
        ),
    )
}
