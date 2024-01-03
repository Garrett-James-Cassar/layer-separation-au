import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.3"
    id("io.spring.dependency-management") version "1.1.3"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
    kotlin("plugin.jpa") version "1.8.22"
}

group = "cool"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-api:2.2.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.liquibase:liquibase-core")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    // tracing
    implementation("io.micrometer:micrometer-tracing-bridge-otel:1.1.4")
    implementation("io.opentelemetry:opentelemetry-exporter-zipkin:1.30.0")
//    implementation("org.springframework.cloud:spring-cloud-starter-zipkin:2.2.4.RELEASE")


    runtimeOnly("com.h2database:h2")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("io.rest-assured:rest-assured:5.4.0")
    testImplementation("com.tngtech.archunit:archunit-junit5:1.1.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
