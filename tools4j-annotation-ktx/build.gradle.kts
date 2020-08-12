import com.novoda.gradle.release.PublishExtension
import java.util.*

plugins {
    id("java-library")
    id("jacoco")
    id("kotlin")
}

group = "com.github.panpf.tools4j"
version = property("VERSION").toString()

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.6"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.6"
    }
}

dependencies {
    api("org.jetbrains:annotations:${property("JETBRAINS_ANNOTATIONS_VERSION")}")
    api("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${property("KOTLIN_VERSION")}")
    api(project(":tools4j-annotation"))

    testImplementation("junit:junit:${property("JUNIT_VERSION")}")
}

tasks.getByName("check").dependsOn(tasks.getByName("jacocoTestReport"))

/*
 * publish
 */
project.file("local.properties").takeIf { it.exists() }?.let { file -> file.inputStream().use { input -> Properties().apply { load(input) } } }?.takeIf { !it.isEmpty }?.let { moduleLocalProperties ->
    apply(plugin = "com.novoda.bintray-release")

    configure<PublishExtension> {
        groupId = "com.github.panpf.tools4j"
        artifactId = "tools4j-annotation-ktx"
        publishVersion = property("VERSION").toString()
        desc = "Java, Tools, Annotation, Ktx"
        website = "https://github.com/panpf/tools4j"
        userOrg = moduleLocalProperties.getProperty("bintray.userOrg")
        bintrayUser = moduleLocalProperties.getProperty("bintray.user")
        bintrayKey = moduleLocalProperties.getProperty("bintray.apikey")
    }
}
