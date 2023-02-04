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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import koin.inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.pushingpixels.aurora.component.AuroraBoxWithHighlights
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.projection.LabelProjection
import preferences.theme.Theme
import preferences.theme.ThemeHandler

@Composable
fun ThemeScreen() {
    val scope = rememberCoroutineScope()
    val themeHandler: ThemeHandler by inject()
    Column(Modifier.padding(10.dp)) {
        LabelProjection(contentModel = LabelContentModel(text = "Theme")).project()
        LazyColumn {
            items(Theme.values()) { theme ->
                AuroraBoxWithHighlights(
                    modifier = Modifier.fillMaxWidth().padding(6.dp),
                    selected = theme == themeHandler.getTheme(),
                    onClick = {
                        if (themeHandler.getTheme() != theme) {
                            scope.launch(Dispatchers.Default) {
                                themeHandler.putTheme(theme)
                            }
                        }
                    }
                ) {
                    LabelProjection(
                        contentModel = LabelContentModel(text = theme.name),
                        presentationModel = LabelPresentationModel(
                            textStyle = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                        )
                    ).project(Modifier.padding(4.dp))
                }
            }
        }
    }
}
