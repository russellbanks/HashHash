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
import flowlayout.FlowMainAxisAlignment
import flowlayout.FlowRow
import helper.FileUtils
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.component.projection.VerticalSeparatorProjection
import java.io.File

@Composable
fun FileInfoSection(
    modifier: Modifier = Modifier,
    file: File
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Image(
            painter = FileUtils.getFileIcon(file),
            contentDescription = null,
            modifier = Modifier.size(60.dp).align(Alignment.CenterVertically)
        )
        SelectionContainer {
            Box(modifier = Modifier.defaultMinSize(minHeight = 120.dp), contentAlignment = Alignment.Center) {
                FlowRow(mainAxisAlignment = FlowMainAxisAlignment.Center, mainAxisSpacing = 4.dp) {
                    LabelProjection(contentModel = LabelContentModel(
                        text = FileUtils.getFileName(file)),
                        presentationModel = LabelPresentationModel(textStyle = TextStyle(fontSize = 16.sp))
                    ).project()
                    VerticalSeparatorProjection().project(modifier = Modifier.height(20.dp))
                    LabelProjection(contentModel = LabelContentModel(
                        text = FileUtils.getFileType(file)),
                        presentationModel = LabelPresentationModel(textStyle = TextStyle(fontSize = 16.sp))
                    ).project()
                    if (FileUtils.getFileType(file) != FileUtils.getFileExtension(file)) {
                        VerticalSeparatorProjection().project(modifier = Modifier.height(20.dp))
                        LabelProjection(contentModel = LabelContentModel(
                            text = FileUtils.getFileExtension(file)),
                            presentationModel = LabelPresentationModel(textStyle = TextStyle(fontSize = 16.sp))
                        ).project()
                    }
                    VerticalSeparatorProjection().project(modifier = Modifier.height(20.dp))
                    LabelProjection(contentModel = LabelContentModel(
                        text = FileUtils.getFormattedBytes(file)),
                        presentationModel = LabelPresentationModel(textStyle = TextStyle(fontSize = 16.sp))
                    ).project()
                    VerticalSeparatorProjection().project(modifier = Modifier.height(20.dp))
                    LabelProjection(contentModel = LabelContentModel(
                        text = FileUtils.getFilePath(file)),
                        presentationModel = LabelPresentationModel(textStyle = TextStyle(fontSize = 16.sp))
                    ).project()
                }
            }
        }
    }
}