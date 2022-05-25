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
import androidx.compose.ui.platform.LocalClipboardManager
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
import data.GitHubData
import helper.DragAndDrop
import helper.GitHub
import helper.Icons
import io.klogging.config.ANSI_CONSOLE
import io.klogging.config.loggingConfiguration
import io.klogging.logger
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.*
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.json.Json
import org.pushingpixels.aurora.component.model.TabContentModel
import org.pushingpixels.aurora.component.model.TabsContentModel
import org.pushingpixels.aurora.component.model.TabsPresentationModel
import org.pushingpixels.aurora.component.projection.TabsProjection
import org.pushingpixels.aurora.component.projection.VerticalSeparatorProjection
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.auroraApplication
import preferences.theme.ThemeHandler
import preferences.titlebar.TitleBar
import preferences.titlebar.TitleBarHandler
import java.io.File

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    loggingConfiguration { ANSI_CONSOLE() }
    auroraApplication {
        // Main File
        var mainFile: File? by remember { mutableStateOf(null) }
        var mainFileHash by remember { mutableStateOf("") }
        var shouldMainFileHashBeUppercase by remember { mutableStateOf(true) }
        var mainFileHashJob: Job? by remember { mutableStateOf(null) }
        var mainFileHashProgress by remember { mutableStateOf(0F) }
        var instantBeforeHash: Instant? by remember { mutableStateOf(null) }
        var instantAfterHash: Instant? by remember { mutableStateOf(null) }
        var mainFileException: Exception? by remember { mutableStateOf(null) }

        // Compare files screen
        var filesMatch by remember { mutableStateOf(false) }
        var comparisonJobList: List<Deferred<Unit>>? by remember { mutableStateOf(null) }

        // 1st Comparison File
        var fileComparisonOne: File? by remember { mutableStateOf(null) }
        var fileComparisonOneHash by remember { mutableStateOf("") }
        var shouldFileComparisonOneHashBeUppercase by remember { mutableStateOf(true) }
        var fileComparisonOneProgress by remember { mutableStateOf(0F) }

        // 2nd Comparison File
        var fileComparisonTwo: File? by remember { mutableStateOf(null) }
        var fileComparisonTwoHash by remember { mutableStateOf("") }
        var shouldFileComparisonTwoHashBeUppercase by remember { mutableStateOf(true) }
        var fileComparisonTwoProgress by remember { mutableStateOf(0F) }

        // Text Screen
        var givenText by remember { mutableStateOf("") }
        var givenTextHash by remember { mutableStateOf("") }
        var shouldGivenTextBeUppercase by remember { mutableStateOf(true) }
        var textComparisonHash by remember { mutableStateOf("") }

        // Dialogs
        var isAboutOpen by remember { mutableStateOf(false) }
        var isPreferencesOpen by remember { mutableStateOf(false) }

        val scope = rememberCoroutineScope()
        val windowState = rememberWindowState(
            position = WindowPosition(Alignment.Center),
            size = DpSize(width = 1035.dp, height = 750.dp)
        )
        val themeHandler = ThemeHandler(isSystemInDarkTheme())
        var auroraSkin by remember { mutableStateOf(themeHandler.getAuroraTheme(scope)) }
        val undecorated by remember { mutableStateOf(TitleBarHandler.getTitleBar() == TitleBar.Custom) }
        var currentScreen by remember { mutableStateOf(Screen.FileScreen) }
        var hasF11TriggeredOnce = false
        var httpResponse: HttpResponse? by remember { mutableStateOf(null) }
        var githubData: GitHubData? by remember { mutableStateOf(null) }
        var algorithm: Algorithm by remember { mutableStateOf(Algorithm.MD5) }
        var retrievedGitHubData by remember { mutableStateOf(false) }
        var httpClient: HttpClient? by remember { mutableStateOf(null) }
        val klogger = logger("Main")
        AuroraWindow(
            skin = auroraSkin,
            state = windowState,
            title = BuildConfig.appName,
            icon = Icons.logo(),
            onCloseRequest = ::exitApplication,
            menuCommands = Header.commands(
                auroraApplicationScope = this,
                scope = scope,
                gitHubData = githubData,
                preferencesAction = { isPreferencesOpen = true },
                toggleFullScreenAction = {
                    if (windowState.placement != WindowPlacement.Fullscreen) {
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
                    if (!hasF11TriggeredOnce) {
                        hasF11TriggeredOnce = true
                        if (windowState.placement != WindowPlacement.Fullscreen) {
                            windowState.placement = WindowPlacement.Fullscreen
                        } else {
                            windowState.placement = WindowPlacement.Floating
                        }
                        true
                    } else {
                        hasF11TriggeredOnce = false
                        false
                    }
                } else {
                    false
                }
            }
        ) {
            if (!retrievedGitHubData) {
                scope.launch(Dispatchers.Default) {
                    retrievedGitHubData = true
                    httpClient = HttpClient(CIO) {
                        install(ContentNegotiation) {
                            json(
                                Json {
                                    ignoreUnknownKeys = true
                                }
                            )
                        }
                        install(Logging) {
                            logger = object: Logger {
                                override fun log(message: String) {
                                    scope.launch(Dispatchers.Default) { klogger.info(message) }
                                }
                            }
                            level = LogLevel.INFO
                        }
                    }.also { client ->
                        httpResponse = client.get(GitHub.HashHash.API.latest).also {
                            if (it.status.value in 200..299) {
                                githubData = it.body()
                            }
                        }
                    }
                }
            }
            window.dropTarget = DragAndDrop.target(
                result = { droppedItems ->
                    droppedItems.first().let {
                        if (it is File && it.isFile) {
                            scope.launch(Dispatchers.Default) { klogger.info("User drag and dropped file: ${it.absoluteFile}") }
                            if (currentScreen == Screen.FileScreen) {
                                mainFile = it
                                scope.launch(Dispatchers.Default) { klogger.info("Set ${it.name} as main file") }
                            }
                            else if (currentScreen == Screen.CompareFilesScreen) {
                                if (fileComparisonOne == null) {
                                    fileComparisonOne = it
                                    scope.launch(Dispatchers.Default) { klogger.info("Set ${it.name} as the 1st comparison file") }
                                }
                                else {
                                    fileComparisonTwo = it
                                    scope.launch(Dispatchers.Default) { klogger.info("Set ${it.name} as the 2nd comparison file") }
                                }
                            }
                        }
                    }
                }
            )
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
                            currentScreen = currentScreen,
                            onAlgorithmClick = { item ->
                                if (item != algorithm) {
                                    algorithm = item
                                    scope.launch(Dispatchers.Default) { klogger.info("Set algorithm as ${item.algorithmName}") }
                                    mainFileHash = ""
                                    instantBeforeHash = null
                                    instantAfterHash = null
                                    mainFileException = null
                                }
                            },
                            onSelectFileResult = { result ->
                                if (result != null && mainFile != result) {
                                    mainFile = result
                                    scope.launch(Dispatchers.Default) { klogger.info("Set user selected file ${result.absolutePath} as main file") }
                                    mainFileHash = ""
                                    instantBeforeHash = null
                                    instantAfterHash = null
                                    mainFileException = null
                                }
                            },
                            onSelectFileComparisonOneResult = { result ->
                                if (result != null && fileComparisonOne != result) {
                                    fileComparisonOne = result
                                    scope.launch(Dispatchers.Default) { klogger.info("Set user selected file ${result.absolutePath} as 1st comparison file") }
                                    fileComparisonOneHash = ""
                                    fileComparisonOneProgress = 0F
                                }
                            },
                            oneSelectFileComparisonTwoResult = { result ->
                                if (result != null && fileComparisonTwo != result) {
                                    fileComparisonTwo = result
                                    scope.launch(Dispatchers.Default) { klogger.info("Set user selected file ${result.absolutePath} as 2nd comparison file") }
                                    fileComparisonTwoHash = ""
                                    fileComparisonTwoProgress = 0F
                                }
                            },
                            onCalculateClick = {
                                if (currentScreen == Screen.CompareFilesScreen) {
                                    if ((comparisonJobList?.count { it.isActive } ?: 0) <= 0) {
                                        scope.launch(Dispatchers.Default) {
                                            comparisonJobList = listOf(
                                                async(Dispatchers.IO) {
                                                    try {
                                                        fileComparisonOneHash = fileComparisonOne?.hash(
                                                            algorithm = algorithm,
                                                            hashProgressCallback = { fileComparisonOneProgress = it }
                                                        )?.run { if (shouldFileComparisonOneHashBeUppercase) uppercase() else lowercase() } ?: ""
                                                    } catch (_: CancellationException) {
                                                    } catch (exception: Exception) {
                                                        mainFileException = exception
                                                    }
                                                },
                                                async(Dispatchers.IO) {
                                                    try {
                                                        fileComparisonTwoHash = fileComparisonTwo?.hash(
                                                            algorithm = algorithm,
                                                            hashProgressCallback = { fileComparisonTwoProgress = it }
                                                        )?.run { if (shouldFileComparisonTwoHashBeUppercase) uppercase() else lowercase() } ?: ""
                                                    } catch (_: CancellationException) {
                                                    } catch (exception: Exception) {
                                                        mainFileException = exception
                                                    }
                                                }
                                            )
                                            comparisonJobList?.awaitAll()
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
                                                )?.run { if (shouldMainFileHashBeUppercase) uppercase() else lowercase() } ?: ""
                                                instantAfterHash = Clock.System.now()
                                            } catch (_: CancellationException) {
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
                        val characterLimit = 20000
                        val clipboardManager = LocalClipboardManager.current
                        Column {
                            TabsProjection(
                                contentModel = TabsContentModel(
                                    tabs = listOf(
                                        TabContentModel(text = "File"),
                                        TabContentModel(text = "Text"),
                                        TabContentModel(text = "Compare Files")
                                    ),
                                    selectedTabIndex = currentScreen.ordinal,
                                    onTriggerTabSelected = {
                                        currentScreen = when (it) {
                                            0 -> Screen.FileScreen
                                            1 -> Screen.TextScreen
                                            2 -> Screen.CompareFilesScreen
                                            else -> Screen.FileScreen
                                        }
                                    }
                                ),
                                presentationModel = TabsPresentationModel(
                                    leadingMargin = 30.dp,
                                    trailingMargin = 30.dp,
                                    tabContentPadding = PaddingValues(horizontal = 30.dp, vertical = 6.dp)
                                )
                            ).project()
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
                                        shouldMainFileHashBeUppercase = mainFileHash == mainFileHash.uppercase()
                                    }
                                )
                                Screen.TextScreen -> TextScreen(
                                    algorithm = algorithm,
                                    givenText = givenText,
                                    givenTextHash = givenTextHash,
                                    textComparisonHash = textComparisonHash,
                                    characterLimit = characterLimit,
                                    onValueChange = {
                                        givenText = if (it.count() < characterLimit) it else it.dropLast(it.count() - characterLimit)
                                        if (it.isNotEmpty()) {
                                            givenTextHash = givenText.hash(algorithm)
                                                .run { if (shouldGivenTextBeUppercase) uppercase() else lowercase() }
                                                .also { scope.launch(Dispatchers.Default) { klogger.info("Hashed \"$givenText\" to be $it") } }
                                        }
                                    },
                                    onUppercaseClick = {
                                        if (givenText != givenText.uppercase()) {
                                            givenText = givenText.uppercase()
                                            if (givenText.isNotEmpty()) {
                                                givenTextHash = givenText.hash(algorithm)
                                                    .run { if (shouldGivenTextBeUppercase) uppercase() else lowercase() }
                                                    .also { scope.launch(Dispatchers.Default) { klogger.info("Hashed \"$givenText\" to be $it") } }
                                            }
                                        }
                                    },
                                    onLowercaseClick = {
                                        if (givenText != givenText.lowercase()) {
                                            givenText = givenText.lowercase()
                                            if (givenText.isNotEmpty()) {
                                                givenTextHash = givenText.hash(algorithm)
                                                    .run { if (shouldGivenTextBeUppercase) uppercase() else lowercase() }
                                                    .also { scope.launch(Dispatchers.Default) { klogger.info("Hashed \"$givenText\" to be $it") } }
                                            }
                                        }
                                    },
                                    onClearTextClick = { givenText = "" },
                                    onComparisonClearClick = { textComparisonHash = "" },
                                    onCaseClick = {
                                        givenTextHash = givenTextHash
                                            .run { if (this == uppercase()) lowercase() else uppercase() }
                                        shouldGivenTextBeUppercase = givenTextHash == givenTextHash.uppercase()
                                    },
                                    onPasteClick = {
                                        textComparisonHash = (clipboardManager.getText()?.text ?: "").filterNot { it.isWhitespace() }
                                    },
                                    onComparisonTextFieldChange = { textComparisonHash = it.filterNot { char -> char.isWhitespace() } }
                                )
                                Screen.CompareFilesScreen -> CompareFilesScreen(
                                    algorithm = algorithm,
                                    fileComparisonOne = fileComparisonOne,
                                    fileComparisonOneHash = fileComparisonOneHash,
                                    fileComparisonOneProgress = fileComparisonOneProgress,
                                    fileComparisonOneOnCaseClick = {
                                        fileComparisonOneHash = fileComparisonOneHash.run {
                                            if (this == uppercase()) lowercase() else uppercase()
                                        }
                                        shouldFileComparisonOneHashBeUppercase = fileComparisonOneHash == fileComparisonOneHash.uppercase()
                                    },
                                    fileComparisonTwo = fileComparisonTwo,
                                    fileComparisonTwoHash = fileComparisonTwoHash,
                                    fileComparisonTwoProgress = fileComparisonTwoProgress,
                                    fileComparisonTwoOnCaseClick = {
                                        fileComparisonTwoHash = fileComparisonTwoHash
                                            .run { if (this == uppercase()) lowercase() else uppercase() }
                                        shouldFileComparisonTwoHashBeUppercase = fileComparisonTwoHash == fileComparisonTwoHash.uppercase()
                                    }
                                )
                            }
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
                        scope.launch(Dispatchers.Default) { themeHandler.putTheme(it.first) }
                        auroraSkin = it.second
                    },
                    onCloseRequest = { isPreferencesOpen = false }
                )
                AboutDialog(
                    visible = isAboutOpen,
                    onCloseRequest = { isAboutOpen = false },
                    httpClient = httpClient,
                    httpResponse = httpResponse,
                    githubData = githubData,
                    onUpdateCheck = {
                        httpResponse = it
                        if (it.status.value in 200..299) {
                            scope.launch(Dispatchers.Default) {
                                githubData = it.body()
                                klogger.info("Successfully retrieved GitHub data with status code ${it.status}")
                            }
                        } else {
                            scope.launch(Dispatchers.Default) {
                                klogger.error("Failed to retrieve GitHub data. Status code: ${it.status}")
                            }
                        }
                    }
                )
            }
        }
    }
}
