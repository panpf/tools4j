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
    api(project(":other"))

    testImplementation("junit:junit:${property("JUNIT_VERSION")}")
}

tasks.getByName("check").dependsOn(tasks.getByName("jacocoTestReport"))