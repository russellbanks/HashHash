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

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import com.appmattus.crypto.Algorithm
import com.russellbanks.HashHash.BuildConfig
import components.Footer
import components.Header
import components.controlpane.ControlPane
import components.dialogs.AboutDialog
import components.dialogs.PreferencesDialog
import components.screens.file.FileScreen
import helper.DragAndDrop
import helper.FileUtils
import helper.Icons
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import org.pushingpixels.aurora.component.projection.VerticalSeparatorProjection
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.auroraApplication
import preferences.mode.Mode
import preferences.mode.ModeHandler
import preferences.theme.ThemeHandler
import preferences.titlebar.TitleBar
import preferences.titlebar.TitleBarHandler
import java.io.File

@OptIn(ExperimentalComposeUiApi::class)
fun main() = auroraApplication {
    var isAboutOpen by remember { mutableStateOf(false) }
    var isPreferencesOpen by remember { mutableStateOf(false) }
    var hashedOutput by remember { mutableStateOf("") }
    var instantBeforeHash: Instant? by remember { mutableStateOf(null) }
    var instantAfterHash: Instant? by remember { mutableStateOf(null) }
    var error: Exception? by remember { mutableStateOf(null) }
    var file: File? by remember { mutableStateOf(null) }
    var hashjob: Job? by remember { mutableStateOf(null) }
    val windowState = rememberWindowState(
        position = WindowPosition(Alignment.Center),
        size = DpSize(width = 1035.dp, height = 750.dp)
    )
    val themeHandler = ThemeHandler(isSystemInDarkTheme())
    var auroraSkin by remember { mutableStateOf(themeHandler.getAuroraTheme()) }
    val undecorated by remember { mutableStateOf(TitleBarHandler.getTitleBar() == TitleBar.Custom) }
    AuroraWindow(
        skin = auroraSkin,
        state = windowState,
        title = BuildConfig.appName,
        icon = Icons.logo(),
        onCloseRequest = ::exitApplication,
        menuCommands = Header.commands(
            auroraApplicationScope = this,
            openAction = {
                FileUtils.openFileDialogAndGetResult().also {
                    if (it != null && file != it) {
                        file = it
                        hashedOutput = ""
                        instantBeforeHash = null
                        instantAfterHash = null
                        error = null
                    }
                }
                         },
            preferencesAction = { isPreferencesOpen = true },
            toggleFullScreenAction = {
                if (windowState.placement == WindowPlacement.Floating) {
                    windowState.placement = WindowPlacement.Fullscreen
                } else {
                    windowState.placement = WindowPlacement.Floating
                }
            },
            aboutAction = { isAboutOpen = true }
        ),
        undecorated = undecorated,
        onKeyEvent = {
            if (it.key == Key.F11) {
                if (windowState.placement == WindowPlacement.Floating) {
                    windowState.placement = WindowPlacement.Fullscreen
                } else {
                    windowState.placement = WindowPlacement.Floating
                }
                true
            } else {
                false
            }
        }
    ) {
        window.dropTarget = DragAndDrop.target(
            result = { droppedItems ->
                droppedItems.first().let {
                    if (it is File && it.isFile) file = it
                }
            }
        )
        var algorithm: Algorithm by remember { mutableStateOf(Algorithm.MD5) }
        val scope = rememberCoroutineScope()
        var hashProgress by remember { mutableStateOf(0F) }
        var mode by remember { mutableStateOf(ModeHandler.getMode()) }
        Box {
            Column {
                Row(Modifier.fillMaxSize().weight(1f)) {
                    ControlPane(
                        algorithm = algorithm,
                        job = hashjob,
                        file = file,
                        mode = mode,
                        onTriggerModeChange = {
                            val newMode = if (mode == Mode.SIMPLE) Mode.ADVANCED else Mode.SIMPLE
                            ModeHandler.putMode(newMode)
                            mode = newMode
                                              },
                        onAlgorithmClick = { item ->
                            if (item != algorithm) {
                                algorithm = item
                                hashedOutput = ""
                                instantBeforeHash = null
                                instantAfterHash = null
                                error = null
                            }
                        },
                        onSelectFileResult = { result ->
                            if (result != null && file != result) {
                                file = result
                                hashedOutput = ""
                                instantBeforeHash = null
                                instantAfterHash = null
                                error = null
                            }
                        },
                        onCalculateClick = {
                            if (hashjob?.isActive != true) {
                                hashjob = scope.launch(Dispatchers.IO) {
                                    instantBeforeHash = Clock.System.now()
                                    try {
                                        hashedOutput = file?.hash(
                                            algorithm = algorithm,
                                            hashProgressCallback = { hashProgress = it }
                                        )!!.uppercase()
                                        instantAfterHash = Clock.System.now()
                                    } catch (_: CancellationException) {
                                        // Cancellations are intended
                                    } catch (exception: Exception) {
                                        error = exception
                                    }
                                }
                            } else {
                                hashjob?.cancel()
                            }
                        }
                    )
                    VerticalSeparatorProjection().project(Modifier.fillMaxHeight())
                    FileScreen(
                        file = file,
                        algorithm = algorithm,
                        hashedOutput = hashedOutput,
                        instantBeforeHash = instantBeforeHash,
                        instantAfterHash = instantAfterHash,
                        onCaseClick = {
                            hashedOutput = hashedOutput.run {
                                if (this == uppercase()) lowercase() else uppercase()
                            }
                        }
                    )
                }
                Footer(error, hashedOutput, hashjob, hashProgress, file)
            }
            PreferencesDialog(
                visible = isPreferencesOpen,
                themeHandler = themeHandler,
                onThemeChange = {
                    themeHandler.putTheme(it.first)
                    auroraSkin = it.second
                                },
                onCloseRequest = { isPreferencesOpen = false }
            )
            AboutDialog(visible = isAboutOpen, onCloseRequest = { isAboutOpen = false })
        }
    }
}
