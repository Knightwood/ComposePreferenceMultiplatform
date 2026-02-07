package androidy.preference.ui.list_item

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidy.preference.ui.list_item.expressive_style.FloatProducer


private fun Modifier.zIndexLambda(zIndex: FloatProducer): Modifier =
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        layout(placeable.width, placeable.height) { placeable.place(0, 0, zIndex = zIndex()) }
    }
