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
            }
        }
    }
}


android {
    namespace = "androidy.preference.helper.datastore"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

