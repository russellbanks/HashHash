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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.pushingpixels.aurora.component.model.ComboBoxContentModel
import org.pushingpixels.aurora.component.model.ComboBoxPresentationModel
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.projection.ComboBoxProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import preferences.titlebar.TitleBar
import preferences.titlebar.TitleBarHandler

@Composable
fun TitleBarItem(titleBarHandler: TitleBarHandler) {
    val scope = rememberCoroutineScope()
    Column {
        Row {
            Box(Modifier.weight(1f)) {
                LabelProjection(contentModel = LabelContentModel(text = "Title bar style")).project()
            }
            ComboBoxProjection(
                contentModel = ComboBoxContentModel(
                    items = TitleBar.values().toList(),
                    selectedItem = titleBarHandler.getTitleBar(),
                    onTriggerItemSelectedChange = {
                        if (titleBarHandler.getTitleBar() != it) {
                            scope.launch(Dispatchers.Default) {
                                titleBarHandler.putTitleBar(it)
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
