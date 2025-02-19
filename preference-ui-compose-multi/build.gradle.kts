import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.vanniktech.mavenPublish)
//    alias(libs.plugins.ksp)
//     alias(libs.plugins.kotlin.cocoapods)
    alias(libs.plugins.androidLibrary)
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

                api(libs.androidx.datastore)
                api(libs.androidx.datastore.preferences)
                api(libs.preference.data.core)
                implementation(libs.github.knightwood.m3ColorUtilities)
            }
        }
        val desktopMain by getting
        desktopMain.dependencies {
            implementation(libs.kotlin.coroutines.swing)
            implementation(compose.desktop.currentOs){
                exclude("org.jetbrains.compose.material")
            }
        }
        val androidMain by getting
        androidMain {
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
    namespace = "androidy.compose.preference"
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

/*afterEvaluate {

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
}*/

val groupId = "com.github.knightwood"
val artifactId = "kmp-preference"
val githubUrl="https://github.com/Knightwood/kmp-preference"
group = groupId
version = "1.0.0"

mavenPublishing {
    publishToMavenCentral()
//    signAllPublications()
    coordinates(group.toString(), artifactId, version.toString())

    pom {
        name = artifactId
        description = "android like desktop runtime"
        inceptionYear = "2024"
        url = "https://github.com/kotlin/multiplatform-library-template/"
        licenses {
            license {
                name = "The Apache License, Version 2.0"
                url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
            }
        }
        developers {
            developer {
                id = "knightwood"
                name = "knightwood"
                email = "33772264+Knightwood@users.noreply.github.com"
            }
        }
        scm {
            url = githubUrl
        }
    }
}

