plugins {
    kotlin("jvm") version "1.8.22"
    application
}

group = "uts"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
}

application {
    mainClass.set("uts_00000075344_faturachman_al_kahfi.MainKt")
}
