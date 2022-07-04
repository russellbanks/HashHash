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

package components.screens.file

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import components.ComparisonTextFieldRow
import components.ElapsedTimeResults
import components.HashProgress
import components.OutputTextFieldRow
import helper.FileUtils
import helper.Icons
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandButtonPresentationModel
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.HorizontalSeparatorProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.theming.AuroraSkin
import org.pushingpixels.aurora.theming.IconFilterStrategy
import java.nio.file.Files

@Composable
fun FileScreen(component: FileScreenComponent) {
    val backgroundColorScheme = AuroraSkin.colors.getBackgroundColorScheme(
        decorationAreaType = AuroraSkin.decorationAreaType
    )
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .padding(16.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(6.dp))
            .padding(start = 16.dp, top = 24.dp, end = 16.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            CommandButtonProjection(
                contentModel = Command(
                    text = "Select file",
                    icon = Icons.Utility.folderOpen(),
                    action = component::selectFile
                ),
                presentationModel = CommandButtonPresentationModel(
                    iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                    iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText,
                    textStyle = TextStyle(textAlign = TextAlign.Center)
                )
            ).project()
            LabelProjection(contentModel = LabelContentModel(text = "or drag and drop a file"),).project()
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Icons.SystemIcon(modifier = Modifier.size(60.dp), file = component.file)
            SelectionContainer {
                Column {
                    LabelProjection(
                        contentModel = LabelContentModel(text = "Type: ${if (component.file != null)
                            Files.probeContentType(component.file!!.toPath()) else ""
                        }")
                    ).project()
                    LabelProjection(
                        contentModel = LabelContentModel(text = "Extension: ${component.file?.extension ?: ""}")
                    ).project()
                    LabelProjection(
                        contentModel = LabelContentModel(text = "Size: ${if (component.file != null)
                            FileUtils.getFormattedBytes(component.file!!.length()) else ""
                        }")
                    ).project()
                    LabelProjection(
                        contentModel = LabelContentModel(text = "Path: ${component.file?.absolutePath ?: ""}")
                    ).project()
                }
            }
        }
        CommandButtonProjection(
            contentModel = Command(
                text = if (component.fileHashJob?.isActive == true) "Cancel" else "Hash",
                icon = Icons.Utility.microChip(),
                action = { component.onCalculateClicked(scope) },
                isActionEnabled = component.file != null
            ),
            presentationModel = CommandButtonPresentationModel(
                iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText,
                textStyle = TextStyle(textAlign = TextAlign.Center)
            )
        ).project()
        HorizontalSeparatorProjection().project(Modifier.fillMaxWidth())
        val clipboardManager = LocalClipboardManager.current
        OutputTextFieldRow(
            algorithm = component.algorithm,
            value = component.resultMap.getOrDefault(component.algorithm, ""),
            isValueUppercase = component.hashedTextUppercase,
            snackbarHostState = component.snackbarHostState,
            onCaseClick = component::switchHashCase
        )
        HorizontalSeparatorProjection().project(Modifier.fillMaxWidth())
        ComparisonTextFieldRow(
            hashedOutput = component.resultMap.getOrDefault(component.algorithm, ""),
            comparisonHash = component.comparisonHash,
            onPasteClick = {
                component.comparisonHash = (clipboardManager.getText()?.text ?: "").filterNot { it.isWhitespace() }
            },
            onClearClick = { component.comparisonHash = "" },
            onTextFieldChange = { component.comparisonHash = it.filterNot { char -> char.isWhitespace() } }
        )
        HorizontalSeparatorProjection().project(Modifier.fillMaxWidth())
        HashProgress(fileHashProgress = component.hashProgress, timer = component.timer)
        HorizontalSeparatorProjection().project(Modifier.fillMaxWidth())
        ElapsedTimeResults(
            instantBeforeHash = component.instantBeforeHash,
            instantAfterHash = component.instantAfterHash
        )
    }
    LabelProjection(
        contentModel = LabelContentModel(text = "Single file hashing"),
        presentationModel = LabelPresentationModel(textStyle = TextStyle(fontSize = 18.sp))
    ).project(
        Modifier.padding(horizontal = 32.dp, vertical = 3.dp).background(backgroundColorScheme.backgroundFillColor)
    )
}
