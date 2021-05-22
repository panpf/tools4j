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
    api(project(":tools4j-regex"))

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
