
plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("com.apollographql.apollo")
    id("dagger.hilt.android.plugin")
    id("org.jlleitschuh.gradle.ktlint")
}

android {

    compileSdkVersion(Versions.compileSdk)
    buildToolsVersion(Versions.buildToolsVersion)

    defaultConfig {
        applicationId = Versions.applicationId
        versionCode = Versions.versionCode
        versionName = Versions.versionName

        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)

        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions { jvmTarget = Versions.jvmTarget }


}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.aar"))))

    implementation("org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}")
    implementation("androidx.core:core-ktx:1.6.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")

    implementation("com.apollographql.apollo:apollo-runtime:2.3.1")
    implementation("com.apollographql.apollo:apollo-coroutines-support:2.3.1")

    implementation("com.squareup.okhttp3:logging-interceptor:4.9.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.3")

    implementation(Deps.dagger_hilt)
    kapt(Deps.dagger_hilt_compiler)
    implementation(Deps.epoxy)
    kapt(Deps.epoxy_processor)
    implementation(Deps.recyclerView)
    implementation(Deps.retrofit_converter_gson)
    implementation(Deps.glide)
    implementation("com.airbnb.android:lottie:3.5.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.11.0")

    testImplementation("junit:junit:4.13.2")
}


repositories {

    google()

    maven { url = uri("https://jitpack.io") }

    flatDir {
        dirs("libs")
    }
}
