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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.destroy
import org.koin.core.annotation.Single
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.pushingpixels.aurora.window.AuroraApplicationScope
import preferences.titlebar.TitleBarHandler
import preferences.windowcorner.WindowCornerHandler

@Single
class ApplicationState : KoinComponent {
    val windows = mutableStateListOf<ApplicationWindowState>()
    private val lifecycle: LifecycleRegistry by inject()

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

    fun exitApplication(auroraApplicationScope: AuroraApplicationScope) {
        auroraApplicationScope.exitApplication()
        lifecycle.destroy()
    }
}

class ApplicationWindowState(
    val openNewWindow: () -> Unit,
    private val close: (ApplicationWindowState) -> Unit
) : KoinComponent {
    private val windowCornerHandler: WindowCornerHandler by inject()
    private val titleBarHandler: TitleBarHandler by inject()

    private val titleBar = titleBarHandler.getTitleBar()

    private val windowCorner = windowCornerHandler.getWindowCorner()

    val titlePaneConfiguration = titleBarHandler.auroraTitleBarConfiguration

    var needsRestarting by mutableStateOf(false)

    fun checkWindowNeedsRestarting() {
        val titleBarChanged = titleBar != titleBarHandler.getTitleBar()
        val windowCornerChanged = windowCorner != windowCornerHandler.getWindowCorner()
        needsRestarting = titleBarChanged || windowCornerChanged
    }

    fun restart() {
        openNewWindow()
        close()
    }

    private fun close() = close(this)
}
