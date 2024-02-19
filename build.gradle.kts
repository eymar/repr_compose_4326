@file:Suppress("DSL_SCOPE_VIOLATION")

import org.jetbrains.kotlin.gradle.targets.native.tasks.PodBuildTask
import org.jetbrains.kotlin.gradle.targets.native.tasks.PodInstallTask
import org.jetbrains.kotlin.gradle.targets.native.tasks.PodSetupBuildTask
import org.jetbrains.kotlin.gradle.tasks.CInteropProcess
import org.jetbrains.kotlin.gradle.tasks.DummyFrameworkTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.tasks.PodspecTask

plugins {

    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.js) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.kotlin.allopen) apply false
    alias(libs.plugins.compose) apply false
    alias(libs.plugins.resources) apply false

    alias(libs.plugins.shadow) apply false
    alias(libs.plugins.micronaut) apply false
    alias(libs.plugins.spotless)
    alias(libs.plugins.ktor) apply false

}
val disableIos = findProperty("disableIos") == "true"


buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-serialization:${libs.versions.kotlin}")
        classpath("com.google.gms:google-services:4.4.0")
        classpath("dev.icerock.moko:resources-generator:${libs.versions.resources}")
        classpath(libs.sqldelight.gradle)
        classpath(libs.atomicfu.gp)
    }
}

val _jvmTarget = findProperty("jvmTarget") as String

subprojects {
    if (disableIos) {
        runCatching {
            tasks.named("generateDummyFramework") {
                enabled = false
            }
        }
        tasks.withType<DummyFrameworkTask>() {
            enabled = false
        }
        tasks.withType<PodspecTask>() {
            enabled = false
        }
        tasks.withType<PodInstallTask>() {
            enabled = false
        }
        tasks.withType<PodBuildTask>() {
            enabled = false
        }
        tasks.withType<PodSetupBuildTask>() {
            enabled = false
        }
        tasks.withType<CInteropProcess>() {
            enabled = false
        }
    }
    tasks.withType<KotlinCompile>{
        kotlinOptions.jvmTarget = _jvmTarget
    }
}


gradle.taskGraph.whenReady {
    this.allTasks.forEach {
        if (it is KotlinCompile){
            it.kotlinOptions {
                jvmTarget = _jvmTarget
            }
        }
        if (it is JavaCompile){
            it.targetCompatibility = _jvmTarget
            it.sourceCompatibility = _jvmTarget
        }
    }
}
//allprojects {
//    apply(plugin = "com.diffplug.spotless")
//    spotless {
//        kotlin {
//            target("**/*.kt")
//            targetExclude("$buildDir/**/*.kt", "bin/**/*.kt", "buildSrc/**/*.kt")
//            ktlint(libs.versions.ktlint.get())
//        }
//        kotlinGradle {
//            target("*.gradle.kts")
//            ktlint(libs.versions.ktlint.get())
//        }
//        java {
//            target("**/*.java")
//            targetExclude("$buildDir/**/*.java", "bin/**/*.java")
//
//        }
//    }
//}


//tasks.register("clean", Delete::class) {
//    delete(rootProject.buildDir)
//}
