repositories {
    maven { url = uri("https://repo.spongepowered.org/maven") }
    maven { url = uri("https://nexus.velocitypowered.com/repository/maven-public/") }
    maven { url = uri("https://repo.iroselle.com/repository/velocity-hosted/") } // 防止 velocitypowered repository 炸裂
}

dependencies {
    compileOnly(project(":common"))
    compileOnly(project(":common-5"))
    compileOnly(project(":module:module-nms"))
    compileOnly("org.tabooproject.reflex:reflex:1.0.19")
    compileOnly("org.tabooproject.reflex:analyser:1.0.19")

    compileOnly(project(":platform:platform-bukkit"))
    compileOnly("ink.ptms:nms-all:1.0.0")
    compileOnly("ink.ptms.core:v11701:11701:universal")

    compileOnly(project(":platform:platform-bungee"))
    compileOnly("net.md_5.bungee:BungeeCord:1")

//    compileOnly(project(":platform:platform-sponge-api7"))
//    compileOnly("org.spongepowered:spongeapi:7.1.0")
//
//    compileOnly(project(":platform:platform-velocity"))
//    compileOnly("com.velocitypowered:velocity-api:3.0.1")
}

tasks {
    withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
        archiveClassifier.set("")
        relocate("org.tabooproject", "taboolib.library")
        minimize()
    }
    build {
        dependsOn(shadowJar)
    }
}