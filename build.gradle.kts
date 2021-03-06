// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
        }
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.0.4")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
        classpath("com.apollographql.apollo:apollo-gradle-plugin:${Versions.apollo}")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}")
        classpath("org.jlleitschuh.gradle:ktlint-gradle:9.4.0")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}


tasks.register("clean") {
    doLast {
        delete(rootProject.buildDir)
    }
}
