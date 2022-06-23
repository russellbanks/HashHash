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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import application.DialogState
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandButtonPresentationModel
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.HorizontalSeparatorProjection

@Composable
fun CloseDialogFooter(dialogState: DialogState) {
    Column {
        HorizontalSeparatorProjection().project(Modifier.fillMaxWidth())
        CommandButtonProjection(
            contentModel = Command(
                text = "Close",
                action = dialogState.About()::close
            ),
            presentationModel = CommandButtonPresentationModel(
                textStyle = TextStyle(
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                ),
                horizontalGapScaleFactor = 1.8f,
                verticalGapScaleFactor = 1.5f
            )
        ).project(Modifier.width(150.dp).align(Alignment.End).padding(20.dp))
    }
}
