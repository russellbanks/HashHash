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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import components.screens.compare.CompareFilesComponent
import components.screens.file.FileScreenComponent
import components.screens.text.TextScreenComponent
import koin.inject
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.theming.DecorationAreaType
import org.pushingpixels.aurora.theming.auroraBackground
import org.pushingpixels.aurora.window.AuroraDecorationArea

@Composable
fun Footer() {
    val root: Root by inject()
    val fileScreen: FileScreenComponent by inject()
    val textScreen: TextScreenComponent by inject()
    val compareScreen: CompareFilesComponent by inject()
    AuroraDecorationArea(decorationAreaType = DecorationAreaType.Footer) {
        Box(Modifier.fillMaxWidth().auroraBackground().padding(6.dp), contentAlignment = Alignment.Center) {
            LabelProjection(
                contentModel = LabelContentModel(
                    text = when (root.childStack.subscribeAsState().value.active.instance) {
                        is Root.Child.File -> fileScreen.footerText
                        is Root.Child.Text -> textScreen.footerText
                        is Root.Child.CompareFiles -> compareScreen.footerText
                    }
                )
            ).project()
        }
    }
}
