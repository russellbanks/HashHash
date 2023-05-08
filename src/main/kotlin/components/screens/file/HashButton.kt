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

import androidx.compose.runtime.Composable
import helper.Icons
import kotlinx.coroutines.Job
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandButtonPresentationModel
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.theming.IconFilterStrategy
import java.io.File

@Composable
fun HashButton(file: File?, fileHashJob: Job?, action: () -> Unit) {
    CommandButtonProjection(
        contentModel = Command(
            text = if (fileHashJob?.isActive == true) "Cancel" else "Hash",
            icon = Icons.Utility.microChip(),
            action = action,
            isActionEnabled = file != null
        ),
        presentationModel = CommandButtonPresentationModel(
            iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
            iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
            iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText,
        )
    ).project()
}
