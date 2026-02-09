package androidy.preference.ui.list_item.expressive_style

import androidx.compose.ui.unit.dp
import androidy.preference.ui.list_item.m3_tokens.ColorSchemeKeyTokens
import androidy.preference.ui.list_item.m3_tokens.ElevationTokens
import androidy.preference.ui.list_item.m3_tokens.ShapeKeyTokens
import androidy.preference.ui.list_item.normal_style.BasicListItemTokens

internal val ExpressiveListItemTokens = BasicExpressiveListItemTokens()

/**
 * Expressive版本的ListItem尺寸有所调整，因此使用新类定义，避免混乱
 */
internal open class BasicExpressiveListItemTokens : BasicListItemTokens() {
//    val FocusIndicatorColor = ColorSchemeKeyTokens.Secondary
    val ContainerShape = ShapeKeyTokens.CornerLarge
    val SegmentedGap = 2.0.dp
    val ItemSegmentedContainerColor = ColorSchemeKeyTokens.Surface
    val ItemDisabledStateLayerOpacity = 0.1f
    val DividerTopSpace = 0.0.dp
    val DividerBottomSpace = 0.0.dp
//    val DividerLeadingSpace = 16.0.dp
//    val DividerTrailingSpace = 16.0.dp

    val ItemTopSpace = 10.0.dp
    val ItemBottomSpace = 10.0.dp
    val ItemLeadingSpace = 16.0.dp
    val ItemTrailingSpace = 16.0.dp
    val ItemBetweenSpace = 12.0.dp//取代了LeadingEnd和TrailStart的
//    val ItemOneLineContainerHeight = 56.0.dp
//    val ItemTwoLineContainerHeight = 72.0.dp
//    val ItemThreeLineContainerHeight = 88.0.dp

    // ItemContainer
//    val ItemContainerColor = ColorSchemeKeyTokens.Surface
//    val ItemContainerElevation = ElevationTokens.Level0
//    val ItemContainerShape = ShapeKeyTokens.CornerNone
//    val ItemDraggedContainerElevation = ElevationTokens.Level4
    val ItemSelectedContainerColor = ColorSchemeKeyTokens.SecondaryContainer
    val ItemSelectedContainerShape = ShapeKeyTokens.CornerLarge
    val ItemContainerExpressiveShape = ShapeKeyTokens.CornerExtraSmall
    val ItemDisabledContainerExpressiveShape = ShapeKeyTokens.CornerExtraSmall
    val ItemSelectedContainerExpressiveShape = ShapeKeyTokens.CornerLarge
    val ItemPressedContainerExpressiveShape = ShapeKeyTokens.CornerLarge
    val ItemFocusedContainerExpressiveShape = ShapeKeyTokens.CornerLarge
    val ItemHoveredContainerExpressiveShape = ShapeKeyTokens.CornerMedium
    val ItemDraggedContainerExpressiveShape = ShapeKeyTokens.CornerLarge

    // overline
//    val ItemOverlineColor = ColorSchemeKeyTokens.OnSurfaceVariant
//    val ItemOverlineFont = TypographyKeyTokens.LabelSmall
//    val ItemDisabledOverlineColor = ColorSchemeKeyTokens.OnSurface
//    val ItemDisabledOverlineOpacity = 0.38f
    val ItemSelectedOverlineColor = ColorSchemeKeyTokens.OnSecondaryContainer
    val ItemDraggedOverlineColor = ItemOverlineColor // 原始版本无定义，直接使用了Overline颜色

    // Headline
    /* ListItem是个Container，其上内容比如文本、图标都是Content，需要用Content Color
      其中Headline被当作主内容，所以ContentColor默认使用Headline颜色
      Headline的所用内容颜色被定义为Label颜色 */
//    val ItemLabelTextColor = ColorSchemeKeyTokens.OnSurface
//    val ItemLabelTextFont = TypographyKeyTokens.BodyLarge
//    val ItemDisabledLabelTextColor = ColorSchemeKeyTokens.OnSurface
//    val ItemDisabledLabelTextOpacity = 0.38f
    val ItemSelectedLabelTextColor = ColorSchemeKeyTokens.OnSecondaryContainer
//    val ItemDraggedLabelTextColor = ColorSchemeKeyTokens.OnSurface
//    val ItemFocusLabelTextColor = ColorSchemeKeyTokens.OnSurface
//    val ItemHoverLabelTextColor = ColorSchemeKeyTokens.OnSurface
//    val ItemPressedLabelTextColor = ColorSchemeKeyTokens.OnSurface

    //  supportingText
//    val ItemSupportingTextColor = ColorSchemeKeyTokens.OnSurfaceVariant
//    val ItemSupportingTextFont = TypographyKeyTokens.BodyMedium
    val ItemSelectedSupportingTextColor = ColorSchemeKeyTokens.OnSecondaryContainer
    val ItemDraggedSupportingTextColor = ItemSupportingTextColor
//    val ItemDisabledSupportingTextColor = ColorSchemeKeyTokens.OnSurface
//    val ItemDisabledSupportingTextOpacity = 0.38f

    // LeadingIcon
//    val ItemLeadingIconColor = ColorSchemeKeyTokens.OnSurfaceVariant
//    val ItemLeadingIconSize = 24.0.dp
//    val ItemDisabledLeadingIconColor = ColorSchemeKeyTokens.OnSurface
//    val ItemDisabledLeadingIconOpacity = 0.38f
    val ItemLeadingIconExpressiveSize = 20.0.dp
    val ItemHoverLeadingIconIconColor = ColorSchemeKeyTokens.OnSurfaceVariant
    val ItemPressedLeadingIconIconColor = ColorSchemeKeyTokens.OnSurfaceVariant
    val ItemDraggedLeadingIconIconColor = ColorSchemeKeyTokens.OnSurfaceVariant
    val ItemFocusLeadingIconIconColor = ColorSchemeKeyTokens.OnSurfaceVariant
    val ItemSelectedLeadingIconColor = ColorSchemeKeyTokens.OnSecondaryContainer

    // LeadingVideo
//    val ItemLeadingVideoWidth = 100.0.dp
//    val ItemLeadingVideoShape = ShapeKeyTokens.CornerSmall
//    val ItemLargeLeadingVideoHeight = 64.0.dp
//    val ItemLargeLeadingVideoWidth = 114.0.dp
//    val ItemSmallLeadingVideoHeight = 56.0.dp
//    val ItemSmallLeadingVideoWidth = 100.0.dp

    // LeadingImage
//    val ItemLeadingImageHeight = 56.0.dp
//    val ItemLeadingImageWidth = 56.0.dp
//    val ItemLeadingImageShape = ShapeKeyTokens.CornerNone
    val ItemLeadingImageExpressiveShape = ShapeKeyTokens.CornerSmall

    // LeadingAvatar
//    val ItemLeadingAvatarColor = ColorSchemeKeyTokens.PrimaryContainer
//    val ItemLeadingAvatarLabelColor = ColorSchemeKeyTokens.OnPrimaryContainer
//    val ItemLeadingAvatarLabelFont = TypographyKeyTokens.TitleMedium
//    val ItemLeadingAvatarShape = ShapeKeyTokens.CornerFull
//    val ItemLeadingAvatarSize = 40.0.dp

    // TrailingIcon
//    val ItemTrailingSupportingTextFont = TypographyKeyTokens.LabelSmall
    val ItemTrailingIconExpressiveSize = 20.0.dp
//    val ItemTrailingSupportingTextColor = ColorSchemeKeyTokens.OnSurfaceVariant
    val ItemSelectedTrailingSupportingTextColor = ColorSchemeKeyTokens.OnSecondaryContainer
//    val ItemTrailingIconSize = 24.0.dp
//    val ItemTrailingIconColor = ColorSchemeKeyTokens.OnSurfaceVariant
//    val ItemDisabledTrailingIconColor = ColorSchemeKeyTokens.OnSurface
//    val ItemDisabledTrailingIconOpacity = 0.38f
    val ItemDraggedTrailingIconIconColor = ColorSchemeKeyTokens.OnSurfaceVariant
    val ItemFocusTrailingIconIconColor = ColorSchemeKeyTokens.OnSurfaceVariant
    val ItemHoverTrailingIconIconColor = ColorSchemeKeyTokens.OnSurfaceVariant
    val ItemPressedTrailingIconIconColor = ColorSchemeKeyTokens.OnSurfaceVariant
//    val ItemSelectedTrailingIconColor = ColorSchemeKeyTokens.OnSecondaryContainer
//    val ItemUnselectedTrailingIconColor = ColorSchemeKeyTokens.OnSurface

    //=========================未使用的状态==============================================//
    val ItemSelectedDisabledContainerColor = ColorSchemeKeyTokens.OnSurface
    val ItemSelectedDisabledContainerExpressiveShape = ShapeKeyTokens.CornerLarge
    val ItemSelectedDisabledContainerOpacity = 0.38f
    val ItemSelectedDisabledLabelTextColor = ColorSchemeKeyTokens.OnSurface
    val ItemSelectedDisabledLabelTextOpacity = 0.38f
    val ItemSelectedDisabledLeadingIconColor = ColorSchemeKeyTokens.OnSurface
    val ItemSelectedDisabledLeadingIconOpacity = 0.38f
    val ItemSelectedDisabledOverlineColor = ColorSchemeKeyTokens.OnSurface
    val ItemSelectedDisabledOverlineOpacity = 0.38f
    val ItemSelectedDisabledStateLayerOpacity = 0.1f
    val ItemSelectedDisabledSupportingTextColor = ColorSchemeKeyTokens.OnSurface
    val ItemSelectedDisabledSupportingTextOpacity = 0.38f
    val ItemSelectedDisabledTrailingIconColor = ColorSchemeKeyTokens.OnSurface
    val ItemSelectedDisabledTrailingIconOpacity = 0.38f
    val ItemSelectedDisabledTrailingSupportingTextColor = ColorSchemeKeyTokens.OnSurface
    val ItemSelectedDisabledTrailingSupportingTextOpacity = 0.38f
    val ItemSelectedDraggedContainerExpressiveShape = ShapeKeyTokens.CornerLarge
    val ItemSelectedDraggedLabelTextColor = ColorSchemeKeyTokens.OnSecondaryContainer
    val ItemSelectedDraggedLeadingIconColor = ColorSchemeKeyTokens.OnSurface
    val ItemSelectedDraggedTrailingIconColor = ColorSchemeKeyTokens.OnSurface
    val ItemSelectedFocusLabelTextColor = ColorSchemeKeyTokens.OnSecondaryContainer
    val ItemSelectedFocusLeadingIconColor = ColorSchemeKeyTokens.OnSurface
    val ItemSelectedFocusTrailingIconColor = ColorSchemeKeyTokens.OnSurface
    val ItemSelectedFocusedContainerExpressiveShape = ShapeKeyTokens.CornerLarge
    val ItemSelectedHoverLabelTextColor = ColorSchemeKeyTokens.OnSecondaryContainer
    val ItemSelectedHoverLeadingIconColor = ColorSchemeKeyTokens.OnSurface
    val ItemSelectedHoverTrailingIconColor = ColorSchemeKeyTokens.OnSurface
    val ItemSelectedHoveredContainerExpressiveShape = ShapeKeyTokens.CornerLarge
    val ItemSelectedPressedContainerExpressiveShape = ShapeKeyTokens.CornerLarge
    val ItemSelectedPressedLabelTextColor = ColorSchemeKeyTokens.OnSecondaryContainer
    val ItemSelectedPressedLeadingIconColor = ColorSchemeKeyTokens.OnSurface
    val ItemSelectedPressedTrailingIconColor = ColorSchemeKeyTokens.OnSurface
    //===============================================================================//
}


internal val InteractiveListStartPadding = ExpressiveListItemTokens.ItemLeadingSpace
internal val InteractiveListEndPadding = ExpressiveListItemTokens.ItemTrailingSpace
internal val InteractiveListTopPadding = ExpressiveListItemTokens.ItemTopSpace
internal val InteractiveListBottomPadding = ExpressiveListItemTokens.ItemBottomSpace
internal val InteractiveListInternalSpacing = ExpressiveListItemTokens.ItemBetweenSpace
