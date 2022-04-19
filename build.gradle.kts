import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
    id("org.jetbrains.compose") version "1.1.1"
}

group = "com.russellbanks"
version = "1.0"

repositories {
    google()
    mavenCentral()
}

@OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
dependencies {
    implementation(compose.desktop.currentOs)

    // Aurora - https://github.com/kirill-grouchnikov/aurora
    implementation(libs.aurora.window)
    implementation(libs.aurora.component)
    implementation(libs.aurora.theming)

    // Crypto - https://github.com/appmattus/crypto
    implementation(libs.crypto.cryptohash)
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
    kotlinOptions.freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "HashHash"
            packageVersion = "1.0.0"
        }
    }
}