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
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appmattus.crypto.Algorithm
import components.NestedAlgorithm
import components.algorithmList
import helper.Unicode
import org.pushingpixels.aurora.component.AuroraBoxWithHighlights
import org.pushingpixels.aurora.component.AuroraVerticalScrollbar
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.theming.AuroraSkin
import org.pushingpixels.aurora.theming.Side
import org.pushingpixels.aurora.theming.Sides

@Composable
fun AlgorithmSelectionList(
    algorithm: Algorithm,
    onSoloClick: (item: Algorithm) -> Unit,
    onSubClick: (nestedItem: Algorithm) -> Unit
) {
    Box(Modifier.fillMaxWidth()) {
        val lazyListState = rememberLazyListState()
        val backgroundColorScheme = AuroraSkin.colors.getBackgroundColorScheme(
            decorationAreaType = AuroraSkin.decorationAreaType
        )
        val backgroundEvenRows = backgroundColorScheme.backgroundFillColor
        val backgroundOddRows = backgroundColorScheme.accentedBackgroundFillColor
        LazyColumn (
            modifier = Modifier.fillMaxSize().border(1.dp, Color.Gray, RoundedCornerShape(4.dp)).padding(horizontal = 6.dp),
            state = lazyListState
        ) {
            itemsIndexed(algorithmList) { index, item ->
                if (item is Algorithm) {
                    AuroraBoxWithHighlights(
                        modifier = Modifier.fillMaxWidth().height(32.dp)
                            .background(if (index % 2 == 0) backgroundEvenRows else backgroundOddRows),
                        selected = (algorithm == item),
                        onClick = { onSoloClick(item) },
                        sides = Sides(straightSides = Side.values().toSet())
                    ) {
                        LabelProjection(contentModel = LabelContentModel(text = item.algorithmName)).project()
                    }
                } else if (item is NestedAlgorithm) {
                    var nestedVisibility by rememberSaveable { mutableStateOf(false) }
                    AuroraBoxWithHighlights(
                        modifier = Modifier.fillMaxWidth().height(32.dp)
                            .background(if (index % 2 == 0) backgroundEvenRows else backgroundOddRows),
                        onClick = { nestedVisibility = !nestedVisibility },
                        sides = Sides(straightSides = Side.values().toSet()),
                    ) {
                        Row {
                            Box(modifier = Modifier.fillMaxHeight().weight(1f), contentAlignment = Alignment.CenterStart) {
                                LabelProjection(contentModel = LabelContentModel(text = item.name)).project()
                            }
                            LabelProjection(
                                contentModel = LabelContentModel(text = Unicode.dropDown),
                                presentationModel = LabelPresentationModel(textStyle = TextStyle(fontSize = 16.sp))
                            ).project(Modifier.padding(8.dp))
                        }
                    }
                    AnimatedVisibility(nestedVisibility) {
                        LazyColumn(Modifier.fillMaxWidth().height((32 * item.listOfAlgorithms.count()).dp)) {
                            items(item.listOfAlgorithms) { nestedItem ->
                                AuroraBoxWithHighlights(
                                    modifier = Modifier.fillMaxWidth().height(32.dp)
                                        .background(if (index % 2 == 0) backgroundEvenRows else backgroundOddRows),
                                    selected = (algorithm == nestedItem),
                                    onClick = { onSoloClick(nestedItem) },
                                    sides = Sides(straightSides = Side.values().toSet())
                                ) {
                                    LabelProjection(
                                        contentModel = LabelContentModel(
                                            text = "${Unicode.bulletPoint} ${nestedItem.algorithmName}"
                                        )
                                    ).project()
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