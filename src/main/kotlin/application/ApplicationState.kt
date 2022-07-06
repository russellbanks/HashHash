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

package application

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import preferences.titlebar.TitleBar
import preferences.titlebar.TitleBarHandler
import preferences.windowcorner.WindowCornerHandler

class ApplicationState {
    val windows = mutableStateListOf<ApplicationWindowState>()

    init {
        windows += windowState()
    }

    private fun openNewWindow() {
        windows += windowState()
    }

    private fun windowState() = ApplicationWindowState(
        openNewWindow = ::openNewWindow,
        windows::remove
    )
}

class ApplicationWindowState(
    val openNewWindow: () -> Unit,
    private val close: (ApplicationWindowState) -> Unit,
) : KoinComponent {
    private val windowCornerHandler: WindowCornerHandler by inject()
    private val titleBarHandler: TitleBarHandler by inject()

    val isUndecorated = titleBarHandler.getTitleBar() == TitleBar.Custom

    private val windowCorner = windowCornerHandler.getWindowCorner()

    var needsRestarting by mutableStateOf(false)

    fun checkWindowNeedsRestarting() {
        needsRestarting = titleBarHandler.getTitleBar() == TitleBar.Custom != isUndecorated ||
                windowCornerHandler.getWindowCorner() != windowCorner
    }

    fun restart() {
        openNewWindow()
        close()
    }

    private fun close() = close(this)
}
