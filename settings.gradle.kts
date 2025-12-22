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
include(":presentation")
include(":presentation_greeting:api")
include(":presentation_greeting:impl")
include(":presentation_courses:api")
include(":presentation_courses:impl")
include(":network:api")
include(":network:impl")
