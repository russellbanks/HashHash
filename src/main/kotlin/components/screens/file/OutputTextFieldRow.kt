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

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appmattus.crypto.Algorithm
import helper.Icons
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CommandButtonStripProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.component.projection.TextFieldStringProjection

@Composable
fun OutputTextFieldRow(
    algorithm: Algorithm,
    hashedOutput: String,
    onCaseClick: () -> Unit
) {
    val clipboardManager =  LocalClipboardManager.current
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        LabelProjection(
            contentModel = LabelContentModel(text = "${algorithm.algorithmName} Hash"),
            presentationModel = LabelPresentationModel(
                textStyle = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
        ).project()
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Box(Modifier.weight(1f)) {
                TextFieldStringProjection(
                    contentModel = TextFieldStringContentModel(
                        value = hashedOutput,
                        placeholder = "Output Hash",
                        readOnly = true,
                        onValueChange = {}
                    )
                ).project(Modifier.fillMaxWidth())
            }
            CommandButtonStripProjection(
                contentModel = CommandGroup(
                    commands = listOf(
                        Command(
                            text = "Copy",
                            icon = Icons.Utility.copy(),
                            action = {
                                if (hashedOutput.isNotBlank()) {
                                    clipboardManager.setText(AnnotatedString(text = hashedOutput))
                                }
                            }
                        ),
                        Command(
                            text = "Case",
                            icon = Icons.Utility.switch(),
                            action = onCaseClick
                        )
                    )
                ),
                presentationModel = CommandStripPresentationModel(
                    orientation = StripOrientation.Horizontal,
                    commandPresentationState = CommandButtonPresentationState.Medium
                )
            ).project()
        }
    }
}