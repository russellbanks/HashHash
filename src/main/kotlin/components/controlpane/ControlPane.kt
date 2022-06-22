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
import components.controlpane.algorithmselection.AlgorithmSelectionList
import components.screens.compare.CompareFilesComponent
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
    fileScreen: FileScreenComponent,
    compareScreen: CompareFilesComponent,
    activeComponent: Root.Child,
    modeHandler: ModeHandler
) {
    val scope = rememberCoroutineScope()
    var selectedMode by remember { mutableStateOf(modeHandler.getMode(scope)) }
    AuroraDecorationArea(decorationAreaType = DecorationAreaType.ControlPane) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .auroraBackground()
                .padding(vertical = 8.dp, horizontal = 12.dp)
                .width(180.dp),
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
                                            fileScreen.resultMap.clear()
                                            ControlPaneHelper.setFiles(
                                                fileScreenComponent = fileScreen,
                                                compareFilesComponent = compareScreen,
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
                        ).project(
                            Modifier
                                .fillMaxWidth(fraction = if (activeComponent is Root.Child.CompareFiles) 0.5f else 1f)
                        )
                        AnimatedVisibility(visible = activeComponent is Root.Child.CompareFiles) {
                            CommandButtonProjection(
                                contentModel = Command(
                                    text = if (activeComponent is Root.Child.CompareFiles) "Select 2nd file" else "",
                                    action = {
                                        FileUtils.openFileDialogAndGetResult().also {
                                            scope.launch(Dispatchers.Default) {
                                                ControlPaneHelper.setFiles(
                                                    fileScreenComponent = fileScreen,
                                                    compareFilesComponent = compareScreen,
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
                            selected = selectedMode == Mode.SIMPLE,
                            onTriggerSelectedChange = {
                                scope.launch(Dispatchers.Default) {
                                    val newMode = if (it) Mode.SIMPLE else Mode.ADVANCED
                                    modeHandler.putMode(newMode)
                                    selectedMode = newMode
                                }
                            }
                        )
                    ).project()
                }
                AlgorithmSelectionList(
                    algorithm = fileScreen.algorithm,
                    mode = selectedMode,
                    onAlgorithmClick = {
                        scope.launch(Dispatchers.Default) {
                            ControlPaneHelper.onAlgorithmClick(algorithm = it, component = fileScreen)
                        }
                    }
                )
            }
            AnimatedVisibility(visible = activeComponent !is Root.Child.Text) {
                CommandButtonProjection(
                    contentModel = Command(
                        text = when (activeComponent) {
                            is Root.Child.File -> if (fileScreen.fileHashJob?.isActive != true) {
                                "Calculate"
                            } else "Cancel"
                            is Root.Child.Text -> ""
                            is Root.Child.CompareFiles -> {
                                if ((compareScreen.comparisonJobList?.count { it.isActive } ?: 0) <= 0) {
                                    "Compare"
                                } else "Cancel"
                            }
                        },
                        action = {
                            if (activeComponent is Root.Child.File) {
                                fileScreen.onCalculateClicked(scope)
                            } else if (activeComponent is Root.Child.CompareFiles) {
                                compareScreen.onCalculateClicked(scope)
                            }
                        },
                        isActionEnabled = if (activeComponent is Root.Child.CompareFiles) {
                            compareScreen.fileOne != null && compareScreen.fileTwo != null
                        } else {
                            fileScreen.file != null
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
