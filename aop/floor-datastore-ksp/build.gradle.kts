plugins {
    alias(libs.plugins.kotlin.jvm)
    id("publish")
    alias(libs.plugins.ksp)

}

kotlin {
    jvmToolchain(17)
    compilerOptions {
        freeCompilerArgs.add("-Xcontext-parameters")
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
    }
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    //kotlin
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.androidx.datastore)
    implementation(libs.androidx.datastore.preferences)

    //引入ksp
    //典型的KSP processor（包括网上大部分的例子）都是分了三个module，
    // 一个是定义注解的module，一个是实现processor的，一个是使用注解和processor的。
    // 但这并不是必须的，为了方便，其实把注解的定义和processor放在一个module就可以了。
    // 只要把processor与使用它的module分开来了，就可以。
    implementation(libs.ksp.api)
    //将ksp的符号类型转换成kotlinpoet的符号类型
    implementation(libs.kotlin.poet.ksp)
    implementation(libs.kotlin.poet)

    //使用autoService就不用手动注册service了
    ksp(libs.autoService.ksp)
    implementation(libs.autoService.annoations)
    implementation(project(":aop:floor-datastore"))
    implementation(project(":aop:floor-core"))
    implementation(project(":aop:floor-core-ksp"))

}
