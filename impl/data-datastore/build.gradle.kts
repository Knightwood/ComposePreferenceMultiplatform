import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.KotlinMultiplatform
import org.gradle.kotlin.dsl.compileOnly

plugins {
    alias(libs.plugins.kotlin.multiplatform)
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
        val commonMain by getting {
            dependencies {
                //kotlin
                implementation(libs.kotlin.coroutines.core)
                implementation(libs.androidx.datastore)
                implementation(libs.androidx.datastore.preferences)
                compileOnly(project(":impl:data-core"))
                api(project(":helper:datastore-helper"))
            }
        }
    }
}

android {
    compileSdk = 34
    namespace = "androidy.compose.preference.data.datastore"
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
