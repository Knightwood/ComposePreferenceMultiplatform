package androidy.preference.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidy.preference.ui.basic.applyOpacity

@Immutable
data class PreferenceTextStyle(
    val titleStyle: TextStyle = TextStyle.Default,
    val descriptionTextStyle: TextStyle = TextStyle.Default,
    val categoryTextStyle: TextStyle = TextStyle.Default,
    val labelTextStyle: TextStyle = TextStyle.Default,
) {

}

fun TextStyle.fixEnabledColor(enabled: Boolean): TextStyle {
    return if (enabled) this else this.copy(
        color = this.color.applyOpacity(false)
    )
}

val defaultPreferenceTextStyle
    @Composable
    get() = PreferenceTextStyle(
        titleStyle = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
        descriptionTextStyle = MaterialTheme.typography.bodyMedium,
        labelTextStyle = MaterialTheme.typography.labelSmall,
        categoryTextStyle = MaterialTheme.typography.bodyMedium.copy(
            color = MaterialTheme.colorScheme.primary,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    )

val LocalPreferenceTextStyle = compositionLocalOf<PreferenceTextStyle> {
    PreferenceTextStyle()
}
