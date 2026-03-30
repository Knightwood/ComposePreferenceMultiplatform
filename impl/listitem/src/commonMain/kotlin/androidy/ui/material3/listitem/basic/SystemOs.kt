package androidy.ui.material3.listitem.basic

internal enum class AppPlatform {
    Desktop, Android, Ios,
    ;
}
internal val appCurrentPlatform by lazy {
    getPlatform()
}

internal expect fun getPlatform(): AppPlatform
