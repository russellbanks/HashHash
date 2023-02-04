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

package helper

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.isCtrlPressed
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.type
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState
import api.Ktor
import application.ApplicationState
import components.dialogs.DialogState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandGroup
import org.pushingpixels.aurora.component.model.CommandMenuContentModel
import org.pushingpixels.aurora.window.AuroraApplicationScope
import java.awt.Dimension
import java.awt.Window
import java.net.URL

object Window {

    private const val minWindowWidth = 750
    private const val minWindowHeight = 600

    @Composable
    fun setupAWTWindow(window: Window) {
        val scope = rememberCoroutineScope { Dispatchers.Default }
        with(window) {
            minimumSize = Dimension(minWindowWidth, minWindowHeight)
            dropTarget = DragAndDrop.target(scope) { droppedItems ->
                DragAndDrop.setResult(droppedItems = droppedItems)
            }
        }
    }

    fun toggleFullscreen(windowState: WindowState) {
        if (windowState.placement != WindowPlacement.Fullscreen) {
            windowState.placement = WindowPlacement.Fullscreen
        } else {
            windowState.placement = WindowPlacement.Floating
        }
    }

    @OptIn(ExperimentalComposeUiApi::class)
    fun onKeyEvent(keyEvent: KeyEvent, windowState: WindowState): Boolean {
        return if (keyEvent.key == Key.F11 && keyEvent.type == KeyEventType.KeyUp) {
            toggleFullscreen(windowState)
            true
        } else if (keyEvent.isCtrlPressed && keyEvent.key == Key.M && keyEvent.type == KeyEventType.KeyUp) {
            windowState.isMinimized = true
            true
        } else {
            false
        }
    }

    object Header : KoinComponent {
        private val applicationState: ApplicationState by inject()

        @Composable
        fun commands(
            auroraApplicationScope: AuroraApplicationScope,
            windowState: WindowState
        ): CommandGroup {
            val ktor: Ktor by inject()
            val dialogState: DialogState by inject()
            return CommandGroup(
                commands = listOf(
                    fileHeaderButton(auroraApplicationScope = auroraApplicationScope, dialogState = dialogState),
                    viewHeaderButton(windowState = windowState),
                    windowHeaderButton(windowState = windowState),
                    helpHeaderButton(ktor = ktor, dialogState = dialogState)
                )
            )
        }

        private fun fileHeaderButton(
            auroraApplicationScope: AuroraApplicationScope,
            dialogState: DialogState
        ): Command {
            return Command(
                text = "File",
                secondaryContentModel = CommandMenuContentModel(
                    listOf(
                        CommandGroup(
                            commands = listOf(
                                Command(
                                    text = "Settings",
                                    action = dialogState.Settings()::open
                                )
                            )
                        ),
                        CommandGroup(
                            commands = listOf(
                                Command(
                                    text = "Exit",
                                    action = { applicationState.exitApplication(auroraApplicationScope) }
                                )
                            )
                        )
                    )
                )
            )
        }

        private fun viewHeaderButton(windowState: WindowState): Command {
            return Command(
                text = "View",
                secondaryContentModel = CommandMenuContentModel(
                    CommandGroup(
                        commands = listOf(
                            Command(
                                text = "Toggle Full Screen",
                                action = { toggleFullscreen(windowState) }
                            )
                        )
                    )
                )
            )
        }

        private fun windowHeaderButton(windowState: WindowState): Command {
            return Command(
                text = "Window",
                secondaryContentModel = CommandMenuContentModel(
                    CommandGroup(
                        commands = listOf(
                            Command(
                                text = "Minimise",
                                action = { windowState.isMinimized = true }
                            )
                        )
                    )
                )
            )
        }

        @Composable
        private fun helpHeaderButton(
            ktor: Ktor,
            dialogState: DialogState
        ): Command {
            val scope = rememberCoroutineScope()
            return Command(
                text = "Help",
                secondaryContentModel = CommandMenuContentModel(
                    listOf(
                        CommandGroup(
                            commands = listOf(
                                Command(
                                    text = "Report issue",
                                    action = {
                                        scope.launch(Dispatchers.Default) {
                                            Browser.open(URL(GitHub.HashHash.Repository.newIssue))
                                        }
                                    }
                                ),
                                Command(
                                    text = "Go to GitHub",
                                    action = {
                                        scope.launch(Dispatchers.Default) {
                                            Browser.open(URL(GitHub.HashHash.Repository.website))
                                        }
                                    }
                                ),
                                Command(
                                    text = "Go to release notes",
                                    action = {
                                        scope.launch(Dispatchers.Default) {
                                            Browser.open(
                                                URL(ktor.githubData?.htmlUrl ?: GitHub.HashHash.Repository.releases)
                                            )
                                        }
                                    }
                                )
                            )
                        ),
                        CommandGroup(
                            commands = listOf(
                                Command(
                                    text = "About",
                                    action = dialogState.About()::open
                                )
                            )
                        )
                    )
                )
            )
        }
    }
}
