plugins {
    id("com.android.library")
    id ("kotlin-android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
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

    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.2.0") // TODO remove

    //testImplementation "junit:junit:4.+"
}

/*


dependencies {
    implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.0"
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.2.0"

    testImplementation "junit:junit:4.+"
}
 */