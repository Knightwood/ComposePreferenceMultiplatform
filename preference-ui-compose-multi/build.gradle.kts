import org.jetbrains.compose.desktop.application.dsl.TargetFormat

/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.ksp)
//     alias(libs.plugins.kotlin.cocoapods)
    alias(libs.plugins.androidLibrary)
//     alias(libs.plugins.skie)
    alias(libs.plugins.jetbrainsCompose)
    id("maven-publish")
}

group = "com.github.knightwood"
version = "1.0.0"

kotlin {
//     iosX64()
//     iosArm64()
//     iosSimulatorArm64()

//     cocoapods {
//         summary = "Dice Roller data module"
//         homepage = "Link to the Shared Module homepage"
//         ios.deploymentTarget = "14.1"
//         podfile = project.file("../iosApp/Podfile")
//         framework {
//             baseName = "shared"
//         }
//     }
    jvm("desktop")
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

//    sourceSets.all {
//         languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
//    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.ui)
                implementation(compose.material3)

                api(libs.androidx.datastore.core.okio)
                api(libs.androidx.datastore.preferences.core)
                api(libs.preference.data.core)
                implementation(libs.github.knightwood.m3ColorUtilities)
                implementation(libs.kotlin.coroutines.core)
            }
        }
//        val commonTest by getting {
//            dependencies {
//                implementation(libs.kotlin.test)
//            }
//        }
        val desktopMain by getting
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
        }
        val androidMain by getting
        androidMain {
//            dependencies {
//                api(libs.androidx.startup.runtime)
//            }
        }

//        val androidUnitTest by getting
//         val iosX64Main by getting
//         val iosArm64Main by getting
//         val iosSimulatorArm64Main by getting
//         val iosMain by creating {
//             dependsOn(commonMain)
//             iosX64Main.dependsOn(this)
//             iosArm64Main.dependsOn(this)
//             iosSimulatorArm64Main.dependsOn(this)
//         }
//         val iosX64Test by getting
//         val iosArm64Test by getting
//         val iosSimulatorArm64Test by getting
//         val iosTest by creating {
//             dependsOn(commonTest)
//             iosX64Test.dependsOn(this)
//             iosArm64Test.dependsOn(this)
//             iosSimulatorArm64Test.dependsOn(this)
//         }
    }

}

android {
    compileSdk = 34
    namespace = "com.kiylx.compose_lib.pref_component"
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

//compose.desktop {
//    application {
//        mainClass = "MainKt"
//    }
//}