buildscript {
    repositories {
        maven { setUrl("https://mirrors.huaweicloud.com/repository/maven/") }   // Huawei maven mirror
        jcenter()
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${property("KOTLIN_VERSION")}")
        classpath("com.github.panpf.bintray-publish:bintray-publish:${property("BINTRAY_PUBLISH")}")
    }
}

allprojects {
    repositories {
        maven { setUrl("https://mirrors.huaweicloud.com/repository/maven/") }   // Huawei maven mirror
        jcenter()
        mavenCentral()
    }
}