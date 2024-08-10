/**

HashHash
Copyright (C) 2024 Russell Banks

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.

 */

package components.dialogs.about

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import api.GitHubImpl
import helper.Icons
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.theming.AuroraSkin
import java.text.SimpleDateFormat

@Composable
fun UpdateCheckText() {
    val backgroundColorScheme = AuroraSkin.colors.getBackgroundColorScheme(
        decorationAreaType = AuroraSkin.decorationAreaType
    )
    val infiniteTransition = rememberInfiniteTransition()
    val rotationAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing)
        )
    )
    SelectionContainer {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (GitHubImpl.checkingGitHubAPI) {
                    Image(
                        painter = Icons.Utility.refresh(),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp).graphicsLayer { rotationZ = rotationAngle },
                        colorFilter = ColorFilter.tint(backgroundColorScheme.foregroundColor)
                    )
                }
                LabelProjection(
                    contentModel = LabelContentModel(text = GitHubImpl.updateResponseText),
                    presentationModel = LabelPresentationModel(textStyle = TextStyle(fontSize = 12.sp))
                ).project()
            }
            AnimatedVisibility(GitHubImpl.lastChecked != null) {
                LabelProjection(
                    contentModel = LabelContentModel(
                        text = "Last checked: " +
                            SimpleDateFormat("dd MMMM yyyy, HH:mm:ss")
                                .format(GitHubImpl.lastChecked?.toEpochMilliseconds())
                    ),
                    presentationModel = LabelPresentationModel(
                        textStyle = TextStyle(fontSize = 12.sp)
                    )
                ).project()
            }
        }
    }
}
