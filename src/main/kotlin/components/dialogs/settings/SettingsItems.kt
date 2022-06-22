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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mayakapps.compose.windowstyler.WindowCornerPreference
import helper.windows.windowsBuild
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.skiko.hostOs
import org.pushingpixels.aurora.component.model.ComboBoxContentModel
import org.pushingpixels.aurora.component.model.ComboBoxPresentationModel
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.projection.ComboBoxProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import preferences.theme.Theme
import preferences.theme.ThemeHandler
import preferences.titlebar.TitleBar
import preferences.titlebar.TitleBarHandler
import preferences.windowcorner.WindowCornerHandler

@Composable
fun SettingsItems(
    themeHandler: ThemeHandler,
    titleBarHandler: TitleBarHandler,
    windowCornerHandler: WindowCornerHandler
) {
    val scope = rememberCoroutineScope()
    var selectedTheme by remember { mutableStateOf(themeHandler.getTheme(scope)) }
    var selectedTitleBar by remember { mutableStateOf(titleBarHandler.getTitleBar()) }
    var selectedWindowCorner by remember { mutableStateOf(windowCornerHandler.getWindowCorner()) }
    Column(Modifier.padding(30.dp)) {
        LazyColumn(
            modifier = Modifier.weight(weight = 1f, fill = false),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                Row {
                    Box(Modifier.weight(1f)) {
                        LabelProjection(contentModel = LabelContentModel(text = "Theme")).project()
                    }
                    ComboBoxProjection(
                        contentModel = ComboBoxContentModel(
                            items = Theme.values().toList(),
                            selectedItem = selectedTheme,
                            onTriggerItemSelectedChange = {
                                if (themeHandler.getTheme(scope) != it) {
                                    scope.launch(Dispatchers.Default) { themeHandler.putTheme(it) }
                                    selectedTheme = it
                                }
                            }
                        ),
                        presentationModel = ComboBoxPresentationModel(
                            displayConverter = {
                                it.name.lowercase().replaceFirstChar { char -> char.titlecase() }
                            }
                        )
                    ).project()
                }
            }
            item {
                Column {
                    Row {
                        Box(Modifier.weight(1f)) {
                            LabelProjection(contentModel = LabelContentModel(text = "Title bar style")).project()
                        }
                        ComboBoxProjection(
                            contentModel = ComboBoxContentModel(
                                items = TitleBar.values().toList(),
                                selectedItem = selectedTitleBar,
                                onTriggerItemSelectedChange = {
                                    if (titleBarHandler.getTitleBar() != it) {
                                        scope.launch(Dispatchers.Default) {
                                            titleBarHandler.putTitleBar(it)
                                        }
                                        selectedTitleBar = it
                                    }
                                }
                            ),
                            presentationModel = ComboBoxPresentationModel(
                                displayConverter = {
                                    it.name.lowercase().replaceFirstChar { char -> char.titlecase() }
                                }
                            )
                        ).project()
                    }
                    LabelProjection(
                        contentModel = LabelContentModel(text = "Requires restart"),
                        presentationModel = LabelPresentationModel(
                            textStyle = TextStyle(
                                color = Color.Gray,
                                fontSize = 12.sp,
                                fontStyle = FontStyle.Italic
                            )
                        )
                    ).project()
                }
            }
            if (hostOs.isWindows && windowsBuild >= 22_000) {
                item {
                    AnimatedVisibility(visible = selectedTitleBar == TitleBar.Native) {
                        Column {
                            Row {
                                Box(Modifier.weight(1f)) {
                                    LabelProjection(contentModel = LabelContentModel(text = "Corner type")).project()
                                }
                                ComboBoxProjection(
                                    contentModel = ComboBoxContentModel(
                                        items = WindowCornerPreference.values().toList(),
                                        selectedItem = selectedWindowCorner,
                                        onTriggerItemSelectedChange = {
                                            if (windowCornerHandler.getWindowCorner() != it) {
                                                scope.launch(Dispatchers.Default) {
                                                    windowCornerHandler.putWindowCorner(it)
                                                }
                                                selectedWindowCorner = it
                                            }
                                        }
                                    ),
                                    presentationModel = ComboBoxPresentationModel(
                                        displayConverter = {
                                            it.name
                                                .lowercase()
                                                .replaceFirstChar { char -> char.titlecase() }
                                                .replace(oldValue = "_", newValue = " ")
                                        }
                                    )
                                ).project()
                            }
                            LabelProjection(
                                contentModel = LabelContentModel(text = "Requires restart"),
                                presentationModel = LabelPresentationModel(
                                    textStyle = TextStyle(
                                        color = Color.Gray,
                                        fontSize = 12.sp,
                                        fontStyle = FontStyle.Italic
                                    )
                                )
                            ).project()
                        }
                    }
                }
            }
        }
    }
}
