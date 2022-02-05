object Versions {
    const val compileSdkVersion = 31
    const val buildTools = "30.0.3"
    const val minSdkVersion = 21
    const val targetSdk = 31

    const val androidx_core_ktx = "1.7.0"
    const val androidx_appcompat = "1.4.1"
    const val androidx_view_model = "2.4.0"
    const val androidx_navigation = "2.4.0-alpha02"

    const val kotlin = "1.5.31"
    const val coroutines_core = "1.4.2"
    const val coroutines_android = "1.5.2"
    const val navigation = "2.3.4"
    const val dagger = "2.34"
    const val room = "2.4.1"
    const val retrofit = "2.6.0"
    const val coil = "1.4.0"
}

object Dependencies {
    const val core_ktx = "androidx.core:core-ktx:${Versions.androidx_core_ktx}"
    object Androidx {
        const val core_ktx = "androidx.core:core-ktx:${Versions.androidx_core_ktx}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.androidx_appcompat}"
        const val view_model = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidx_view_model}"
        const val navigation_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.androidx_navigation}"
        const val navigation_ui = "androidx.navigation:navigation-ui-ktx:${Versions.androidx_navigation}"
    }

    object Dagger {
        private const val base_url = "com.google.dagger"
        const val dagger_android = "$base_url:dagger-android:${Versions.dagger}"
        const val dagger_android_processor = "$base_url:dagger-android-processor:${Versions.dagger}"
        const val dagger_android_compiler = "$base_url:dagger-compiler:${Versions.dagger}"
    }

    object Testing {
        const val test_runner = "androidx.test:runner:1.2.0"
        const val test_espresso = "androidx.test.espresso:espresso-core:3.2.0"

        const val test_junit_api = "org.junit.jupiter:junit-jupiter-api:5.2.0"
        const val test_junit_engine = "org.junit.jupiter:junit-jupiter-engine:5.2.0"
        const val test_junit_params = "org.junit.jupiter:junit-jupiter-params:5.2.0"
        const val test_mockk = "io.mockk:mockk:1.11.0"
        const val test_assertj = "org.assertj:assertj-core:3.19.0"
    }


    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_core}"
    const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_android}"
    const val material = "com.google.android.material:material:1.5.0"
    const val junit = "junit:junit:4.13.2"
    const val timber = "com.jakewharton.timber:timber:4.7.1"
    const val ktlint = "com.pinterest:ktlint:0.37.1"
}
