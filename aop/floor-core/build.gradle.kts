plugins {
    alias(libs.plugins.kotlin.multiplatform)
    id("publish")
}

kotlin {
    jvmToolchain(17)
    jvm()
    sourceSets {
        val commonMain by getting {
            kotlin.srcDir("src/main/kotlin")
            resources.srcDir("src/main/resources")
            dependencies {
                implementation(libs.kotlin.coroutines.core)
            }
        }
    }
}
