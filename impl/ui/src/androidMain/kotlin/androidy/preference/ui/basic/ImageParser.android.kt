package androidy.preference.ui.basic

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

@Composable
actual fun platformParseIcon(
    model: AnyIcon,
    modifier: Modifier,
    contentDescription: String?,
    tint: Color,
) {
    when (model.icon) {
        is Int -> {
            Icon(
                painter = painterResource(id = model.icon),
                modifier = modifier,
                contentDescription = contentDescription,
                tint = tint
            )
        }

        else -> {}
    }
}
