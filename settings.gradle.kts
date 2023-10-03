@file: Suppress("UnstableApiUsage", "LocalVariableName")

import org.gradle.api.initialization.resolve.RepositoriesMode.FAIL_ON_PROJECT_REPOS
import build.less.plugin.settings.buildless

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "motion"
include(":packages:core")

// Project-level plugins
pluginManagement {
  repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
  }
}

// Settings plugins
plugins {
  id("build.less") version("1.0.0-beta1")
}

// Centralized dependency management
dependencyResolutionManagement {
  repositoriesMode.set(FAIL_ON_PROJECT_REPOS)

  repositories {
    google()
    mavenCentral()
    mavenLocal()
  }
}

// Remote build cache (Buildless)
buildless {
  remoteCache {
    // allow disabling pushing to the remote cache
    val cachePush: String? by settings
    push = cachePush?.toBooleanStrictOrNull() ?: true
  }
}