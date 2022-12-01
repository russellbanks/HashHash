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

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import io.klogging.Klogging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.annotation.Single
import org.pushingpixels.aurora.window.AuroraWindowTitlePaneConfiguration
import org.pushingpixels.aurora.window.AuroraWindowTitlePaneConfigurations
import java.util.prefs.Preferences

@Single
class TitleBarHandler : Klogging {

    private val preferences = Preferences.userNodeForPackage(javaClass)

    private var cachedTitleBar: TitleBar? by mutableStateOf(null)

    val auroraTitleBarConfiguration: AuroraWindowTitlePaneConfiguration
        get() = getTitleBar().toAuroraTitlePane()

    fun getTitleBar(): TitleBar {
        return cachedTitleBar ?: when (preferences.getInt(titleBarKey, TitleBar.Native.ordinal)) {
            TitleBar.Custom.ordinal -> TitleBar.Custom
            else -> TitleBar.Native
        }.also { cachedTitleBar = it }
    }

    fun putTitleBar(titleBar: TitleBar) {
        preferences.putInt(titleBarKey, titleBar.ordinal)
        cachedTitleBar = titleBar
        CoroutineScope(Dispatchers.Default).launch {
            logger.info {
                "Put ${titleBar.name} into preferences with the key of " +
                        "\"$titleBarKey\" and the value of ${titleBar.ordinal}"
            }
        }
    }

    private fun TitleBar?.toAuroraTitlePane(): AuroraWindowTitlePaneConfiguration {
        return when (this) {
            TitleBar.Custom -> AuroraWindowTitlePaneConfigurations.AuroraPlain()
            else -> AuroraWindowTitlePaneConfigurations.System
        }
    }

    companion object {
        private const val titleBarKey = "titleBar"
    }
}
