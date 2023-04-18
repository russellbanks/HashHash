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

package components.controlpane

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.SelectorContentModel
import org.pushingpixels.aurora.component.projection.CheckBoxProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import preferences.mode.Mode
import preferences.mode.ModeHandler

@Composable
fun ModeSelector() {
    Row {
        Box(Modifier.weight(1f)) {
            LabelProjection(
                contentModel = LabelContentModel(
                    text = "${Mode.SIMPLE.name.lowercase().replaceFirstChar(Char::titlecaseChar)} list"
                )
            ).project()
        }
        CheckBoxProjection(
            contentModel = SelectorContentModel(
                text = "",
                selected = ModeHandler.selectedMode == Mode.SIMPLE,
                onClick = {
                    ModeHandler.mode = if (ModeHandler.selectedMode == Mode.SIMPLE) Mode.ADVANCED else Mode.SIMPLE
                }
            )
        ).project()
    }
}
