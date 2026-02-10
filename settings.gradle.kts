rootProject.name = "ComposePreference-Multi"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
includeBuild("./build-logic")

pluginManagement {
    repositories {
        mavenLocal()
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.aliyun.com/repository/public/")
        maven("https://maven.aliyun.com/repository/central")
        maven("https://www.jitpack.io")
        maven("./build-logic/repo")
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenLocal()
        google()
        mavenCentral()
        maven("https://www.jitpack.io")
        maven("https://maven.aliyun.com/repository/public/")
        maven("https://maven.aliyun.com/repository/central")
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
    }

}

include(":composeApp")
//ui实现
include(":impl:listitem")
include(":impl:ui")
include(":impl:ui-auto")
//ui-auto所需的数据读写工具实现
include(":impl:data-core")
include(":impl:data-datastore")
include(":impl:data-mmkv")
include(":impl:data-preference")
//各类属性委托工具
include(":helper:datastore-helper")
include(":helper:mmkv-helper")
include(":helper:preference-helper")
//aop
//include(":aop:floor-core")
//include(":aop:floor-core-ksp")
//include(":aop:floor-datastore")
//include(":aop:floor-datastore-ksp")
//include(":aop:floor-mmkv")
//include(":aop:floor-mmkv-ksp")

//  前缀               +组件库名       +module名 +版本号
//com.github.knightwood.kmp-preference:ui       :1.0.0

//包名：
//ui ： androidy.preference.ui
//ui-auto ： androidy.preference.uiauto

//data-core : androidy.preference.data.core
//data-datastore : androidy.preference.data.datastore
//data-mmkv : androidy.preference.data.mmkv
//data-preference : androidy.preference.data.preference

//helper-datastore : androidy.preference.helper.datastore
//helper-mmkv : androidy.preference.helper.mmkv
//helper-preference : androidy.preference.helper.preference
