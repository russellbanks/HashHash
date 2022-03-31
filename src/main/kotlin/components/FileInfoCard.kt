package components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.pushingpixels.aurora.component.projection.VerticalSeparatorProjection
import org.pushingpixels.aurora.theming.auroraBackground
import java.io.File

@Composable
fun FileInfoCard(file: File) {
    Row(modifier = Modifier.fillMaxWidth(0.8f).clip(RoundedCornerShape(4.dp)).auroraBackground().padding(14.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Image(painter = painterResource(resourcePath = FileUtils.getFileIcon(file)), contentDescription = null)
        Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
            SelectionContainer { Text(text = FileUtils.getFileName(file), fontSize = 20.sp) }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                SelectionContainer { Text(text = FileUtils.getFileType(file)) }
                VerticalSeparatorProjection().project(modifier = Modifier.height(20.dp))
                SelectionContainer { Text(text = FileUtils.getFormattedBytes(file)) }
            }
        }
    }
}
