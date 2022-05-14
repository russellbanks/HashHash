package components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import helper.Icons
import org.pushingpixels.aurora.component.model.*
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
            val hashesMatch = areTextFieldsBlank && hashedOutput.equals(comparisonHash, true)
            LabelProjection(
                contentModel = LabelContentModel(
                    text = "Hashes${if (!hashesMatch) " do not" else ""} match",
                    icon = if (hashesMatch) Icons.Utility.check() else Icons.Utility.cross()
                )
            ).project()
        }
    }
}