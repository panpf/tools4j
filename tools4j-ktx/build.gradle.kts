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