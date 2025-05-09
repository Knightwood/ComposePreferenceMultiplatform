package androidy.compose.preference.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidy.compose.preference.theme.Preferences
import androidy.compose.preference.theme.fixEnabledColor

private const val TAG = "PreferenceLayout"

@Composable
fun SamplePreference(
    modifier: Modifier = Modifier,
    title: String? = null,
    icon: Any? = null,
    desc: String? = null,
    enabled: Boolean = true,
    end: @Composable (BoxScope.() -> Unit)? = null,

    start: @Composable BoxScope.() -> Unit = {
        val style = Preferences.iconStyle
        ParseIcon(
            tint = style.fixEnabledTint(enabled),
            model = icon,
            shape = style.shape,
            paddingValues = style.paddingValues,
            backgroundColor = style.fixEnabledBackgroundColor(enabled),
            contentDescription = "icon"
        )
    },

    titleContent: @Composable ColumnScope.(title: String) -> Unit = {
        Text(
            it,
            maxLines = Preferences.style.titleMaxLine,
            style = Preferences.textStyle.titleStyle.fixEnabledColor(enabled),
        )
    },

    descContent: @Composable ColumnScope.(desc: String) -> Unit = {
        Text(
            it,
            style = Preferences.textStyle.descriptionTextStyle.fixEnabledColor(enabled),
            maxLines = Preferences.style.descMaxLine,
        )
    },

    onClick: (() -> Unit)?,
) {
    PreferenceRow(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
        start = if (icon != null) {
            start
        } else {
            null
        },
        title = if (title != null) {
            { titleContent(title) }
        } else null,
        description = if (desc != null) {
            { descContent(desc) }
        } else null,
        end = end
    )
}

@Composable
internal fun PreferenceRow(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: (() -> Unit)?,
    start: @Composable (BoxScope.() -> Unit)? = null,
    title: @Composable (ColumnScope.() -> Unit)? = null,
    description: @Composable (ColumnScope.() -> Unit)? = null,
    end: @Composable (BoxScope.() -> Unit)? = null,
) {
    val boxStyle = Preferences.boxStyle

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(Preferences.dimens.heightMin)
            .padding(Preferences.dimens.boxMarginValues),
        shape = boxStyle.shape,
        color = boxStyle.color,
        contentColor = boxStyle.contentColor,
        tonalElevation = boxStyle.tonalElevation,
        shadowElevation = boxStyle.shadowElevation,
        border = boxStyle.border
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .takeIf({ onClick != null }) {
                    clickable(
                        interactionSource = null,
                        enabled = enabled,
                        onClick = onClick!!,
                        indication = rippleOrFallbackImplementation(),
                    )
                }
                .padding(Preferences.dimens.boxPaddingValues)
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            start?.let {
                Box(
                    modifier = Modifier
                        .padding(Preferences.dimens.startPaddingValues)
                        .sizeIn(Preferences.dimens.iconSize),
                    content = it
                )
            }
            Column(
                modifier = Modifier
                    .padding(Preferences.dimens.contentPaddingValues)
                    .weight(1f),
                horizontalAlignment = Alignment.Start,
            ) {
                title?.invoke(this)
                description?.invoke(this)
            }
            if (end != null) {
                Box(
                    modifier = Modifier
                        .minimumInteractiveComponentSize()
                        .fillMaxHeight()
                        .padding(Preferences.dimens.endPaddingValues),
                    content = end
                )
            }
        }
    }
}
