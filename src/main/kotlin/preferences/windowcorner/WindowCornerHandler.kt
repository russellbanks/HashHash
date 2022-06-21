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
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class WindowCornerHandler : Klogging {

    private val preferences = Preferences.userNodeForPackage(javaClass)

    var windowCornerListeners = ArrayList<(KProperty<*>, WindowCornerPreference?, WindowCornerPreference?) -> Unit>()

    private var cachedWindowCorner: WindowCornerPreference? by Delegates.observable(
        initialValue = null
    ) { property, oldWindowCorner, newWindowCorner ->
        windowCornerListeners.forEach { it(property, oldWindowCorner, newWindowCorner) }
    }

    fun getWindowCorner(): WindowCornerPreference {
        return cachedWindowCorner
            ?: (when (preferences.getInt(windowCornerKey, WindowCornerPreference.DEFAULT.ordinal)) {
                WindowCornerPreference.ROUNDED.ordinal -> WindowCornerPreference.ROUNDED
                WindowCornerPreference.NOT_ROUNDED.ordinal -> WindowCornerPreference.NOT_ROUNDED
                WindowCornerPreference.SMALL_ROUNDED.ordinal -> WindowCornerPreference.SMALL_ROUNDED
                else -> WindowCornerPreference.DEFAULT
            }).also { cachedWindowCorner = it }
    }

    suspend fun putWindowCorner(windowCornerPreference: WindowCornerPreference) {
        preferences.putInt(windowCornerKey, windowCornerPreference.ordinal)
        cachedWindowCorner = windowCornerPreference
        logger.info {
            "Put ${windowCornerPreference.name} into preferences with the key of " +
                    "\"${windowCornerKey}\" and the value of ${windowCornerPreference.ordinal}"
        }
    }

    companion object {
        private const val windowCornerKey = "windowCorner"
    }
}
