package components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.ProgressDeterminateContentModel
import org.pushingpixels.aurora.component.projection.DeterminateLinearProgressProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.theming.DecorationAreaType
import org.pushingpixels.aurora.theming.auroraBackground
import org.pushingpixels.aurora.window.AuroraDecorationArea
import java.io.File

@Composable
fun Footer(
    error: String?,
    hashedOutput: String,
    job: Job?,
    hashProgress: Float,
    file: File?
) {
    AuroraDecorationArea(decorationAreaType = DecorationAreaType.Footer) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .auroraBackground()
                .padding(horizontal = 8.dp, vertical = 6.dp)
        ) {
            Crossfade(targetState = job?.isActive ?: false) {
                if (it) {
                    DeterminateLinearProgressProjection(
                        contentModel = ProgressDeterminateContentModel(
                            progress = hashProgress
                        )
                    ).project(Modifier.fillMaxWidth().padding(4.dp))
                } else {
                    LabelProjection(
                        contentModel = LabelContentModel(
                            text = when {
                                error != null -> error
                                hashedOutput.isNotBlank() -> "Done!"
                                file != null -> "No hash"
                                else -> "No file selected"
                            }
                        )
                    ).project()
                }
            }
        }
    }
}