import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
    id("org.jetbrains.compose") version "1.2.0-alpha01-dev683"
    id("com.github.gmazzo.buildconfig") version "3.0.3"
}

group = "com.russellbanks"
version = "1.3.0"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    implementation(compose.desktop.currentOs)

    // Accompanist (Multiplatform fork) - https://github.com/Syer10/accompanist
    implementation(libs.accompanist.flowlayouts)

    // Aurora - https://github.com/kirill-grouchnikov/aurora
    implementation(libs.aurora.window)
    implementation(libs.aurora.component)
    implementation(libs.aurora.theming)

    // Crypto - https://github.com/appmattus/crypto
    implementation(libs.crypto.cryptohash)

    // Kotlin Coroutines - https://github.com/Kotlin/kotlinx.coroutines
    implementation(libs.coroutines.core)

    // KotlinX DateTime - https://github.com/Kotlin/kotlinx-datetime
    implementation(libs.kotlinx.datetime)

    // LWJGL - https://github.com/LWJGL/lwjgl3
    val lwjglCore = libs.lwjgl.core.get()
    val tinyFD = libs.lwjgl.tinyfd.get()
    implementation(libs.lwjgl.core)
    implementation(libs.lwjgl.tinyfd)
    runtimeOnly(lwjglCore.module.group, lwjglCore.module.name, lwjglCore.versionConstraint.toString(), classifier = lwjglNatives())
    runtimeOnly(tinyFD.module.group, tinyFD.module.name, tinyFD.versionConstraint.toString(), classifier = lwjglNatives())
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
    kotlinOptions.freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Exe,TargetFormat.Msi, TargetFormat.Deb, TargetFormat.Rpm)
            modules("java.instrument", "java.prefs", "jdk.unsupported")
            javaHome = System.getenv("JDK_18")
            packageName = project.name
            packageVersion = project.version.toString()
            description = "A Multiplatform GUI for Hashing."
            vendor = "Russell Banks"
            licenseFile.set(project.file("src/main/resources/gpl-3.0.rst"))
            linux {
                iconFile.set(project.file("src/main/resources/logo.png"))
                menuGroup = "HashHash"
            }
            macOS {
                bundleID = "${project.group}.${project.name.toLowerCase()}"
            }
            windows {
                iconFile.set(project.file("src/main/resources/logo.ico"))
                menuGroup = "HashHash"
                dirChooser = true
                upgradeUuid = "1A4C2D6B-AC84-47D4-A6EE-407A4AA8DED8"
            }
        }
    }
}

buildConfig {
    buildConfigField("String", "appName", "\"${project.name}\"")
    buildConfigField("String", "appVersion", provider { "\"${project.version}\"" })
}

fun lwjglNatives() = Pair(
    System.getProperty("os.name")!!,
    System.getProperty("os.arch")!!
).let { (name, arch) ->
    when {
        arrayOf("Linux", "FreeBSD", "SunOS", "Unit").any { name.startsWith(it) } ->
            if (arrayOf("arm", "aarch64").any { arch.startsWith(it) })
                "natives-linux${if (arch.contains("64") || arch.startsWith("armv8")) "-arm64" else "-arm32"}"
            else
                "natives-linux"
        arrayOf("Mac OS X", "Darwin").any { name.startsWith(it) }                ->
            "natives-macos${if (arch.startsWith("aarch64")) "-arm64" else ""}"
        arrayOf("Windows").any { name.startsWith(it) }                           ->
            if (arch.contains("64"))
                "natives-windows${if (arch.startsWith("aarch64")) "-arm64" else ""}"
            else
                "natives-windows-x86"
        else -> throw Error("Unrecognized or unsupported platform. Please set \"lwjglNatives\" manually")
    }
}