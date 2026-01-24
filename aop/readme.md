# kmp gradle

如果一些代码需要跨平台使用，比如你的库中只有如下一个文件
```kotlin
object MatchHelper{
    fun plus(add1:String,add2: String): String {
        return add1 + add2
    }
}
```
* 这些代码没有用到其他平台的特性
* 没有用到kotlin中的expect/actual特性
* 这些代码如果引入kmp插件也只有commonMain模块有代码，其他模块是空的
还需要引入kmp插件吗？ 答案是需要。
如果你引入kotlin("jvm")插件，这些代码虽然是不需要其他平台特性的，通用的。
但是你在一个kmp项目的common模块引入这个库，她只有jvm平台能识别，只有jvm模块中的代码能引用到，其他平台是没法用的。
所以，即使你的代码足够通用，不需要其他平台特性，也应该引入kotlin multiplatform插件。

比如：
```kotlin
plugins {
    //虽然我们的代码只存在于commonMain源集，但依旧要引入kmp插件
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    jvmToolchain(17)
    //声明此库可以在哪些平台使用
    jvm()
    androidTarget()
    iosX64()
    iosArm64()
    
    sourceSets {
        val commonMain by getting {
            //把src/main文件夹放入commonMain源集，这样你就不用把代码携带commonMain文件夹下了
            kotlin.srcDir("src/main/kotlin")
            resources.srcDir("src/main/resources")
            dependencies {
                implementation(libs.kotlin.coroutines.core)
            }
        }
    }
}
```
## 不同平台区别

jvm() - 运行在 Java 虚拟机上的目标，需要 JVM 环境
linuxX64() - 原生编译的目标，直接编译为 Linux 平台的本地代码，受 Kotlin/Native 互操作性限制

* ios
iosARM32适用于较老的iOS设备( iPhone 5和low)
iOS ARM64用于最新的iOS设备(继iPhone 5之后)
iOSX64则用于iOS模拟器。
因此，imo iOSARM64将适用于大多数设备。

# ksp

ksp模块只能是一个jvm模块，他只在项目编译过程运行。
https://kotlinlang.org/docs/ksp-multiplatform.html
ksp模块需要使用如下方式引入项目
```kotlin
plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
}

kotlin{
    jvm("desktop")
    sourceSets {
        val desktopMain by getting
        commonMain {
            kotlin.srcDir("build/generated/ksp/main/kotlin")
        }
        androidMain {
            
        }
        desktopMain {
            
        }
    }
}

dependencies {
    val aop = ":aop:floor-core-ksp" //模块名
    add("kspCommonMainMetadata", project(aop))
    //之所以是kspDesktop，而不是kspJvm，是因为我们配置源集时调用了 jvm("desktop") 
    add("kspDesktop", project(aop))

    //另一个ksp模块，datastoreAop模块依赖aop
    val datastoreAop = ":aop:floor-datastore-ksp" 
    add("kspCommonMainMetadata", project(datastoreAop))
    add("kspDesktop", project(datastoreAop))
}

```

## 如果遇到如下错误
```
Execution failed for task ':composeApp:kspKotlinDesktop'.
> A failure occurred while executing com.google.devtools.ksp.gradle.KspAAWorkerAction
   > com/google/devtools/ksp/impl/KotlinSymbolProcessing$ExitCode

Unable to load class 'com.google.devtools.ksp.impl.KotlinSymbolProcessing$ExitCode'
com.google.devtools.ksp.impl.KotlinSymbolProcessing$ExitCode

```

请检查是否你注册了SymbolProcessorProvider服务
手动注册：
1. 在创建如下文件夹：`ksp\src\main\resources\META-INF\services\`
2. 然后创建文件，文件名：`com.google.devtools.ksp.processing.SymbolProcessorProvider`
3. 在文件中写入你的SymbolProcessorProvider类全路径
   比如：`com.example.MyAnnotationProcessorProvider`

自动注册:
```kotlin
@AutoService(SymbolProcessorProvider::class)
class AnnotationProcessorProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return AnnotationProcessor(environment)
    }
}
```
