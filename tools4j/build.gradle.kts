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

dependencies {
    api("org.jetbrains:annotations:${property("JETBRAINS_ANNOTATIONS_VERSION")}")
    api(project(":tools4j-annotation"))
    api(project(":tools4j-base64"))
    api(project(":tools4j-collections"))
    api(project(":tools4j-compare"))
    api(project(":tools4j-crypto"))
    api(project(":tools4j-date"))
    api(project(":tools4j-grouping"))
    api(project(":tools4j-io"))
    api(project(":tools4j-lang"))
    api(project(":tools4j-math"))
    api(project(":tools4j-net"))
    api(project(":tools4j-premise"))
    api(project(":tools4j-ranges"))
    api(project(":tools4j-ranges-date"))
    api(project(":tools4j-reflect"))
    api(project(":tools4j-regex"))
    api(project(":tools4j-resources"))
    api(project(":tools4j-run"))
    api(project(":tools4j-security"))
    api(project(":tools4j-sequences"))
    api(project(":tools4j-zip"))

    testImplementation("junit:junit:${property("JUNIT_VERSION")}")
    testImplementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${property("KOTLIN_VERSION")}")
}

tasks.getByName("check").dependsOn(tasks.getByName("jacocoTestReport"))

/*
 * publish to bintray
 */
`java.util`.Properties().apply {
    rootProject.file("local.properties").takeIf { it.exists() }?.inputStream()?.use { load(it) }
    project.file("local.properties").takeIf { it.exists() }?.inputStream()?.use { load(it) }
}.takeIf {
    it.getProperty("bintray.user") != null && it.getProperty("bintray.userOrg") != null && it.getProperty("bintray.apiKey") != null
}?.let { localProperties ->
    apply { plugin("com.github.panpf.bintray-publish") }
    configure<com.github.panpf.bintray.publish.PublishExtension> {
        groupId = "com.github.panpf.tools4j"
        artifactId = "tools4j"
        publishVersion = property("VERSION").toString()
        desc = "Java, Tools"
        website = "https://github.com/panpf/tools4j"
        userOrg = localProperties.getProperty("bintray.userOrg")
        bintrayUser = localProperties.getProperty("bintray.user")
        bintrayKey = localProperties.getProperty("bintray.apiKey")
    }
}
