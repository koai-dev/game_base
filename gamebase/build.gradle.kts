plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("org.jlleitschuh.gradle.ktlint") version "12.1.0"
    id("maven-publish")
}

group = "com.github.koai-dev"
version = "v1.1.1"

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
                "proguard-rules.pro",
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
            version = version.toString()

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
        val tagName = version.toString()
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

tasks.register("pushNewVersion"){
    dependsOn("clean")
    dependsOn("createReleaseTag")
    val createReleaseTag = getTasksByName("createReleaseTag", false).stream().findFirst().orElse(null)
    if (createReleaseTag != null) {
        createReleaseTag.mustRunAfter("clean")
        createReleaseTag.finalizedBy("createReleaseTag")
    }
}
