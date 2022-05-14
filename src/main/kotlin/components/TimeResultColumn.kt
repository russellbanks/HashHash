package components

import androidx.compose.runtime.Composable
import com.google.accompanist.flowlayout.FlowColumn
import helper.Time
import kotlinx.datetime.Instant
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.projection.LabelProjection
import java.text.SimpleDateFormat

@Composable
fun TimeResultColumn(
    instantBeforeHash: Instant?,
    instantAfterHash: Instant?
) {
    FlowColumn {
        LabelProjection(contentModel = LabelContentModel(
            text = "Started at: ${
                if (instantBeforeHash != null) {
                    SimpleDateFormat("dd MMMM yyyy, HH:mm:ss").format(instantBeforeHash.toEpochMilliseconds())
                } else "-"
            }")
        ).project()
        LabelProjection(contentModel = LabelContentModel(
            text = "Finished at: ${
                if (instantAfterHash != null) {
                    SimpleDateFormat("dd MMMM yyyy, HH:mm:ss").format(instantAfterHash.toEpochMilliseconds())
                } else "-"
            }")
        ).project()
        LabelProjection(contentModel = LabelContentModel(
            text = "Time taken: ${
                if (instantBeforeHash != null && instantAfterHash != null) {
                    Time.formatElapsedTime(instantAfterHash - instantBeforeHash)
                } else "-"
            }")
        ).project()
    }
}