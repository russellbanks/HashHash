/**

HashHash
Copyright (C) 2023 Russell Banks

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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.russellbanks.HashHash.BuildConfig
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.projection.LabelProjection

@Composable
fun JVMInformationText() {
    SelectionContainer {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            LabelProjection(
                contentModel = LabelContentModel(
                    text = "${BuildConfig.appName} ${BuildConfig.appVersion}"
                ),
                presentationModel = LabelPresentationModel(
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            ).project()
            Column {
                LabelProjection(
                    contentModel = LabelContentModel(
                        text = "Runtime version: ${System.getProperty("java.runtime.version")}"
                    ),
                    presentationModel = LabelPresentationModel(
                        textStyle = TextStyle(fontSize = 12.sp)
                    )
                ).project()
                LabelProjection(
                    contentModel = LabelContentModel(
                        text = "VM: ${System.getProperty("java.vm.name")} by ${System.getProperty("java.vm.vendor")}"
                    ),
                    presentationModel = LabelPresentationModel(
                        textStyle = TextStyle(fontSize = 12.sp)
                    )
                ).project()
            }
        }
    }
}
