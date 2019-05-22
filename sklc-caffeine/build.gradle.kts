import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm")
}

group = "io.github.thefrontier"
version = "0.5.0"

repositories {
    mavenCentral()
}

dependencies {
    // Kotlin
    compileOnly(kotlin("stdlib-jdk8"))

    // Core library
    compileOnly(project(":sklc-core"))

    // Caffeine
    compileOnly("com.github.ben-manes.caffeine:caffeine:2.5.4")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}