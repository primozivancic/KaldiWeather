plugins {
    id("ui-plugin")
}

android {
    namespace = "software.ivancic.base.ui"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose)

    api(libs.koin.annotations)
    implementation(libs.koin.annotations.ksp)

    api(libs.androidx.lifecycle.viewmodel)

    testImplementation(libs.junit)
}

// Compile time check
ksp {
    arg("KOIN_CONFIG_CHECK", "true")
}
