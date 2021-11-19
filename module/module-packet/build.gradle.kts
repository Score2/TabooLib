dependencies {
    compileOnly(project(":common"))
    compileOnly(project(":module:module-nms"))

    compileOnly(project(":platform:platform-bukkit"))
    compileOnly("ink.ptms:nms-all:1.0.0")
    compileOnly("ink.ptms.core:v11701:11701:universal")

    compileOnly(project(":platform:platform-bungee"))
    compileOnly("net.md_5.bungee:BungeeCord:1:all")

    compileOnly(project(":platform:platform-sponge-api7"))
    compileOnly("org.spongepowered:spongeapi:7.2.0")

    compileOnly(project(":platform:platform-sponge-api8"))
    compileOnly("org.spongepowered:spongeapi:8.0.0-SNAPSHOT")
}