plugins {
	kotlin("jvm") version "1.8.0"
	kotlin("kapt") version "1.8.0"
	kotlin("plugin.spring") version "1.8.0"
	/*
	코틀린 전용 linter
	 */
	id("org.jlleitschuh.gradle.ktlint") version "12.1.0"
}
dependencies {
	implementation(project(":persistence"))

	testImplementation(kotlin("test"))
// 	kapt("org.projectlombok:lombok")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.0")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	// Jpa를 제외한 @Transactional만 사용
	implementation("org.springframework:spring-tx")
}

tasks.test {
	useJUnitPlatform()
}
kotlin {
	jvmToolchain(11)
}
tasks.bootJar { enabled = false }
