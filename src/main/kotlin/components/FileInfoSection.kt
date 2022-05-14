package components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
                contentDescription = "${(file?.extension ?: "file").lowercase().replaceFirstChar { it.titlecase() }} icon",
                modifier = Modifier.size(80.dp).padding(start = 20.dp)
            )
            FlowRow(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                mainAxisAlignment = FlowMainAxisAlignment.SpaceEvenly
            ) {
                LabelProjection(
                    contentModel = LabelContentModel(text = FileUtils.getFileName(file)),
                    presentationModel = LabelPresentationModel(textStyle = TextStyle(fontSize = 16.sp))
                ).project()
                if (FileUtils.getFileExtension(file).isNotBlank()) {
                    VerticalSeparatorProjection().project(modifier = Modifier.height(20.dp))
                    LabelProjection(
                        contentModel = LabelContentModel(text = FileUtils.getFileType(file)),
                        presentationModel = LabelPresentationModel(textStyle = TextStyle(fontSize = 16.sp))
                    ).project()
                }
                if (FileUtils.getFileType(file) != FileUtils.getFileExtension(file)) {
                    VerticalSeparatorProjection().project(modifier = Modifier.height(20.dp))
                    LabelProjection(
                        contentModel = LabelContentModel(text = FileUtils.getFileExtension(file)),
                        presentationModel = LabelPresentationModel(textStyle = TextStyle(fontSize = 16.sp))
                    ).project()
                }
                VerticalSeparatorProjection().project(modifier = Modifier.height(20.dp))
                LabelProjection(
                    contentModel = LabelContentModel(text = FileUtils.getFormattedBytes(file)),
                    presentationModel = LabelPresentationModel(textStyle = TextStyle(fontSize = 16.sp))
                ).project()
                VerticalSeparatorProjection().project(modifier = Modifier.height(20.dp))
                LabelProjection(
                    contentModel = LabelContentModel(text = FileUtils.getFilePath(file)),
                    presentationModel = LabelPresentationModel(textStyle = TextStyle(fontSize = 16.sp))
                ).project()
            }
        }
    }
}