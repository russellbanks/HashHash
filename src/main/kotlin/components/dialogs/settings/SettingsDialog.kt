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
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import application.ApplicationWindowState
import application.DialogState
import helper.windows.windowsBuild
import org.jetbrains.skiko.hostOs
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.projection.HorizontalSeparatorProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.theming.AuroraSkin
import preferences.theme.ThemeHandler
import preferences.titlebar.TitleBarHandler
import preferences.windowcorner.WindowCornerHandler

@Composable
fun SettingsDialog(
    dialogState: DialogState,
    themeHandler: ThemeHandler,
    titleBarHandler: TitleBarHandler,
    windowCornerHandler: WindowCornerHandler,
    window: ApplicationWindowState
) {
    val backgroundColorScheme = AuroraSkin.colors.getBackgroundColorScheme(
        decorationAreaType = AuroraSkin.decorationAreaType
    )
    AnimatedVisibility(
        visible = dialogState.Settings().isOpen(),
        enter = fadeIn() + slideInVertically(initialOffsetY = { -it / 10 }),
        exit = fadeOut() + slideOutVertically(targetOffsetY = { -it / 10 })
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Surface(
                modifier = Modifier.width(450.dp),
                shape = RoundedCornerShape(8.dp),
                color = backgroundColorScheme.backgroundFillColor,
                border = BorderStroke(1.dp, Color.Black),
                elevation = 4.dp
            ) {
                Column {
                    Column(modifier = Modifier.weight(weight = 1f, fill = false)) {
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
                        Column(Modifier.padding(30.dp)) {
                            LazyColumn(
                                modifier = Modifier.weight(weight = 1f, fill = false),
                                verticalArrangement = Arrangement.spacedBy(20.dp)
                            ) {
                                item { ThemeItem(themeHandler = themeHandler) }
                                item { TitleBarItem(
                                    titleBarHandler = titleBarHandler,
                                    windowCornerHandler = windowCornerHandler,
                                    window = window
                                ) }
                                if (hostOs.isWindows && windowsBuild >= 22_000) {
                                    item {
                                        WindowCornerItem(
                                            windowCornerHandler = windowCornerHandler,
                                            titleBarHandler = titleBarHandler,
                                            window = window
                                        )
                                    }
                                }
                            }
                        }
                    }
                    RestartWindowFooter(dialogState = dialogState, window = window)
                }
            }
        }
    }
}
