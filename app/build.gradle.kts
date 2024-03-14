plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.oyoke.iara"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.oyoke.iara"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "15.07.2023"

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    val nav_version = "2.7.7"
    val gson_version = "2.10"
    val circleimageview_version = "3.1.0"
    val core_ktx_version = "1.12.0"
    val appcompat_version = "1.6.1"
    val material_version = "1.11.0"
    val constraintlayout_version = "2.1.4"
    val lifecycle_version = "2.7.0"
    val navigation_version = "2.7.7"
    val junit_version = "4.13.2"
    val junit_test_version = "1.1.5"
    val espresso_test_version = "3.5.1"

    //Navigation Component
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    //Gson
    implementation("com.google.code.gson:gson:$gson_version")

    //CircleImageView
    implementation("de.hdodenhof:circleimageview:$circleimageview_version")

    //Core
    implementation("androidx.core:core-ktx:$core_ktx_version")

    //AppCompat
    implementation("androidx.appcompat:appcompat:$appcompat_version")

    //Material
    implementation("com.google.android.material:material:$material_version")

    //ConstraintLayout
    implementation("androidx.constraintlayout:constraintlayout:$constraintlayout_version")

    //Lifecycle
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")

    //Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:$navigation_version")
    implementation("androidx.navigation:navigation-ui-ktx:$navigation_version")

    //Testing
    testImplementation("junit:junit:$junit_version")
    androidTestImplementation("androidx.test.ext:junit:$junit_test_version")
    androidTestImplementation("androidx.test.espresso:espresso-core:$espresso_test_version")
}