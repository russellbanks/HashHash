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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.LabelProjection

@Composable
fun AboutScreen(
    onGoClicked: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Image(
            painter = painterResource(resourcePath = "hash.png"),
            contentDescription = "HashHash logo",
            modifier = Modifier.size(60.dp)
        )
        LabelProjection(
            contentModel = LabelContentModel(text = "HashHash"),
            presentationModel = LabelPresentationModel(
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        ).project()
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LabelProjection(
                contentModel = LabelContentModel(
                    text = "v1.0.0"
                )
            ).project()
            LabelProjection(
                contentModel = LabelContentModel(
                    text = "A Multiplatform GUI for Hashing, written in Compose for Desktop"
                )
            ).project()
        }
        CommandButtonProjection(
            contentModel = Command(
                text = "Attribution",
                action = { onGoClicked() }
            )
        ).project(Modifier.width(100.dp))
    }
}