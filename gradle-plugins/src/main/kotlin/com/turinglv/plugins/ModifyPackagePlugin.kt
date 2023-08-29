package com.turinglv.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.FileVisitDetails
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.SourceSetContainer
import java.io.File

class ModifyPackagePlugin : Plugin<Project> {

    private val oldPackageName: String = "com.turinglv"

    private val tempCodeDir: String = "tempCode"

    private var newPackageName: String = ""

    override fun apply(project: Project) {
        project.task("modifyPackage") {

            newPackageName = project.findProperty("projectGroup")?.toString() ?: newPackageName

            if (newPackageName.isEmpty()) {
                println("Stop modifyPackage")
                return@task
            }

            this.doLast {
                println("Start modifyPackage")

                val tempDir = project.buildDir.resolve(tempCodeDir)

                if (!tempDir.exists()) {
                    tempDir.mkdirs()
                }

                // 拷贝文件至临时目录
                copyCode(project, tempDir)

                // 修改临时目录中的代码包名
                modifyPackageName(tempDir, newPackageName)

                val oldDir = tempDir.resolve(oldPackageName.replace(".", "/"))

                if (oldDir.exists()) {
                    project.delete(oldDir)
                }

                changeSrcDir(project, tempDir)
                println("End modifyPackage")
            }
            project.tasks.getByName("compileJava").dependsOn("modifyPackage")
        }
    }


    private fun copyCode(project: Project, tempCodeDir: File) {
        val sourceDirs = project.extensions.getByType(JavaPluginExtension::class.java)
            .sourceSets.getByName("main")

        // 创建一个Copy任务，将源代码复制到临时目录
        project.copy {
            from(sourceDirs.java.srcDirs, sourceDirs.resources.srcDirs)
            into(tempCodeDir)

            // 定义一个自定义操作，用于修改包名和移动文件到新目录
            eachFile {
                val relativePath = relativePath.pathString
                if (relativePath.endsWith(".java")) {
                    // 移动文件到新目录
                    val newRelativePath = relativePath.replace(oldPackageName.replace(".", "/"),
                        newPackageName.replace(".", "/"))
                    path = newRelativePath
                }
            }
        }
    }

    private fun changeSrcDir(project: Project, tempDir: File) {
        val sourceSets = project.extensions.getByType(SourceSetContainer::class.java)

        // 修改源代码目录
        val mainSourceSet = sourceSets.getByName("main")
        mainSourceSet.java.setSrcDirs(listOf(tempDir.absolutePath))

        // 修改资源目录（可选）
        mainSourceSet.resources.setSrcDirs(listOf(tempDir.absolutePath))
    }

    private fun modifyPackageName(directory: File, newPackageName: String) {

        println("modifyPackageName directory ${directory.path}")
        // 遍历目录下的所有文件，修改包名
        directory.walkTopDown().forEach { file ->
            if (file.isFile) {
                val content = file.readText().replace(oldPackageName, newPackageName)
                file.writeText(content)
            }
        }
    }
}



