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
    api(project(":tools4j-environment"))
    api(project(":tools4j-io-ktx"))
    api(project(":tools4j-lang-ktx"))
    api(project(":tools4j-math-ktx"))
    api(project(":tools4j-net"))
    api(project(":tools4j-premise-ktx"))
    api(project(":tools4j-ranges-ktx"))
    api(project(":tools4j-ranges-date-ktx"))
    api(project(":tools4j-reflect-ktx"))
    api(project(":tools4j-regex-ktx"))
    api(project(":tools4j-resources"))
    api(project(":tools4j-security-ktx"))
    api(project(":tools4j-zip-ktx"))

    testImplementation("junit:junit:${property("JUNIT_VERSION")}")
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