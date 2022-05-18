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

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.appmattus.crypto.Algorithm
import components.FileInfoSection
import components.HashProgress
import components.OutputTextFieldRow
import org.pushingpixels.aurora.component.projection.HorizontalSeparatorProjection
import java.io.File

@Composable
fun CompareFilesScreen(
    algorithm: Algorithm,
    fileComparisonOne: File?,
    fileComparisonOneHash: String,
    fileComparisonOneProgress: Float,
    fileComparisonTwo: File?,
    fileComparisonTwoHash: String,
    fileComparisonTwoProgress: Float
) {
    Column {
        Column(Modifier.fillMaxHeight(0.5f)) {
            FileInfoSection(fileComparisonOne)
            Column(
                modifier = Modifier.padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                HashProgress(fileComparisonOneProgress)
                OutputTextFieldRow(algorithm, fileComparisonOneHash, onCaseClick = {  })
            }
        }
        HorizontalSeparatorProjection().project(Modifier.fillMaxWidth())
        FileInfoSection(fileComparisonTwo)
        Column(
            modifier = Modifier.padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            HashProgress(fileComparisonTwoProgress)
            OutputTextFieldRow(algorithm, fileComparisonTwoHash, onCaseClick = {  })
        }
    }
}