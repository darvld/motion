plugins {
  alias(libs.plugins.kotlin)
}

kotlin {
  explicitApi()
}

dependencies {
  testImplementation(kotlin("test"))
}

tasks.test {
  useJUnitPlatform()
}