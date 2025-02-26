plugins {
    id("ui-plugin")
}

android {
    namespace = "software.ivancic.geo.ui"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.compose)
    testImplementation(libs.junit)

    api(project(":core:ui"))
    implementation(project(":geo:domain"))

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose)

    implementation(libs.location)

    implementation(libs.timber)
}
