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

package components.screens.text

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import components.ComparisonTextFieldRow
import components.OutputTextFieldRow
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.model.TextFieldStringContentModel
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.component.projection.TextFieldStringProjection

@Composable
fun TextScreen(component: TextScreenComponent) {
    var givenText by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Column {
            Row {
                Box(Modifier.weight(1f)) {
                    LabelProjection(
                        contentModel = LabelContentModel(text = "Text Hashing"),
                        presentationModel = LabelPresentationModel(
                            textStyle = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    ).project()
                }
                LabelProjection(
                    contentModel = LabelContentModel(
                        text = "${"%,d".format(givenText.count())} ${if (givenText.count() == 1) "character" else "characters"}"
                    )
                ).project()
            }
            TextFieldStringProjection(
                contentModel = TextFieldStringContentModel(
                    value = givenText,
                    onValueChange = {
                        givenText = it
                    }
                )
            ).project(Modifier.fillMaxWidth().height(200.dp))
            Row(
                modifier = Modifier.fillMaxWidth().padding(4.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                CommandButtonProjection(
                    contentModel = Command(
                        text = "Uppercase",
                        action = component.onUppercaseClick
                    ),
                ).project(Modifier.fillMaxWidth(1f / 3))
                CommandButtonProjection(
                    contentModel = Command(
                        text = "Lowercase",
                        action = component.onLowercaseClick
                    ),
                ).project(Modifier.fillMaxWidth(1f / 2))
                CommandButtonProjection(
                    contentModel = Command(
                        text = "Clear text area",
                        action = component.onClearTextClick
                    ),
                ).project(Modifier.fillMaxWidth())
            }
            AnimatedVisibility(visible = givenText.count() >= TextScreenComponent.characterLimit) {
                LabelProjection(
                    contentModel = LabelContentModel(
                        text = "To conserve memory usage, you cannot input more than ${"%,d".format(TextScreenComponent.characterLimit)} characters."
                    )
                ).project()
            }
        }
        OutputTextFieldRow(
            algorithm = component.algorithm,
            value = if (givenText.isNotEmpty()) component.givenTextHash else "",
            onCaseClick = component.onCaseClick
        )
        ComparisonTextFieldRow(
            hashedOutput = component.givenTextHash,
            comparisonHash = component.textComparisonHash,
            onPasteClick = component.onPasteClick,
            onClearClick = component.onComparisonClearClick,
            onTextFieldChange = component.onComparisonTextFieldChange
        )
    }
}