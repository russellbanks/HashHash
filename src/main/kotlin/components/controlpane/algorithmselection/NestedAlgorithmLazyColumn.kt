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

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.appmattus.crypto.Algorithm
import components.controlpane.NestedAlgorithm
import org.pushingpixels.aurora.component.AuroraBoxWithHighlights
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.theming.AuroraSkin
import org.pushingpixels.aurora.theming.Side
import org.pushingpixels.aurora.theming.Sides

@OptIn(ExperimentalStdlibApi::class)
@Composable
fun NestedAlgorithmLazyColumn(
    algorithm: Algorithm,
    item: NestedAlgorithm,
    itemIndex: Int,
    onAlgorithmClick: (item: Algorithm) -> Unit
) {
    val backgroundColorScheme = AuroraSkin.colors.getBackgroundColorScheme(
        decorationAreaType = AuroraSkin.decorationAreaType
    )
    val backgroundEvenRows = backgroundColorScheme.backgroundFillColor
    val backgroundOddRows = backgroundColorScheme.accentedBackgroundFillColor
    LazyColumn(Modifier.fillMaxWidth().height((32 * item.listOfAlgorithms.count()).dp)) {
        items(item.listOfAlgorithms) { nestedItem ->
            AuroraBoxWithHighlights(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp)
                    .background(if (itemIndex % 2 == 0) backgroundEvenRows else backgroundOddRows),
                selected = algorithm == nestedItem,
                onClick = { onAlgorithmClick(nestedItem) },
                sides = Sides(straightSides = Side.entries.toSet())
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Canvas(Modifier.padding(start = 12.dp)) {
                        drawCircle(color = backgroundColorScheme.foregroundColor, radius = 2f)
                    }
                    LabelProjection(contentModel = LabelContentModel(text = nestedItem.algorithmName)).project()
                }
            }
        }
    }
}
