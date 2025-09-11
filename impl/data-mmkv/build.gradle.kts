plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("publish")
}

android {
    namespace = "androidy.preference.data.mmkv"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
kotlin {
    jvmToolchain(17)
}
dependencies {
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.github.mmkv)
    compileOnly(project(":impl:data-core"))
    api(project(":helper:mmkv-helper"))
}
