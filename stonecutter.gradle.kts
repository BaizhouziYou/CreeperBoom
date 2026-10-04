plugins {
    id("dev.kikugie.stonecutter")
    id("net.fabricmc.fabric-loom-remap") version "1.17.21" apply false
    id("net.fabricmc.fabric-loom") version "1.17.21" apply false
}

stonecutter active "1.21.4-fabric"

stonecutter parameters {
    constants.match(node.metadata.project.substringAfterLast('-'), "fabric")
}
