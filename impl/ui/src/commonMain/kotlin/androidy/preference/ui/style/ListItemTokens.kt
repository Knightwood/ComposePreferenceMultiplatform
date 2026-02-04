package androidy.preference.ui.style

import androidx.compose.ui.unit.dp
import androidy.preference.ui.m3_tokens.ColorSchemeKeyTokens
import androidy.preference.ui.m3_tokens.ElevationTokens
import androidy.preference.ui.m3_tokens.ShapeKeyTokens
import androidy.preference.ui.m3_tokens.TypographyKeyTokens


internal object ListItemTokens {
    val DividerSpace = 16.0.dp

    // ListItem整体的padding
    val ListItemVerticalPadding = 8.dp
    val ListItemThreeLineVerticalPadding = 12.dp
    val ListItemStartPadding = 16.dp
    val ListItemEndPadding = 16.dp

    //ListItem内部的前中后三元素padding
    val LeadingContentEndPadding = 16.dp
    val TrailingContentStartPadding = 16.dp

    //ListItem的容器高度
    val ListItemOneLineContainerHeight = 56.0.dp
    val ListItemTwoLineContainerHeight = 72.0.dp
    val ListItemThreeLineContainerHeight = 88.0.dp

    // 容器样式
    val ListItemContainerColor = ColorSchemeKeyTokens.Surface
    val ListItemContainerElevation = ElevationTokens.Level0
    val ListItemContainerShadowElevation = ElevationTokens.Level0
    val ListItemContainerShape = ShapeKeyTokens.CornerNone
    val ListItemContainerHeightMin = ListItemOneLineContainerHeight
    val ListItemContainerHeightMax = ListItemThreeLineContainerHeight

    //overline字体样式
    val ListItemOverlineColor = ColorSchemeKeyTokens.OnSurfaceVariant
    val ListItemOverlineFont = TypographyKeyTokens.LabelSmall

    //headline字体样式
    val ListItemLabelTextColor = ColorSchemeKeyTokens.OnSurface
    val ListItemLabelTextFont = TypographyKeyTokens.BodyLarge
    val ListItemDisabledLabelTextColor = ColorSchemeKeyTokens.OnSurface
    val ListItemDisabledLabelTextOpacity = 0.38f

    //supporting字体样式
    val ListItemSupportingTextColor = ColorSchemeKeyTokens.OnSurfaceVariant
    val ListItemSupportingTextFont = TypographyKeyTokens.BodyMedium

    //leadingIcon样式
    //leadingIcon容器的contentColor
    val ListItemLeadingIconColor = ColorSchemeKeyTokens.OnSurfaceVariant
    val ListItemLeadingIconSize = 24.0.dp
    val ListItemDisabledLeadingIconColor = ColorSchemeKeyTokens.OnSurface
    val ListItemDisabledLeadingIconOpacity = 0.38f

    //leadingAvatar样式
    val ListItemLeadingAvatarColor = ColorSchemeKeyTokens.PrimaryContainer
    val ListItemLeadingAvatarLabelColor = ColorSchemeKeyTokens.OnPrimaryContainer
    val ListItemLeadingAvatarLabelFont = TypographyKeyTokens.TitleMedium
    val ListItemLeadingAvatarShape = ShapeKeyTokens.CornerFull
    val ListItemLeadingAvatarSize = 40.0.dp

    //leadingImage样式
    val ListItemLeadingImageHeight = 56.0.dp
    val ListItemLeadingImageShape = ShapeKeyTokens.CornerNone
    val ListItemLeadingImageWidth = 56.0.dp

    //leadingVideo样式
    val ListItemLeadingVideoShape = ShapeKeyTokens.CornerNone
    val ListItemLeadingVideoWidth = 100.0.dp
    val ListItemLargeLeadingVideoHeight = 69.0.dp
    val ListItemSmallLeadingVideoHeight = 56.0.dp

    //trailingIcon样式
    val ListItemTrailingIconColor = ColorSchemeKeyTokens.OnSurfaceVariant
    val ListItemTrailingIconSize = 24.0.dp
    val ListItemDisabledTrailingIconColor = ColorSchemeKeyTokens.OnSurface
    val ListItemDisabledTrailingIconOpacity = 0.38f
    val ListItemTrailingSupportingTextFont = TypographyKeyTokens.LabelSmall
}
