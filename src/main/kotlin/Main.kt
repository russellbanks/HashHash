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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import components.Root
import components.RootComponent
import components.Tabs
import components.controlpane.ControlPane
import components.dialogs.AboutDialog
import components.dialogs.PreferencesDialog
import components.screens.ParentComponent
import components.screens.compare.CompareFilesComponent
import components.screens.compare.CompareFilesScreen
import components.screens.file.FileScreen
import components.screens.file.FileScreenComponent
import components.screens.text.TextScreen
import components.screens.text.TextScreenComponent
import data.GitHubData
import helper.GitHub
import helper.Icons
import helper.Ktor
import helper.Window
import io.klogging.config.ANSI_CONSOLE
import io.klogging.config.loggingConfiguration
import io.klogging.logger
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.pushingpixels.aurora.component.projection.VerticalSeparatorProjection
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.auroraApplication
import preferences.theme.ThemeHandler
import preferences.titlebar.TitleBar
import preferences.titlebar.TitleBarHandler

@OptIn(ExperimentalDecomposeApi::class)
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
        val parentComponent = remember { ParentComponent() }
        val fileScreenComponent = remember {
            FileScreenComponent(
                componentContext = DefaultComponentContext(lifecycle), parentComponent = parentComponent
            )
        }
        val textScreenComponent = remember {
            TextScreenComponent(
                componentContext = DefaultComponentContext(lifecycle), parentComponent = parentComponent
            )
        }
        val compareFilesComponent = remember {
            CompareFilesComponent(
                componentContext = DefaultComponentContext(lifecycle), parentComponent = parentComponent
            )
        }
        LifecycleController(lifecycle, windowState)
        AuroraWindow(
            skin = auroraSkin,
            state = windowState,
            title = BuildConfig.appName,
            icon = Icons.logo(),
            onCloseRequest = ::exitApplication,
            menuCommands = Window.Header.commands(
                auroraApplicationScope = this,
                windowState = windowState,
                gitHubData = githubData,
                preferencesAction = { isPreferencesOpen = true },
                aboutAction = { isAboutOpen = true }
            ),
            undecorated = undecorated,
            onPreviewKeyEvent = { Window.onKeyEvent(it, windowState) }
        ) {
            Window.setupAWTWindow(
                window = window,
                fileScreenComponent = fileScreenComponent,
                compareFilesComponent = compareFilesComponent,
                activeComponent = activeComponent
            )
            if (!retrievedGitHubData) {
                retrievedGitHubData = true
                httpClient = Ktor.createHttpClient().also { client ->
                    scope.launch(Dispatchers.Default) {
                        httpResponse = client.get(GitHub.HashHash.API.latest).also {
                            if (it.status == HttpStatusCode.OK) githubData = it.body()
                        }
                    }
                }
            }
            Box {
                Column {
                    Row(Modifier.fillMaxSize().weight(1f)) {
                        ControlPane(
                            fileScreen = fileScreenComponent,
                            compareScreen = compareFilesComponent,
                            activeComponent = activeComponent
                        )
                        VerticalSeparatorProjection().project(Modifier.fillMaxHeight())
                        Column {
                            Tabs(activeComponent = activeComponent, root = root)
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
                        compareScreen = compareFilesComponent
                    )
                }
                PreferencesDialog(
                    visible = isPreferencesOpen,
                    themeHandler = themeHandler,
                    onThemeChange = { auroraSkin = it },
                    onCloseRequest = { isPreferencesOpen = false }
                )
                AboutDialog(
                    visible = isAboutOpen,
                    onCloseRequest = { isAboutOpen = false },
                    httpClient = httpClient,
                    httpResponse = httpResponse,
                    githubData = githubData,
                    onUpdateCheck = { response ->
                        scope.launch(Dispatchers.Default) {
                            httpResponse = response
                            if (response.status == HttpStatusCode.OK) {
                                githubData = response.body()
                                logger.info {
                                    "Successfully retrieved GitHub data with status code ${response.status}"
                                }
                            } else {
                                logger.error {
                                    "Failed to retrieve GitHub data. Status code: ${response.status}"
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}
