package components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandButtonPresentationModel
import org.pushingpixels.aurora.component.model.CommandButtonPresentationState
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import java.io.File

@Composable
fun CalculateButton(
    modifier: Modifier,
    file: File,
    isHashing: Boolean,
    timerCall: (time: Pair<String, String>) -> Unit,
    hashCall: (job: Job) -> Unit
) {
    val scope = rememberCoroutineScope()
    CommandButtonProjection(
        contentModel = Command(
            text = "Calculate",
            action = {
                if (file.exists() && file != FileUtils.emptyFile && !isHashing) {
                    val job = scope.launch {
                        flow {
                            System.currentTimeMillis().also { millisAtStart ->
                                while (currentCoroutineContext().isActive) {
                                    delay(1000)
                                    emit(System.currentTimeMillis() - millisAtStart)
                                }
                            }
                        }.collect { milliseconds ->
                            val minutes = "%02d".format((milliseconds / 1000) / 60)
                            val seconds = "%02d".format((milliseconds / 1000) % 60)
                            timerCall(Pair(minutes, seconds))
                        }
                    }
                    scope.launch(Dispatchers.IO) {
                        hashCall(job)
                    }
                }
            }
        ),
        presentationModel = CommandButtonPresentationModel(
            presentationState = CommandButtonPresentationState.Medium
        )
    ).project(modifier)
}