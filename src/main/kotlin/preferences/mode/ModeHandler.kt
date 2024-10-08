/**

HashHash
Copyright (C) 2024 Russell Banks

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

package preferences.mode

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import io.klogging.Klogging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.prefs.Preferences

object ModeHandler : Klogging {
    private const val MODE_KEY = "mode"

    private val preferences = Preferences.userNodeForPackage(javaClass)

    private var cachedMode: Mode? = null

    var selectedMode by mutableStateOf(mode)

    var mode: Mode
        get() = cachedMode
            ?: if (preferences.getInt(MODE_KEY, Mode.SIMPLE.ordinal) == Mode.ADVANCED.ordinal) {
                Mode.ADVANCED
            } else {
                Mode.SIMPLE
            }.also { cachedMode = it }
        set(value) {
            preferences.putInt(MODE_KEY, value.ordinal)
            cachedMode = value
            selectedMode = value
            CoroutineScope(Dispatchers.Default).launch {
                logger.info {
                    "Put ${value.name} into preferences with the key of \"$MODE_KEY\" and the value of ${value.ordinal}"
                }
            }
        }
}
