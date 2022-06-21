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

package preferences.windowcorner

import com.mayakapps.compose.windowstyler.WindowCornerPreference
import io.klogging.Klogging
import java.util.prefs.Preferences

class WindowCornerHandler : Klogging {

    private val preferences = Preferences.userNodeForPackage(javaClass)

    private var cachedWindowCorner: WindowCornerPreference? = null

    fun getWindowCorner(): WindowCornerPreference {
        return cachedWindowCorner
            ?: (when (preferences.getInt(titleBarKey, defaultWindowCornerOrdinal)) {
                WindowCornerPreference.ROUNDED.ordinal -> WindowCornerPreference.ROUNDED
                WindowCornerPreference.NOT_ROUNDED.ordinal -> WindowCornerPreference.NOT_ROUNDED
                WindowCornerPreference.SMALL_ROUNDED.ordinal -> WindowCornerPreference.SMALL_ROUNDED
                else -> WindowCornerPreference.DEFAULT
            }).also { cachedWindowCorner = it }
    }

    suspend fun putWindowCorner(windowCornerPreference: WindowCornerPreference) {
        preferences.putInt(titleBarKey, windowCornerPreference.ordinal)
        cachedWindowCorner = windowCornerPreference
        logger.info {
            "Put ${windowCornerPreference.name} into preferences with the key of " +
                    "\"${titleBarKey}\" and the value of ${windowCornerPreference.ordinal}"
        }
    }

    companion object {
        private const val titleBarKey = "windowCorner"
        private const val defaultWindowCornerOrdinal = -1
    }
}
