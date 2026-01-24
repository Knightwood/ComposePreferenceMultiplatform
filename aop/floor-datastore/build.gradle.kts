plugins {
    alias(libs.plugins.kotlin.multiplatform)
    id("publish")
//    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.android.library)
}

kotlin {
    jvmToolchain(17)
    jvm()
    androidTarget(){
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_1_8)
        }
    }
//    iosArm64()
//    macosX64()
//    macosArm64()

//    androidLibrary {
//        namespace = "com.knightwood.floor.datastore"
//        compileSdk = 33
//        minSdk = 24
//        withJava() // enable java compilation support
//
//        compilerOptions {
//            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_1_8)
//        }
//    }
    sourceSets {
        val commonMain by getting {
//            kotlin.srcDir("src/main/kotlin")
//            resources.srcDir("src/main/resources")
            dependencies {
                //kotlin
                implementation(libs.kotlin.coroutines.core)
                implementation(libs.androidx.datastore)
                implementation(libs.androidx.datastore.preferences)
                implementation(project(":aop:floor-core"))
            }
        }
    }
}
android{
    namespace = "com.knightwood.floor.datastore"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
