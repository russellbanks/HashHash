package preferences.theme

import org.pushingpixels.aurora.theming.AuroraSkinDefinition
import org.pushingpixels.aurora.theming.dustSkin
import org.pushingpixels.aurora.theming.nightShadeSkin

fun Theme?.toAuroraTheme(systemDark: Boolean): AuroraSkinDefinition {
    return when (this) {
        Theme.LIGHT -> dustSkin()
        Theme.DARK -> nightShadeSkin()
        else -> if (systemDark) nightShadeSkin() else dustSkin()
    }
}