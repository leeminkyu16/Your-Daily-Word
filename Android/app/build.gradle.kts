@file:Suppress("UnstableApiUsage")

import com.google.protobuf.gradle.proto


plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.protobuf")
}

android {
    namespace = "com.minkyu.yourdailyword.android"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.minkyu.yourdailyword.android"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
			signingConfig = signingConfigs.getByName("debug")
		}
		named("release") {
            isMinifyEnabled = false
            setProguardFiles(
                listOf(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            )
        }
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    sourceSets.getByName("main") {
        proto {
            srcDir(project(":Your-Daily-Word-Common").file("src/main/proto"))
        }
    }
}

dependencies {
    implementation(group = "androidx.core", name = "core-ktx", version = "1.13.1")
    implementation(group = "androidx.lifecycle", name = "lifecycle-runtime-ktx", version = "2.8.4")
    implementation(group = "androidx.activity", name = "activity-compose", version = "1.9.1")
    implementation(group="androidx.compose.material", name="material-icons-extended", version="1.6.8")
    implementation(group="androidx.navigation", name="navigation-runtime-ktx", version="2.7.7")
    implementation(group="androidx.navigation", name="navigation-compose", version="2.7.7")
    implementation(group="androidx.hilt", name="hilt-navigation-compose", version="1.2.0")
    implementation(platform("androidx.compose:compose-bom:2024.08.00"))
    implementation(group="androidx.compose.ui", name="ui", version="1.6.8")
    implementation(group="androidx.compose.ui", name="ui-graphics", version="1.6.8")
    implementation(group="androidx.compose.ui", name="ui-tooling-preview", version="1.6.8")
    implementation(group="androidx.compose.material3", name="material3", version="1.2.1")
    implementation(group="com.google.android.gms", name="play-services-code-scanner", version="16.1.0")
    implementation(group="com.google.protobuf", name="protobuf-java", version="4.27.0")
    implementation(group="com.google.protobuf", name="protobuf-java-util", version="4.27.0")
    implementation(group="com.google.protobuf", name="protobuf-kotlin", version="4.27.0")
    implementation(group="io.grpc", name="grpc-android", version="1.66.0")
    implementation(group="io.grpc", name="grpc-okhttp", version="1.66.0")
    implementation(group="io.grpc", name="grpc-kotlin-stub", version="1.4.1")
    implementation(project(":Your-Daily-Word-Common"))
    implementation ("com.google.dagger:hilt-android:2.49")
    kapt ("com.google.dagger:hilt-compiler:2.46.1")
    runtimeOnly ("androidx.compose.animation:animation:1.6.8")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.2.1")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation (platform("androidx.compose:compose-bom:2024.08.00"))
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4")
    debugImplementation ("androidx.compose.ui:ui-tooling")
    debugImplementation ("androidx.compose.ui:ui-test-manifest")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:4.27.0"
    }
    plugins {
        create("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:1.4.1:jdk8@jar"
        }
    }
    generateProtoTasks {
        all().forEach { task ->
            task.plugins {
                create("grpckt") {
                }
            }
            task.builtins {
                create("kotlin") {}
            }
        }
    }
}

kapt {
    correctErrorTypes = true
}
