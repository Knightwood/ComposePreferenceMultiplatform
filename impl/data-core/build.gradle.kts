@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    id("publish")
}

kotlin {
    //工具链的版本就是所用jdk版本，
    //但是并不等同于最终生成字节码的版本，我们可以在下面指定不同于工具链版本生成字节码
    jvmToolchain(17)
    compilerOptions {
        freeCompilerArgs.add("-opt-in=kotlin.RequiresOptIn")
    }

    jvm() {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        }
    }
    androidTarget() {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_1_8)
        }
    }
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.coroutines.core)
                compileOnly("com.github.skydoves:compose-stable-marker:1.0.7")
            }
        }
        val jvmMain by getting {}
        val androidMain by getting {}
    }
}

android {
    compileSdk = 36
    namespace = "androidy.preference.data.core"
    defaultConfig {
        minSdk = 21
        lint.targetSdk = 33
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    publishing {
        this.singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}
