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

import com.russellbanks.HashHash.BuildConfig
import helper.Browser
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandGroup
import org.pushingpixels.aurora.component.model.CommandMenuContentModel
import org.pushingpixels.aurora.window.AuroraApplicationScope
import java.net.URL

object Header {

    fun commands(
        auroraApplicationScope: AuroraApplicationScope,
        preferencesAction: () -> Unit,
        toggleFullScreenAction: () -> Unit,
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
                                    action = toggleFullScreenAction
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
                                        action = { Browser.open(URL("https://github.com/russellbanks/HashHash/issues/new/choose")) }
                                    ),
                                    Command(
                                        text = "Go to GitHub",
                                        action = { Browser.open(URL("https://github.com/russellbanks/HashHash")) }
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