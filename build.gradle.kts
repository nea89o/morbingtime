plugins {
    java
    id("dev.architectury.loom") version "0.11.0.+"
}


repositories {
    maven("https://maven.shedaniel.me/")
    maven("https://maven.terraformersmc.com/releases/")
    maven("https://pkgs.dev.azure.com/djtheredstoner/DevAuth/_packaging/public/maven/v1")
}

dependencies {
    minecraft("com.mojang:minecraft:1.18.2")
    mappings("net.fabricmc:yarn:1.18.2+build.3:v2")
    modImplementation("net.fabricmc:fabric-loader:0.14.6")
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.55.1+1.18.2")
    modRuntimeOnly("com.terraformersmc:modmenu:4.0.0-beta.1")
    modRuntimeOnly("me.djtheredstoner:DevAuth-fabric:1.0.0")
}
