package androidy.preference.ui.theme

sealed class PreferenceType {
    open class Title : PreferenceType() {
        data object LargeTitle : Title()
        data object SubTitle : PreferenceType()
    }

    open class Description : PreferenceType() {
        data object SingleLine : Description()
        data object MultiLine : Description()
    }

    data object Category : PreferenceType()
    data object Item : PreferenceType()
}
