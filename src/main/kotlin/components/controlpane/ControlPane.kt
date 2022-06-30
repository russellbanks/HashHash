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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import components.Root
import components.controlpane.algorithmselection.AlgorithmSelectionList
import components.screens.compare.CompareFilesComponent
import components.screens.file.FileScreenComponent
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandButtonPresentationModel
import org.pushingpixels.aurora.component.model.CommandButtonPresentationState
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.theming.DecorationAreaType
import org.pushingpixels.aurora.theming.auroraBackground
import org.pushingpixels.aurora.window.AuroraDecorationArea
import preferences.mode.ModeHandler

@Composable
fun ControlPane(
    fileScreen: FileScreenComponent,
    compareScreen: CompareFilesComponent,
    activeComponent: Root.Child,
    modeHandler: ModeHandler
) {
    val scope = rememberCoroutineScope()
    val controlPaneHelper = ControlPaneHelper(fileScreen, compareScreen, activeComponent, modeHandler, scope)
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
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                AnimatedVisibility(visible = activeComponent !is Root.Child.Text) {
                    Row {
                        CommandButtonProjection(
                            contentModel = Command(
                                text = controlPaneHelper.getSelectFileButtonText(FileSelectButton.ONE),
                                action = { controlPaneHelper.getSelectFileButtonAction(FileSelectButton.ONE) }
                            ),
                            presentationModel = CommandButtonPresentationModel(
                                presentationState = CommandButtonPresentationState.Tile,
                                textStyle = TextStyle(textAlign = TextAlign.Center)
                            )
                        ).project(Modifier.fillMaxWidth(if (activeComponent is Root.Child.CompareFiles) 0.5f else 1f))
                        AnimatedVisibility(visible = activeComponent is Root.Child.CompareFiles) {
                            CommandButtonProjection(
                                contentModel = Command(
                                    text = controlPaneHelper.getSelectFileButtonText(FileSelectButton.TWO),
                                    action = { controlPaneHelper.getSelectFileButtonAction(FileSelectButton.TWO) }
                                ),
                                presentationModel = CommandButtonPresentationModel(
                                    presentationState = CommandButtonPresentationState.Tile,
                                    textStyle = TextStyle(textAlign = TextAlign.Center)
                                )
                            ).project(Modifier.fillMaxWidth())
                        }
                    }
                }
                ModeSelector(controlPaneHelper)
                AlgorithmSelectionList(controlPaneHelper)
            }
            AnimatedVisibility(visible = activeComponent !is Root.Child.Text) {
                CommandButtonProjection(
                    contentModel = Command(
                        text = controlPaneHelper.getActionButtonText(),
                        action = controlPaneHelper::getActionButtonAction,
                        isActionEnabled = controlPaneHelper.isActionButtonEnabled()
                    ),
                    presentationModel = CommandButtonPresentationModel(
                        presentationState = CommandButtonPresentationState.Tile
                    )
                ).project(Modifier.fillMaxWidth())
            }
        }
    }
}
