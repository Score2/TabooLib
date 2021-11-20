repositories {
    maven { url = uri("https://repo.spongepowered.org/maven") }
    maven { url = uri("https://nexus.velocitypowered.com/repository/maven-public/") }
    maven { url = uri("https://repo.iroselle.com/repository/velocity-hosted/") } // 防止 velocitypowered repository 炸裂
}

dependencies {
    compileOnly(project(":common"))
    compileOnly(project(":module:module-nms"))

    compileOnly(project(":platform:platform-bukkit"))
    compileOnly("ink.ptms:nms-all:1.0.0")
    compileOnly("ink.ptms.core:v11701:11701:universal")

    compileOnly(project(":platform:platform-bungee"))
    compileOnly("net.md_5.bungee:BungeeCord:1:all")

    compileOnly(project(":platform:platform-sponge-api7"))
    compileOnly("org.spongepowered:spongeapi:7.1.0")

    compileOnly(project(":platform:platform-velocity"))
    compileOnly("com.velocitypowered:velocity-api:3.0.1")
}