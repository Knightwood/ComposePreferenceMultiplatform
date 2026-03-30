plugins {
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.serialization) apply false

    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.compose.multiplatform) apply false

    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.kotlin.multiplatform.library) apply false

    alias(libs.plugins.vanniktech.mavenPublish) apply false
    alias(libs.plugins.ksp) apply false

}
//val jarGroup = "com.github.knightwood.kmp-preference"
// 改名为
val jarGroup = "androidy.ui.material3.preference"
val jarVersion = "2.1.0"

ext {
    this["group"] =jarGroup
    this["version"] = jarVersion
}

