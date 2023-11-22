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
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.isCtrlPressed
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.type
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState
import api.GitHubConstants
import api.GitHubImpl
import application.ApplicationState
import components.dialogs.DialogState
import java.net.URI
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandGroup
import org.pushingpixels.aurora.component.model.CommandMenuContentModel
import org.pushingpixels.aurora.window.AuroraApplicationScope

object Window {

    const val minWindowWidth = 750
    const val minWindowHeight = 600

    fun toggleFullscreen(windowState: WindowState) {
        if (windowState.placement != WindowPlacement.Fullscreen) {
            windowState.placement = WindowPlacement.Fullscreen
        } else {
            windowState.placement = WindowPlacement.Floating
        }
    }

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
        @Composable
        fun commands(
            auroraApplicationScope: AuroraApplicationScope,
            windowState: WindowState
        ): CommandGroup = CommandGroup(
            commands = listOf(
                fileHeaderButton(auroraApplicationScope),
                viewHeaderButton(windowState),
                windowHeaderButton(windowState),
                helpHeaderButton()
            )
        )

        private fun fileHeaderButton(
            auroraApplicationScope: AuroraApplicationScope
        ): Command = Command(
            text = "File",
            secondaryContentModel = CommandMenuContentModel(
                groups = listOf(
                    CommandGroup(
                        commands = listOf(
                            Command(
                                text = "Settings",
                                action = DialogState.Settings::open
                            )
                        )
                    ),
                    CommandGroup(
                        commands = listOf(
                            Command(
                                text = "Exit",
                                action = { ApplicationState.exitApplication(auroraApplicationScope) }
                            )
                        )
                    )
                )
            )
        )

        private fun viewHeaderButton(windowState: WindowState): Command = Command(
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

        private fun windowHeaderButton(windowState: WindowState): Command = Command(
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

        @Composable
        private fun helpHeaderButton(): Command = Command(
            text = "Help",
            secondaryContentModel = CommandMenuContentModel(
                groups = listOf(
                    CommandGroup(
                        commands = listOf(
                            Command(
                                text = "Report issue",
                                action = {
                                    Browser.open(URI(GitHubConstants.HashHash.Repository.newIssue))
                                }
                            ),
                            Command(
                                text = "Go to GitHub",
                                action = {
                                    Browser.open(URI(GitHubConstants.HashHash.Repository.website))
                                }
                            ),
                            Command(
                                text = "Go to release notes",
                                action = {
                                    Browser.open(
                                        URI(
                                            GitHubImpl.latestRelease?.htmlUrl
                                                ?: GitHubConstants.HashHash.Repository.releases
                                        )
                                    )
                                }
                            )
                        )
                    ),
                    CommandGroup(
                        commands = listOf(
                            Command(
                                text = "About",
                                action = DialogState.About::open
                            )
                        )
                    )
                )
            )
        )
    }
}
