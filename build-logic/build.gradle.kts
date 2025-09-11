plugins {
    //`kotlin-dsl` 即 id("org.gradle.kotlin.dsl") 用于gradle kts支持
    `kotlin-dsl`
}

dependencies {
    //版本需与 kotlin-dsl 一致
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.0.20")
    implementation("com.vanniktech:gradle-maven-publish-plugin:0.34.0")
}
