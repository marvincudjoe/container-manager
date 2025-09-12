import org.gradle.api.JavaVersion.VERSION_21
import org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.5.5"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.jetbrains.kotlin.jvm") version "2.2.20"
    id("org.jetbrains.dokka") version "1.9.10"
    kotlin("plugin.spring") version "2.2.20"
    kotlin("plugin.jpa") version "2.2.20"
}

val javaVersion = VERSION_21.majorVersion
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(javaVersion))
    }
}

repositories {
    mavenLocal()
    mavenCentral()
}

object Versions {
    const val KOTLIN_VERSION = "2.0.0"
    const val GOOGLE_GUAVA = "33.4.8-jre"
    const val DOCKER_JAVA_VERSION = "3.6.0"
    const val SPRING_VERSION = "3.5.5"
    const val SPRING_DOC_VERSION = "2.8.13"
}

dependencies {
    // This dependency is used by the application.
    implementation("com.google.guava:guava:${Versions.GOOGLE_GUAVA}")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${Versions.SPRING_DOC_VERSION}")
    implementation("org.springdoc:springdoc-openapi-starter-common:${Versions.SPRING_DOC_VERSION}")

    implementation("org.springframework.boot:spring-boot-starter-web:${Versions.SPRING_VERSION}")
    // https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.devtools
    developmentOnly("org.springframework.boot:spring-boot-devtools:${Versions.SPRING_VERSION}")

    implementation("org.springframework.boot:spring-boot-starter-actuator")

    implementation("com.github.docker-java:docker-java:${Versions.DOCKER_JAVA_VERSION}")
    implementation("com.github.docker-java:docker-java-transport-httpclient5:${Versions.DOCKER_JAVA_VERSION}")
    testImplementation("org.springframework.boot:spring-boot-starter-test:${Versions.SPRING_VERSION}")
}

tasks.withType<KotlinCompile> {
    compilerOptions {
        // Enable strict null checks https://kotlinlang.org/docs/java-interop.html#jsr-305-support
        freeCompilerArgs.add("-Xjsr305=strict")
        jvmTarget.set(JVM_21)
    }
}
tasks.test {
    useJUnitPlatform()
}
