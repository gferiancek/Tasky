plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        applicationId = ProjectConfig.appId
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
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
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.composeVersion
    }
    packagingOptions {
        resources.excludes.addAll(listOf("META-INF/AL2.0","META-INF/LGPL2.1"))
    }
}

dependencies {
    // AndroidX
    implementation(AndroidX.appCompat)
    implementation(AndroidX.coreKtx)
    implementation(AndroidX.lifecycleRuntime)

    // Compose
    implementation(Compose.activityCompose)
    implementation(Compose.hiltNavigation)
    implementation(Compose.material)
    implementation(Compose.navigation)
    implementation(Compose.toolingPreview)
    implementation(Compose.ui)
    debugImplementation(Compose.tooling)

    // Hilt
    implementation(Hilt.android)
    kapt(Hilt.compiler)

    // LeakCanary
    debugImplementation(LeakCanary.leakCanary)

    // Modules
    implementation(project(Modules.core))
    implementation(project(Modules.agendaData))
    implementation(project(Modules.agendaDomain))
    implementation(project(Modules.agendaPresentation))
    implementation(project(Modules.loginData))
    implementation(project(Modules.loginDomain))
    implementation(project(Modules.loginPresentation))

    // Testing
    androidTestImplementation(AndroidXTest.runner)
    androidTestImplementation(ComposeTest.uiTest)
    androidTestImplementation(HiltTest.hiltAndroidTesting)
    androidTestImplementation(Junit.junitAndroidExt)
    debugImplementation(ComposeTest.uiTestManifest)
    kaptAndroidTest(Hilt.compiler)
    testImplementation(Junit.junit4)
}