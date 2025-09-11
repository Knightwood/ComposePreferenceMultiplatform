plugins {
    id("com.vanniktech.maven.publish")
}

//val org.gradle.api.Project.`publishing`: org.gradle.api.publish.PublishingExtension
//    get() =
//        (this as org.gradle.api.plugins.ExtensionAware).extensions.getByName("publishing") as org.gradle.api.publish.PublishingExtension

fun org.gradle.api.Project.publishing(configure: Action<org.gradle.api.publish.PublishingExtension>): Unit =
    (this as org.gradle.api.plugins.ExtensionAware).extensions.configure("publishing", configure)

fun org.gradle.api.Project.mavenPublishing(configure: Action<com.vanniktech.maven.publish.MavenPublishBaseExtension>): Unit =
    (this as org.gradle.api.plugins.ExtensionAware).extensions.configure(
        "mavenPublishing",
        configure
    )

//fun org.gradle.api.Project.ext(configure: org.gradle.api.Action<org.gradle.api.plugins.ExtraPropertiesExtension>): Unit {
//    (this as org.gradle.api.plugins.ExtensionAware).extensions.configure("ext", configure)
//}
//
//val org.gradle.api.Project.`ext`: org.gradle.api.plugins.ExtraPropertiesExtension
//    get() =
//        (this as org.gradle.api.plugins.ExtensionAware).extensions.getByName("ext") as org.gradle.api.plugins.ExtraPropertiesExtension


//统一发布逻辑
if (plugins.hasPlugin("com.vanniktech.maven.publish")) {
    val myMavenName = rootProject.findProperty("my.maven.name") as String? ?: "MyLocalMaven"
    val myMavenUrl = rootProject.findProperty("my.maven.url") as String? ?: "D:\\maven"
    val group = rootProject.findProperty("group") as String? ?: throw IllegalArgumentException("group is null")
    val version = rootProject.findProperty("version") as String? ?: throw IllegalArgumentException("version is null")
    publishing {
        repositories {
            maven {
                name = myMavenName
                url = uri(myMavenUrl)
            }
        }
    }
    mavenPublishing {
        publishToMavenCentral()
//      signAllPublications()
        afterEvaluate {
            coordinates(
                groupId = group,
                artifactId = project.name,
                version = version
            )
        }
        pom {
            name.set(project.name)
            description.set("some preference toolkit library for android and kmp")
            url.set("https://github.com/knightwood/ComposePrefernce-Multi")
            licenses {
                license {
                    name.set("The Apache Software License, Version 2.0")
                    url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                }
            }
        }
    }

} else {
    print("${project.name} no plugin com.vanniktech.maven.publish\n")
}
