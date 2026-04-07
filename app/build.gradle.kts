plugins {
    alias(libs.plugins.agp.app)
}
android {
    namespace = "io.tairitsu.neigui"
    compileSdk = 36
    defaultConfig {
        minSdk = 34
        targetSdk = 36
        versionCode = 2603052
        versionName = "1.5.2"
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            ndk { abiFilters.add("arm64-v8a") }
            signingConfig = signingConfigs["debug"]
        }
    }
    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    packaging {
        resources {
            merges += "META_INF/xposed/*"
        }
    }
}
dependencies {
    compileOnly(libs.libxposed.api)
    implementation(libs.libxposed.service)
}