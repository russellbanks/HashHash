/**

HashHash
Copyright (C) 2022  Russell Banks

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.

 */

package theme

import org.pushingpixels.aurora.theming.AuroraSkinDefinition
import org.pushingpixels.aurora.theming.dustSkin
import org.pushingpixels.aurora.theming.nightShadeSkin
import java.util.prefs.Preferences

class ThemeHandler(private val systemDark: Boolean) {

    private val preferences = Preferences.userNodeForPackage(javaClass)

    fun getTheme(): Theme {
        return when (preferences.getInt(themeKey, defaultThemeOrdinal)) {
            Theme.LIGHT.ordinal -> Theme.LIGHT
            Theme.DARK.ordinal -> Theme.DARK
            else -> Theme.SYSTEM
        }
    }

    fun getAuroraTheme(): AuroraSkinDefinition {
        return when (preferences.getInt(themeKey, defaultThemeOrdinal)) {
            Theme.LIGHT.ordinal -> dustSkin()
            Theme.DARK.ordinal -> nightShadeSkin()
            else -> if (systemDark) nightShadeSkin() else dustSkin()
        }
    }

    fun putTheme(theme: Theme) = preferences.putInt(themeKey, theme.ordinal)

    companion object {
        const val themeKey = "theme"
        const val defaultThemeOrdinal = -1
    }
}