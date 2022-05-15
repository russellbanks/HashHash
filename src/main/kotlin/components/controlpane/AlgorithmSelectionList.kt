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

package components.controlpane

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appmattus.crypto.Algorithm
import preferences.mode.Mode
import helper.Unicode
import org.pushingpixels.aurora.component.AuroraBoxWithHighlights
import org.pushingpixels.aurora.component.AuroraVerticalScrollbar
import org.pushingpixels.aurora.component.ScrollBarSizingConstants
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.theming.AuroraSkin
import org.pushingpixels.aurora.theming.Side
import org.pushingpixels.aurora.theming.Sides

@Composable
fun AlgorithmSelectionList(
    algorithm: Algorithm,
    mode: Mode,
    onAlgorithmClick: (item: Algorithm) -> Unit
) {
    Box(Modifier.fillMaxWidth()) {
        val lazyListState = rememberLazyListState()
        val backgroundColorScheme = AuroraSkin.colors.getBackgroundColorScheme(
            decorationAreaType = AuroraSkin.decorationAreaType
        )
        val backgroundEvenRows = backgroundColorScheme.backgroundFillColor
        val backgroundOddRows = backgroundColorScheme.accentedBackgroundFillColor
        LazyColumn(
            modifier = Modifier.fillMaxSize().border(1.dp, Color.Gray, RoundedCornerShape(4.dp)).padding(end = ScrollBarSizingConstants.DefaultScrollBarThickness),
            state = lazyListState
        ) {
            itemsIndexed(if (mode == Mode.SIMPLE) AlgorithmList.simple else AlgorithmList.advanced) { index, item ->
                if (item is Algorithm) {
                    AuroraBoxWithHighlights(
                        modifier = Modifier.fillMaxWidth().height(32.dp)
                            .background(if (index % 2 == 0) backgroundEvenRows else backgroundOddRows),
                        selected = (algorithm == item),
                        onClick = { onAlgorithmClick(item) },
                        sides = Sides(straightSides = Side.values().toSet())
                    ) {
                        LabelProjection(contentModel = LabelContentModel(text = item.algorithmName)).project()
                    }
                } else if (item is NestedAlgorithm) {
                    var rotate by remember { mutableStateOf(false) }
                    val rotationAngle by animateFloatAsState(targetValue = if (rotate) 90F else 0F)
                    var nestedVisibility by rememberSaveable { mutableStateOf(false) }
                    AuroraBoxWithHighlights(
                        modifier = Modifier.fillMaxWidth().height(32.dp)
                            .background(if (index % 2 == 0) backgroundEvenRows else backgroundOddRows),
                        onClick = {
                            rotate = !rotate
                            nestedVisibility = !nestedVisibility
                                  },
                        sides = Sides(straightSides = Side.values().toSet()),
                    ) {
                        Row {
                            Box(modifier = Modifier.fillMaxHeight().weight(1f), contentAlignment = Alignment.CenterStart) {
                                LabelProjection(contentModel = LabelContentModel(text = item.name)).project()
                            }
                            LabelProjection(
                                contentModel = LabelContentModel(text = Unicode.dropDown),
                                presentationModel = LabelPresentationModel(textStyle = TextStyle(fontSize = 16.sp))
                            ).project(Modifier.rotate(rotationAngle).padding(8.dp))
                        }
                    }
                    AnimatedVisibility(nestedVisibility) {
                        LazyColumn(Modifier.fillMaxWidth().height((32 * item.listOfAlgorithms.count()).dp)) {
                            items(item.listOfAlgorithms) { nestedItem ->
                                AuroraBoxWithHighlights(
                                    modifier = Modifier.fillMaxWidth().height(32.dp)
                                        .background(if (index % 2 == 0) backgroundEvenRows else backgroundOddRows),
                                    selected = (algorithm == nestedItem),
                                    onClick = { onAlgorithmClick(nestedItem) },
                                    sides = Sides(straightSides = Side.values().toSet())
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Canvas(Modifier.padding(start = 12.dp)) {
                                            drawCircle(
                                                color = backgroundColorScheme.foregroundColor,
                                                radius = 2f
                                            )
                                        }
                                        LabelProjection(
                                            contentModel = LabelContentModel(text = nestedItem.algorithmName)
                                        ).project()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        AuroraVerticalScrollbar(
            modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight().padding(2.dp),
            adapter = rememberScrollbarAdapter(scrollState = lazyListState)
        )
    }
}