package components

import FileUtils
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.component.projection.VerticalSeparatorProjection
import org.pushingpixels.aurora.theming.auroraBackground
import java.io.File

@Composable
fun ProgressCard(
    hashedOutput: String,
    comparisonHash: String,
    hashTimer: String,
    timerVisible: Boolean,
    file: File
) {
    Row(
        modifier = Modifier.fillMaxWidth(0.8f).height(50.dp).clip(RoundedCornerShape(4.dp)).auroraBackground(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(if (hashedOutput.isNotBlank() && comparisonHash.isNotBlank()) 0.5f else 1f),
            contentAlignment = Alignment.Center
        ) {
            Crossfade(targetState = timerVisible) { isHashing ->
                if (isHashing) {
                    HashTimer(timerVisible = true, hashTimer = hashTimer)
                } else {
                    LabelProjection(
                        contentModel = LabelContentModel(
                            text = when {
                                hashedOutput.isNotBlank() -> "Done!"
                                file != FileUtils.emptyFile -> "No hash"
                                else -> "No file selected"
                            }
                        )
                    ).project(Modifier.padding(14.dp))
                }
            }
        }
        AnimatedVisibility(
            visible = hashedOutput.isNotBlank() && comparisonHash.isNotBlank(),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            VerticalSeparatorProjection().project(Modifier.fillMaxHeight())
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                LabelProjection(
                    contentModel = LabelContentModel(
                        text = if (hashedOutput.isNotBlank() && comparisonHash.isNotBlank() && hashedOutput.lowercase() == comparisonHash.lowercase()) {
                            "Hashes match"
                        } else  {
                            "Hashes do not match"
                        }
                    )
                ).project(Modifier.padding(14.dp))
                Image(
                    painter = painterResource(
                        resourcePath = if (hashedOutput.isNotBlank() && comparisonHash.isNotBlank() && hashedOutput.lowercase() == comparisonHash.lowercase()) "check.png" else "cross.png"
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(14.dp)
                )
            }
        }
    }
}