package components

import androidx.compose.runtime.Composable
import com.google.accompanist.flowlayout.FlowColumn
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.projection.LabelProjection

@Composable
fun TimeResultColumn(
    timeBeforeHash: String?,
    timeAfterHash: String?,
    timeTaken: String
) {
    FlowColumn {
        LabelProjection(contentModel = LabelContentModel(
            text = "Started at: ${timeBeforeHash ?: "-"}")
        ).project()
        LabelProjection(contentModel = LabelContentModel(
            text = "Finished at: ${timeAfterHash ?: "-"}")
        ).project()
        LabelProjection(contentModel = LabelContentModel(
            text = "Time taken: ${if (timeAfterHash != null) timeTaken else "-"}")
        ).project()
    }
}