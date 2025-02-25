plugins {
    id("data-plugin")
}

android {
    namespace = "software.ivancic.core.data"

    defaultConfig {

        buildConfigField("String", "WEATHER_API_URL", "\"https://api.open-meteo.com/\"")
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    implementation(project(":core:domain"))
    api(libs.retrofit)
    api(libs.retrofit.json.converter)
    api(libs.okhttp)
    api(libs.okhttpLoggingInterceptor)
    api(libs.kotlin.serialization.json)

    api(libs.koin.annotations)
    implementation(libs.koin.annotations.ksp)

    testImplementation(libs.junit)
}

// Compile time check
ksp {
    arg("KOIN_CONFIG_CHECK", "true")
}
