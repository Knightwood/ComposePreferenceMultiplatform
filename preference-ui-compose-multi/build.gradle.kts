import org.jetbrains.compose.desktop.application.dsl.TargetFormat

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
version = rootProject.ext["version"].toString()

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
    publishing {
        singleVariant("release") {
            withSourcesJar()
            //withJavadocJar()
        }
    }
}

//compose.desktop {
//    application {
//        mainClass = "MainKt"
//    }
//}


val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
}

fun getExtraString(name: String) = ext[name]?.toString()

afterEvaluate {

    publishing {
        // Configure maven central repository
        repositories {
            maven("https://jitpack.io")
        }

        // Configure all publications
        publications {
            withType<MavenPublication> {
                // Stub javadoc.jar artifact
                artifact(javadocJar.get())
                // Provide artifacts information requited by Maven Central
                pom {
                    name.set("ComposePreferenceMultiplatform")
                    description.set("ComposePreferenceMultiplatform")
                    url.set("https://github.com/Knightwood/ComposePreferenceMultiplatform")
                }
            }
            create<MavenPublication>("release") {
                groupId = "com.github.knightwood"
                artifactId = "preference-ui-compose-multi-android"
                version = rootProject.ext["version"].toString()
                afterEvaluate {
                    from(components["release"])
                }
            }
        }
    }
}
