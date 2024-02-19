pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://packages.jetbrains.team/maven/p/kpm/public/")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")

    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://packages.jetbrains.team/maven/p/kpm/public/")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
        mavenLocal()
    }
}



rootProject.name = "test"
include(":core")
