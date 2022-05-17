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
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appmattus.crypto.Algorithm
import components.screens.file.ComparisonTextFieldRow
import components.screens.file.OutputTextFieldRow
import hash
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.model.TextFieldStringContentModel
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.component.projection.TextFieldStringProjection

@Composable
fun TextScreen(algorithm: Algorithm) {
    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        var comparisonHash by remember { mutableStateOf("") }
        var givenText by remember { mutableStateOf("") }
        var hashedText by remember { mutableStateOf("") }
        val clipboardManager = LocalClipboardManager.current
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
            val characterLimit = 20000
            TextFieldStringProjection(
                contentModel = TextFieldStringContentModel(
                    value = givenText,
                    onValueChange = {
                        givenText = if (it.count() < characterLimit) it else it.dropLast(it.count() - characterLimit)
                        hashedText = it.hash(algorithm)
                    }
                )
            ).project(Modifier.fillMaxWidth().height(200.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                CommandButtonProjection(
                    contentModel = Command(
                        text = "Uppercase",
                        action = { givenText = givenText.uppercase() }
                    ),
                ).project(Modifier.fillMaxWidth(1f / 3))
                CommandButtonProjection(
                    contentModel = Command(
                        text = "Lowercase",
                        action = { givenText = givenText.lowercase() }
                    ),
                ).project(Modifier.fillMaxWidth(1f / 2))
                CommandButtonProjection(
                    contentModel = Command(
                        text = "Clear text area",
                        action = { givenText = "" }
                    ),
                ).project(Modifier.fillMaxWidth())
            }
            AnimatedVisibility(visible = givenText.count() >= characterLimit) {
                LabelProjection(
                    contentModel = LabelContentModel(
                        text = "To conserve memory usage, you cannot input more than ${"%,d".format(characterLimit)} characters."
                    )
                ).project()
            }
        }
        OutputTextFieldRow(
            algorithm = algorithm,
            value = if (givenText.isNotEmpty()) givenText.hash(algorithm).uppercase() else "",
            onCaseClick = {
                hashedText = hashedText.run {
                    if (this == uppercase()) lowercase() else uppercase()
                }
            }
        )
        ComparisonTextFieldRow(
            hashedOutput = hashedText,
            comparisonHash = comparisonHash,
            onPasteClick = {
                comparisonHash = (clipboardManager.getText()?.text ?: "").filterNot { it.isWhitespace() }
            },
            onClearClick = { comparisonHash = "" },
            onTextFieldChange = { comparisonHash = it.filterNot { char -> char.isWhitespace() } }
        )
    }
}