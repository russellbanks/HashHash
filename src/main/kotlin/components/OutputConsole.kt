package components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appmattus.crypto.Algorithm
import org.pushingpixels.aurora.component.AuroraVerticalScrollbar
import org.pushingpixels.aurora.component.projection.HorizontalSeparatorProjection
import org.pushingpixels.aurora.theming.auroraBackground
import java.io.File
import java.text.SimpleDateFormat

@Composable
fun OutputConsole(
    timeBeforeHashVisibility: Boolean,
    timeAfterHashVisibility: Boolean,
    timeBeforeHash: String,
    timeAfterHash: String,
    timeTaken: String,
    hashedOutput: String,
    algorithm: Algorithm,
    file: File
) {
    val fontSize = 13.sp
    val fileInfo = listOf(
        "File path and name: ${file.absolutePath}",
        "Name: ${file.name}",
        "Type: ${file.extension}",
        "Size: ${FileUtils.getFormattedBytes(file.length())}",
        "Last modified: ${SimpleDateFormat("dd MMMM yyyy, HH:mm:ss").format(file.lastModified())}",
        "${algorithm.algorithmName}: ${if (hashedOutput == "") "Not yet calculated" else hashedOutput}"
    )
    val timeList = listOf(timeBeforeHash, timeAfterHash, timeTaken)
    Column(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(4.dp)).auroraBackground().padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Console output")
        SelectionContainer {
            Box(Modifier.fillMaxSize()) {
                val lazyListState = rememberLazyListState()
                LazyColumn (
                    modifier = Modifier.fillMaxSize().border(1.dp, Color.Gray, RoundedCornerShape(4.dp)).padding(horizontal = 10.dp),
                    state = lazyListState,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    item { Spacer(Modifier.size(4.dp)) }
                    items(fileInfo) {
                        androidx.compose.animation.AnimatedVisibility(visible = file != FileUtils.emptyFile, enter = fadeIn(), exit = fadeOut()) {
                            Text(text = it, fontSize = fontSize)
                        }
                    }
                    item {
                        androidx.compose.animation.AnimatedVisibility(visible = timeBeforeHashVisibility, enter = fadeIn(), exit = fadeOut()) {
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                HorizontalSeparatorProjection().project(Modifier.fillMaxWidth().padding(vertical = 4.dp))
                            }
                        }
                    }
                    items(timeList) {
                        androidx.compose.animation.AnimatedVisibility(visible = timeAfterHashVisibility, enter = fadeIn(), exit = fadeOut()) {
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text(text = it, fontSize = fontSize)
                            }
                        }
                    }
                    item { Spacer(Modifier.size(4.dp)) }
                }
                AuroraVerticalScrollbar(
                    modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight().padding(2.dp),
                    adapter = rememberScrollbarAdapter(scrollState = lazyListState)
                )
            }
        }
    }
}