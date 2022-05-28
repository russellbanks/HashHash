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
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.appmattus.crypto.Algorithm
import components.Root
import helper.FileUtils
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CheckBoxProjection
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.theming.DecorationAreaType
import org.pushingpixels.aurora.theming.auroraBackground
import org.pushingpixels.aurora.window.AuroraDecorationArea
import preferences.mode.Mode
import preferences.mode.ModeHandler
import java.io.File

@Composable
fun ControlPane(
    algorithm: Algorithm,
    job: Job?,
    compareJobList: List<Deferred<Unit>>?,
    file: File?,
    fileComparisonOne: File?,
    fileComparisonTwo: File?,
    currentScreen: Root.Child,
    onAlgorithmClick: (Algorithm) -> Unit,
    onSelectFileResult: (File?) -> Unit,
    onSelectFileComparisonOneResult: (File?) -> Unit,
    oneSelectFileComparisonTwoResult: (File?) -> Unit,
    onCalculateClick: () -> Unit
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
                AnimatedVisibility(visible = currentScreen !is Root.Child.Text) {
                    CommandButtonProjection(
                        contentModel = Command(
                            text = when (currentScreen) {
                                is Root.Child.File -> "Select file"
                                is Root.Child.CompareFiles -> "Select 1st file"
                                else -> ""
                            },
                            action = {
                                FileUtils.openFileDialogAndGetResult().also {
                                    if (currentScreen is Root.Child.File) onSelectFileResult(it)
                                    else if (currentScreen is Root.Child.CompareFiles) onSelectFileComparisonOneResult(it)
                                }
                            }
                        ),
                        presentationModel = CommandButtonPresentationModel(
                            presentationState = CommandButtonPresentationState.Tile
                        )
                    ).project(Modifier.fillMaxWidth())
                }
                AnimatedVisibility(visible = currentScreen is Root.Child.CompareFiles) {
                    CommandButtonProjection(
                        contentModel = Command(
                            text = if (currentScreen is Root.Child.CompareFiles) "Select 2nd file" else "",
                            action = { FileUtils.openFileDialogAndGetResult().also { oneSelectFileComparisonTwoResult(it) } }
                        ),
                        presentationModel = CommandButtonPresentationModel(
                            presentationState = CommandButtonPresentationState.Tile
                        )
                    ).project(Modifier.fillMaxWidth())
                }
                Row {
                    Box(Modifier.weight(1f)) {
                        LabelProjection(
                            contentModel = LabelContentModel(text = "${Mode.SIMPLE.name.lowercase().replaceFirstChar { it.titlecase() }} mode")
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
                AlgorithmSelectionList(algorithm = algorithm, mode = mode, onAlgorithmClick = { onAlgorithmClick(it) })
            }
            AnimatedVisibility(visible = currentScreen !is Root.Child.Text) {
                CommandButtonProjection(
                    contentModel = Command(
                        text = when (currentScreen) {
                            is Root.Child.File -> if (job?.isActive != true) "Calculate" else "Cancel"
                            is Root.Child.Text -> ""
                            is Root.Child.CompareFiles -> if ((compareJobList?.count { it.isActive } ?: 0) <= 0) "Compare" else "Cancel"
                        },
                        action = onCalculateClick,
                        isActionEnabled = if (currentScreen is Root.Child.CompareFiles) {
                            fileComparisonOne != null && fileComparisonTwo != null
                        } else {
                            file != null
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