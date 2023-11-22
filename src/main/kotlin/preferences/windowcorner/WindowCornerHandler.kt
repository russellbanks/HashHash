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

package preferences.windowcorner

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.mayakapps.compose.windowstyler.WindowCornerPreference
import io.klogging.Klogging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.prefs.Preferences

object WindowCornerHandler : Klogging {
    private const val WINDOW_CORNER_KEY = "windowCorner"

    private val preferences = Preferences.userNodeForPackage(javaClass)

    private var cachedWindowCorner: WindowCornerPreference? by mutableStateOf(null)

    var windowCorner: WindowCornerPreference
        get() = cachedWindowCorner
            ?: when (preferences.getInt(WINDOW_CORNER_KEY, WindowCornerPreference.DEFAULT.ordinal)) {
                WindowCornerPreference.NOT_ROUNDED.ordinal -> WindowCornerPreference.NOT_ROUNDED
                WindowCornerPreference.ROUNDED.ordinal -> WindowCornerPreference.ROUNDED
                WindowCornerPreference.SMALL_ROUNDED.ordinal -> WindowCornerPreference.SMALL_ROUNDED
                else -> WindowCornerPreference.DEFAULT
            }.also { cachedWindowCorner = it }
        set(value) {
            preferences.putInt(WINDOW_CORNER_KEY, value.ordinal)
            cachedWindowCorner = value
            CoroutineScope(Dispatchers.Default).launch {
                logger.info {
                    "Put ${value.name} into preferences with the key of " +
                            "\"$WINDOW_CORNER_KEY\" and the value of ${value.ordinal}"
                }
            }
        }
}
