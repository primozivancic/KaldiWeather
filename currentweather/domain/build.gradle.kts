plugins {
    id("domain-plugin")
}

dependencies {
    api(project(":core:domain"))
    api(libs.koin.annotations)
    implementation(libs.koin.annotations.ksp)
}

// Compile time check
ksp {
    arg("KOIN_CONFIG_CHECK", "true")
}
