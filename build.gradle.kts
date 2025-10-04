import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.buildconfig)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.detekt)
    alias(libs.plugins.kotlin)
}

group = "com.russellbanks"
version = "1.13.0"

repositories {
    google()
    mavenCentral()
    maven("https://jitpack.io")
    maven("https://central.sonatype.com/repository/maven-snapshots/")
}

dependencies {
    implementation(compose.desktop.currentOs)

    // Aurora - https://github.com/kirill-grouchnikov/aurora
    implementation(libs.aurora.component)
    implementation(libs.aurora.theming)
    implementation(libs.aurora.window)

    // Kotlin Coroutines - https://github.com/Kotlin/kotlinx.coroutines
    implementation(libs.coroutines.core)

    // Crypto - https://github.com/appmattus/crypto
    implementation(libs.crypto.cryptohash)

    // Flow Extensions - https://github.com/hoc081098/FlowExt
    implementation(libs.flowext)

    // GitHub API - https://github.com/hub4j/github-api
    implementation(libs.github.api)

    // Java Native Access - https://github.com/java-native-access/jna
    implementation(libs.jna)
    implementation(libs.jna.platform)

    // Klogging - https://github.com/klogging/klogging
    implementation(libs.klogging.jvm)

    // Detekt Formatting Plugin - https://github.com/detekt/detekt
    detektPlugins(libs.detekt.formatting)

    // KotlinX DateTime - https://github.com/Kotlin/kotlinx-datetime
    implementation(libs.kotlinx.datetime)

    // jSystemThemeDetector - https://github.com/Dansoftowner/jSystemThemeDetector
    implementation(libs.jsystemthemedetector)

    // Kotlin CSV - https://github.com/doyaaaaaken/kotlin-csv
    implementation(libs.kotlincsv)

    // Ktor - https://github.com/ktorio/ktor
    implementation(libs.ktor.client.java)

    // LWJGL - https://github.com/LWJGL/lwjgl3
    implementation(libs.lwjgl.core)
    implementation(libs.lwjgl.tinyfd)
    runtimeOnly(variantOf(libs.lwjgl.core) { classifier(lwjglNatives) })
    runtimeOnly(variantOf(libs.lwjgl.tinyfd) { classifier(lwjglNatives) })

    // SLF4J No-operation implementation - https://www.slf4j.org
    implementation(libs.slf4j.nop)

    // Voyager - https://github.com/adrielcafe/voyager
    implementation(libs.voyager.navigator)
    implementation(libs.voyager.screenmodel)
    implementation(libs.voyager.tabNavigator)

    // Window Styler - https://github.com/MayakaApps/ComposeWindowStyler
    implementation(libs.windowstyler)
}

tasks.withType<KotlinCompile> {
    compilerOptions {
        jvmTarget.set(JVM_21)
        freeCompilerArgs.set(listOf("-opt-in=kotlin.RequiresOptIn"))
    }
}

java {
    sourceCompatibility = JavaVersion.current()
    targetCompatibility = JavaVersion.VERSION_21
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Exe, TargetFormat.Msi, TargetFormat.Deb, TargetFormat.Rpm)
            modules("java.instrument", "java.management", "java.prefs", "jdk.unsupported")
            System.getenv("JAVA_HOME")?.let { javaHome = it }
            packageName = project.name
            packageVersion = project.version.toString()
            description = "A Multiplatform GUI for Hashing"
            vendor = "Russell Banks"
            licenseFile.set(project.file("src/main/resources/gpl-3.0.rst"))
            linux {
                iconFile.set(project.file("src/main/resources/logo.png"))
                menuGroup = project.name
            }
            macOS {
                iconFile.set(project.file("src/main/resources/logo.icns"))
                bundleID = "${project.group}.${project.name.lowercase()}"
            }
            windows {
                iconFile.set(project.file("src/main/resources/logo.ico"))
                menuGroup = project.name
                dirChooser = true
                upgradeUuid = "1A4C2D6B-AC84-47D4-A6EE-407A4AA8DED8"
            }
        }
    }
}

detekt {
    ignoreFailures = true
}

buildConfig {
    buildConfigField("String", "appName", "\"${project.name}\"")
    buildConfigField("String", "appVersion", provider { "\"${project.version}\"" })
}

val lwjglNatives = Pair(
    System.getProperty("os.name")!!,
    System.getProperty("os.arch")!!
).let { (name, _) ->
    when {
        arrayOf("Linux", "FreeBSD", "SunOS", "Unit").any(name::startsWith) -> "natives-linux"
        arrayOf("Mac OS X", "Darwin").any(name::startsWith) -> "natives-macos"
        name.startsWith("Windows") -> "natives-windows"
        else -> throw Error("Unrecognized or unsupported platform. Please set \"lwjglNatives\" manually")
    }
}
