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

package components.controlpane.algorithmselection

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import components.controlpane.NestedAlgorithm
import helper.Icons
import org.pushingpixels.aurora.component.AuroraBoxWithHighlights
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.theming.AuroraSkin
import org.pushingpixels.aurora.theming.Side
import org.pushingpixels.aurora.theming.Sides

@OptIn(ExperimentalStdlibApi::class)
@Composable
fun DropDownAlgorithmBox(item: NestedAlgorithm, index: Int, onClick: () -> Unit) {
    val backgroundColorScheme = AuroraSkin.colors.getBackgroundColorScheme(
        decorationAreaType = AuroraSkin.decorationAreaType
    )
    val backgroundEvenRows = backgroundColorScheme.backgroundFillColor
    val backgroundOddRows = backgroundColorScheme.accentedBackgroundFillColor
    var rotate by rememberSaveable { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(targetValue = if (rotate) 90F else 0F)
    AuroraBoxWithHighlights(
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp)
            .background(if (index % 2 == 0) backgroundEvenRows else backgroundOddRows),
        onClick = {
            rotate = !rotate
            onClick()
        },
        sides = Sides(straightSides = Side.entries.toSet())
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier.fillMaxHeight().weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                LabelProjection(contentModel = LabelContentModel(text = item.name)).project()
            }
            Image(
                painter = Icons.Utility.chevronRight(),
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
                    .padding(horizontal = 6.dp)
                    .rotate(rotationAngle),
                colorFilter = ColorFilter.tint(color = backgroundColorScheme.foregroundColor)
            )
        }
    }
}
