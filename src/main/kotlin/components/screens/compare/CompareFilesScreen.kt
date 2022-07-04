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

package components.screens.compare

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import components.HashProgress
import components.OutputTextFieldRow
import components.controlpane.FileSelectButton
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
fun CompareFilesScreen(component: CompareFilesComponent) {
    val backgroundColorScheme = AuroraSkin.colors.getBackgroundColorScheme(
        decorationAreaType = AuroraSkin.decorationAreaType
    )
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(6.dp))
            .padding(start = 16.dp, top = 24.dp, end = 16.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Column(modifier = Modifier.fillMaxWidth(0.5f), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                CommandButtonProjection(
                    contentModel = Command(
                        text = "Select file",
                        icon = Icons.Utility.folderOpen(),
                        action = { component.selectFile(FileSelectButton.One) }
                    ),
                    presentationModel = CommandButtonPresentationModel(
                        iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                        iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText,
                        textStyle = TextStyle(textAlign = TextAlign.Center)
                    )
                ).project()
                SelectionContainer {
                    Column {
                        Icons.SystemIcon(modifier = Modifier.size(60.dp), file = component.fileOne)
                        Spacer(Modifier.height(10.dp))
                        LabelProjection(
                            contentModel = LabelContentModel(text = "Type: ${if (component.fileOne != null)
                                Files.probeContentType(component.fileOne!!.toPath()) else ""
                            }")
                        ).project()
                        LabelProjection(
                            contentModel = LabelContentModel(text = "Extension: ${component.fileOne?.extension ?: ""}")
                        ).project()
                        LabelProjection(
                            contentModel = LabelContentModel(text = "Size: ${if (component.fileOne != null)
                                FileUtils.getFormattedBytes(component.fileOne!!.length()) else ""
                            }")
                        ).project()
                        LabelProjection(
                            contentModel = LabelContentModel(text = "Path: ${component.fileOne?.absolutePath ?: ""}")
                        ).project()
                    }
                }
                HorizontalSeparatorProjection().project(Modifier.fillMaxWidth())
                OutputTextFieldRow(
                    algorithm = component.algorithm,
                    value = component.fileOneResultMap.getOrDefault(component.algorithm, ""),
                    isValueUppercase = component.fileOneHashUppercase,
                    snackbarHostState = component.snackbarHostState,
                    onCaseClick = { component.switchHashCase(CompareFilesComponent.FileComparison.One) }
                )
                HorizontalSeparatorProjection().project(Modifier.fillMaxWidth())
                HashProgress(fileHashProgress = component.fileOneHashProgress, timer = component.fileOneTimer)
            }
            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                CommandButtonProjection(
                    contentModel = Command(
                        text = "Select file",
                        icon = Icons.Utility.folderOpen(),
                        action = { component.selectFile(FileSelectButton.Two) }
                    ),
                    presentationModel = CommandButtonPresentationModel(
                        iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                        iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText,
                        textStyle = TextStyle(textAlign = TextAlign.Center)
                    )
                ).project()
                SelectionContainer {
                    Column {
                        Icons.SystemIcon(modifier = Modifier.size(60.dp), file = component.fileTwo)
                        Spacer(Modifier.height(10.dp))
                        LabelProjection(
                            contentModel = LabelContentModel(text = "Type: ${if (component.fileTwo != null)
                                Files.probeContentType(component.fileTwo!!.toPath()) else ""
                            }")
                        ).project()
                        LabelProjection(
                            contentModel = LabelContentModel(text = "Extension: ${component.fileTwo?.extension ?: ""}")
                        ).project()
                        LabelProjection(
                            contentModel = LabelContentModel(text = "Size: ${if (component.fileTwo != null)
                                FileUtils.getFormattedBytes(component.fileTwo!!.length()) else ""
                            }")
                        ).project()
                        LabelProjection(
                            contentModel = LabelContentModel(text = "Path: ${component.fileTwo?.absolutePath ?: ""}")
                        ).project()
                    }
                }
                HorizontalSeparatorProjection().project(Modifier.fillMaxWidth())
                OutputTextFieldRow(
                    algorithm = component.algorithm,
                    value = component.fileTwoResultMap.getOrDefault(component.algorithm, ""),
                    isValueUppercase = component.fileTwoHashUppercase,
                    snackbarHostState = component.snackbarHostState,
                    onCaseClick = { component.switchHashCase(CompareFilesComponent.FileComparison.Two) }
                )
                HorizontalSeparatorProjection().project(Modifier.fillMaxWidth())
                HashProgress(fileHashProgress = component.fileTwoHashProgress, timer = component.fileTwoTimer)
            }
        }
        CommandButtonProjection(
            contentModel = Command(
                text = component.getActionButtonText(),
                icon = Icons.Utility.microChip(),
                action = { component.onCalculateClicked(scope) },
                isActionEnabled = component.isActionButtonEnabled()
            ),
            presentationModel = CommandButtonPresentationModel(
                iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText
            )
        ).project(Modifier.align(Alignment.CenterHorizontally).width(100.dp).height(30.dp))
    }
    LabelProjection(
        contentModel = LabelContentModel(text = "Compare files"),
        presentationModel = LabelPresentationModel(textStyle = TextStyle(fontSize = 18.sp))
    ).project(
        Modifier.padding(horizontal = 32.dp, vertical = 3.dp).background(backgroundColorScheme.backgroundFillColor)
    )
}
