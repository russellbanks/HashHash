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

import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.Theme
import theme.ThemeHandler
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.*
import org.pushingpixels.aurora.theming.*

@Composable
fun PreferencesDialog(
    visible: Boolean,
    systemDark: Boolean,
    onThemeChange: (Pair<Theme, AuroraSkinDefinition>) -> Unit,
    onCloseRequest: () -> Unit
) {
    TranslucentDialogOverlay(
        visible = visible,
        onClick = onCloseRequest
    )
    val backgroundColorScheme = AuroraSkin.colors.getBackgroundColorScheme(
        decorationAreaType = AuroraSkin.decorationAreaType
    )
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + slideInVertically(initialOffsetY = { -it / 10 }),
        exit = fadeOut() + slideOutVertically(targetOffsetY = { -it / 10 })
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Surface(
                modifier = Modifier.width(450.dp).height(350.dp),
                shape = RoundedCornerShape(8.dp),
                color = backgroundColorScheme.backgroundFillColor,
                border = BorderStroke(1.dp, Color.Black),
                elevation = 4.dp
            ) {
                Column {
                    Column(modifier = Modifier
                        .weight(1f)
                    ) {
                        Column {
                            LabelProjection(
                                contentModel = LabelContentModel(text = "Preferences"),
                                presentationModel = LabelPresentationModel(
                                    textStyle = TextStyle(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            ).project(Modifier.align(Alignment.CenterHorizontally).padding(20.dp))
                            HorizontalSeparatorProjection().project(Modifier.fillMaxWidth())
                        }
                        Row(Modifier.padding(30.dp)) {
                            Box(Modifier.weight(1f)) {
                                LabelProjection(contentModel = LabelContentModel(text = "Theme")).project()
                            }
                            ComboBoxProjection(
                                contentModel = ComboBoxContentModel(
                                    items = listOf(Theme.LIGHT, Theme.DARK, Theme.SYSTEM),
                                    selectedItem = ThemeHandler(systemDark).getTheme(),
                                    onTriggerItemSelectedChange = {
                                        onThemeChange(
                                            Pair(it, when (it) {
                                                    Theme.LIGHT -> dustSkin()
                                                    Theme.DARK -> nightShadeSkin()
                                                    else -> if (systemDark) nightShadeSkin() else dustSkin()
                                                }
                                            )
                                        )
                                    }
                                ),
                                presentationModel = ComboBoxPresentationModel(
                                    displayConverter = { it.name.lowercase().replaceFirstChar { char -> char.titlecase() } }
                                )
                            ).project()
                        }
                    }
                    Column {
                        HorizontalSeparatorProjection().project(Modifier.fillMaxWidth())
                        CommandButtonProjection(
                            contentModel = Command(
                                text = "Close",
                                action = onCloseRequest
                            )
                        ).project(Modifier.width(150.dp).align(Alignment.End).padding(20.dp))
                    }
                }
            }
        }
    }
}