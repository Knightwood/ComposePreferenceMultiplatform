package androidy.ui.material3.listitem.normal_style

import androidx.compose.ui.unit.dp
import androidy.ui.material3.listitem.m3_tokens.ColorSchemeKeyTokens
import androidy.ui.material3.listitem.m3_tokens.ElevationTokens
import androidy.ui.material3.listitem.m3_tokens.ShapeKeyTokens
import androidy.ui.material3.listitem.m3_tokens.TypographyKeyTokens

internal val ListItemTokens = BasicListItemTokens()

internal open class BasicListItemTokens {
    val FocusIndicatorColor = ColorSchemeKeyTokens.Secondary
    val DividerSpace = 16.0.dp

    //    val ItemLeadingSpace = 16.0.dp
//    val ItemTrailingSpace = 16.0.dp
    val DividerLeadingSpace = 16.0.dp
    val DividerTrailingSpace = 16.0.dp

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
    val ItemDisabledSupportingTextColor = ColorSchemeKeyTokens.OnSurface
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
    val ItemLeadingVideoWidth = 100.0.dp
    val ItemLeadingVideoShape = ShapeKeyTokens.CornerSmall
    val ItemSmallLeadingVideoHeight = 56.0.dp
    val ItemSmallLeadingVideoWidth = 100.0.dp
    val ItemLargeLeadingVideoHeight = 64.0.dp
    val ItemLargeLeadingVideoWidth = 114.0.dp

    //trailingIcon样式
    val ItemTrailingIconSize = 24.0.dp
    val ItemTrailingIconColor = ColorSchemeKeyTokens.OnSurfaceVariant
    val ItemDisabledTrailingIconColor = ColorSchemeKeyTokens.OnSurface
    val ItemDisabledTrailingIconOpacity = 0.38f
    val ItemTrailingSupportingTextColor = ColorSchemeKeyTokens.OnSurfaceVariant
    val ItemTrailingSupportingTextFont = TypographyKeyTokens.LabelSmall


    //其他
    val ItemDraggedContainerElevation = ElevationTokens.Level4
    val ItemDraggedLabelTextColor = ColorSchemeKeyTokens.OnSurface
    val ItemDraggedLeadingIconColor = ColorSchemeKeyTokens.OnSurfaceVariant
    val ItemDraggedTrailingIconColor = ColorSchemeKeyTokens.OnSurfaceVariant

    val ItemFocusLabelTextColor = ColorSchemeKeyTokens.OnSurface
    val ItemFocusLeadingIconColor = ColorSchemeKeyTokens.OnSurfaceVariant
    val ItemFocusTrailingIconColor = ColorSchemeKeyTokens.OnSurfaceVariant

    val ItemHoverLabelTextColor = ColorSchemeKeyTokens.OnSurface
    val ItemHoverLeadingIconColor = ColorSchemeKeyTokens.OnSurfaceVariant
    val ItemHoverTrailingIconColor = ColorSchemeKeyTokens.OnSurfaceVariant

    val ItemPressedLabelTextColor = ColorSchemeKeyTokens.OnSurface
    val ItemPressedLeadingIconColor = ColorSchemeKeyTokens.OnSurfaceVariant
    val ItemPressedTrailingIconColor = ColorSchemeKeyTokens.OnSurfaceVariant

    val ItemSelectedTrailingIconColor = ColorSchemeKeyTokens.Primary
    val ItemUnselectedTrailingIconColor = ColorSchemeKeyTokens.OnSurface
}

internal val ListItemVerticalPadding = ListItemTokens.ItemVerticalPadding
internal val ListItemThreeLineVerticalPadding = ListItemTokens.ItemThreeLineContainerHeight
internal val ListItemStartPadding = ListItemTokens.ItemStartPadding
internal val ListItemEndPadding = ListItemTokens.ItemEndPadding
internal val LeadingContentEndPadding = ListItemTokens.LeadingContentEndPadding
internal val TrailingContentStartPadding = ListItemTokens.TrailingContentStartPadding
