plugins {
    id("data-plugin")
}

android {
    namespace = "software.ivancic.data"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    implementation(project(":core:data"))
    implementation(project(":currentweather:domain"))

    implementation(libs.timber)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.android)

    api(libs.koin.annotations)
    implementation(libs.koin.annotations.ksp)

    testImplementation(libs.junit)
}

ksp {
    arg("KOIN_CONFIG_CHECK", "true")
}
