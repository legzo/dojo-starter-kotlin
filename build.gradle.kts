import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.7.20"
    id("com.github.ben-manes.versions") version "0.42.0"
}

group = "com.orange.ccmd.dojo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.slf4j:slf4j-api:2.0.3")

    testImplementation("ch.qos.logback:logback-classic:1.4.3")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.1")
    testImplementation("io.kotest:kotest-assertions-jvm:4.0.7")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

tasks.dependencyUpdates {
    resolutionStrategy {
        componentSelection {
            all {
                val rejected = listOf("alpha", "beta", "b", "rc", "cr", "m", "preview", "eap", "M")
                    .map { qualifier -> Regex("(?i).*[.-]$qualifier[.\\d-]*") }
                    .any { it.matches(candidate.version) }
                if (rejected) {
                    reject("Release candidate")
                }
            }
        }
    }
    checkForGradleUpdate = true
    outputFormatter = "json"
    outputDir = "$projectDir/build/dependencyUpdates"
}