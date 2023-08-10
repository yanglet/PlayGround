import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.1.0"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.8.21"
	kotlin("plugin.spring") version "1.8.21"
}

allprojects {
	group = "com.example"
	version = "0.0.1-SNAPSHOT"

	apply(plugin = "org.springframework.boot")
	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "org.jetbrains.kotlin.jvm")
	apply(plugin = "org.jetbrains.kotlin.plugin.spring")

	java {
		sourceCompatibility = JavaVersion.VERSION_17
	}

	configurations {
		compileOnly {
			extendsFrom(configurations.annotationProcessor.get())
		}
	}

	repositories {
		mavenCentral()
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
}

subprojects {
	extra["springCloudVersion"] = "2022.0.3"

	dependencies {
		implementation("org.springframework.boot:spring-boot-starter-web")
		implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		implementation("org.springframework.cloud:spring-cloud-stream")
		annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
		testImplementation("org.springframework.boot:spring-boot-starter-test")
		testImplementation("org.springframework.cloud:spring-cloud-stream-test-binder")
	}

	dependencyManagement {
		imports {
			mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
		}
	}
}

tasks.bootJar {
	enabled = false
}

tasks.jar {
	enabled = true
}