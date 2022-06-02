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

package components.controlpane

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import components.Root
import components.screens.comparefiles.CompareFilesComponent
import components.screens.file.FileScreenComponent
import helper.FileUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandButtonPresentationModel
import org.pushingpixels.aurora.component.model.CommandButtonPresentationState
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.SelectorContentModel
import org.pushingpixels.aurora.component.projection.CheckBoxProjection
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.theming.DecorationAreaType
import org.pushingpixels.aurora.theming.auroraBackground
import org.pushingpixels.aurora.window.AuroraDecorationArea
import preferences.mode.Mode
import preferences.mode.ModeHandler

@Composable
fun ControlPane(
    fileScreenComponent: FileScreenComponent,
    compareFilesComponent: CompareFilesComponent,
    activeComponent: Root.Child,
) {
    val scope = rememberCoroutineScope()
    var mode by remember { mutableStateOf(ModeHandler.getMode(scope)) }
    AuroraDecorationArea(decorationAreaType = DecorationAreaType.ControlPane) {
        Column(
            modifier = Modifier.fillMaxHeight().auroraBackground().padding(vertical = 8.dp, horizontal = 12.dp).width(180.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                AnimatedVisibility(visible = activeComponent !is Root.Child.Text) {
                    Row {
                        CommandButtonProjection(
                            contentModel = Command(
                                text = when (activeComponent) {
                                    is Root.Child.File -> "Select file"
                                    is Root.Child.CompareFiles -> "Select 1st file"
                                    else -> ""
                                },
                                action = {
                                    FileUtils.openFileDialogAndGetResult().also {
                                        scope.launch(Dispatchers.Default) {
                                            fileScreenComponent.resultMap = hashMapOf()
                                            ControlPaneHelper.setFiles(
                                                fileScreenComponent = fileScreenComponent,
                                                compareFilesComponent = compareFilesComponent,
                                                activeComponent = activeComponent,
                                                file = it,
                                                buttonIndex = 0
                                            )
                                        }
                                    }
                                }
                            ),
                            presentationModel = CommandButtonPresentationModel(
                                presentationState = CommandButtonPresentationState.Tile,
                                textStyle = TextStyle(textAlign = TextAlign.Center),
                            )
                        ).project(Modifier.fillMaxWidth(if (activeComponent is Root.Child.CompareFiles) 0.5f else 1f))
                        AnimatedVisibility(visible = activeComponent is Root.Child.CompareFiles) {
                            CommandButtonProjection(
                                contentModel = Command(
                                    text = if (activeComponent is Root.Child.CompareFiles) "Select 2nd file" else "",
                                    action = {
                                        FileUtils.openFileDialogAndGetResult().also {
                                            scope.launch(Dispatchers.Default) {
                                                ControlPaneHelper.setFiles(
                                                    fileScreenComponent = fileScreenComponent,
                                                    compareFilesComponent = compareFilesComponent,
                                                    activeComponent = activeComponent,
                                                    file = it,
                                                    buttonIndex = 1
                                                )
                                            }
                                        }
                                    }
                                ),
                                presentationModel = CommandButtonPresentationModel(
                                    presentationState = CommandButtonPresentationState.Tile,
                                    textStyle = TextStyle(textAlign = TextAlign.Center)
                                )
                            ).project(Modifier.fillMaxWidth())
                        }
                    }
                }
                Row {
                    Box(Modifier.weight(1f)) {
                        LabelProjection(
                            contentModel = LabelContentModel(
                                text = "${Mode.SIMPLE.name.lowercase().replaceFirstChar { it.titlecase() }} list"
                            )
                        ).project()
                    }
                    CheckBoxProjection(
                        contentModel = SelectorContentModel(
                            text = "",
                            selected = mode == Mode.SIMPLE,
                            onTriggerSelectedChange = {
                                val newMode = if (mode == Mode.SIMPLE) Mode.ADVANCED else Mode.SIMPLE
                                scope.launch(Dispatchers.Default) { ModeHandler.putMode(newMode) }
                                mode = newMode
                            }
                        )
                    ).project()
                }
                AlgorithmSelectionList(
                    algorithm = fileScreenComponent.algorithm,
                    mode = mode,
                    onAlgorithmClick = {
                        scope.launch(Dispatchers.Default) {
                            ControlPaneHelper.onAlgorithmClick(algorithm = it, component = fileScreenComponent)
                        }
                    }
                )
            }
            AnimatedVisibility(visible = activeComponent !is Root.Child.Text) {
                CommandButtonProjection(
                    contentModel = Command(
                        text = when (activeComponent) {
                            is Root.Child.File -> if (fileScreenComponent.fileHashJob?.isActive != true) "Calculate" else "Cancel"
                            is Root.Child.Text -> ""
                            is Root.Child.CompareFiles -> if ((compareFilesComponent.comparisonJobList?.count { it.isActive } ?: 0) <= 0) "Compare" else "Cancel"
                        },
                        action = {
                            if (activeComponent is Root.Child.File) {
                                fileScreenComponent.onCalculateClicked(scope)
                            } else if (activeComponent is Root.Child.CompareFiles) {
                                compareFilesComponent.onCalculateClicked(scope)
                            }
                        },
                        isActionEnabled = if (activeComponent is Root.Child.CompareFiles) {
                            compareFilesComponent.fileComparisonOne != null && compareFilesComponent.fileComparisonTwo != null
                        } else {
                            fileScreenComponent.file != null
                        }
                    ),
                    presentationModel = CommandButtonPresentationModel(
                        presentationState = CommandButtonPresentationState.Tile
                    )
                ).project(Modifier.fillMaxWidth())
            }
        }
    }
}
