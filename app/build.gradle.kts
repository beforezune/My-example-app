plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

import java.util.Properties

val appConfig = Properties()

file("../app-config.properties").inputStream().use {
    appConfig.load(it)
}

val applicationIdValue =
    appConfig.getProperty(
        "APPLICATION_ID",
        "com.example.myandroidapp"
    )

val appNameValue =
    appConfig.getProperty(
        "APP_NAME",
        "My Android App"
    )

val versionCodeValue =
    appConfig.getProperty(
        "VERSION_CODE",
        "1"
    ).toInt()

val versionNameValue =
    appConfig.getProperty(
        "VERSION_NAME",
        "1.0"
    )

android {
    namespace = "com.example.template"
    compileSdk = 35

    defaultConfig {
        applicationId = applicationIdValue

        minSdk = 24
        targetSdk = 35

        versionCode = versionCodeValue
        versionName = versionNameValue

        testInstrumentationRunner =
            "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }

        resValue(
            "string",
            "app_name",
            appNameValue
        )
    }

    buildTypes {
        release {
            isMinifyEnabled = false

            proguardFiles(
                getDefaultProguardFile(
                    "proguard-android-optimize.txt"
                ),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    val composeBom =
        platform("androidx.compose:compose-bom:2024.09.03")

    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation("androidx.core:core-ktx:1.13.1")

    implementation(
        "androidx.lifecycle:lifecycle-runtime-ktx:2.8.6"
    )

    implementation(
        "androidx.activity:activity-compose:1.9.2"
    )

    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    debugImplementation(
        "androidx.compose.ui:ui-tooling"
    )

    testImplementation("junit:junit:4.13.2")

    androidTestImplementation(
        "androidx.test.ext:junit:1.2.1"
    )

    androidTestImplementation(
        "androidx.test.espresso:espresso-core:3.6.1"
    )

    androidTestImplementation(
        "androidx.compose.ui:ui-test-junit4"
    )
}
