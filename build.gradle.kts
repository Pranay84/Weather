// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.gms.google.services) apply false
    // alias(libs.plugins.kotlin.compose) apply false
    // kotlin("kapt") version "2.1.0"
    // alias(libs.plugins.google.gms.google.services) apply false
    id("com.google.devtools.ksp").version("1.6.10-1.0.4")
    kotlin("multiplatform") version "1.9.20" apply false
}

buildscript {
    dependencies {
        // Ensure the required classpath dependencies are present
        classpath("com.android.tools.build:gradle:8.2.0")
    }
}
