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

package application

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import preferences.titlebar.TitleBarHandler
import preferences.windowcorner.WindowCornerHandler

class ApplicationWindowState(val openNewWindow: () -> Unit, private val close: (ApplicationWindowState) -> Unit) {
    private val titleBar = TitleBarHandler.titleBar

    private val windowCorner = WindowCornerHandler.windowCorner

    val titlePaneConfiguration = TitleBarHandler.auroraTitleBarConfiguration

    var needsRestarting by mutableStateOf(false)

    fun checkWindowNeedsRestarting(): Boolean {
        val titleBarChanged = titleBar != TitleBarHandler.titleBar
        val windowCornerChanged = windowCorner != WindowCornerHandler.windowCorner
        needsRestarting = titleBarChanged || windowCornerChanged
        return needsRestarting
    }

    fun restart() {
        openNewWindow()
        close()
    }

    private fun close() = close(this)
}
