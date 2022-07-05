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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import application.ApplicationWindowState
import components.dialogs.DialogState
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import components.dialogs.Dialog
import components.dialogs.settings.screens.ThemeScreen
import components.dialogs.settings.screens.TitleBarScreen
import components.dialogs.settings.screens.WindowCornerScreen
import helper.Icons
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
import preferences.theme.ThemeHandler
import preferences.titlebar.TitleBarHandler
import preferences.windowcorner.WindowCornerHandler

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun SettingsDialog(
    root: SettingsRoot,
    dialogState: DialogState,
    themeHandler: ThemeHandler,
    titleBarHandler: TitleBarHandler,
    windowCornerHandler: WindowCornerHandler,
    window: ApplicationWindowState
) {
    val routerState = root.routerState.subscribeAsState()
    val activeComponent = routerState.value.activeChild.instance
    Dialog(dialogState = dialogState, dialog = DialogState.Dialogs.Settings) {
        Row {
            val list = listOfCategories()
            Column(Modifier.padding(horizontal = 15.dp, vertical = 6.dp)) {
                LazyColumn {
                    items(list) { category ->
                        AuroraBoxWithHighlights(
                            modifier = Modifier.width(140.dp).padding(6.dp),
                            selected = activeComponent == category.first.toComponent(),
                            onClick = when (category.first) {
                                Category.Theme -> root::onThemeTabClicked
                                Category.TitleBar -> root::onTitleBarTabClicked
                                Category.WindowCorner -> root::onWindowCornerTabClicked
                            }
                        ) {
                            LabelProjection(
                                contentModel = LabelContentModel(
                                    text = category.first.name.toFriendlyCase(),
                                    icon = category.second
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
                        contentModel = Command(text = "Restart", action = window::restart),
                        presentationModel = CommandButtonPresentationModel(
                            textStyle = TextStyle(fontSize = 12.sp, textAlign = TextAlign.Center),
                            horizontalGapScaleFactor = 1.8f,
                            verticalGapScaleFactor = 1.5f
                        )
                    ).project(Modifier.width(140.dp).padding(6.dp))
                }
            }
            Children(routerState = routerState.value) {
                when (it.instance) {
                    is SettingsRoot.Child.Theme -> ThemeScreen(themeHandler)
                    is SettingsRoot.Child.TitleBar -> TitleBarScreen(
                        titleBarHandler = titleBarHandler, windowCornerHandler = windowCornerHandler, window = window
                    )
                    is SettingsRoot.Child.WindowCorner -> WindowCornerScreen(
                        windowCornerHandler = windowCornerHandler, titleBarHandler = titleBarHandler, window = window
                    )
                }
            }
        }
    }
}

@Composable
fun listOfCategories() = listOf(
    Category.Theme to Icons.Utility.paintBrush(),
    Category.TitleBar to Icons.Utility.windowAlt(),
    Category.WindowCorner to Icons.Utility.window()
).filter { if (it.first == Category.WindowCorner) hostOs.isWindows && windowsBuild >= 22_000 else true }
