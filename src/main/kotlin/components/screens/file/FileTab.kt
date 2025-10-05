/**

HashHash
Copyright (C) 2024 Russell Banks

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

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.awtTransferable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import components.ComparisonTextFieldRow
import components.ElapsedTimeResults
import components.HashProgress
import components.OutputTextFieldRow
import components.screens.ParentComponent
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.projection.HorizontalSeparatorProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.theming.AuroraSkin
import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor
import java.io.File
import kotlin.time.ExperimentalTime

object FileTab : Tab {
    private fun readResolve(): Any = FileTab

    override val options: TabOptions
        @Composable
        get() = remember {
            TabOptions(
                index = 0u,
                title = "File",
            )
        }

    @OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class, ExperimentalTime::class)
    @Composable
    override fun Content() {
        val backgroundColorScheme = AuroraSkin.colors.getBackgroundColorScheme(
            decorationAreaType = AuroraSkin.decorationAreaType
        )
        Column(
            modifier = Modifier
                .padding(16.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(6.dp))
                .padding(start = 16.dp, top = 24.dp, end = 16.dp, bottom = 16.dp)
                .dragAndDropTarget(
                    shouldStartDragAndDrop = { event ->
                        event.awtTransferable.isDataFlavorSupported(DataFlavor.javaFileListFlavor)
                    },
                    target = object : DragAndDropTarget {
                        override fun onDrop(event: DragAndDropEvent): Boolean {
                            val files = event.awtTransferable.getTransferData(DataFlavor.javaFileListFlavor) as List<File>
                            FileScreenModel.setComponentFile(files.first())
                            return true
                        }
                    }
                ),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            SelectFileRow(FileScreenModel::selectFile)
            FileInfoRow(FileScreenModel.file)
            HashButton(FileScreenModel.file, FileScreenModel.fileHashJob) { FileScreenModel.onCalculateClicked() }
            HorizontalSeparatorProjection().project(Modifier.fillMaxWidth())
            OutputTextFieldRow(
                value = FileScreenModel.resultMap.getOrDefault(ParentComponent.algorithm, ""),
                isValueUppercase = FileScreenModel.hashedTextUppercase,
                onCaseClick = FileScreenModel::switchHashCase
            )
            HorizontalSeparatorProjection().project(Modifier.fillMaxWidth())
            ComparisonTextFieldRow(
                hashedOutput = FileScreenModel.resultMap.getOrDefault(ParentComponent.algorithm, ""),
                comparisonHash = FileScreenModel.comparisonHash,
                onPasteClick = {
                    val clipboard = Toolkit.getDefaultToolkit().systemClipboard
                    val pasteString = clipboard.getData(DataFlavor.stringFlavor) as String
                    FileScreenModel.comparisonHash = pasteString.filterNot(Char::isWhitespace)
                },
                onClearClick = { FileScreenModel.comparisonHash = "" },
                onTextFieldChange = { FileScreenModel.comparisonHash = it.filterNot(Char::isWhitespace) }
            )
            HorizontalSeparatorProjection().project(Modifier.fillMaxWidth())
            HashProgress(fileHashProgress = FileScreenModel.hashProgress, timer = FileScreenModel.timer)
            HorizontalSeparatorProjection().project(Modifier.fillMaxWidth())
            ElapsedTimeResults(
                instantBeforeHash = FileScreenModel.instantBeforeHash,
                instantAfterHash = FileScreenModel.instantAfterHash
            )
        }
        LabelProjection(
            contentModel = LabelContentModel(text = "Single file hashing"),
            presentationModel = LabelPresentationModel(textStyle = TextStyle(fontSize = 18.sp))
        ).project(
            Modifier.padding(horizontal = 32.dp, vertical = 3.dp).background(backgroundColorScheme.backgroundFillColor)
        )
    }
}
