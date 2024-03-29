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
//    api(project(":tools4j-collections"))
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
 * publish config
 */
if (hasProperty("signing.keyId")    // configured in the ~/.gradle/gradle.properties file
    && hasProperty("signing.password")    // configured in the ~/.gradle/gradle.properties file
    && hasProperty("signing.secretKeyRingFile")    // configured in the ~/.gradle/gradle.properties file
    && hasProperty("mavenCentralUsername")    // configured in the ~/.gradle/gradle.properties file
    && hasProperty("mavenCentralPassword")    // configured in the ~/.gradle/gradle.properties file
    && hasProperty("GROUP")    // configured in the rootProject/gradle.properties file
    && hasProperty("POM_ARTIFACT_ID")    // configured in the project/gradle.properties file
) {
    apply { plugin("com.github.panpf.maven.publish") }

    configure<com.github.panpf.maven.publish.MavenPublishPluginExtension> {
        sonatypeHost = com.github.panpf.maven.publish.SonatypeHost.S01
    }
}
