import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun LocalVerticalScrollbar(
    modifier: Modifier = Modifier,
    scrollState: ScrollState,
)
