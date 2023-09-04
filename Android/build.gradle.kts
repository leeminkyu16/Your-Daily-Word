// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:8.0.0")
    }
}

plugins {
    id("com.android.application") version "8.0.0" apply false
    id("com.android.library") version "8.0.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.21" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.0-RC3"
}

detekt {
    config.setFrom(
        files(
            "./config/detekt-config.yml",
        )
    )
    source.setFrom(
        files(
            "./app/src/main/java",
        )
    )
}
