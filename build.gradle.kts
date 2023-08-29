import java.util.Properties

apply(from = "version.gradle.kts")

plugins {
    `java-library`
    `maven-publish`

//    id("com.github.johnrengelman.shadow")
    id("com.github.ben-manes.versions")
}

val credentialsFile = file("credentials.properties")
val properties = Properties()

// 加载文件内容
credentialsFile.inputStream().use { input ->
    properties.load(input)
}

// 获取配置值
val myUserName: String = properties.getProperty("username")
val myPassWord: String = properties.getProperty("password")

val snapshotUrl = uri(properties.getProperty("snapshot.url") as String)
val releaseUrl = uri(properties.getProperty("release.url") as String)

subprojects {
    apply( plugin = "java-library")
    apply( plugin = "maven-publish")

    publishing {
        publications {

            // 通过-PprojectGroup -PprojectVersion 设置 group version
            group = project.findProperty("projectGroup")?.toString() ?: group
            version = project.findProperty("projectVersion")?.toString() ?: version

            create<MavenPublication>("maven") {
                from(components["java"])

                versionMapping {
                    usage("java-api") {
                        fromResolutionOf("runtimeClasspath")
                    }
                    usage("java-runtime") {
                        fromResolutionResult()
                    }
                }
            }
        }

        repositories {
            maven {
                url = if (version.toString().endsWith("SNAPSHOT")) snapshotUrl else releaseUrl
                credentials {
                    username = myUserName
                    password = myPassWord
                }
            }
        }
    }
}



allprojects {
    buildscript {
        repositories {
            maven { url = snapshotUrl }
            maven { url = releaseUrl }
            mavenCentral()
            mavenLocal()
        }
    }

    repositories {
        maven { url = snapshotUrl }
        maven { url = releaseUrl }
        mavenCentral()
        mavenLocal()
    }
}


val springBootVersion = "2.5.4"
val springCloudVersion = "2020.0.4"

val catVersion = "3.3.0-SNAPSHOT"
val lombokVersion = "1.18.2"
val ttlVersion = "2.13.2"
val jsr305Version = "3.0.2"
val jetbrainsVersion = "24.0.1"
val apacheCommonsVersion = "3.12.0"
val javassistVersion = "3.29.2-GA"
val slf4jVersion = "2.0.7"

val DEPENDENCY_BOMS = listOf(
    "org.springframework.boot:spring-boot-dependencies:$springBootVersion",
    "org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion"
)

val API_DEPENDENCIES = listOf(
    "com.dianping.cat:cat-client:$catVersion",
    "com.alibaba:transmittable-thread-local:$ttlVersion",
//    "com.google.code.findbugs:jsr305:$jsr305Version",
    "org.jetbrains:annotations:$jetbrainsVersion",
    "org.apache.commons:commons-lang3:$apacheCommonsVersion",
    "org.javassist:javassist:$javassistVersion"
)

val DEPENDENCIES = listOf(
    "org.projectlombok:lombok:$lombokVersion",
    "org.springframework:spring-context",
    "org.springframework.boot:spring-boot-autoconfigure",
    "org.slf4j:slf4j-api:$slf4jVersion",
    "org.springframework.boot:spring-boot-configuration-processor"
)


project(":cat-spring-boot-starter") {

    dependencies {
        for (bom in DEPENDENCY_BOMS) {
            implementation(platform(bom))
        }

        for (dependency in API_DEPENDENCIES) {
            api(dependency)
        }

        for (dependency in DEPENDENCIES) {
            implementation(dependency)
        }

        annotationProcessor("org.projectlombok:lombok:$lombokVersion")
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}