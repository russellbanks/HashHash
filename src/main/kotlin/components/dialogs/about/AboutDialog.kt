/**

HashHash
Copyright (C) 2022  Russell Banks

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
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import api.Ktor
import application.DialogState
import com.russellbanks.HashHash.BuildConfig
import helper.Icons
import kotlinx.coroutines.Dispatchers
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandButtonPresentationModel
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.theming.AuroraSkin

@Composable
fun AboutDialog(dialogState: DialogState, ktor: Ktor) {
    val backgroundColorScheme = AuroraSkin.colors.getBackgroundColorScheme(
        decorationAreaType = AuroraSkin.decorationAreaType
    )
    val scope = rememberCoroutineScope { Dispatchers.Default }
    AnimatedVisibility(
        visible = dialogState.About().isOpen(),
        enter = fadeIn() + slideInVertically(initialOffsetY = { -it / 10 }),
        exit = fadeOut() + slideOutVertically(targetOffsetY = { -it / 10 })
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Surface(
                modifier = Modifier.width(450.dp),
                shape = RoundedCornerShape(8.dp),
                color = backgroundColorScheme.backgroundFillColor,
                border = BorderStroke(1.dp, Color.Black),
                elevation = 4.dp
            ) {
                Column {
                    Row(modifier = Modifier.weight(weight = 1f, fill = false).padding(30.dp)) {
                        Box(Modifier.padding(end = 30.dp)) {
                            Image(
                                painter = Icons.logo(),
                                contentDescription = "${BuildConfig.appName} logo",
                                modifier = Modifier.size(60.dp)
                            )
                        }
                        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                            JVMInformationText()
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                CommandButtonProjection(
                                    contentModel = Command(
                                        text = "Check for Updates",
                                        action = { ktor.checkForHashHashUpdate(scope) }
                                    ),
                                    presentationModel = CommandButtonPresentationModel(
                                        textStyle = TextStyle(fontSize = 12.sp, textAlign = TextAlign.Center)
                                    )
                                ).project()
                                AnimatedVisibility(ktor.checkingGitHubAPI || ktor.httpResponse != null) {
                                    UpdateCheckText(ktor = ktor)
                                }
                            }
                        }
                    }
                    CloseDialogFooter(dialogState = dialogState)
                }
            }
        }
    }
}
