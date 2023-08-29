pluginManagement {
    plugins {
        id("com.github.ben-manes.versions") version "0.46.0"
        `java-library`
        `maven-publish`
        `java-gradle-plugin`
        id("com.github.johnrengelman.shadow") version "8.1.1"
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        mavenLocal()
    }
}

includeBuild("gradle-plugins")
rootProject.name = "turinglv-arch-framework"
include(":cat-spring-boot-starter")
include(":dependencyManagement")
//include(":gradle-plugins")
