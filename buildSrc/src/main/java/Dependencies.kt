object Versions {
    const val compileSdkVersion = 31
    const val buildTools = "30.0.3"
    const val minSdkVersion = 21
    const val targetSdk = 31

    const val androidx_core_ktx = "1.7.0"
    const val androidx_view_model = "2.4.0"
    const val androidx_room = "2.4.1"

    const val androidx_compose = "1.0.5"
    const val androidx_compose_activity = "1.4.0"
    const val androidx_compose_navigation = "2.5.0-alpha01"

    const val android_material = "1.5.0"
    const val coil = "1.4.0"
    const val coroutines_android = "1.5.2"
    const val coroutines_core = "1.4.2"
    const val dagger = "2.34"
    const val hilt = "2.40.5"
    const val kotlin = "1.5.31"
    const val ktlint = "0.37.1"
    const val logging_interceptor = "4.9.3"
    const val navigation = "2.3.4"
    const val retrofit = "2.6.0"
    const val timber = "4.7.1"
}

object Dependencies {


    const val android_material = "com.google.android.material:material:${Versions.android_material}"
    const val coil = "io.coil-kt:coil-compose:${Versions.coil}"
    const val ktlint = "com.pinterest:ktlint:${Versions.ktlint}"
    const val logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.logging_interceptor}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    object Kotlin {
        const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
        const val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_core}"
        const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_android}"
    }

    object Androidx {
        const val core_ktx = "androidx.core:core-ktx:${Versions.androidx_core_ktx}"
        const val view_model = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidx_view_model}"

        object Compose {
            const val compose_activity = "androidx.activity:activity-compose:${Versions.androidx_compose_activity}"
            const val compose_animation = "androidx.compose.animation:animation:${Versions.androidx_compose}"
            const val compose_material = "androidx.compose.material:material:${Versions.androidx_compose}"
            const val compose_navigation = "androidx.navigation:navigation-compose:${Versions.androidx_compose_navigation}"
            const val compose_ui_tooling = "androidx.compose.ui:ui-tooling:${Versions.androidx_compose}"
            const val compose_viewmodel = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.androidx_view_model}"
        }

        object Room {
            const val room = "androidx.room:room-ktx:${Versions.androidx_room}"
            const val room_compiler = "androidx.room:room-compiler:${Versions.androidx_room}"
        }
    }

    object Hilt {
        const val hilt_android = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val hilt_android_compiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
        const val hilt_plugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val retrofit_converter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    }

    object Testing {
        const val test_assertj = "org.assertj:assertj-core:3.19.0"
        const val test_espresso = "androidx.test.espresso:espresso-core:3.2.0"
        const val test_junit = "junit:junit:4.13.2"
        const val test_junit_api = "org.junit.jupiter:junit-jupiter-api:5.2.0"
        const val test_junit_engine = "org.junit.jupiter:junit-jupiter-engine:5.2.0"
        const val test_junit_params = "org.junit.jupiter:junit-jupiter-params:5.2.0"
        const val test_mockk = "io.mockk:mockk:1.11.0"
        const val test_runner = "androidx.test:runner:1.2.0"
    }
}
