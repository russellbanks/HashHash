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

package components.dialogs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.pushingpixels.aurora.component.model.ComboBoxContentModel
import org.pushingpixels.aurora.component.model.ComboBoxPresentationModel
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandButtonPresentationModel
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.projection.ComboBoxProjection
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.HorizontalSeparatorProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.theming.AuroraSkin
import org.pushingpixels.aurora.theming.AuroraSkinDefinition
import org.pushingpixels.aurora.theming.dustSkin
import org.pushingpixels.aurora.theming.nightShadeSkin
import preferences.theme.Theme
import preferences.theme.ThemeHandler
import preferences.titlebar.TitleBar
import preferences.titlebar.TitleBarHandler

@Composable
fun PreferencesDialog(
    visible: Boolean,
    themeHandler: ThemeHandler,
    onThemeChange: (AuroraSkinDefinition) -> Unit,
    onCloseRequest: () -> Unit
) {
    val scope = rememberCoroutineScope()
    var selectedTheme by remember { mutableStateOf(themeHandler.getTheme(scope)) }
    var selectedTitleBar by remember { mutableStateOf(TitleBarHandler.getTitleBar()) }
    val backgroundColorScheme = AuroraSkin.colors.getBackgroundColorScheme(
        decorationAreaType = AuroraSkin.decorationAreaType
    )
    TranslucentDialogOverlay(
        visible = visible,
        onClick = onCloseRequest
    )
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + slideInVertically(initialOffsetY = { -it / 10 }),
        exit = fadeOut() + slideOutVertically(targetOffsetY = { -it / 10 })
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Surface(
                modifier = Modifier.width(450.dp).height(290.dp),
                shape = RoundedCornerShape(8.dp),
                color = backgroundColorScheme.backgroundFillColor,
                border = BorderStroke(1.dp, Color.Black),
                elevation = 4.dp
            ) {
                Column {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Column {
                            LabelProjection(
                                contentModel = LabelContentModel(text = "Settings"),
                                presentationModel = LabelPresentationModel(
                                    textStyle = TextStyle(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            ).project(Modifier.align(Alignment.CenterHorizontally).padding(20.dp))
                            HorizontalSeparatorProjection().project(Modifier.fillMaxWidth())
                        }
                        Column(Modifier.padding(30.dp)) {
                            Column(
                                modifier = Modifier.weight(1f),
                                verticalArrangement = Arrangement.spacedBy(20.dp)
                            ) {
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
                                                    selectedTheme = it
                                                    scope.launch(Dispatchers.Default) { themeHandler.putTheme(it) }
                                                    onThemeChange(
                                                        when (it) {
                                                            Theme.LIGHT -> dustSkin()
                                                            Theme.DARK -> nightShadeSkin()
                                                            else -> if (themeHandler.isSystemDark()) {
                                                                nightShadeSkin()
                                                            } else dustSkin()
                                                        }
                                                    )
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
                                Column {
                                    Row {
                                        Box(Modifier.weight(1f)) {
                                            LabelProjection(
                                                contentModel = LabelContentModel(text = "Title bar style")
                                            ).project()
                                        }
                                        ComboBoxProjection(
                                            contentModel = ComboBoxContentModel(
                                                items = TitleBar.values().toList(),
                                                selectedItem = selectedTitleBar,
                                                onTriggerItemSelectedChange = {
                                                    if (TitleBarHandler.getTitleBar() != it) {
                                                        selectedTitleBar = it
                                                        scope.launch(Dispatchers.Default) {
                                                            TitleBarHandler.putTitleBar(it)
                                                        }
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
                        }
                    }
                    Column {
                        HorizontalSeparatorProjection().project(Modifier.fillMaxWidth())
                        CommandButtonProjection(
                            contentModel = Command(
                                text = "Close",
                                action = onCloseRequest
                            ),
                            presentationModel = CommandButtonPresentationModel(
                                textStyle = TextStyle(
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.Center
                                ),
                                horizontalGapScaleFactor = 1.8f,
                                verticalGapScaleFactor = 1.5f
                            )
                        ).project(Modifier.width(150.dp).align(Alignment.End).padding(20.dp))
                    }
                }
            }
        }
    }
}
