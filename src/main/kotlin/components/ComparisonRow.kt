package components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.TextFieldPresentationModel
import org.pushingpixels.aurora.component.model.TextFieldValueContentModel
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.TextFieldValueProjection

@Composable
fun ComparisonRow(
    givenHash: String,
    onComparisonValueChange: (textFieldValue: TextFieldValue) -> Unit,
    pasteAction: () -> Unit,
    clearAction: () -> Unit
) {
    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        TextFieldValueProjection(
            contentModel = TextFieldValueContentModel(
                value = TextFieldValue(annotatedString = AnnotatedString(text = givenHash)),
                placeholder = "Comparison Hash",
                onValueChange = { onComparisonValueChange(it) }
            ),
            presentationModel = TextFieldPresentationModel(showBorder = false)
        ).project(Modifier.fillMaxWidth(0.8f))
        CommandButtonProjection(
            contentModel = Command(
                text = "Paste",
                icon = painterResource(resourcePath = "paste.png"),
                action = { pasteAction() }
            )
        ).project(Modifier.weight(0.1f))
        CommandButtonProjection(
            contentModel = Command(
                text = "Clear",
                icon = painterResource(resourcePath = "eraser.png"),
                action = { clearAction() }
            )
        ).project(Modifier.weight(0.1f))
    }
}