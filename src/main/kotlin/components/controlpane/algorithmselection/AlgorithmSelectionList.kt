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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.unit.dp
import com.appmattus.crypto.Algorithm
import components.controlpane.ControlPaneHelper
import components.controlpane.NestedAlgorithm
import koin.inject
import org.pushingpixels.aurora.component.AuroraVerticalScrollbar
import org.pushingpixels.aurora.component.ScrollBarSizingConstants
import preferences.mode.ModeHandler

@Composable
fun AlgorithmSelectionList(controlPaneHelper: ControlPaneHelper) {
    val modeHandler: ModeHandler by inject()
    Box(Modifier.fillMaxWidth()) {
        val lazyListState = rememberLazyListState()
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                .padding(end = ScrollBarSizingConstants.DefaultScrollBarThickness),
            state = lazyListState
        ) {
            itemsIndexed(AlgorithmList.getAlgorithmList(modeHandler)) { index, item ->
                if (item is Algorithm) {
                    AlgorithmBox(
                        item = item,
                        algorithm = controlPaneHelper.fileScreen.algorithm,
                        index = index,
                        onAlgorithmClick = { controlPaneHelper.onAlgorithmClick(algorithm = it) }
                    )
                } else if (item is NestedAlgorithm) {
                    var nestedVisibility by rememberSaveable { mutableStateOf(false) }
                    DropDownAlgorithmBox(item = item, index = index, onClick = { nestedVisibility = !nestedVisibility })
                    AnimatedVisibility(visible = nestedVisibility) {
                        NestedAlgorithmLazyColumn(
                            algorithm = controlPaneHelper.fileScreen.algorithm,
                            item = item,
                            itemIndex = index,
                            onAlgorithmClick = { controlPaneHelper.onAlgorithmClick(algorithm = it) }
                        )
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
