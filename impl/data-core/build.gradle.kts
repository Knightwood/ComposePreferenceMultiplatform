plugins {
    id("java-library")
    alias(libs.plugins.kotlin.jvm)
    id("publish")
}

kotlin {
    //工具链的版本就是所用jdk版本，
    //但是并不等同于最终生成字节码的版本，我们可以在下面指定不同于工具链版本生成字节码
    jvmToolchain(17)
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_1_8)
        freeCompilerArgs.add("-opt-in=kotlin.RequiresOptIn")
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
    withSourcesJar()
    withJavadocJar()
}

dependencies {
    implementation(libs.kotlin.coroutines.core)
}
