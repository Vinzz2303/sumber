plugins {
    kotlin("jvm") version "1.8.22"
    application
}

group = "uas"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
}

application {
    mainClass.set("uas_00000075344_faturachman_al_kahfi.MainKt")
}
