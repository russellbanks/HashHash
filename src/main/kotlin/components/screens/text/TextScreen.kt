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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.TextStyle
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
    val clipboardManager = LocalClipboardManager.current
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
                            textStyle = TextStyle(fontSize = 18.sp)
                        )
                    ).project()
                }
                LabelProjection(contentModel = LabelContentModel(text = component.characterCountAsString())).project()
            }
            TextFieldStringProjection(
                contentModel = TextFieldStringContentModel(
                    value = component.givenText,
                    onValueChange = { component.givenText = it }
                )
            ).project(Modifier.fillMaxWidth().fillMaxHeight(fraction = 0.5f).padding(horizontal = 4.dp))
            Row(
                modifier = Modifier.fillMaxWidth().padding(4.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                CommandButtonProjection(
                    contentModel = Command(
                        text = "Uppercase",
                        action = { component.givenText = component.givenText.uppercase() }
                    )
                ).project(Modifier.fillMaxWidth(fraction = 1f / 3))
                CommandButtonProjection(
                    contentModel = Command(
                        text = "Lowercase",
                        action = { component.givenText = component.givenText.lowercase() }
                    )
                ).project(Modifier.fillMaxWidth(1f / 2))
                CommandButtonProjection(
                    contentModel = Command(
                        text = "Clear text area",
                        action = { component.givenText = "" }
                    )
                ).project(Modifier.fillMaxWidth())
            }
            AnimatedVisibility(visible = component.givenText.count() >= TextScreenComponent.characterLimit) {
                LabelProjection(
                    contentModel = LabelContentModel(
                        text = "To conserve memory usage, you cannot input more than ${"%,d".format(TextScreenComponent.characterLimit)} characters."
                    )
                ).project()
            }
        }
        OutputTextFieldRow(
            algorithm = component.algorithm,
            value = if (component.givenText.isNotEmpty()) component.hashGivenText() else "",
            isValueUppercase = component.hashedTextUppercase,
            onCaseClick = { component.hashedTextUppercase = !component.hashedTextUppercase }
        )
        ComparisonTextFieldRow(
            hashedOutput = "",
            comparisonHash = component.comparisonHash,
            onPasteClick = {
                component.comparisonHash = (clipboardManager.getText()?.text ?: "").filterNot { it.isWhitespace() }
            },
            onClearClick = { component.comparisonHash = "" },
            onTextFieldChange = { component.comparisonHash = it.filterNot { char -> char.isWhitespace() } }
        )
    }
}
