plugins {
  alias(libs.plugins.kotlin)
  alias(libs.plugins.dokkatoo)
}

kotlin {
  explicitApi()
}

java {
  withSourcesJar()
}

dependencies {
  testImplementation(kotlin("test"))
}

tasks.test {
  useJUnitPlatform()
}

tasks.named<Jar>("javadocJar") {
  from(tasks.named("dokkatooGeneratePublicationHtml"))
}

publishing.publications.named<MavenPublication>("maven") {
  from(components["kotlin"])
  artifact(tasks.named("sourcesJar"))
  artifact(tasks.named("javadocJar"))

  pom {
    name = "Motion Core"
    description = "Low level bindings for the Chipmunk 2D physics engine."
  }
}