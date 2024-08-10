/**

HashHash
Copyright (C) 2024 Russell Banks

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

package components.dialogs.about

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import api.GitHubImpl
import com.russellbanks.HashHash.BuildConfig
import components.dialogs.Dialog
import components.dialogs.DialogState
import helper.Icons
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandButtonPresentationModel
import org.pushingpixels.aurora.component.projection.CommandButtonProjection

@Composable
fun AboutDialog() {
    val scope = rememberCoroutineScope(Dispatchers::IO)
    Dialog(dialog = DialogState.Dialogs.About) {
        Row(Modifier.padding(30.dp)) {
            Box(Modifier.padding(end = 30.dp)) {
                Image(
                    painter = Icons.logo(),
                    contentDescription = "${BuildConfig.appName} logo",
                    modifier = Modifier.size(60.dp)
                )
            }
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                JVMInformationText()
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    CommandButtonProjection(
                        contentModel = Command(
                            text = "Check for Updates",
                            action = {
                                scope.launch {
                                    GitHubImpl.checkForHashHashUpdate()
                                }
                            }
                        ),
                        presentationModel = CommandButtonPresentationModel(
                            textStyle = TextStyle(fontSize = 12.sp, textAlign = TextAlign.Center)
                        )
                    ).project()
                    AnimatedVisibility(GitHubImpl.checkingGitHubAPI || GitHubImpl.latestRelease != null) {
                        UpdateCheckText()
                    }
                }
            }
        }
    }
}
