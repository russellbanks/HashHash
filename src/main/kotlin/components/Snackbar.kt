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

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.SnackbarData
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import components.screens.ParentComponent
import helper.Icons
import koin.inject
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.theming.AuroraSkin

@Composable
fun BoxScope.Snackbar() {
    val parentComponent: ParentComponent by inject()
    SnackbarHost(
        modifier = Modifier.align(Alignment.BottomCenter).padding(50.dp),
        hostState = parentComponent.snackbarHostState,
        snackbar = { snackbarData: SnackbarData ->
            val backgroundColorScheme = AuroraSkin.colors.getBackgroundColorScheme(
                decorationAreaType = AuroraSkin.decorationAreaType
            )
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = backgroundColorScheme.backgroundFillColor,
                border = BorderStroke(1.dp, Color.Gray),
                elevation = 4.dp
            ) {
                LabelProjection(
                    contentModel = LabelContentModel(
                        text = snackbarData.message,
                        icon = Icons.Utility.check()
                    )
                ).project(Modifier.padding(5.dp))
            }
        }
    )
}
