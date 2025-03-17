plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.linglingdr00.taipeizoo"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.linglingdr00.taipeizoo"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // network
    implementation(libs.moshi.kotlin)
    implementation(libs.converter.moshi)
    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)

    // room
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
    implementation(libs.room.ktx)

    // hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // viewmodel
    implementation(libs.viewmodel.ktx)
    implementation(libs.livedata.ktx)
    implementation(libs.viewmodel.savedstate)

    // coroutines
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    // image
    implementation(libs.coil)
    implementation(libs.fresco)

    // navigation
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}