@file:Suppress("DSL_SCOPE_VIOLATION")

import org.jetbrains.compose.compose


plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
}


kotlin {

    androidTarget() {}

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    jvm("desktop"){}

    js(IR){
        browser {  }
        nodejs()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0") // comment this to make it work
                // implementation(libs.coroutines.test)
            }
        }
    }
}

android {
    namespace = "test"
    compileSdk = Android.compileSdk
    defaultConfig {
        minSdk = Android.minSdk
    }
}
