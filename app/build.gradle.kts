plugins {
    id ("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Versions.compileSdkVersion
    buildToolsVersion = Versions.buildTools

    defaultConfig {
        minSdk = Versions.minSdkVersion
        targetSdk = Versions.targetSdk

        version = 1
        versionCode = 2
        versionName = "2"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.5"
    }

    buildTypes {

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    packagingOptions {
        resources.excludes += "META-INF/atomicfu.kotlin_module"
    }
}

dependencies {
    implementation(project(":core"))

    implementation(Dependencies.Androidx.core_ktx)
    implementation(Dependencies.Androidx.view_model)
    implementation(Dependencies.android_material)
    implementation(Dependencies.coil)
    implementation(Dependencies.timber)

    // Compose
    implementation(Dependencies.Androidx.Compose.compose_activity)
    implementation(Dependencies.Androidx.Compose.compose_animation)
    implementation(Dependencies.Androidx.Compose.compose_material)
    implementation(Dependencies.Androidx.Compose.compose_navigation)
    implementation(Dependencies.Androidx.Compose.compose_ui_tooling)
    implementation(Dependencies.Androidx.Compose.compose_viewmodel)

    // Hilt
    kapt(Dependencies.Hilt.hilt_android_compiler)
    implementation(Dependencies.Hilt.hilt_android)

    // Room
    implementation(Dependencies.Androidx.Room.room)
    kapt(Dependencies.Androidx.Room.room_compiler)

    // Retrofit
    implementation(Dependencies.logging_interceptor)
    implementation(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.retrofit_converter)
}
