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

package components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.screens.compare.CompareFilesComponent
import components.screens.file.FileScreenComponent
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.theming.DecorationAreaType
import org.pushingpixels.aurora.theming.auroraBackground
import org.pushingpixels.aurora.window.AuroraDecorationArea

@Composable
fun Footer(
    activeComponent: Root.Child,
    fileScreen: FileScreenComponent,
    compareScreen: CompareFilesComponent
) {
    AuroraDecorationArea(decorationAreaType = DecorationAreaType.Footer) {
        Box(Modifier.fillMaxWidth().auroraBackground().padding(6.dp), contentAlignment = Alignment.Center) {
            LabelProjection(
                contentModel = LabelContentModel(
                    text = when (activeComponent) {
                        is Root.Child.File -> {
                            when {
                                fileScreen.file != null -> "No hash"
                                else -> "No file selected"
                            }
                        }
                        is Root.Child.Text -> ""
                        is Root.Child.CompareFiles -> {
                            when {
                                compareScreen.fileComparisonOne == null &&
                                    compareScreen.fileComparisonTwo == null -> "No files selected"
                                compareScreen.fileComparisonOne == null &&
                                    compareScreen.fileComparisonTwo != null -> "1st file not selected"
                                compareScreen.fileComparisonOne != null &&
                                    compareScreen.fileComparisonTwo == null -> "2nd file not selected"
                                compareScreen.fileComparisonOneHash.isBlank() &&
                                    compareScreen.fileComparisonTwoHash.isBlank() -> "No hashes"
                                compareScreen.fileComparisonOneHash.isBlank() &&
                                    compareScreen.fileComparisonTwoHash.isNotBlank() -> "No hash for 1st file"
                                compareScreen.fileComparisonOneHash.isNotBlank() &&
                                    compareScreen.fileComparisonTwoHash.isBlank() -> "No hash for 2nd file"
                                compareScreen.fileComparisonOneHash.isNotBlank() &&
                                    compareScreen.fileComparisonTwoHash.isNotBlank() -> {
                                    if (compareScreen.filesMatch) "Files match" else "Files do not match"
                                }
                                else -> ""
                            }
                        }
                    }
                )
            ).project()
        }
    }
}
