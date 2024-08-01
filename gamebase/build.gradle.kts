plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("org.jlleitschuh.gradle.ktlint") version "12.1.0"
    id("maven-publish")
}

group = "com.github.koai-dev"
version = "1.0.0"

android {
    namespace = "com.koai.gamebase"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.koai"
            artifactId = "gamebase"
            version = "v1.0.1"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}

dependencies {

    api(libs.androidx.core.ktx)
    api(libs.androidx.appcompat)
    api(libs.material)
    api(libs.androidx.ui.graphics.android)
    api(libs.androidx.animation.core.android)
    testApi(libs.junit)
    androidTestApi(libs.androidx.junit)
    androidTestApi(libs.androidx.espresso.core)

    api(libs.androidx.activity.compose)
    api(platform(libs.androidx.compose.bom))
    androidTestApi(platform(libs.androidx.compose.bom))
    api(libs.androidx.ui)
    api(libs.androidx.ui.graphics)
    api(libs.androidx.ui.tooling.preview)
    api(libs.androidx.material3)
}

tasks.register("localBuild") {
    dependsOn("assembleRelease")
}

tasks.register("createReleaseTag") {
    doLast {
        val tagName = "v1.0.1"
        try {
            exec {
                commandLine("git", "tag", "-a", tagName, "-m", "Release tag $tagName")
            }

            exec {
                commandLine("git", "push", "origin", tagName)
            }
        } catch (e: Exception) {
            println(e.toString())
        }
    }
}