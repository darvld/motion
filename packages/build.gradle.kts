// resolve project version and group (allow overrides using Gradle properties)
val motionVersion = project.findProperty("motion.version") as? String ?: rootProject.file(".version").readText()
val motionGroup = project.findProperty("motion.group") as? String ?: "io.github.darvld.motion"

subprojects {
  apply(plugin = "signing")
  apply(plugin = "maven-publish")

  // common properties
  version = motionVersion
  group = motionGroup

  // configure javadoc task from Dokka
  tasks.maybeCreate("javadocJar", Jar::class.java).apply {
    archiveClassifier = "javadoc"
    isReproducibleFileOrder = true
  }

  // configure the Maven publication
  extensions.configure<PublishingExtension> {
    publications.create<MavenPublication>("maven") {
      artifactId = "motion-${name.lowercase()}"
      groupId = motionGroup
      version = motionVersion

      pom {
        // artifact name and description are set in each project
        url.set("https://github.com/darvld/motion")

        licenses {
          license {
            name = "The Apache License, Version 2.0"
            url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
          }
        }

        developers {
          developer {
            id = "darvld"
            name = "Dario Valdespino"
            email = "dvaldespino00@gmail.com"
          }
        }

        scm {
          connection = "scm:git:git://github.com/darvld/motion"
          developerConnection = "scm:git:ssh://github.com/darvld/motion"
          url = "https://github.com/darvld/motion"
        }
      }
    }

    repositories {
      maven {
        name = "GithubPackages"
        url = uri("https://maven.pkg.github.com/darvld/motion")

        credentials {
          username = project.findProperty("gpr.user") as? String ?: System.getenv("GITHUB_ACTOR")
          password = project.findProperty("gpr.key") as? String ?: System.getenv("GITHUB_TOKEN")
        }
      }
    }
  }

  // configure publication signing
  extensions.configure<SigningExtension> {
    // sign every publication
    extensions.getByType<PublishingExtension>().publications.forEach { sign(it) }
  }
}