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

package components.screens.compare

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.FileInfoSection
import components.HashProgress
import components.OutputTextFieldRow
import org.pushingpixels.aurora.component.projection.HorizontalSeparatorProjection

@Composable
fun CompareFilesScreen(component: CompareFilesComponent) {
    Column {
        Column(
            modifier = Modifier.fillMaxHeight(fraction = 0.5f),
            verticalArrangement = Arrangement.Center
        ) {
            FileInfoSection(component.fileOne)
            Column(
                modifier = Modifier.padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                HashProgress(fileHashProgress = component.fileOneHashProgress, timer = component.fileOneTimer)
                OutputTextFieldRow(
                    algorithm = component.algorithm,
                    value = component.fileOneHash,
                    isValueUppercase = component.fileOneHashUppercase,
                    onCaseClick = { component.switchHashCase(CompareFilesComponent.FileComparison.FileComparisonOne) }
                )
            }
        }
        HorizontalSeparatorProjection().project(Modifier.fillMaxWidth())
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            FileInfoSection(component.fileTwo)
            Column(
                modifier = Modifier.padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                HashProgress(fileHashProgress = component.fileTwoHashProgress, timer = component.fileTwoTimer)
                OutputTextFieldRow(
                    algorithm = component.algorithm,
                    isValueUppercase = component.fileTwoHashUppercase,
                    value = component.fileTwoHash,
                    onCaseClick = { component.switchHashCase(CompareFilesComponent.FileComparison.FileComparisonTwo) }
                )
            }
        }
    }
}
