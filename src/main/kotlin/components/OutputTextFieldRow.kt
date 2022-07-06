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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import components.screens.ParentComponent
import helper.Icons
import koin.inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
fun OutputTextFieldRow(value: String, isValueUppercase: Boolean, onCaseClick: () -> Unit) {
    val parent: ParentComponent by inject()
    val clipboardManager = LocalClipboardManager.current
    val scope = rememberCoroutineScope() { Dispatchers.Default }
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        LabelProjection(
            contentModel = LabelContentModel(text = "${parent.algorithm.algorithmName} Hash"),
            presentationModel = LabelPresentationModel(textStyle = TextStyle(fontSize = 15.sp))
        ).project()
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Box(Modifier.weight(1f)) {
                TextFieldStringProjection(
                    contentModel = TextFieldStringContentModel(
                        value = value,
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
                                if (value.isNotBlank()) {
                                    clipboardManager.setText(AnnotatedString(text = value))
                                    parent.snackbarHostState.currentSnackbarData?.dismiss()
                                    scope.launch { parent.snackbarHostState.showSnackbar("Copied to clipboard") }
                                }
                            }
                        ),
                        Command(
                            text = "Case",
                            icon = if (isValueUppercase) Icons.Utility.capitalA() else Icons.Utility.lowerCaseA(),
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
