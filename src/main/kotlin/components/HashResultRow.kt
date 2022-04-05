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
import com.appmattus.crypto.Algorithm
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.TextFieldPresentationModel
import org.pushingpixels.aurora.component.model.TextFieldValueContentModel
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.TextFieldValueProjection

@Composable
fun HashResultRow(
    algorithm: Algorithm,
    hashedOutput: String,
    onClearClick: () -> Unit
) {
    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        TextFieldValueProjection(
            contentModel = TextFieldValueContentModel(
                value = TextFieldValue(annotatedString = AnnotatedString(text = hashedOutput.uppercase())),
                placeholder = "${algorithm.algorithmName} Hash",
                readOnly = true,
                onValueChange = {}
            ),
            presentationModel = TextFieldPresentationModel( showBorder = false)
        ).project(Modifier.fillMaxWidth(0.8f))
        CommandButtonProjection(
            contentModel = Command(
                text = "Copy",
                icon = painterResource(resourcePath = "copy.png"),
                action = {
                    if (hashedOutput.isNotBlank()) Clipboard.setContent(hashedOutput.uppercase())
                }
            )
        ).project(Modifier.weight(0.1f))
        CommandButtonProjection(
            contentModel = Command(
                text = "Clear",
                icon = painterResource(resourcePath = "eraser.png"),
                action = onClearClick
            )
        ).project(Modifier.weight(0.1f))
    }
}