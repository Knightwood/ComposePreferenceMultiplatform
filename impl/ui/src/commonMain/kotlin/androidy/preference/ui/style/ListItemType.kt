package androidy.preference.ui.style

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

        internal operator fun invoke(
            hasOverline: Boolean,
            hasSupporting: Boolean,
            isSupportingMultiline: Boolean
        ): ListItemType {
            return when {
                (hasOverline && hasSupporting) || isSupportingMultiline -> ThreeLine
                hasOverline || hasSupporting -> TwoLine
                else -> OneLine
            }
        }
    }
}
