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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import api.Ktor
import components.dialogs.DialogState
import helper.Icons
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandButtonPresentationModel
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.VerticalSeparatorProjection
import org.pushingpixels.aurora.theming.BackgroundAppearanceStrategy
import org.pushingpixels.aurora.theming.DecorationAreaType
import org.pushingpixels.aurora.theming.IconFilterStrategy
import org.pushingpixels.aurora.theming.auroraBackground
import org.pushingpixels.aurora.window.AuroraDecorationArea

@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    iconDimension: Dp = 16.dp,
    dialogState: DialogState,
    ktor: Ktor
) {
    AuroraDecorationArea(decorationAreaType = DecorationAreaType.Toolbar) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .auroraBackground()
                .padding(horizontal = 8.dp, vertical = 6.dp)
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // TODO
            }
            Row {
                AnimatedVisibility(ktor.isUpdateAvailable()) {
                    CommandButtonProjection(
                        contentModel = Command(
                            text = "",
                            icon = Icons.Utility.info(),
                            action = dialogState.Update()::open
                        ),
                        presentationModel = CommandButtonPresentationModel(
                            backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                            iconDimension = iconDimension,
                            iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                            iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText
                        )
                    ).project()
                }
                Spacer(modifier = Modifier.width(4.dp))
                VerticalSeparatorProjection().project(modifier = Modifier.height(20.dp))
                Spacer(modifier = Modifier.width(4.dp))
                CommandButtonProjection(
                    contentModel = Command(
                        text = "",
                        icon = Icons.Utility.settings(),
                        action = dialogState.Settings()::open
                    ),
                    presentationModel = CommandButtonPresentationModel(
                        backgroundAppearanceStrategy = BackgroundAppearanceStrategy.Flat,
                        iconDimension = iconDimension,
                        iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                        iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText
                    )
                ).project()
            }
        }
    }
}
