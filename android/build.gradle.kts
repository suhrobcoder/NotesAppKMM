plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "uz.suhrob.notesapp.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "uz.suhrob.notesapp.android"
        minSdk = 23
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    applicationVariants.all {
        kotlin.sourceSets {
            getByName(name) {
                kotlin.srcDir("build/generated/ksp/$name/kotlin")
            }
        }
    }
}

dependencies {
    implementation(project(":shared"))

    val composeVersion = "1.3.0"
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    implementation("androidx.compose.foundation:foundation:$composeVersion")
    implementation("androidx.compose.material3:material3:1.0.1")
    implementation("androidx.activity:activity-compose:1.6.1")

    val decomposeVersion = "1.0.0-beta-01"
    implementation("com.arkivanov.decompose:extensions-compose-jetpack:$decomposeVersion")

    val koinVersion = "3.2.0"
    implementation("io.insert-koin:koin-android:$koinVersion")
}