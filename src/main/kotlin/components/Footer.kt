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
import components.screens.Screen
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.theming.DecorationAreaType
import org.pushingpixels.aurora.theming.auroraBackground
import org.pushingpixels.aurora.window.AuroraDecorationArea
import java.io.File

@Composable
fun Footer(
    currentScreen: Screen,
    error: Exception?,
    mainFileHash: String,
    fileComparisonOneHash: String,
    fileComparisonTwoHash: String,
    file: File?,
    filesMatch: Boolean,
) {
    AuroraDecorationArea(decorationAreaType = DecorationAreaType.Footer) {
        Box(Modifier.fillMaxWidth().auroraBackground().padding(6.dp), contentAlignment = Alignment.Center) {
            LabelProjection(
                contentModel = LabelContentModel(
                    text = when (currentScreen) {
                        Screen.CompareFilesScreen -> {
                            if (fileComparisonOneHash.isNotBlank() && fileComparisonTwoHash.isNotBlank()) {
                                if (filesMatch) "Files match" else "Files do not match"
                            } else ""
                        }
                        Screen.TextScreen -> ""
                        Screen.FileScreen -> {
                            when {
                                error != null -> error.localizedMessage.replaceFirstChar { it.titlecase() }
                                mainFileHash.isNotBlank() -> "Done!"
                                file != null -> "No hash"
                                else -> "No file selected"
                            }
                        }
                    }
                )
            ).project()
        }
    }
}