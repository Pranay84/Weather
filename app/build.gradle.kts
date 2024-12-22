plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    // alias(libs.plugins.kotlin.compose)
    // kotlin("kapt") version "2.1.0"
    // alias(libs.plugins.google.gms.google.services)
    id("com.google.devtools.ksp").version("1.6.10-1.0.4")
    kotlin("multiplatform") version "1.9.20" apply false

    // id ("com.android.application")
    id ("kotlin-android")
    alias(libs.plugins.gms.google.services) // Apply the Kotlin Android plugin
}

android {
    namespace = "com.example.weather"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.weather"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            buildConfigField("String", "API_KEY", "\"${project.findProperty("API_KEY")}\"")
        }
        getByName("release") {
            buildConfigField("String", "API_KEY", "\"${project.findProperty("API_KEY")}\"")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10" // Use a compatible Compose Compiler version
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation ("androidx.compose.compiler:compiler:1.5.15")

    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
    implementation("com.squareup.moshi:moshi:1.15.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.0")
    implementation("com.squareup.moshi:moshi-adapters:1.15.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation(libs.firebase.auth)
    // kapt("com.squareup.moshi:moshi-kotlin-codegen:1.15.0")
    implementation ("io.github.cdimascio:dotenv-kotlin:6.2.2")

    val lifecycle_version = "2.8.7"

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")

    implementation("io.coil-kt:coil:2.2.2")
    implementation("io.coil-kt:coil-compose:2.2.2")

    implementation("androidx.navigation:navigation-compose:2.7.3")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:1.15.2")

    // Android Dependencies

    // implementation ("com.google.android.gms:play-services-auth:20.6.0")
    implementation(libs.androidx.credentials)
    implementation(libs.googleid)
    implementation(platform(libs.firebase.bom))

    implementation ("com.google.firebase:firebase-auth-ktx")
    implementation ("com.google.android.gms:play-services-auth:20.5.0")


    /*
    implementation(platform("com.google.firebase:firebase-bom:33.7.0"))
    implementation ("com.google.android.gms:play-services-auth:21.1.0")
    implementation ("com.google.cloud:google-cloud-credentials:2.0.0")
    implementation ("com.google.auth:google-auth-library:2.20.0")
    
     */


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}