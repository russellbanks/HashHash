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

package components.screens.comparefiles

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.appmattus.crypto.Algorithm
import components.screens.file.FileInfoSection
import components.screens.file.OutputTextFieldRow
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.projection.LabelProjection
import java.io.File

@Composable
fun CompareFilesScreen(
    algorithm: Algorithm,
    fileComparisonOne: File?,
    fileComparisonTwo: File?,
    hashedOutputComparisonOne: String,
    hashedOutputComparisonTwo: String,
    filesMatch: Boolean
) {
    Column {
        FileInfoSection(fileComparisonOne)
        Box(Modifier.padding(20.dp)) {
            OutputTextFieldRow(algorithm, hashedOutputComparisonOne, onCaseClick = {  })
        }
        FileInfoSection(fileComparisonTwo)
        Box(Modifier.padding(20.dp)) {
            OutputTextFieldRow(algorithm, hashedOutputComparisonTwo, onCaseClick = {  })
        }
        Column(
            modifier = Modifier.fillMaxSize().padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            println(hashedOutputComparisonOne)
            println(hashedOutputComparisonTwo)
            AnimatedVisibility(
                visible = hashedOutputComparisonOne.isNotBlank() && hashedOutputComparisonTwo.isNotBlank()
            ) {
                LabelProjection(
                    contentModel = LabelContentModel(
                        text = if (filesMatch) "Matches" else "Doesn't match"
                    )
                ).project()
            }
        }
    }
}