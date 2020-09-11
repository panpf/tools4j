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
        artifactId = "tools4j-common-rangesbase"
        publishVersion = property("VERSION").toString()
        desc = "Java, Tools, Common, Range, Base"
        website = "https://github.com/panpf/tools4j"
        userOrg = localProperties.getProperty("bintray.userOrg")
        bintrayUser = localProperties.getProperty("bintray.user")
        bintrayKey = localProperties.getProperty("bintray.apiKey")
    }
}
