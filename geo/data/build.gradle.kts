plugins {
    id("data-plugin")
}

android {
    namespace = "software.ivancic.geo.data"

    defaultConfig {
        // let's say this is the api used all over the app, so we keep it in :core:data module
        buildConfigField("String", "GEO_API_URL", "\"https://geocoding-api.open-meteo.com/\"")
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    api(project(":core:data"))
    implementation(project(":geo:domain"))

    implementation(libs.timber)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.android.base)
    implementation(libs.koin.android)

    api(libs.koin.annotations)
    implementation(libs.koin.annotations.ksp)

    ksp(libs.room.compiler)
    implementation(libs.bundles.room)

    testImplementation(libs.junit)
}
