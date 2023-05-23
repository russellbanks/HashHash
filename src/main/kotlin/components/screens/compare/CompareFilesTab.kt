/**

HashHash
Copyright (C) 2023 Russell Banks

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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.DragData
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.onExternalDrag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import helper.Icons
import java.io.File
import java.net.URI
import koin.getScreenModel
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandButtonPresentationModel
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.theming.AuroraSkin
import org.pushingpixels.aurora.theming.IconFilterStrategy

object CompareFilesTab : Tab {
    override val options: TabOptions
        @Composable
        get() = remember {
            TabOptions(
                index = 2u,
                title = "Compare files"
            )
        }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    override fun Content() {
        val compareFilesModel = getScreenModel<CompareFilesModel>()
        val backgroundColorScheme = AuroraSkin.colors.getBackgroundColorScheme(
            decorationAreaType = AuroraSkin.decorationAreaType
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(6.dp))
                .padding(start = 16.dp, top = 24.dp, end = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                compareFilesModel.FileComparisonColumn(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .onExternalDrag { externalDragValue ->
                            val dragData = externalDragValue.dragData
                            if (dragData is DragData.FilesList) {
                                compareFilesModel.setDroppedFile(
                                    fileComparison = CompareFilesModel.FileComparison.One,
                                    file = File(URI(dragData.readFiles().first()))
                                )
                            }
                        },
                    fileComparison = CompareFilesModel.FileComparison.One
                )
                compareFilesModel.FileComparisonColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .onExternalDrag { externalDragValue ->
                            val dragData = externalDragValue.dragData
                            if (dragData is DragData.FilesList) {
                                compareFilesModel.setDroppedFile(
                                    fileComparison = CompareFilesModel.FileComparison.Two,
                                    file = File(URI(dragData.readFiles().first()))
                                )
                            }
                        },
                    fileComparison = CompareFilesModel.FileComparison.Two
                )
            }
            CommandButtonProjection(
                contentModel = Command(
                    text = if (compareFilesModel.areJobsActive()) "Compare" else "Cancel",
                    icon = Icons.Utility.microChip(),
                    action = { compareFilesModel.onCalculateClicked() },
                    isActionEnabled = compareFilesModel.fileOne != null && compareFilesModel.fileTwo != null
                ),
                presentationModel = CommandButtonPresentationModel(
                    iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                    iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                    iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText
                )
            ).project(Modifier.align(Alignment.CenterHorizontally).width(100.dp).height(30.dp))
            AnimatedVisibility(modifier = Modifier.fillMaxWidth(), visible = compareFilesModel.areHashesNotBlank()) {
                val hashesMatch = compareFilesModel.doHashesMatch
                LabelProjection(
                    contentModel = LabelContentModel(
                        text = if (hashesMatch) "Files match" else "Files do not match",
                        icon = if (hashesMatch) Icons.Utility.check() else Icons.Utility.cross()
                    )
                ).project(Modifier.align(Alignment.CenterHorizontally))
            }
        }
        LabelProjection(
            contentModel = LabelContentModel(text = "Compare files"),
            presentationModel = LabelPresentationModel(textStyle = TextStyle(fontSize = 18.sp))
        ).project(
            Modifier.padding(horizontal = 32.dp, vertical = 3.dp).background(backgroundColorScheme.backgroundFillColor)
        )
    }
}
