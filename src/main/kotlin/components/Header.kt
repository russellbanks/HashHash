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

package components

import androidx.compose.ui.window.WindowState
import data.GitHubData
import helper.Browser
import helper.GitHub
import helper.Window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandGroup
import org.pushingpixels.aurora.component.model.CommandMenuContentModel
import org.pushingpixels.aurora.window.AuroraApplicationScope
import java.net.URL

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
                                    action = { Window.toggleFullscreen(windowState) }
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