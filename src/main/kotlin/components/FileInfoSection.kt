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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import helper.FileUtils
import helper.Icons
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.component.projection.VerticalSeparatorProjection
import java.io.File

@Composable
fun FileInfoSection(file: File?) {
    SelectionContainer {
        Row(
            modifier = Modifier.defaultMinSize(minHeight = 120.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = Icons.FileTypes.getFileIcon(file),
                contentDescription = "${(file?.extension ?: "file")
                    .lowercase().replaceFirstChar { it.titlecase() }} icon",
                modifier = Modifier.size(80.dp).padding(start = 20.dp)
            )
            FlowRow(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                mainAxisAlignment = FlowMainAxisAlignment.SpaceEvenly
            ) {
                LabelProjection(
                    contentModel = LabelContentModel(text = file?.name ?: "File name"),
                    presentationModel = LabelPresentationModel(textStyle = TextStyle(fontSize = 16.sp))
                ).project()
                if ((file?.extension ?: "Extension").isNotBlank()) {
                    VerticalSeparatorProjection().project(modifier = Modifier.height(20.dp))
                    LabelProjection(
                        contentModel = LabelContentModel(text = FileUtils.getFileType(file)),
                        presentationModel = LabelPresentationModel(textStyle = TextStyle(fontSize = 16.sp))
                    ).project()
                }
                if (FileUtils.getFileType(file) != (file?.extension ?: "Extension")) {
                    VerticalSeparatorProjection().project(modifier = Modifier.height(20.dp))
                    LabelProjection(
                        contentModel = LabelContentModel(text = file?.extension ?: "Extension"),
                        presentationModel = LabelPresentationModel(textStyle = TextStyle(fontSize = 16.sp))
                    ).project()
                }
                VerticalSeparatorProjection().project(modifier = Modifier.height(20.dp))
                LabelProjection(
                    contentModel = LabelContentModel(
                        text = if (file != null) FileUtils.getFormattedBytes(file.length()) else "Size"
                    ),
                    presentationModel = LabelPresentationModel(textStyle = TextStyle(fontSize = 16.sp))
                ).project()
                VerticalSeparatorProjection().project(modifier = Modifier.height(20.dp))
                LabelProjection(
                    contentModel = LabelContentModel(text = file?.absolutePath ?: "Path"),
                    presentationModel = LabelPresentationModel(textStyle = TextStyle(fontSize = 16.sp))
                ).project()
            }
        }
    }
}
