package components

import FileUtils
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.component.projection.VerticalSeparatorProjection
import org.pushingpixels.aurora.theming.auroraBackground
import java.io.File

@Composable
fun FileInfoCard(file: File) {
    Row(modifier = Modifier.fillMaxWidth(0.8f).clip(RoundedCornerShape(4.dp)).auroraBackground().padding(14.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Image(painter = painterResource(resourcePath = FileUtils.getFileIcon(file)), contentDescription = null)
        Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
            SelectionContainer {
                LabelProjection(
                    contentModel = LabelContentModel(
                        text = FileUtils.getFileName(file)
                    )
                ).project()
            }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                SelectionContainer {
                    LabelProjection(
                        contentModel = LabelContentModel(
                            text = FileUtils.getFileType(file)
                        )
                    ).project()
                }
                VerticalSeparatorProjection().project(modifier = Modifier.height(20.dp))
                SelectionContainer {
                    LabelProjection(
                        contentModel = LabelContentModel(
                            text = FileUtils.getFormattedBytes(file)
                        )
                    ).project()
                }
            }
        }
    }
}
