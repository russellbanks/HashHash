package components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.model.ProgressDeterminateContentModel
import org.pushingpixels.aurora.component.projection.DeterminateLinearProgressProjection
import org.pushingpixels.aurora.component.projection.LabelProjection

@Composable
fun HashProgress(fileHashProgress: Float) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        LabelProjection(
            contentModel = LabelContentModel(text = "Hash progress"),
            presentationModel = LabelPresentationModel(
                textStyle = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
            )
        ).project()
        DeterminateLinearProgressProjection(
            contentModel = ProgressDeterminateContentModel(
                progress = fileHashProgress
            )
        ).project(Modifier.fillMaxWidth())
    }
}