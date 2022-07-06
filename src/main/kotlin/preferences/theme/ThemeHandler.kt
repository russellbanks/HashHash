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

package preferences.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.jthemedetecor.OsThemeDetector
import io.klogging.Klogging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.annotation.Single
import org.pushingpixels.aurora.theming.AuroraSkinDefinition
import org.pushingpixels.aurora.theming.dustSkin
import org.pushingpixels.aurora.theming.nightShadeSkin
import java.util.prefs.Preferences

@Single
class ThemeHandler : Klogging {

    private val preferences = Preferences.userNodeForPackage(javaClass)

    private var cachedTheme: Theme? = null

    private val osThemeDetector = OsThemeDetector.getDetector()

    var auroraSkin by mutableStateOf(getTheme().toAuroraTheme())

    init {
        osThemeDetector.registerListener { isDark: Boolean ->
            if (getTheme() == Theme.System) auroraSkin = if (isDark) nightShadeSkin() else dustSkin()
        }
    }

    fun isDark(): Boolean {
        return when (cachedTheme) {
            Theme.Dark -> true
            Theme.Light -> false
            else -> osThemeDetector.isDark
        }
    }

    fun getTheme(): Theme {
        return cachedTheme ?: when (preferences.getInt(themeKey, Theme.System.ordinal)) {
            Theme.Light.ordinal -> Theme.Light
            Theme.Dark.ordinal -> Theme.Dark
            else -> Theme.System
        }.also { cachedTheme = it }
    }

    fun putTheme(theme: Theme) {
        preferences.putInt(themeKey, theme.ordinal)
        cachedTheme = theme
        auroraSkin = theme.toAuroraTheme()
        CoroutineScope(Dispatchers.Default).launch {
            logger.info {
                "Put ${theme.name} into preferences with the key of \"$themeKey\" and the value of ${theme.ordinal}"
            }
        }
    }

    private fun Theme?.toAuroraTheme(): AuroraSkinDefinition {
        return when (this) {
            Theme.Light -> dustSkin()
            Theme.Dark -> nightShadeSkin()
            else -> if (osThemeDetector.isDark) nightShadeSkin() else dustSkin()
        }
    }

    companion object {
        const val themeKey = "theme"
    }
}
