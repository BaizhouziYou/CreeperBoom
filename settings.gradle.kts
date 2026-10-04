pluginManagement {
    repositories {
        maven("https://maven.fabricmc.net/") { name = "Fabric" }
        maven("https://maven.kikugie.dev/snapshots") { name = "KikuGie Snapshots" }
        gradlePluginPortal()
    }
}

plugins {
    id("dev.kikugie.stonecutter") version "0.9.1-beta.4"
}

stonecutter {
    create(rootProject) {
        mapBuilds { _, data -> "build.${data.project.substringAfterLast('-')}.gradle" }
        version("1.21.3-fabric", "1.21.3")
        version("1.21.4-fabric", "1.21.4")
        version("1.21.8-fabric", "1.21.8")
        version("1.21.11-fabric", "1.21.11")
        version("26.1.2-fabric", "26.1.2")
        version("26.2-fabric", "26.2")
        version("26.3-fabric", "26.3")
        vcsVersion = "1.21.4-fabric"
    }
}

rootProject.name = "creeperboom"
