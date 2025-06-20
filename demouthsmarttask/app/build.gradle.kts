plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("kotlin-kapt") // Đã có, cần cho Glide
}

android {
    namespace = "com.example.demouthsmarttask"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.example.demouthsmarttask"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true // Đảm bảo cái này là TRUE
    }
}

dependencies {
    // Firebase platform (BOM) - Sử dụng phiên bản ổn định mới nhất (33.0.0)
    implementation(platform("com.google.firebase:firebase-bom:33.0.0"))

    // Firebase dependencies
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-storage-ktx") // Thêm cho lưu ảnh

    // Google Play Services Auth
    implementation("com.google.android.gms:play-services-auth:21.2.0")

    // Material Design
    implementation("com.google.android.material:material:1.12.0")

    // AndroidX Core & UI
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.cardview:cardview:1.0.0")

    // Circle Image View
    implementation("de.hdodenhof:circleimageview:3.1.0")

    // Glide for image loading
    implementation("com.github.bumptech.glide:glide:4.16.0")
    kapt("com.github.bumptech.glide:compiler:4.16.0")

    // Material Date Picker (cho Date of Birth)
    implementation("com.google.android.material:material:1.12.0") // Đã có, đủ dùng

    // Coroutines cho xử lý bất đồng bộ
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Lifecycle & Navigation
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")
}