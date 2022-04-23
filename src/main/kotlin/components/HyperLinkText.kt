package components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.projection.LabelProjection

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HyperLinkText(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    var textDecoration by remember { mutableStateOf(TextDecoration.None) }
    LabelProjection(
        contentModel = LabelContentModel(
            text = text
        ),
        presentationModel = LabelPresentationModel(
            textStyle = TextStyle(
                color = Color(0xff2e81de),
                textDecoration = textDecoration
            )
        )
    ).project(modifier
        .clickable(
            interactionSource = MutableInteractionSource(),
            indication = null,
            onClick = onClick
        )
        .onPointerEvent(PointerEventType.Enter) { textDecoration = TextDecoration.Underline }
        .onPointerEvent(PointerEventType.Exit) { textDecoration = TextDecoration.None }
    )
}