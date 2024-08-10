/**

HashHash
Copyright (C) 2024 Russell Banks

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

package components.dialogs.settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import application.ApplicationWindowState
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import components.dialogs.Dialog
import components.dialogs.DialogState
import components.dialogs.settings.screens.ThemeTab
import components.dialogs.settings.screens.TitleBarTab
import components.dialogs.settings.screens.WindowCornerTab
import helper.windows.windowsBuild
import org.jetbrains.skiko.hostOs
import org.pushingpixels.aurora.component.AuroraBoxWithHighlights
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandButtonPresentationModel
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.theming.IconFilterStrategy

@Composable
fun SettingsDialog(window: ApplicationWindowState) {
    Dialog(dialog = DialogState.Dialogs.Settings) {
        Row {
            val tabs = listOfSettingsTabs(window)
            TabNavigator(ThemeTab) { tabNavigator ->
                Column(Modifier.padding(horizontal = 15.dp, vertical = 6.dp)) {
                    LazyColumn {
                        items(tabs) { tab ->
                            AuroraBoxWithHighlights(
                                modifier = Modifier.width(140.dp).padding(6.dp),
                                selected = tabNavigator.current == tab,
                                onClick = { tabNavigator.current = tab }
                            ) {
                                LabelProjection(
                                    contentModel = LabelContentModel(
                                        text = tab.options.title,
                                        icon = tab.options.icon
                                    ),
                                    presentationModel = LabelPresentationModel(
                                        textStyle = TextStyle(fontSize = 12.sp),
                                        iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText
                                    )
                                ).project(Modifier.padding(4.dp))
                            }
                        }
                    }
                    AnimatedVisibility(visible = window.needsRestarting) {
                        CommandButtonProjection(
                            contentModel = Command(
                                text = "Restart",
                                action = window::restart
                            ),
                            presentationModel = CommandButtonPresentationModel(
                                textStyle = TextStyle(fontSize = 12.sp),
                                horizontalGapScaleFactor = 1.8f,
                                verticalGapScaleFactor = 1.5f
                            )
                        ).project(Modifier.width(140.dp).padding(6.dp))
                    }
                }
                CurrentTab()
            }
        }
    }
}

@Composable
fun listOfSettingsTabs(window: ApplicationWindowState) = listOf(ThemeTab, TitleBarTab(window), WindowCornerTab(window))
    .filter { if (it is WindowCornerTab) hostOs.isWindows && windowsBuild >= 22_000 else true }
