plugins {
    id ("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs")
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
        versionName = "1.1"

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
        resources.excludes.add("META-INF/atomicfu.kotlin_module")
    }

    (this as ExtensionAware).configure<org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions> {
        jvmTarget = "1.8"
    }

    /*
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = kotlin.collections.listOf("-Xjvm-default=compatibility")
    }

    (kotlinOptions as org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions).apply {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        freeCompilerArgs = kotlin.collections.listOf("-Xjvm-default=compatibility")
    }
     */
}

dependencies {
    implementation(project(":core"))

    implementation(Dependencies.Androidx.core_ktx)
    implementation(Dependencies.Androidx.appcompat)
    implementation(Dependencies.Androidx.view_model)
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")

    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")

    implementation(Dependencies.material)
    implementation(Dependencies.timber)

    // Compose
    implementation("androidx.activity:activity-compose:1.4.0")
    implementation("androidx.compose.material:material:1.0.5")
    implementation("androidx.compose.animation:animation:1.0.5")
    implementation("androidx.compose.ui:ui-tooling:1.0.5")
    implementation("androidx.compose.runtime:runtime-livedata:1.0.5")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.0.5")
    implementation("androidx.navigation:navigation-compose:2.5.0-alpha01")

    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:${Versions.androidx_navigation}")
    implementation("androidx.navigation:navigation-ui-ktx:${Versions.androidx_navigation}")
    implementation("androidx.navigation:navigation-fragment-ktx:${Versions.androidx_navigation}")
    implementation("androidx.navigation:navigation-ui-ktx:${Versions.androidx_navigation}")

    // Dagger
    implementation("com.google.dagger:dagger:${Versions.dagger}")
    kapt("com.google.dagger:dagger-compiler:${Versions.dagger}")
    annotationProcessor("com.google.dagger:dagger-compiler:${Versions.dagger}")

    // hilt dependencies
    kapt("com.google.dagger:hilt-compiler:2.40.5")
    implementation("com.google.dagger:hilt-android:2.40.5")

    // Room
    implementation("androidx.room:room-ktx:${Versions.room}")
    kapt("androidx.room:room-compiler:${Versions.room}")
    androidTestImplementation("androidx.room:room-testing:${Versions.room}")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:${Versions.retrofit}")
    implementation("com.squareup.retrofit2:converter-gson:${Versions.retrofit}")

    // Coil
    implementation("io.coil-kt:coil-compose:${Versions.coil}")
}
