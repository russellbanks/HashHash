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

package components.dialogs.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import application.ApplicationWindowState
import application.DialogState
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandButtonPresentationModel
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.HorizontalSeparatorProjection

@Composable
fun RestartWindowFooter(dialogState: DialogState, window: ApplicationWindowState) {
    Column {
        HorizontalSeparatorProjection().project(Modifier.fillMaxWidth())
        Row(Modifier.padding(20.dp)) {
            Box(Modifier.weight(1f)) {
                androidx.compose.animation.AnimatedVisibility(visible = window.needsRestarting) {
                    CommandButtonProjection(
                        contentModel = Command(
                            text = "Restart",
                            action = {
                                window.openNewWindow()
                                window.close()
                            }
                        ),
                        presentationModel = CommandButtonPresentationModel(
                            textStyle = TextStyle(
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center
                            ),
                            horizontalGapScaleFactor = 1.8f,
                            verticalGapScaleFactor = 1.5f
                        )
                    ).project(Modifier.width(110.dp))
                }
            }
            CommandButtonProjection(
                contentModel = Command(
                    text = "Close",
                    action = dialogState.Settings()::close
                ),
                presentationModel = CommandButtonPresentationModel(
                    textStyle = TextStyle(
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    ),
                    horizontalGapScaleFactor = 1.8f,
                    verticalGapScaleFactor = 1.5f
                )
            ).project(Modifier.width(110.dp))
        }
    }
}
