package components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.pushingpixels.aurora.component.projection.IndeterminateLinearProgressProjection

@Composable
fun HashTimer(timerVisible: Boolean, hashTimer: String) {
    AnimatedVisibility(visible = timerVisible, enter = fadeIn(), exit = fadeOut()) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = hashTimer, fontSize = 20.sp)
            IndeterminateLinearProgressProjection().project(Modifier.fillMaxWidth())
        }
    }
}