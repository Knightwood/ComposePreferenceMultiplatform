plugins {
    alias(libs.plugins.kotlin.android)
    id("publish")
    alias(libs.plugins.android.library)
}

kotlin {
    jvmToolchain(17)
    compilerOptions {
        freeCompilerArgs.add("-Xcontext-receivers")
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
    }
//    androidTarget()
//    sourceSets {
//        commonMain {
//            dependencies {
//                //kotlin
//                implementation(libs.kotlin.coroutines.core)
//                implementation(project(":aop:floor-core"))
//                implementation(libs.github.mmkv)
//            }
//        }
//    }
}

android {
    namespace = "com.knightwood.floor.mmkv"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    sourceSets["main"].kotlin.srcDir("src/commonMain/kotlin")
    buildTypes {
        release {
            isMinifyEnabled = false
            isShrinkResources = false
            consumerProguardFiles("consumer-rules.pro")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

}
dependencies {
    //kotlin
    implementation(libs.kotlin.coroutines.core)
    implementation(project(":aop:floor-core"))
    implementation(libs.github.mmkv)
}
