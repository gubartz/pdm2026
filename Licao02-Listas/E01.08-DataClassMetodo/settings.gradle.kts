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
        // TODO(1) Adicionar suporte o jitpack para o componente de seleção de datas (datepicker)
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "AppLazyColumn"
include(":app")
 