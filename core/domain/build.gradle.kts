import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.ksp)
}
java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}
kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_21
    }
}
dependencies {
    api(libs.kotlin.coroutines.android)

    api(platform(libs.koin.bom))
    api(libs.koin.android)
//    api(libs.koin.android.base)

    api(libs.koin.annotations)
    implementation(libs.koin.annotations.ksp)
}

// Compile time check
ksp {
    arg("KOIN_CONFIG_CHECK","true")
}
