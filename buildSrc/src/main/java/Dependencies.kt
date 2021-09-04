import org.gradle.kotlin.dsl.DependencyHandlerScope

object Versions {
    const val compileSdk = 30
    const val buildToolsVersion = "30.0.2"
    const val minSdk = 19
    const val targetSdk = 30
    const val applicationId = "com.omeriyioz.spacex"
    const val versionCode = 1
    const val versionName = "1.0"
    
    const val recyclerView = "1.2.1"
    const val retrofitConverterGson = "2.9.0"
    const val glide = "4.11.0"
    const val hilt = "2.37"
    const val apollo = "2.5.4"
    const val kotlin = "1.4.30"

    const val lifecycleVersion = "2.2.0"
    const val coreKtxVersion = "1.5.0"
    const val lifecycleKtxVersion = "2.3.0"

    const val jvmTarget = "1.8"



    //Coroutines
    const val coroutinesVersion = "1.4.3"

    const val activityKtxVersion = "1.2.0"
    const val fragmentKtxVersion = "1.3.0"
    const val fragmentVersion = "1.2.5"
}

fun DependencyHandlerScope.lifeCycleDependencies() {
    "implementation"(Deps.androidLifeCycle)
    "implementation"(Deps.androidLifeCycleProcess)
    "implementation"(Deps.androidLifeCycleKtx)
    "implementation"(Deps.androidLifeCycleLiveData)
    "implementation"(Deps.androidCommonLifeCycle)
}



object Deps {
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val retrofit_converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofitConverterGson}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val dagger_hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val dagger_hilt_compiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"


    //LifeCycle
    const val androidLifeCycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleVersion}"
    const val androidLifeCycleProcess = "androidx.lifecycle:lifecycle-process:${Versions.lifecycleVersion}"
    const val androidLifeCycleKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleKtxVersion}"
    const val androidLifeCycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleKtxVersion}"
    const val androidCommonLifeCycle = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycleVersion}"


}