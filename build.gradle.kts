import org.spongepowered.gradle.plugin.config.PluginLoaders
import org.spongepowered.plugin.metadata.model.PluginDependency

plugins {
    kotlin("jvm") version "1.8.0"
    kotlin("plugin.serialization") version "1.8.0"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("org.spongepowered.gradle.plugin") version "2.1.1"
}

val spongeVersion: String = "8.0.0-SNAPSHOT"
val kotlinVersion: String = "1.8.0"

group = "me.zodd"
version = "0.1.0"

repositories {
    mavenCentral()
}

sponge {
    apiVersion(spongeVersion)
    license("CHANGEME")
    loader {
        name(PluginLoaders.JAVA_PLAIN)
        version("1.0")
    }
    plugin("kruntime") {
        displayName("KrunTime")
        entrypoint("me.zodd.kruntime.KrunTime")
        version(project.version as String)
        description("Provides the Kotlin runtime for other plugins.")
        links {
            homepage("https://github.com/DrZoddiak/kruntime")
            source("https://github.com/DrZoddiak/kruntime")
            issues("https://github.com/DrZoddiak/kruntime/issues")
        }
        dependency("spongeapi") {
            loadOrder(PluginDependency.LoadOrder.AFTER)
            optional(false)
        }
    }
}

dependencies {
    val stdlib = create(kotlin("stdlib-jdk8"))
    api(stdlib)
    shadow(stdlib)
    val reflect = create(kotlin("reflect"))
    api(reflect)
    shadow(reflect)
    val coroutines = create("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    api(coroutines)
    shadow(coroutines)
    val serialization = create("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    api(serialization)
    shadow(serialization)
}

tasks.shadowJar {
    archiveBaseName.set("${project.name}-$spongeVersion-$kotlinVersion")
    configurations = listOf(project.configurations.shadow.get())
    archiveClassifier.set("")
}

tasks.jar {
    enabled = false
}

tasks.build {
    dependsOn(tasks.shadowJar.get())
}

tasks.compileKotlin {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

artifacts {
    archives(tasks.shadowJar.get())
}

