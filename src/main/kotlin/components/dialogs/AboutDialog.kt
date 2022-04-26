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

package components.dialogs

import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import components.dialogs.about.NavHostComponent
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.HorizontalSeparatorProjection
import org.pushingpixels.aurora.theming.AuroraSkin

@Composable
fun AboutDialog(
    visible: Boolean,
    onCloseRequest: () -> Unit
) {
    val backgroundColorScheme = AuroraSkin.colors.getBackgroundColorScheme(
        decorationAreaType = AuroraSkin.decorationAreaType
    )
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + slideInVertically(initialOffsetY = { -it / 10 }),
        exit = fadeOut() + slideOutVertically(targetOffsetY = { -it / 10 })
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Surface(
                modifier = Modifier.width(450.dp).height(350.dp),
                shape = RoundedCornerShape(8.dp),
                color = backgroundColorScheme.backgroundFillColor,
                border = BorderStroke(1.dp, Color.Black),
                elevation = 4.dp
            ) {
                Column {
                    Column(modifier = Modifier
                        .weight(1f)
                        .padding(30.dp)
                    ) {
                        remember {
                            DefaultComponentContext(LifecycleRegistry()).let(::NavHostComponent)
                        }.render()
                    }
                    Column {
                        HorizontalSeparatorProjection().project(Modifier.fillMaxWidth())
                        CommandButtonProjection(
                            contentModel = Command(
                                text = "Close",
                                action = onCloseRequest
                            )
                        ).project(Modifier.width(150.dp).align(Alignment.End).padding(20.dp))
                    }
                }
            }
        }
    }
}