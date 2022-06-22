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
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.pushingpixels.aurora.component.model.ComboBoxContentModel
import org.pushingpixels.aurora.component.model.ComboBoxPresentationModel
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.projection.ComboBoxProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import preferences.theme.Theme
import preferences.theme.ThemeHandler

@Composable
fun ThemeItem(themeHandler: ThemeHandler) {
    val scope = rememberCoroutineScope()
    var selectedTheme by remember { mutableStateOf(themeHandler.getTheme()) }
    Row {
        Box(Modifier.weight(1f)) {
            LabelProjection(contentModel = LabelContentModel(text = "Theme")).project()
        }
        ComboBoxProjection(
            contentModel = ComboBoxContentModel(
                items = Theme.values().toList(),
                selectedItem = selectedTheme,
                onTriggerItemSelectedChange = {
                    if (themeHandler.getTheme() != it) {
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
