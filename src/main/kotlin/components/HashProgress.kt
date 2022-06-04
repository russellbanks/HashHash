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

package components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.model.ProgressDeterminateContentModel
import org.pushingpixels.aurora.component.projection.DeterminateLinearProgressProjection
import org.pushingpixels.aurora.component.projection.LabelProjection

@Composable
fun HashProgress(fileHashProgress: Float) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        LabelProjection(
            contentModel = LabelContentModel(text = "Hash progress"),
            presentationModel = LabelPresentationModel(
                textStyle = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
            )
        ).project()
        DeterminateLinearProgressProjection(
            contentModel = ProgressDeterminateContentModel(
                progress = fileHashProgress
            )
        ).project(Modifier.fillMaxWidth())
    }
}
