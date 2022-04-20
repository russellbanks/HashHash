package components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.projection.IndeterminateLinearProgressProjection
import org.pushingpixels.aurora.component.projection.LabelProjection

@Composable
fun HashTimer(timerVisible: Boolean, hashTimer: String) {
    AnimatedVisibility(visible = timerVisible, enter = fadeIn(), exit = fadeOut()) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            SelectionContainer {
                LabelProjection(
                    contentModel = LabelContentModel(
                        text = hashTimer
                    )
                ).project()
            }
            IndeterminateLinearProgressProjection().project(Modifier.fillMaxWidth())
        }
    }
}