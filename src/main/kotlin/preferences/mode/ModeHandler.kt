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

package preferences.mode

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import io.klogging.Klogging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.annotation.Single
import java.util.prefs.Preferences

@Single
class ModeHandler : Klogging {

    private val preferences = Preferences.userNodeForPackage(javaClass)

    private var cachedMode: Mode? = null

    var selectedMode by mutableStateOf(getMode())

    private fun getMode(): Mode {
        return cachedMode
            ?: if (preferences.getInt(modeKey, Mode.SIMPLE.ordinal) == Mode.ADVANCED.ordinal) Mode.ADVANCED
            else { Mode.SIMPLE }.also { cachedMode = it }
    }

    fun putMode(mode: Mode) {
        preferences.putInt(modeKey, mode.ordinal)
        cachedMode = mode
        selectedMode = mode
        CoroutineScope(Dispatchers.Default).launch {
            logger.info {
                "Put ${mode.name} into preferences with the key of \"$modeKey\" and the value of ${mode.ordinal}"
            }
        }
    }

    companion object {
        private const val modeKey = "mode"
    }
}
