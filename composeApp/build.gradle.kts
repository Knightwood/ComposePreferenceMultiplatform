import org.gradle.kotlin.dsl.implementation
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
        }
    }

    jvm()

    sourceSets {
        androidMain.dependencies {
//            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.github.mmkv)
            implementation(projects.impl.dataMmkv)
            implementation(projects.impl.dataPreference)
//            implementation(projects.aop.floorMmkv)

        }
        commonMain {
//            kotlin.srcDir("build/generated/ksp/main/kotlin")
            dependencies {
                implementation(libs.kotlin.coroutines.core)
                implementation(compose.runtime)
                implementation(compose.materialIconsExtended)
                implementation(compose.foundation)
//                implementation(compose.material3)
                implementation(libs.jetbrains.compose.material3)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
                implementation(libs.androidx.lifecycle.viewmodel.compose)
                implementation(libs.androidx.lifecycle.runtime.compose)
                implementation(libs.compose.icon.core)

                implementation(libs.androidx.datastore)
                implementation(libs.androidx.datastore.preferences)

                implementation(project.dependencies.platform(libs.koin.bom))
                implementation(libs.koin.core)

                implementation(projects.impl.ui)
                implementation(projects.impl.uiAuto)
                implementation(projects.impl.dataCore)
                implementation(projects.impl.dataDatastore)

//                implementation(projects.aop.floorCore)
//                implementation(projects.aop.floorDatastore)

            }
        }
        jvmMain {
            kotlin.srcDir("build/generated/ksp/jvm/jvmMain/kotlin")
            dependencies {
                implementation(compose.desktop.currentOs) {
                    exclude("org.jetbrains.compose.material")
                }
                implementation(libs.slf4j.logback)
            }
        }
    }
}

android {
    namespace = "com.github.knight.composepreference_multi"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/androidMain/resources")

    defaultConfig {
        applicationId = "com.github.knight.composepreference_multi"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            isShrinkResources = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    dependencies {
//        debugImplementation(libs.compose.ui.tooling)
    }
}

dependencies {
//    //https://kotlinlang.org/docs/ksp-multiplatform.html
//    val aop = ":aop:floor-core-ksp"
//    add("kspCommonMainMetadata", project(aop))
//    add("kspJvm", project(aop))
//    add("kspAndroid", project(aop))
//
//    val datastoreAop = ":aop:floor-datastore-ksp"
//    add("kspCommonMainMetadata", project(datastoreAop))
//    add("kspJvm", project(datastoreAop))
//    add("kspAndroid", project(datastoreAop))
//
//    val mmkvAop = ":aop:floor-mmkv-ksp"
//    add("kspCommonMainMetadata", project(mmkvAop))
//    add("kspAndroid", project(mmkvAop))
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.github.knight.composepreference_multi"
            packageVersion = "1.0.0"
        }
    }
}
