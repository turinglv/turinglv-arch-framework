buildscript {
    repositories {
        mavenCentral()
        mavenLocal()
    }
    dependencies {
        // 由于使用的 Kotlin 须要须要添加 Kotlin 插件，这里使用最新的kotlin插件
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20")
    }
}

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation(gradleApi())
}

gradlePlugin {
    plugins {
        create("modifyPackage") {
            id = "com.turinglv.ModifyPackagePlugin" // 插件的 ID
            implementationClass = "com.turinglv.plugins.ModifyPackagePlugin" // 插件的实现类
        }
    }
}
