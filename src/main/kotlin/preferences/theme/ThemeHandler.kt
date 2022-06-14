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

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import io.klogging.Klogging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.prefs.Preferences
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class ThemeHandler : Klogging {

    private val preferences = Preferences.userNodeForPackage(javaClass)

    var themeListeners = ArrayList<(KProperty<*>, Theme?, Theme?) -> Unit>()

    private var cachedTheme: Theme? by Delegates.observable(initialValue = null) { property, oldValue, newValue ->
        themeListeners.forEach { it(property, oldValue, newValue) }
    }

    @Composable
    fun isDark(): Boolean {
        return when (cachedTheme) {
            Theme.DARK -> true
            Theme.LIGHT -> false
            else -> isSystemInDarkTheme()
        }
    }

    fun getTheme(scope: CoroutineScope): Theme {
        return cachedTheme.also {
            scope.launch(Dispatchers.Default) {
                logger.info("Returned ${it?.name} (${if (it == null) "Created" else "Cached"})")
            }
        } ?: when (preferences.getInt(themeKey, defaultThemeOrdinal)) {
            Theme.LIGHT.ordinal -> Theme.LIGHT
            Theme.DARK.ordinal -> Theme.DARK
            else -> Theme.SYSTEM
        }.also { cachedTheme = it }
    }

    suspend fun putTheme(theme: Theme) = preferences.putInt(themeKey, theme.ordinal)
        .also {
            cachedTheme = theme
            logger.info {
                "Put ${theme.name} into preferences with the key of \"$themeKey\" and the value of ${theme.ordinal}"
            }
        }

    companion object {
        const val themeKey = "theme"
        const val defaultThemeOrdinal = -1
    }
}
