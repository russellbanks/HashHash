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

package components.screens.text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.projection.CommandButtonProjection

@Composable
fun TextFieldShortcuts(component: TextScreenComponent) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(4.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        CommandButtonProjection(
            contentModel = Command(
                text = "Uppercase",
                action = { component.givenText = component.givenText.uppercase() }
            )
        ).project(Modifier.fillMaxWidth(fraction = 1f / 3))
        CommandButtonProjection(
            contentModel = Command(
                text = "Lowercase",
                action = { component.givenText = component.givenText.lowercase() }
            )
        ).project(Modifier.fillMaxWidth(fraction = 1f / 2))
        CommandButtonProjection(
            contentModel = Command(
                text = "Clear text area",
                action = { component.givenText = "" }
            )
        ).project(Modifier.fillMaxWidth())
    }
}
