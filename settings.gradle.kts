pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
    includeBuild("convenientplugins")
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "KaldiWeather"

include(":app")
include(":core:data")
include(":core:domain")
include(":core:ui")
include(":currentweather:data")
include(":currentweather:domain")
include(":currentweather:ui")
include(":geo:data")
include(":geo:domain")
include(":geo:ui")
