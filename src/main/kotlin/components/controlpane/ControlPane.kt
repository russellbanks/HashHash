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

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.appmattus.crypto.Algorithm
import helper.FileUtils
import preferences.mode.Mode
import kotlinx.coroutines.Job
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CheckBoxProjection
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.theming.DecorationAreaType
import org.pushingpixels.aurora.theming.auroraBackground
import org.pushingpixels.aurora.window.AuroraDecorationArea
import java.io.File

@Composable
fun ControlPane(
    algorithm: Algorithm,
    job: Job?,
    file: File?,
    mode: Mode,
    onTriggerModeChange: (Boolean) -> Unit,
    onSoloAlgorithmClick: (Algorithm) -> Unit,
    onSubAlgorithmClick: (Algorithm) -> Unit,
    onSelectFileResult: (File?) -> Unit,
    onCalculateClick: () -> Unit
) {
    AuroraDecorationArea(decorationAreaType = DecorationAreaType.ControlPane) {
        Column(
            modifier = Modifier.fillMaxHeight().auroraBackground().padding(vertical = 8.dp, horizontal = 12.dp).width(200.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                CommandButtonProjection(
                    contentModel = Command(
                        text = "Select file",
                        action = { FileUtils.openFileDialogAndGetResult().also { onSelectFileResult(it) } }
                    )
                ).project(Modifier.fillMaxWidth().height(40.dp))
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
                            onTriggerSelectedChange = { onTriggerModeChange(it) }
                        )
                    ).project()
                }
                AlgorithmSelectionList(
                    algorithm = algorithm,
                    mode = mode,
                    onSoloClick = { onSoloAlgorithmClick(it) },
                    onSubClick = { onSubAlgorithmClick(it) }
                )
            }
            CommandButtonProjection(
                contentModel = Command(
                    text = if (job?.isActive != true) "Calculate" else "Cancel",
                    action = onCalculateClick,
                    isActionEnabled = file != null
                ),
                presentationModel = CommandButtonPresentationModel(
                    presentationState = CommandButtonPresentationState.Medium
                )
            ).project(Modifier.fillMaxWidth().height(40.dp))
        }
    }
}