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
import api.Ktor
import application.ApplicationState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.animation.child.childAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.animation.child.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.lifecycle.LifecycleController
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.mayakapps.compose.windowstyler.WindowBackdrop
import com.mayakapps.compose.windowstyler.WindowFrameStyle
import com.mayakapps.compose.windowstyler.WindowStyle
import components.Footer
import components.Root
import components.RootComponent
import components.Snackbar
import components.Tabs
import components.Toolbar
import components.controlpane.ControlPane
import components.dialogs.DialogState
import components.dialogs.TranslucentDialogOverlay
import components.dialogs.UpdateAvailableDialog
import components.dialogs.about.AboutDialog
import components.dialogs.settings.SettingsDialog
import components.dialogs.settings.SettingsRootComponent
import components.screens.ParentComponent
import components.screens.compare.CompareFilesComponent
import components.screens.compare.CompareFilesScreen
import components.screens.file.FileScreen
import components.screens.file.FileScreenComponent
import components.screens.text.TextScreen
import components.screens.text.TextScreenComponent
import helper.Icons
import helper.Window
import io.klogging.config.ANSI_CONSOLE
import io.klogging.config.loggingConfiguration
import koin.KoinLogger
import koin.get
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.pushingpixels.aurora.component.projection.VerticalSeparatorProjection
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.auroraApplication
import preferences.mode.ModeHandler
import preferences.theme.ThemeHandler
import preferences.titlebar.TitleBarHandler
import preferences.windowcorner.WindowCornerHandler

@OptIn(ExperimentalDecomposeApi::class)
fun main() {
    loggingConfiguration { ANSI_CONSOLE() }

    val lifecycle = LifecycleRegistry()
    val root = RootComponent(DefaultComponentContext(lifecycle))
    val settingsRoot = SettingsRootComponent(DefaultComponentContext(lifecycle))

    startKoin {
        modules(
            module {
                singleOf(::Ktor)
                singleOf(::TitleBarHandler)
                singleOf(::WindowCornerHandler)
                singleOf(::DialogState)
                singleOf(::ThemeHandler)
                singleOf(::ModeHandler)
                singleOf(::ApplicationState)
            }
        )
        logger(KoinLogger())
    }

    val parentComponent = ParentComponent()
    val fileScreenComponent = FileScreenComponent(DefaultComponentContext(lifecycle), parentComponent)
    val textScreenComponent = TextScreenComponent(DefaultComponentContext(lifecycle), parentComponent)
    val compareFilesComponent = CompareFilesComponent(DefaultComponentContext(lifecycle), parentComponent)
    auroraApplication {
        val windowState = rememberWindowState(
            position = WindowPosition(Alignment.Center),
            size = DpSize(width = 1035.dp, height = 750.dp)
        )
        for (window in get<ApplicationState>().windows) {
            key(window) {
                LifecycleController(lifecycle, windowState)
                AuroraWindow(
                    skin = get<ThemeHandler>().auroraSkin,
                    state = windowState,
                    title = "",
                    icon = Icons.logo(),
                    onCloseRequest = ::exitApplication,
                    menuCommands = Window.Header.commands(
                        auroraApplicationScope = this,
                        windowState = windowState
                    ),
                    undecorated = window.isUndecorated,
                    onPreviewKeyEvent = { Window.onKeyEvent(it, windowState) }
                ) {
                    val routerState = root.routerState.subscribeAsState()
                    val activeComponent = routerState.value.activeChild.instance
                    WindowStyle(
                        isDarkTheme = get<ThemeHandler>().isDark(),
                        backdropType = WindowBackdrop.Mica,
                        frameStyle = WindowFrameStyle(cornerPreference = get<WindowCornerHandler>().getWindowCorner())
                    )
                    Window.setupAWTWindow(
                        window = this.window,
                        fileScreenComponent = fileScreenComponent,
                        compareFilesComponent = compareFilesComponent,
                        activeComponent = activeComponent
                    )
                    Box {
                        Column {
                            Toolbar()
                            Tabs(activeComponent = activeComponent, root = root)
                            Row(Modifier.fillMaxSize().weight(1f)) {
                                ControlPane(
                                    fileScreen = fileScreenComponent,
                                    textScreen = textScreenComponent,
                                    compareScreen = compareFilesComponent
                                )
                                VerticalSeparatorProjection().project(Modifier.fillMaxHeight())
                                Column {
                                    Children(
                                        routerState = routerState.value,
                                        animation = childAnimation(fade(tween(durationMillis = 200)))
                                    ) {
                                        when (it.instance) {
                                            is Root.Child.File -> FileScreen(fileScreenComponent)
                                            is Root.Child.Text -> TextScreen(textScreenComponent)
                                            is Root.Child.CompareFiles -> CompareFilesScreen(compareFilesComponent)
                                        }
                                    }
                                }
                            }
                            Footer(
                                activeComponent = activeComponent,
                                fileScreen = fileScreenComponent,
                                textScreen = textScreenComponent,
                                compareScreen = compareFilesComponent
                            )
                        }
                        Snackbar(parentComponent = parentComponent)
                        TranslucentDialogOverlay()
                        UpdateAvailableDialog()
                        SettingsDialog(root = settingsRoot, window = window)
                        AboutDialog()
                    }
                }
            }
        }
    }
}
