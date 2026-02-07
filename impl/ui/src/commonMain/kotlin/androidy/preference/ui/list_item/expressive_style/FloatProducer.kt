package androidy.preference.ui.list_item.expressive_style

/**
 * Alternative to `() -> Float` but avoids boxing.
 *
 * !!! Do not use in public APIs !!!
 */
internal fun interface FloatProducer {
    /** Returns the Float. */
    operator fun invoke(): Float
}
