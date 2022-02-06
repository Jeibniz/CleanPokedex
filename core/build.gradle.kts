plugins {
    id("com.android.library")
    id ("kotlin-android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Versions.compileSdkVersion
    buildToolsVersion = Versions.buildTools

    defaultConfig {
        minSdk = Versions.minSdkVersion
        targetSdk = Versions.targetSdk
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            //minifyEnabled(false) TODO
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(Dependencies.kotlin_stdlib)
    implementation(Dependencies.coroutines_core)
    implementation(Dependencies.coroutines_android)
    implementation(Dependencies.timber)

    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.0") // TODO remove

    // hilt dependencies
    kapt("com.google.dagger:hilt-compiler:2.40.5")
    implementation("com.google.dagger:hilt-android:2.40.5")
}