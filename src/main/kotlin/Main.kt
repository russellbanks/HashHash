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
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.type
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.animation.child.childAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.animation.child.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.lifecycle.LifecycleController
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.russellbanks.HashHash.BuildConfig
import components.Footer
import components.Header
import components.Root
import components.RootComponent
import components.controlpane.ControlPane
import components.dialogs.AboutDialog
import components.dialogs.PreferencesDialog
import components.screens.comparefiles.CompareFilesComponent
import components.screens.comparefiles.CompareFilesScreen
import components.screens.file.FileScreen
import components.screens.file.FileScreenComponent
import components.screens.text.TextScreen
import components.screens.text.TextScreenComponent
import data.GitHubData
import helper.*
import io.klogging.config.ANSI_CONSOLE
import io.klogging.config.loggingConfiguration
import io.klogging.logger
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.*
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

@OptIn(ExperimentalComposeUiApi::class, ExperimentalDecomposeApi::class)
fun main() {
    loggingConfiguration { ANSI_CONSOLE() }
    val logger = logger("Main")

    val lifecycle = LifecycleRegistry()
    val root = RootComponent(DefaultComponentContext(lifecycle))

    var httpResponse: HttpResponse? = null
    var githubData: GitHubData? = null
    var retrievedGitHubData = false
    var httpClient: HttpClient? = null
    val undecorated = TitleBarHandler.getTitleBar() == TitleBar.Custom
    var isAboutOpen by mutableStateOf(false)
    var isPreferencesOpen by mutableStateOf(false)
    auroraApplication {
        val routerState = root.routerState.subscribeAsState()
        val activeComponent = routerState.value.activeChild.instance

        val scope = rememberCoroutineScope()
        val windowState = rememberWindowState(
            position = WindowPosition(Alignment.Center),
            size = DpSize(width = 1035.dp, height = 750.dp)
        )
        val themeHandler = ThemeHandler(isSystemInDarkTheme())
        var auroraSkin by remember { mutableStateOf(themeHandler.getAuroraTheme(scope)) }
        val fileScreenComponent = remember {
            FileScreenComponent(componentContext = DefaultComponentContext(lifecycle))
        }
        val textScreenComponent = remember {
            TextScreenComponent(componentContext = DefaultComponentContext(lifecycle))
        }
        val compareFilesComponent = remember {
            CompareFilesComponent(componentContext = DefaultComponentContext(lifecycle))
        }
        LifecycleController(lifecycle, windowState)
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
                toggleFullScreenAction = { Window.toggleFullscreen(windowState) },
                aboutAction = { isAboutOpen = true }
            ),
            undecorated = undecorated,
            onPreviewKeyEvent = {
                if (it.key == Key.F11 && it.type == KeyEventType.KeyUp ) {
                    Window.toggleFullscreen(windowState)
                    true
                } else {
                    false
                }
            }
        ) {
            if (!retrievedGitHubData) {
                scope.launch(Dispatchers.Default) {
                    retrievedGitHubData = true
                    httpClient = Ktor.createHttpClient(scope).also { client ->
                        httpResponse = client.get(GitHub.HashHash.API.latest).also {
                            if (it.status.value in 200..299) {
                                githubData = it.body()
                            }
                        }
                    }
                }
            }
            window.dropTarget = DragAndDrop.target(scope) { droppedItems ->
                droppedItems.first().let {
                    if (it is File && it.isFile) {
                        scope.launch(Dispatchers.Default) { logger.info("User drag and dropped file: ${it.absoluteFile}") }
                        if (activeComponent is Root.Child.File) {
                            fileScreenComponent.file = it
                            scope.launch(Dispatchers.Default) { logger.info("Set ${it.name} as main file") }
                        } else if (activeComponent is Root.Child.CompareFiles) {
                            if (compareFilesComponent.fileComparisonOne == null) {
                                compareFilesComponent.fileComparisonOne = it
                                scope.launch(Dispatchers.Default) { logger.info("Set ${it.name} as the 1st comparison file") }
                            } else {
                                compareFilesComponent.fileComparisonTwo = it
                                scope.launch(Dispatchers.Default) { logger.info("Set ${it.name} as the 2nd comparison file") }
                            }
                        }
                    }
                }
            }
            Box {
                Column {
                    Row(Modifier.fillMaxSize().weight(1f)) {
                        ControlPane(
                            fileScreenComponent = fileScreenComponent,
                            compareFilesComponent = compareFilesComponent,
                            activeChild = activeComponent,
                            onSelectFileResult = { child, result, buttonIndex ->
                                if (result != null) {
                                    if (child is Root.Child.File) {
                                        if (fileScreenComponent.file != result) {
                                            fileScreenComponent.file = result
                                            scope.launch(Dispatchers.Default) { logger.info("Set user selected file ${result.absolutePath} as main file") }
                                        }
                                    } else if (child is Root.Child.CompareFiles) {
                                        if (buttonIndex == 0) {
                                            if (compareFilesComponent.fileComparisonOne != result) {
                                                compareFilesComponent.fileComparisonOne = result
                                                scope.launch(Dispatchers.Default) { logger.info("Set user selected file ${result.absolutePath} as 1st comparison file") }
                                            }
                                        } else {
                                            if (compareFilesComponent.fileComparisonTwo != result) {
                                                compareFilesComponent.fileComparisonTwo = result
                                                scope.launch(Dispatchers.Default) { logger.info("Set user selected file ${result.absolutePath} as 2nd comparison file") }
                                            }
                                        }
                                    }
                                }
                            },
                            onCalculateClick = {
                                if (activeComponent is Root.Child.File) {
                                    fileScreenComponent.onCalculateClicked(scope)
                                } else if (activeComponent is Root.Child.CompareFiles){
                                    compareFilesComponent.onCalculateClicked(scope)
                                }
                            },
                            onAlgorithmChange = {
                                fileScreenComponent.algorithm = it
                                textScreenComponent.algorithm = it
                                compareFilesComponent.algorithm = it
                            }
                        )
                        VerticalSeparatorProjection().project(Modifier.fillMaxHeight())
                        Column {
                            TabsProjection(
                                contentModel = TabsContentModel(
                                    tabs = listOf(
                                        TabContentModel(text = "File"),
                                        TabContentModel(text = "Text"),
                                        TabContentModel(text = "Compare Files")
                                    ),
                                    selectedTabIndex = activeComponent.toInt(),
                                    onTriggerTabSelected = {
                                        when (it) {
                                            0 -> root.onFileTabClicked()
                                            1 -> root.onTextTabClicked()
                                            2 -> root.onCompareFilesTabClicked()
                                        }
                                    }
                                ),
                                presentationModel = TabsPresentationModel(
                                    leadingMargin = 30.dp,
                                    trailingMargin = 30.dp,
                                    tabContentPadding = PaddingValues(horizontal = 30.dp, vertical = 6.dp)
                                )
                            ).project()
                            Children(
                                routerState = routerState.value,
                                animation = childAnimation(fade(tween(200)))
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
                        fileScreenComponent = fileScreenComponent,
                        compareFilesComponent = compareFilesComponent
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
                        scope.launch(Dispatchers.Default) {
                            if (it.status.value in 200..299) {
                                githubData = it.body()
                                logger.info("Successfully retrieved GitHub data with status code ${it.status}")
                            } else {
                                logger.error("Failed to retrieve GitHub data. Status code: ${it.status}")
                            }
                        }
                    }
                )
            }
        }
    }
}
