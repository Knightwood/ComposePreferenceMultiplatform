package androidy.preference.ui.list_item.normal_style

import androidx.compose.ui.unit.dp
import androidy.preference.ui.list_item.m3_tokens.ColorSchemeKeyTokens
import androidy.preference.ui.list_item.m3_tokens.ElevationTokens
import androidy.preference.ui.list_item.m3_tokens.ShapeKeyTokens
import androidy.preference.ui.list_item.m3_tokens.TypographyKeyTokens

internal object ListItemTokens {
    val DividerSpace = 16.0.dp

    // ListItem整体的padding
    val ItemVerticalPadding = 10.dp
    val ItemThreeLineVerticalPadding = 12.dp
    val ItemStartPadding = 16.dp
    val ItemEndPadding = 16.dp

    //ListItem内部的前中后三元素padding
    val LeadingContentEndPadding = 16.dp
    val TrailingContentStartPadding = 16.dp

    //ListItem的容器高度
    val ItemOneLineContainerHeight = 56.0.dp
    val ItemTwoLineContainerHeight = 72.0.dp
    val ItemThreeLineContainerHeight = 88.0.dp

    // 容器样式
    val ItemContainerColor = ColorSchemeKeyTokens.Surface
    val ItemContainerElevation = ElevationTokens.Level0
    val ItemContainerShadowElevation = ElevationTokens.Level0
    val ItemContainerShape = ShapeKeyTokens.CornerNone
    val ItemContainerHeightMin = ItemOneLineContainerHeight
    val ItemContainerHeightMax = ItemThreeLineContainerHeight

    //overline字体样式
    val ItemOverlineColor = ColorSchemeKeyTokens.OnSurfaceVariant
    val ItemOverlineFont = TypographyKeyTokens.LabelSmall
    val ItemDisabledOverlineColor = ColorSchemeKeyTokens.OnSurface
    val ItemDisabledOverlineOpacity = 0.38f

    //headline字体样式
    val ItemLabelTextColor = ColorSchemeKeyTokens.OnSurface
    val ItemLabelTextFont = TypographyKeyTokens.BodyLarge
    val ItemDisabledLabelTextColor = ColorSchemeKeyTokens.OnSurface
    val ItemDisabledLabelTextOpacity = 0.38f

    //supporting字体样式
    val ItemSupportingTextColor = ColorSchemeKeyTokens.OnSurfaceVariant
    val ItemSupportingTextFont = TypographyKeyTokens.BodyMedium
    val ItemDisabledSupportingTextOpacity = 0.38f

    //leadingIcon样式
    //leadingIcon容器的contentColor
    val ItemLeadingIconColor = ColorSchemeKeyTokens.OnSurfaceVariant
    val ItemLeadingIconSize = 24.0.dp
    val ItemDisabledLeadingIconColor = ColorSchemeKeyTokens.OnSurface
    val ItemDisabledLeadingIconOpacity = 0.38f

    //leadingAvatar样式
    val ItemLeadingAvatarColor = ColorSchemeKeyTokens.PrimaryContainer
    val ItemLeadingAvatarLabelColor = ColorSchemeKeyTokens.OnPrimaryContainer
    val ItemLeadingAvatarLabelFont = TypographyKeyTokens.TitleMedium
    val ItemLeadingAvatarShape = ShapeKeyTokens.CornerFull
    val ItemLeadingAvatarSize = 40.0.dp

    //leadingImage样式
    val ItemLeadingImageHeight = 56.0.dp
    val ItemLeadingImageShape = ShapeKeyTokens.CornerNone
    val ItemLeadingImageWidth = 56.0.dp

    //leadingVideo样式
    val ItemLeadingVideoShape = ShapeKeyTokens.CornerSmall
    val ItemSmallLeadingVideoHeight = 56.0.dp
    val ItemSmallLeadingVideoWidth = 100.0.dp
    val ItemLargeLeadingVideoHeight = 64.0.dp
    val ItemLargeLeadingVideoWidth = 114.0.dp

    //trailingIcon样式
    val ItemTrailingIconColor = ColorSchemeKeyTokens.OnSurfaceVariant
    val ItemTrailingIconSize = 24.0.dp
    val ItemDisabledTrailingIconColor = ColorSchemeKeyTokens.OnSurface
    val ItemDisabledTrailingIconOpacity = 0.38f
    val ItemTrailingSupportingTextFont = TypographyKeyTokens.LabelSmall
}
