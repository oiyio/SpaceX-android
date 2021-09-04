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


    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    apollo {
        generateKotlinModels.set(true)
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.aar"))))

    implementation("org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}")
    implementation("androidx.core:core-ktx:1.6.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")

    implementation("com.apollographql.apollo:apollo-runtime:${Versions.apollo}")
    implementation("com.apollographql.apollo:apollo-coroutines-support:${Versions.apollo}")

    implementation("com.squareup.okhttp3:logging-interceptor:4.9.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0")

    api(project(":common"))

    implementation(Deps.dagger_hilt)
    implementation("com.google.android.material:material:1.4.0")
    kapt(Deps.dagger_hilt_compiler)
    implementation(Deps.recyclerView)
    implementation(Deps.retrofit_converter_gson)
    implementation(Deps.glide)
    implementation("com.airbnb.android:lottie:3.5.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.11.0")

    testImplementation("junit:junit:4.13.2")

    lifeCycleDependencies()

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}")

    implementation("androidx.fragment:fragment-ktx:${Versions.fragmentKtxVersion}")
    implementation("androidx.fragment:fragment:${Versions.fragmentVersion}")
    implementation("androidx.activity:activity-ktx:${Versions.activityKtxVersion}")

    implementation("androidx.paging:paging-runtime-ktx:3.0.1")
}


repositories {

    google()

    maven { url = uri("https://jitpack.io") }

    flatDir {
        dirs("libs")
    }
}
