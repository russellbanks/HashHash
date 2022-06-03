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

package components.controlpane.algorithmselection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.appmattus.crypto.Algorithm
import org.pushingpixels.aurora.component.AuroraBoxWithHighlights
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.theming.AuroraSkin
import org.pushingpixels.aurora.theming.Side
import org.pushingpixels.aurora.theming.Sides

@Composable
fun AlgorithmBox(item: Algorithm, algorithm: Algorithm, index: Int, onAlgorithmClick: (Algorithm) -> Unit) {
    val backgroundColorScheme = AuroraSkin.colors.getBackgroundColorScheme(
        decorationAreaType = AuroraSkin.decorationAreaType
    )
    val backgroundEvenRows = backgroundColorScheme.backgroundFillColor
    val backgroundOddRows = backgroundColorScheme.accentedBackgroundFillColor
    AuroraBoxWithHighlights(
        modifier = Modifier.fillMaxWidth().height(32.dp)
            .background(if (index % 2 == 0) backgroundEvenRows else backgroundOddRows),
        selected = (algorithm == item),
        onClick = { onAlgorithmClick(item) },
        sides = Sides(straightSides = Side.values().toSet())
    ) {
        LabelProjection(contentModel = LabelContentModel(text = item.algorithmName)).project()
    }
}
