package androidy.preference.ui.basic

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Stable
@JvmInline
value class AnyIcon(val icon: Any)

@Composable
fun AnyIcon(
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    model: Any?,
    contentDescription: String? = null,
    shape: Shape = CircleShape,
    paddingValues: PaddingValues = PaddingValues(0.dp),
    backgroundColor: Color = Color.Transparent,
    enabled: Boolean = true,
    tint: Color = MaterialTheme.colorScheme.onSurfaceVariant.applyOpacity(enabled),
) {
    if (model == null) return
    val icon = remember(model) {
        AnyIcon(model)
    }
    AnyIcon(
        modifier = modifier,
        iconModifier = iconModifier,
        model = icon,
        contentDescription = contentDescription,
        shape = shape,
        paddingValues = paddingValues,
        backgroundColor = backgroundColor,
        enabled = enabled,
        tint = tint
    )
}

/**
 * icon组件
 *
 * @param modifier icon外层surface的modifier
 * @param iconModifier icon的modifier
 * @param model ImageVector 或者 Painter 或者 resourceId
 * @param contentDescription
 * @param shape
 * @param paddingValues
 * @param backgroundColor
 * @param enabled
 * @param tint
 */
@Composable
fun AnyIcon(
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    model: AnyIcon,
    contentDescription: String? = null,
    shape: Shape = CircleShape,
    paddingValues: PaddingValues = PaddingValues(0.dp),
    backgroundColor: Color = Color.Transparent,
    enabled: Boolean = true,
    tint: Color = MaterialTheme.colorScheme.onSurfaceVariant.applyOpacity(enabled),
) {
    Surface(
        modifier = modifier.wrapContentSize(),
        color = backgroundColor,
        shape = shape,
    ) {
        when (model.icon) {
            is ImageVector -> {
                Icon(
                    imageVector = model.icon,
                    contentDescription = contentDescription,
                    modifier = iconModifier.padding(paddingValues),
                    tint = tint
                )
            }

            is Painter -> {
                Icon(
                    painter = model.icon,
                    contentDescription = contentDescription,
                    modifier = iconModifier.padding(paddingValues),
                    tint = tint
                )
            }

            else -> {
                platformParseIcon(
                    model,
                    iconModifier.padding(paddingValues),
                    contentDescription,
                    tint
                )
            }
        }
    }
}

/**
 * 如果传入图标不是通用类型，则调用此方法使当前平台实现进行解析
 */
@Composable
expect fun platformParseIcon(
    model: AnyIcon,
    modifier: Modifier,
    contentDescription: String?,
    tint: Color,
)

@Composable
fun IconPreview() {
    Surface(modifier = Modifier.size(500.dp, 500.dp)) {
        Column(modifier = Modifier.padding(24.dp)) {
            AnyIcon(
                model = Icons.Filled.Settings,
                paddingValues = PaddingValues(8.dp),
                tint = MaterialTheme.colorScheme.onPrimary,
                backgroundColor = MaterialTheme.colorScheme.primary,
                contentDescription = "test",
            )
        }

    }
}
