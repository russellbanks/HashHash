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
import components.screens.Screen
import components.screens.comparefiles.CompareFilesScreen
import components.screens.file.FileScreen
import components.screens.text.TextScreen
import helper.DragAndDrop
import helper.Icons
import kotlinx.coroutines.*
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
    // Main File
    var mainFile: File? by remember { mutableStateOf(null) }
    var mainFileHash by remember { mutableStateOf("") }
    var mainFileHashJob: Job? by remember { mutableStateOf(null) }
    var mainFileHashProgress by remember { mutableStateOf(0F) }
    var instantBeforeHash: Instant? by remember { mutableStateOf(null) }
    var instantAfterHash: Instant? by remember { mutableStateOf(null) }
    var mainFileException: Exception? by remember { mutableStateOf(null) }

    // 1st Comparison File
    var fileComparisonOne: File? by remember { mutableStateOf(null) }
    var fileComparisonOneHash by remember { mutableStateOf("") }
    var fileComparisonOneProgress by remember { mutableStateOf(0F) }

    // 2nd Comparison File
    var fileComparisonTwo: File? by remember { mutableStateOf(null) }
    var fileComparisonTwoHash by remember { mutableStateOf("") }
    var fileComparisonTwoProgress by remember { mutableStateOf(0F) }

    var isAboutOpen by remember { mutableStateOf(false) }
    var isPreferencesOpen by remember { mutableStateOf(false) }
    var filesMatch by remember { mutableStateOf(false) }
    val windowState = rememberWindowState(
        position = WindowPosition(Alignment.Center),
        size = DpSize(width = 1035.dp, height = 750.dp)
    )
    val themeHandler = ThemeHandler(isSystemInDarkTheme())
    var auroraSkin by remember { mutableStateOf(themeHandler.getAuroraTheme()) }
    val undecorated by remember { mutableStateOf(TitleBarHandler.getTitleBar() == TitleBar.Custom) }
    var currentScreen by remember { mutableStateOf(Screen.FileScreen) }
    var comparisonJobList: List<Deferred<Unit>>? by remember { mutableStateOf(null) }
    AuroraWindow(
        skin = auroraSkin,
        state = windowState,
        title = BuildConfig.appName,
        icon = Icons.logo(),
        onCloseRequest = ::exitApplication,
        menuCommands = Header.commands(
            auroraApplicationScope = this,
            preferencesAction = { isPreferencesOpen = true },
            toggleFullScreenAction = {
                if (windowState.placement == WindowPlacement.Floating) {
                    windowState.placement = WindowPlacement.Fullscreen
                } else {
                    windowState.placement = WindowPlacement.Floating
                }
            },
            aboutAction = { isAboutOpen = true },
            fileScreenAction = { currentScreen = Screen.FileScreen },
            textScreenAction = { currentScreen = Screen.TextScreen },
            compareFilesScreenAction = { currentScreen = Screen.CompareFilesScreen }
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
                    if (it is File && it.isFile) {
                        if (currentScreen == Screen.FileScreen) mainFile = it
                        else if (currentScreen == Screen.CompareFilesScreen) {
                            if (fileComparisonOne == null) fileComparisonOne = it
                            else fileComparisonTwo = it
                        }
                    }
                }
            }
        )
        var algorithm: Algorithm by remember { mutableStateOf(Algorithm.MD5) }
        val scope = rememberCoroutineScope()
        var mode by remember { mutableStateOf(ModeHandler.getMode()) }
        Box {
            Column {
                Row(Modifier.fillMaxSize().weight(1f)) {
                    ControlPane(
                        algorithm = algorithm,
                        job = mainFileHashJob,
                        compareJobList = comparisonJobList,
                        file = mainFile,
                        fileComparisonOne = fileComparisonOne,
                        fileComparisonTwo = fileComparisonTwo,
                        mode = mode,
                        currentScreen = currentScreen,
                        onTriggerModeChange = {
                            val newMode = if (mode == Mode.SIMPLE) Mode.ADVANCED else Mode.SIMPLE
                            ModeHandler.putMode(newMode)
                            mode = newMode
                                              },
                        onAlgorithmClick = { item ->
                            if (item != algorithm) {
                                algorithm = item
                                mainFileHash = ""
                                instantBeforeHash = null
                                instantAfterHash = null
                                mainFileException = null
                            }
                        },
                        onSelectFileResult = { result ->
                            if (result != null && mainFile != result) {
                                mainFile = result
                                mainFileHash = ""
                                instantBeforeHash = null
                                instantAfterHash = null
                                mainFileException = null
                            }
                        },
                        onSelectFileComparisonOneResult = { result ->
                            if (result != null && fileComparisonOne != result) {
                                fileComparisonOne = result
                                fileComparisonOneHash = ""
                                fileComparisonOneProgress = 0F
                            }
                        },
                        oneSelectFileComparisonTwoResult = { result ->
                            if (result != null && fileComparisonTwo != result) {
                                fileComparisonTwo = result
                                fileComparisonTwoHash = ""
                                fileComparisonTwoProgress = 0F
                            }
                        },
                        onCalculateClick = {
                            if (currentScreen == Screen.CompareFilesScreen) {
                                if (!comparisonJobList.isActive()) {
                                    scope.launch {
                                        comparisonJobList = listOf(
                                            async(Dispatchers.IO) {
                                                try {
                                                    fileComparisonOneHash = fileComparisonOne?.hash(
                                                        algorithm = algorithm,
                                                        hashProgressCallback = { fileComparisonOneProgress = it }
                                                    )!!.uppercase()
                                                } catch (_: CancellationException) {
                                                    // Cancellations are intended
                                                } catch (exception: Exception) {
                                                    mainFileException = exception
                                                }
                                            },
                                            async(Dispatchers.IO) {
                                                try {
                                                    fileComparisonTwoHash = fileComparisonTwo?.hash(
                                                        algorithm = algorithm,
                                                        hashProgressCallback = { fileComparisonTwoProgress = it }
                                                    )!!.uppercase()
                                                } catch (_: CancellationException) {
                                                    // Cancellations are intended
                                                } catch (exception: Exception) {
                                                    mainFileException = exception
                                                }
                                            }
                                        )
                                        comparisonJobList!!.awaitAll()
                                        filesMatch = fileComparisonOneHash.equals(fileComparisonTwoHash, ignoreCase = true)
                                    }
                                } else {
                                    comparisonJobList?.forEach { it.cancel() }
                                    fileComparisonOneProgress = 0F
                                    fileComparisonTwoProgress = 0F
                                }
                            } else {
                                if (mainFileHashJob?.isActive != true) {
                                    mainFileHashJob = scope.launch(Dispatchers.IO) {
                                        instantBeforeHash = Clock.System.now()
                                        try {
                                            mainFileHash = mainFile?.hash(
                                                algorithm = algorithm,
                                                hashProgressCallback = { mainFileHashProgress = it }
                                            )!!.uppercase()
                                            instantAfterHash = Clock.System.now()
                                        } catch (_: CancellationException) {
                                            // Cancellations are intended
                                        } catch (exception: Exception) {
                                            mainFileException = exception
                                        }
                                    }
                                } else {
                                    mainFileHashProgress = 0F
                                    mainFileHashJob?.cancel()
                                }
                            }
                        }
                    )
                    VerticalSeparatorProjection().project(Modifier.fillMaxHeight())
                    when (currentScreen) {
                        Screen.FileScreen -> FileScreen(
                            mainFile = mainFile,
                            algorithm = algorithm,
                            mainFileHash = mainFileHash,
                            instantBeforeHash = instantBeforeHash,
                            instantAfterHash = instantAfterHash,
                            mainFileHashProgress = mainFileHashProgress,
                            onCaseClick = {
                                mainFileHash = mainFileHash.run {
                                    if (this == uppercase()) lowercase() else uppercase()
                                }
                            }
                        )
                        Screen.TextScreen -> TextScreen(algorithm)
                        Screen.CompareFilesScreen -> CompareFilesScreen(
                            algorithm, fileComparisonOne, fileComparisonOneHash, fileComparisonOneProgress,
                            fileComparisonTwo, fileComparisonTwoHash, fileComparisonTwoProgress
                        )
                    }
                }
                Footer(
                    currentScreen, mainFileException, mainFileHash, fileComparisonOneHash, fileComparisonTwoHash,
                    mainFile, filesMatch
                )
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

fun List<Deferred<Unit>>?.isActive(): Boolean {
    var count = 0
    this?.forEach { if (it.isActive) count++ }
    return count > 0
}
