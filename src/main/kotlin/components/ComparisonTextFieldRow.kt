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

package components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import helper.Icons
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandButtonPresentationState
import org.pushingpixels.aurora.component.model.CommandGroup
import org.pushingpixels.aurora.component.model.CommandStripPresentationModel
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.model.StripOrientation
import org.pushingpixels.aurora.component.model.TextFieldStringContentModel
import org.pushingpixels.aurora.component.projection.CommandButtonStripProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.component.projection.TextFieldStringProjection

@Composable
fun ComparisonTextFieldRow(
    hashedOutput: String,
    comparisonHash: String,
    onPasteClick: () -> Unit,
    onClearClick: () -> Unit,
    onTextFieldChange: (String) -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        LabelProjection(
            contentModel = LabelContentModel(text = "Comparison Hash"),
            presentationModel = LabelPresentationModel(
                textStyle = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
            )
        ).project()
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Box(Modifier.weight(1f)) {
                TextFieldStringProjection(
                    contentModel = TextFieldStringContentModel(
                        value = comparisonHash,
                        placeholder = "Comparison Hash",
                        onValueChange = onTextFieldChange
                    )
                ).project(Modifier.fillMaxWidth())
            }
            CommandButtonStripProjection(
                contentModel = CommandGroup(
                    commands = listOf(
                        Command(
                            text = "Paste",
                            icon = Icons.Utility.clipboard(),
                            action = onPasteClick
                        ),
                        Command(
                            text = "Clear",
                            icon = Icons.Utility.eraser(),
                            action = onClearClick
                        )
                    )
                ),
                presentationModel = CommandStripPresentationModel(
                    orientation = StripOrientation.Horizontal,
                    commandPresentationState = CommandButtonPresentationState.Medium
                )
            ).project()
        }
        val areTextFieldsBlank = hashedOutput.isNotBlank() && comparisonHash.isNotBlank()
        AnimatedVisibility(visible = areTextFieldsBlank) {
            val hashesMatch = areTextFieldsBlank && hashedOutput.equals(comparisonHash, ignoreCase = true)
            LabelProjection(
                contentModel = LabelContentModel(
                    text = "Hashes${if (!hashesMatch) " do not" else ""} match",
                    icon = if (hashesMatch) Icons.Utility.check() else Icons.Utility.cross()
                )
            ).project()
        }
    }
}
