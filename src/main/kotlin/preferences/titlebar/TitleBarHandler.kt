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

package preferences.titlebar

import io.klogging.Klogging
import java.util.prefs.Preferences
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class TitleBarHandler : Klogging {

    private val preferences = Preferences.userNodeForPackage(javaClass)

    var titleBarListeners = ArrayList<(KProperty<*>, TitleBar?, TitleBar?) -> Unit>()

    private var cachedTitleBar: TitleBar? by Delegates.observable(initialValue = null) { property, oldValue, newValue ->
        titleBarListeners.forEach { it(property, oldValue, newValue) }
    }

    fun getTitleBar(): TitleBar {
        return cachedTitleBar
            ?: (if (preferences.getInt(titleBarKey, defaultTitleBarOrdinal) == TitleBar.Custom.ordinal) TitleBar.Custom
            else TitleBar.Native).also { cachedTitleBar = it }
    }

    suspend fun putTitleBar(titleBar: TitleBar) {
        preferences.putInt(titleBarKey, titleBar.ordinal)
        cachedTitleBar = titleBar
        logger.info {
            "Put ${titleBar.name} into preferences with the key of " +
                    "\"${titleBarKey}\" and the value of ${titleBar.ordinal}"
        }
    }

    companion object {
        private const val defaultTitleBarOrdinal = -1
        private const val titleBarKey = "titleBar"
    }
}
