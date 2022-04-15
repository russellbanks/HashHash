pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

rootProject.name = "HashHash"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            aurora()
            crypto()
        }
    }
}

fun VersionCatalogBuilder.aurora() {
    val aurora = version("aurora", "1.1.0")

    library("aurora-window","org.pushing-pixels", "aurora-window").versionRef(aurora)
    library("aurora-component","org.pushing-pixels", "aurora-component").versionRef(aurora)
    library("aurora-theming","org.pushing-pixels", "aurora-theming").versionRef(aurora)
}

fun VersionCatalogBuilder.crypto() {
    library("crypto-cryptohash", "com.appmattus.crypto", "cryptohash").version("0.10.0")
}
