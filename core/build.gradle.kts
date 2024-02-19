@file:Suppress("DSL_SCOPE_VIOLATION")

import org.jetbrains.compose.compose


plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.kotlin.serialization)
//    kotlin("native.cocoapods")
    id("kotlinx-atomicfu")
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

    wasmJs(){
        browser()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                implementation(compose.ui)
                implementation(compose("org.jetbrains.compose.ui:ui-util"))
                implementation(compose.foundation)
                implementation(libs.serialization)
                implementation(libs.datetime)
//                implementation(libs.coroutines.core)
                implementation(libs.koin.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.coroutines.test)
                implementation(libs.ktor.client.test)
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
