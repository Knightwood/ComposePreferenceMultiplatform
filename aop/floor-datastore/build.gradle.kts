plugins {
    alias(libs.plugins.kotlin.multiplatform)
    id("publish")
}

kotlin {
    jvmToolchain(17)
    jvm()
    sourceSets {
        val commonMain by getting {
            kotlin.srcDir("src/main/kotlin")
            resources.srcDir("src/main/resources")
            dependencies {
                //kotlin
                implementation(libs.kotlin.coroutines.core)
                implementation(libs.androidx.datastore)
                implementation(libs.androidx.datastore.preferences)

                implementation(project.dependencies.platform(libs.koin.bom))
                implementation(libs.koin.core)

                implementation(project(":aop:floor-core"))
            }
        }
    }
}
