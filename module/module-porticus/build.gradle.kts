repositories {
    maven { url = uri("https://nexus.velocitypowered.com/repository/maven-public/") }
    maven { url = uri("https://repo.iroselle.com/repository/velocity-hosted/") } // 防止 velocitypowered repository 炸裂
}

dependencies {
    implementation("com.google.guava:guava:21.0")
    compileOnly("org.spigotmc:spigot:1.16.5-R0.1-20210611.090701-17")
//    compileOnly("ink.ptms.core:v11605:11605")
    compileOnly("net.md_5.bungee:BungeeCord:1")
//    compileOnly("com.velocitypowered:velocity-api:1.1.8")
    compileOnly(project(":common"))
}

tasks {
    withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
        archiveClassifier.set("")
        archiveBaseName.set("${archiveBaseName.get()}-shaded")
        dependencies {
            include(dependency("com.google.guava:guava:21.0"))
        }
    }
    build {
        dependsOn(shadowJar)
    }
}