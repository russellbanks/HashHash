/**

HashHash
Copyright (C) 2023 Russell Banks

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
import org.pushingpixels.aurora.theming.AuroraSkinDefinition
import org.pushingpixels.aurora.theming.geminiSkin
import org.pushingpixels.aurora.theming.nightShadeSkin
import java.util.prefs.Preferences

object ThemeHandler : Klogging {
    private const val themeKey = "theme"

    private val preferences = Preferences.userNodeForPackage(javaClass)

    private var cachedTheme: Theme? = null

    private val osThemeDetector = OsThemeDetector.getDetector()

    var auroraSkin by mutableStateOf(theme.toAuroraTheme())

    var theme: Theme
        get() = cachedTheme ?: when (preferences.getInt(themeKey, Theme.System.ordinal)) {
            Theme.Light.ordinal -> Theme.Light
            Theme.Dark.ordinal -> Theme.Dark
            else -> Theme.System
        }.also { cachedTheme = it }
        set(value) {
            preferences.putInt(themeKey, value.ordinal)
            cachedTheme = value
            auroraSkin = value.toAuroraTheme()
            CoroutineScope(Dispatchers.Default).launch {
                logger.info {
                    "Put ${value.name} into preferences with the key of \"$themeKey\" and the value of ${value.ordinal}"
                }
            }
        }

    init {
        osThemeDetector.registerListener {
            theme.also { if (it == Theme.System) auroraSkin = it.toAuroraTheme() }
        }
    }

    private fun Theme?.toAuroraTheme(): AuroraSkinDefinition {
        return when (this) {
            Theme.Light -> geminiSkin()
            Theme.Dark -> nightShadeSkin()
            else -> if (osThemeDetector.isDark) nightShadeSkin() else geminiSkin()
        }
    }
}
