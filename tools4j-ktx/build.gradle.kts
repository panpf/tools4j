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
    api("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${property("KOTLIN_VERSION")}")
    api(project(":tools4j-annotation-ktx"))
    api(project(":tools4j-base64-ktx"))
    api(project(":tools4j-collections-ktx"))
    api(project(":tools4j-crypto-ktx"))
    api(project(":tools4j-date-ktx"))
    api(project(":tools4j-io-ktx"))
    api(project(":tools4j-lang-ktx"))
    api(project(":tools4j-math-ktx"))
    api(project(":tools4j-net"))
    api(project(":tools4j-premise-ktx"))
    api(project(":tools4j-ranges-ktx"))
    api(project(":tools4j-ranges-date-ktx"))
    api(project(":tools4j-reflect-ktx"))
    api(project(":tools4j-regex-ktx"))
    api(project(":tools4j-security-ktx"))
    api(project(":tools4j-zip-ktx"))

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
        artifactId = "tools4j-ktx"
        publishVersion = property("VERSION").toString()
        desc = "Java, Tools, Ktx"
        website = "https://github.com/panpf/tools4j"
        userOrg = moduleLocalProperties.getProperty("bintray.userOrg")
        bintrayUser = moduleLocalProperties.getProperty("bintray.user")
        bintrayKey = moduleLocalProperties.getProperty("bintray.apikey")
    }
}