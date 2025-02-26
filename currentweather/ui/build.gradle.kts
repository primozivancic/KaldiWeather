plugins {
    id("ui-plugin")
}

android {
    namespace = "software.ivancic.ui"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose)

    implementation(platform(libs.koin.bom))
    implementation(libs.bundles.koin)

    api(libs.koin.annotations)
    implementation(libs.koin.annotations.ksp)

    implementation(project(":core:ui"))
    implementation(project(":geo:ui"))
    implementation(project(":currentweather:domain"))

    implementation(libs.bundles.coil)

    testImplementation(libs.junit)
}

// Compile time check
ksp {
    arg("KOIN_CONFIG_CHECK", "true")
}
