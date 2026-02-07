package androidy.preference.ui.list_item

@kotlin.jvm.JvmInline
internal value class ListItemType private constructor(private val lines: Int) :
    Comparable<ListItemType> {

    override operator fun compareTo(other: ListItemType) = lines.compareTo(other.lines)

    companion object {
        /** One line list item */
        val OneLine = ListItemType(1)

        /** Two line list item */
        val TwoLine = ListItemType(2)

        /** Three line list item */
        val ThreeLine = ListItemType(3)

        /**
         * 存在overline、supporting时为三行
         * 还要判断supporting高度，根据ListItem源码，当supporting高度大于30sp时也算做三行
         */
        internal operator fun invoke(
            hasOverline: Boolean,
            hasSupporting: Boolean,
            supportingIsMultiline: Boolean,
        ): ListItemType {
            return when {
                (hasOverline && hasSupporting) || supportingIsMultiline -> ThreeLine
                hasOverline || hasSupporting -> TwoLine
                else -> OneLine
            }
        }
    }
}
