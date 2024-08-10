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

package components.dialogs.settings.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import helper.Icons
import org.pushingpixels.aurora.component.AuroraBoxWithHighlights
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.projection.LabelProjection
import preferences.theme.Theme
import preferences.theme.ThemeHandler

object ThemeTab : Tab {
    private fun readResolve(): Any = ThemeTab

    override val options: TabOptions
        @Composable
        get() {
            val icon = Icons.Utility.paintBrush()
            return remember {
                TabOptions(
                    index = 0u,
                    title = "Theme",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Column(Modifier.padding(10.dp)) {
            LabelProjection(contentModel = LabelContentModel(text = options.title)).project()
            LazyColumn {
                items(Theme.entries) { theme ->
                    AuroraBoxWithHighlights(
                        modifier = Modifier.fillMaxWidth().padding(6.dp),
                        selected = theme == ThemeHandler.theme,
                        onClick = {
                            if (ThemeHandler.theme != theme) {
                                ThemeHandler.theme = theme
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
}
