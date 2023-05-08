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

package components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import kotlinx.datetime.Instant
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.projection.LabelProjection
import java.text.SimpleDateFormat
import kotlin.time.DurationUnit

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ElapsedTimeResults(instantBeforeHash: Instant?, instantAfterHash: Instant?) {
    SelectionContainer {
        FlowColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            LabelProjection(
                contentModel = LabelContentModel(
                    text = "Started at: ${
                        if (instantBeforeHash != null) {
                            SimpleDateFormat("dd MMMM yyyy, HH:mm:ss").format(instantBeforeHash.toEpochMilliseconds())
                        } else {
                            "-"
                        }
                    }"
                )
            ).project()
            LabelProjection(
                contentModel = LabelContentModel(
                    text = "Finished at: ${
                        if (instantAfterHash != null) {
                            SimpleDateFormat("dd MMMM yyyy, HH:mm:ss").format(instantAfterHash.toEpochMilliseconds())
                        } else {
                            "-"
                        }
                    }"
                )
            ).project()
            LabelProjection(
                contentModel = LabelContentModel(
                    text = "Time taken: ${
                        if (
                            instantBeforeHash != null && instantAfterHash != null &&
                            (instantAfterHash - instantBeforeHash).isPositive()
                        ) {
                            (instantAfterHash - instantBeforeHash).toString(unit = DurationUnit.SECONDS, decimals = 3)
                        } else {
                            "-"
                        }
                    }"
                )
            ).project()
        }
    }
}
