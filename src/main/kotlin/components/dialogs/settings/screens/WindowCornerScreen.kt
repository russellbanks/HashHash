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

package components.dialogs.settings.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import application.ApplicationWindowState
import com.mayakapps.compose.windowstyler.WindowCornerPreference
import org.pushingpixels.aurora.component.AuroraBoxWithHighlights
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.projection.LabelProjection
import preferences.windowcorner.WindowCornerHandler

@OptIn(ExperimentalStdlibApi::class)
@Composable
fun WindowCornerScreen(window: ApplicationWindowState) {
    Column(Modifier.padding(10.dp)) {
        LabelProjection(contentModel = LabelContentModel(text = "Window Corner")).project()
        LabelProjection(
            contentModel = LabelContentModel(text = "Requires Restart"),
            presentationModel = LabelPresentationModel(
                textStyle = TextStyle(color = Color.Gray, fontSize = 12.sp, fontStyle = FontStyle.Italic)
            )
        ).project()
        LazyColumn {
            items(WindowCornerPreference.entries) { windowCornerPreference ->
                AuroraBoxWithHighlights(
                    modifier = Modifier.fillMaxWidth().padding(6.dp),
                    selected = windowCornerPreference == WindowCornerHandler.windowCorner,
                    onClick = {
                        if (WindowCornerHandler.windowCorner != windowCornerPreference) {
                            WindowCornerHandler.windowCorner = windowCornerPreference
                            window.checkWindowNeedsRestarting()
                        }
                    }
                ) {
                    LabelProjection(
                        contentModel = LabelContentModel(
                            text = windowCornerPreference.name
                                .lowercase()
                                .replaceFirstChar(Char::titlecaseChar)
                                .replace('_', ' ')
                        ),
                        presentationModel = LabelPresentationModel(
                            textStyle = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                        )
                    ).project(Modifier.padding(4.dp))
                }
            }
        }
    }
}
