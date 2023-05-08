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

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import application.ApplicationState
import application.LifecycleController
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.mayakapps.compose.windowstyler.WindowBackdrop
import com.mayakapps.compose.windowstyler.WindowFrameStyle
import com.mayakapps.compose.windowstyler.WindowStyle
import com.russellbanks.HashHash.BuildConfig
import components.Footer
import components.Root
import components.Snackbar
import components.Tabs
import components.Toolbar
import components.controlpane.ControlPane
import components.dialogs.TranslucentDialogOverlay
import components.dialogs.UpdateAvailableDialog
import components.dialogs.about.AboutDialog
import components.dialogs.settings.SettingsDialog
import components.screens.compare.CompareFilesScreen
import components.screens.file.FileScreen
import components.screens.text.TextScreen
import helper.Icons
import helper.Window
import org.koin.compose.koinInject
import org.pushingpixels.aurora.component.projection.VerticalSeparatorProjection
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.auroraApplication
import preferences.theme.ThemeHandler
import preferences.windowcorner.WindowCornerHandler

@OptIn(ExperimentalDecomposeApi::class)
fun hashHashApplication() = auroraApplication {
    val windowState = rememberWindowState(
        position = WindowPosition(Alignment.Center),
        size = DpSize(width = 1035.dp, height = 750.dp)
    )
    for (window in ApplicationState.windows) {
        key(window) {
            LifecycleController(koinInject(), windowState)
            AuroraWindow(
                skin = ThemeHandler.auroraSkin,
                state = windowState,
                title = BuildConfig.appName,
                icon = Icons.logo(),
                onCloseRequest = { ApplicationState.exitApplication(this) },
                menuCommands = Window.Header.commands(auroraApplicationScope = this, windowState = windowState),
                windowTitlePaneConfiguration = window.titlePaneConfiguration,
                onPreviewKeyEvent = { Window.onKeyEvent(it, windowState) }
            ) {
                WindowStyle(
                    isDarkTheme = true,
                    backdropType = WindowBackdrop.Mica,
                    frameStyle = WindowFrameStyle(cornerPreference = WindowCornerHandler.windowCorner)
                )
                Window.setupAWTWindow(window = this.window)
                Box {
                    Column {
                        Toolbar()
                        Tabs()
                        Row(Modifier.fillMaxSize().weight(1f)) {
                            ControlPane()
                            VerticalSeparatorProjection().project(Modifier.fillMaxHeight())
                            Column {
                                Children(
                                    stack = koinInject<Root>().childStack,
                                    animation = stackAnimation(fade(tween(durationMillis = 200)))
                                ) {
                                    when (it.instance) {
                                        is Root.Child.File -> FileScreen()
                                        is Root.Child.Text -> TextScreen()
                                        is Root.Child.CompareFiles -> CompareFilesScreen()
                                    }
                                }
                            }
                        }
                        Footer()
                    }
                    Snackbar()
                    TranslucentDialogOverlay()
                    UpdateAvailableDialog()
                    SettingsDialog(window)
                    AboutDialog()
                }
            }
        }
    }
}
