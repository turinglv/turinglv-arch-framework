//
//
//plugins {
//    `java-platform`
//
//    id("com.github.ben-manes.versions")
//}
//
//data class DependencySet(val group: String, val version: String, val modules: List<String>)
//
//val dependencyVersions = hashMapOf<String, String>()
//rootProject.extra["versions"] = dependencyVersions
//
//val springBootVersion = "2.5.4"
//val springCloudVersion = "2020.0.4"
//
//val catVersion = "3.0.0"
//
//val DEPENDENCY_BOMS = listOf(
//    "org.springframework.boot:spring-boot-dependencies:$springBootVersion",
//    "org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion"
//)
//
//val CORE_DEPENDENCIES = listOf(
//    "com.dianping.cat:cat-client:$catVersion"
//)
//
//val DEPENDENCIES = listOf(
//    "ch.qos.logback:logback-classic:1.3.7"
//)
//
//javaPlatform {
//    allowDependencies()
//}
//
//dependencies {
//    for (bom in DEPENDENCY_BOMS) {
//        api(enforcedPlatform(bom))
//        val split = bom.split(':')
//        dependencyVersions[split[0]] = split[2]
//    }
//    constraints {
//        for (dependency in CORE_DEPENDENCIES) {
//            api(dependency)
//            val split = dependency.split(':')
//            dependencyVersions[split[0]] = split[2]
//        }
//        for (dependency in DEPENDENCIES) {
//            api(dependency)
//            val split = dependency.split(':')
//            dependencyVersions[split[0]] = split[2]
//        }
//    }
//}
