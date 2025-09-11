plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.android.library)
    id("publish")
}

kotlin {
    jvmToolchain(17)
    jvm(){
        //这里的会重写top level中的配置
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        }
    }
    androidTarget {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_1_8)
        }
    }
    sourceSets {
        commonMain{
            dependencies {
                //kotlin
                implementation(libs.kotlin.coroutines.core)
                //compose
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
                implementation(libs.androidx.lifecycle.viewmodel.compose)
                implementation(libs.androidx.lifecycle.runtime.compose)

                api(project(":impl:ui"))
                compileOnly(project(":impl:data-core"))
                implementation(libs.github.knightwood.m3ColorUtilities)
            }
        }
    }
}

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
}

android {
    compileSdk = 34
    namespace = "androidy.compose.preference.uiauto"
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    buildFeatures {
        buildConfig = false
    }
    defaultConfig {
        minSdk = 26
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
