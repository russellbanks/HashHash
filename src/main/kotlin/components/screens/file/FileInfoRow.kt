/**

HashHash
Copyright (C) 2023 Russell Banks

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

package components.screens.file

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import helper.FileUtils
import helper.Icons
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.projection.LabelProjection
import java.io.File
import java.nio.file.Files

@Composable
fun FileInfoRow(file: File?) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
        Icons.SystemIcon(modifier = Modifier.size(60.dp), file = file)
        SelectionContainer {
            Column {
                file?.let { file ->
                    Files.probeContentType(file.toPath())?.let {
                        LabelProjection(contentModel = LabelContentModel(text = "Type: $it")).project()
                    }
                }
                LabelProjection(
                    contentModel = LabelContentModel(text = "Extension: ${file?.extension ?: ""}")
                ).project()
                LabelProjection(
                    contentModel = LabelContentModel(
                        text = "Size: ${file?.let { FileUtils.getFormattedBytes(it.length()) } ?: ""}"
                    )
                ).project()
                LabelProjection(
                    contentModel = LabelContentModel(text = "Path: ${file?.absolutePath ?: ""}")
                ).project()
            }
        }
    }
}
