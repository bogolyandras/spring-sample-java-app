
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
    java
    jacoco
    id("org.springframework.boot") version "2.6.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("com.diffplug.spotless") version "6.6.1"
}

group = "com.bogolyandras"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        html.required.set(true)
        xml.required.set(true)
        csv.required.set(false)
    }
}

tasks.getByName<BootBuildImage>("bootBuildImage") {
    imageName = "bogolyandras.jfrog.io/default-docker-local/${project.name}"
}

spotless {
    format("misc") {
        target("*.gradle", "*.gradle.kts", "*.md", ".gitignore")

        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }

    java {
        importOrder()
        removeUnusedImports()
    }
}
