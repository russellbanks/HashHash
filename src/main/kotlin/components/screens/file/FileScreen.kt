package components.screens.file

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.unit.dp
import com.appmattus.crypto.Algorithm
import components.ComparisonTextFieldRow
import components.FileInfoSection
import components.OutputTextFieldRow
import components.TimeResultColumn
import kotlinx.datetime.Instant
import org.pushingpixels.aurora.component.projection.HorizontalSeparatorProjection
import java.io.File

@Composable
fun FileScreen(
    file: File?,
    algorithm: Algorithm,
    hashedOutput: String,
    instantBeforeHash: Instant?,
    instantAfterHash: Instant?,
    onCaseClick: () -> Unit
) {
    var comparisonHash by remember { mutableStateOf("") }
    Column(Modifier.fillMaxSize()) {
        FileInfoSection(file)
        HorizontalSeparatorProjection().project(Modifier.fillMaxWidth())
        Column(
            modifier = Modifier.weight(1f).padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val clipboardManager = LocalClipboardManager.current
            OutputTextFieldRow(
                algorithm = algorithm,
                hashedOutput = hashedOutput,
                onCaseClick = onCaseClick
            )
            ComparisonTextFieldRow(
                hashedOutput = hashedOutput,
                comparisonHash = comparisonHash,
                onPasteClick = {
                    comparisonHash = (clipboardManager.getText()?.text ?: "")
                        .filterNot { it.isWhitespace() }
                },
                onClearClick = { comparisonHash = "" },
                onTextFieldChange = { comparisonHash = it.filterNot { char -> char.isWhitespace() } }
            )
            TimeResultColumn(instantBeforeHash, instantAfterHash)
        }
    }
}