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

package components.screens.file

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.unit.dp
import components.*
import org.pushingpixels.aurora.component.projection.HorizontalSeparatorProjection

@Composable
fun FileScreen(component: FileScreenComponent) {
    Column(Modifier.fillMaxSize()) {
        FileInfoSection(component.file)
        HorizontalSeparatorProjection().project(Modifier.fillMaxWidth())
        Column(
            modifier = Modifier.weight(1f).padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val clipboardManager = LocalClipboardManager.current
            OutputTextFieldRow(
                algorithm = component.algorithm,
                value = component.fileHash,
                onCaseClick = {
                    component.fileHash = if (component.fileHash == component.fileHash.uppercase()) {
                        component.fileHash.lowercase()
                    } else {
                        component.fileHash.uppercase()
                    }
                }
            )
            ComparisonTextFieldRow(
                hashedOutput = component.fileHash,
                comparisonHash = component.comparisonHash,
                onPasteClick = { component.comparisonHash = (clipboardManager.getText()?.text ?: "").filterNot { it.isWhitespace() } },
                onClearClick = { component.comparisonHash = "" },
                onTextFieldChange = { component.comparisonHash = it.filterNot { char -> char.isWhitespace() } }
            )
            TimeResultColumn(component.instantBeforeHash, component.instantAfterHash)
            HashProgress(component.hashProgress)
        }
    }
}