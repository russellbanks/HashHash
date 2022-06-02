package helper

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.input.key.*
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState
import data.GitHubData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandGroup
import org.pushingpixels.aurora.component.model.CommandMenuContentModel
import org.pushingpixels.aurora.window.AuroraApplicationScope
import java.net.URL

object Window {

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

    object Header {

        fun commands(
            auroraApplicationScope: AuroraApplicationScope,
            windowState: WindowState,
            scope: CoroutineScope,
            gitHubData: GitHubData?,
            preferencesAction: () -> Unit,
            aboutAction: () -> Unit
        ): CommandGroup {
            return CommandGroup(
                commands = listOf(
                    Command(
                        text = "File",
                        secondaryContentModel = CommandMenuContentModel(
                            listOf(
                                CommandGroup(
                                    commands = listOf(
                                        Command(
                                            text = "Settings",
                                            action = preferencesAction
                                        )
                                    )
                                ),
                                CommandGroup(
                                    commands = listOf(
                                        Command(
                                            text = "Exit",
                                            action = { auroraApplicationScope.exitApplication() }
                                        )
                                    )
                                )
                            )
                        )
                    ),
                    Command(
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
                    ),
                    Command(
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
                    ),
                    Command(
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
                                                    Browser.open(URL(gitHubData?.htmlUrl ?: GitHub.HashHash.Repository.releases))
                                                }
                                            }
                                        )
                                    )
                                ),
                                CommandGroup(
                                    commands = listOf(
                                        Command(
                                            text = "About",
                                            action = aboutAction
                                        )
                                    )
                                )
                            )
                        )
                    )
                )
            )
        }
    }
}
