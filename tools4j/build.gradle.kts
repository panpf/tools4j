plugins {
    id("java-library")
    id("jacoco")
    id("kotlin")
}

group = property("GROUP").toString()
version = property("VERSION").toString()

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_6
    targetCompatibility = JavaVersion.VERSION_1_6
}

dependencies {
    api("org.jetbrains:annotations:${property("JETBRAINS_ANNOTATIONS_VERSION")}")
    api(project(":tools4j-annotation"))
    api(project(":tools4j-base64"))
    api(project(":tools4j-collections"))
    api(project(":tools4j-compare"))
    api(project(":tools4j-crypto"))
    api(project(":tools4j-date"))
    api(project(":tools4j-environment"))
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
    api(project(":tools4j-security"))
    api(project(":tools4j-sequences"))
    api(project(":tools4j-zip"))

    testImplementation("junit:junit:${property("JUNIT_VERSION")}")
    testImplementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${property("KOTLIN_VERSION")}")
}

tasks.getByName("check").dependsOn(tasks.getByName("jacocoTestReport"))

/**
 * publish config, The following properties are generally configured in the ~/.gradle/gradle.properties file
 */
if (hasProperty("signing.keyId")
    && hasProperty("signing.password")
    && hasProperty("signing.secretKeyRingFile")
    && hasProperty("mavenCentralUsername")
    && hasProperty("mavenCentralPassword")
) {
    apply { plugin("com.vanniktech.maven.publish") }

    configure<com.vanniktech.maven.publish.MavenPublishPluginExtension> {
        sonatypeHost = com.vanniktech.maven.publish.SonatypeHost.S01
    }
}
