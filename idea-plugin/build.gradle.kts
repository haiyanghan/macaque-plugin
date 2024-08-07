plugins {
  id("java")
  id("org.jetbrains.intellij") version "1.13.3"
}

group = "six.eared.macaque.plugin"
version = "1.5"

repositories {
  mavenLocal()
  mavenCentral()
}

dependencies {
  implementation("io.github.haiyanghan:six-eared-macaque-core:1.1.0-SNAPSHOT")
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
  version.set("2021.1")
  type.set("IU") // Target IDE Platform
  plugins.set(listOf())
}

tasks {
  // Set the JVM compatibility versions
  withType<JavaCompile> {
    sourceCompatibility = "11"
    targetCompatibility = "11"
  }

  signPlugin {
    certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
    privateKey.set(System.getenv("PRIVATE_KEY"))
    password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
  }

  publishPlugin {
    token.set(System.getenv("PUBLISH_TOKEN"))
  }
  patchPluginXml {
    version.set(project.version.toString())
    sinceBuild.set("211")
    untilBuild.set("251")
  }
}

sourceSets {
  main {
    java {
      srcDir("src/java")
    }
    resources {
      srcDir("src/resources")
    }
  }
  test {
    java {
      srcDir("test/java")
    }
  }
}
