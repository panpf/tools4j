buildscript {
    repositories {
        maven { setUrl("https://mirrors.huaweicloud.com/repository/maven/") }
        mavenCentral()
        jcenter()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${property("KOTLIN_VERSION")}")
        classpath("com.vanniktech:gradle-maven-publish-plugin:0.15.1")
    }
}

allprojects {
    repositories {
        maven { setUrl("https://mirrors.huaweicloud.com/repository/maven/") }
        mavenCentral()
        jcenter()
    }
}