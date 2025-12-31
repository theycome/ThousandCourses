enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

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
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ThousandCourses"

include(":app")
include(":core")
include(":navigator")
include(":presentation:core")
include(":presentation:greeting:api")
include(":presentation:greeting:impl")
include(":presentation:courses:api")
include(":presentation:courses:impl")
include(":network:api")
include(":network:impl")
