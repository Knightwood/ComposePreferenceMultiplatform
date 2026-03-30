@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

import com.vanniktech.maven.publish.KotlinMultiplatform
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.android.library)
    alias(libs.plugins.vanniktech.mavenPublish)
}
//配置java版本
//https://www.jetbrains.com/help/kotlin-multiplatform-dev/multiplatform-dsl-reference.html#targets
kotlin {
    compilerOptions {
        freeCompilerArgs.addAll(
            "-Xcontext-parameters",
            "-opt-in=kotlin.RequiresOptIn"
        )
    }
    jvmToolchain(17)//使用jdk17工具链，也就是使用jdk17编译
    jvm() {
        //这里的会重写top level中的配置，jvm平台使用java17语法
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        }
    }
    androidTarget {
        compilerOptions {//android平台还是使用1.8语法，保持兼容性
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_1_8)
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
                implementation(libs.jetbrains.compose.material3)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
                implementation(libs.androidx.lifecycle.viewmodel.compose)
                implementation(libs.androidx.lifecycle.runtime.compose)
                implementation(libs.compose.icon.core)
                implementation(libs.slf4j.api)
            }
        }
    }

}

android {
    compileSdk = 34
    namespace = "androidy.ui.material3.listitem"
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


val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
}

val myMavenName = rootProject.findProperty("my.maven.name") as String? ?: "MyLocalMaven"
val myMavenUrl = rootProject.findProperty("my.maven.url") as String? ?: "D:\\maven"

group = "androidy.ui.material3.listitem"
val artifactId = "listitem"
version = "1.0.0"

publishing {
    repositories {
        maven {
            name = myMavenName
            url = uri(myMavenUrl)
        }
    }
}
mavenPublishing {
    publishToMavenCentral()
    coordinates(group.toString(), artifactId, version.toString())
    configure(
        KotlinMultiplatform()
    )
    pom {
        name.set(project.name)
        description.set("some preference toolkit library for android and kmp")
    }
}
