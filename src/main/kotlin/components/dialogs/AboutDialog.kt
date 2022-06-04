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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.russellbanks.HashHash.BuildConfig
import data.GitHubData
import helper.GitHub
import helper.Icons
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandButtonPresentationModel
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.theming.AuroraSkin

@Composable
fun AboutDialog(
    visible: Boolean,
    onCloseRequest: () -> Unit,
    httpClient: HttpClient?,
    httpResponse: HttpResponse?,
    githubData: GitHubData?,
    onUpdateCheck: (HttpResponse) -> Unit
) {
    val scope = rememberCoroutineScope()
    var checkingGitHubAPI by remember { mutableStateOf(false) }
    var lastChecked: Instant? by remember { mutableStateOf(null) }
    val backgroundColorScheme = AuroraSkin.colors.getBackgroundColorScheme(
        decorationAreaType = AuroraSkin.decorationAreaType
    )
    TranslucentDialogOverlay(
        visible = visible,
        onClick = onCloseRequest
    )
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + slideInVertically(initialOffsetY = { -it / 10 }),
        exit = fadeOut() + slideOutVertically(targetOffsetY = { -it / 10 })
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Surface(
                modifier = Modifier.width(450.dp).height(310.dp),
                shape = RoundedCornerShape(8.dp),
                color = backgroundColorScheme.backgroundFillColor,
                border = BorderStroke(1.dp, Color.Black),
                elevation = 4.dp
            ) {
                Column {
                    Row(modifier = Modifier.weight(1f).fillMaxSize().padding(30.dp)) {
                        Box(Modifier.fillMaxHeight().padding(end = 30.dp)) {
                            Image(
                                painter = Icons.logo(),
                                contentDescription = "${BuildConfig.appName} logo",
                                modifier = Modifier.size(60.dp)
                            )
                        }
                        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                            SelectionContainer {
                                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                                    LabelProjection(
                                        contentModel = LabelContentModel(
                                            text = "${BuildConfig.appName} ${BuildConfig.appVersion}"
                                        ),
                                        presentationModel = LabelPresentationModel(
                                            textStyle = TextStyle(
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.SemiBold
                                            )
                                        )
                                    ).project()
                                    Column {
                                        LabelProjection(
                                            contentModel = LabelContentModel(
                                                text = "Runtime version: ${System.getProperty("java.runtime.version")}"
                                            ),
                                            presentationModel = LabelPresentationModel(
                                                textStyle = TextStyle(fontSize = 12.sp)
                                            )
                                        ).project()
                                        LabelProjection(
                                            contentModel = LabelContentModel(
                                                text = "VM: " + System.getProperty("java.vm.name") + " by " +
                                                    System.getProperty("java.vm.vendor")
                                            ),
                                            presentationModel = LabelPresentationModel(
                                                textStyle = TextStyle(fontSize = 12.sp)
                                            )
                                        ).project()
                                    }
                                }
                            }
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                CommandButtonProjection(
                                    contentModel = Command(
                                        text = "Check for Updates",
                                        action = {
                                            if (!checkingGitHubAPI) {
                                                scope.launch(Dispatchers.Default) {
                                                    httpClient?.run {
                                                        checkingGitHubAPI = true
                                                        onUpdateCheck(get(GitHub.HashHash.API.latest))
                                                        lastChecked = Clock.System.now()
                                                        checkingGitHubAPI = false
                                                    }
                                                }
                                            }
                                        }
                                    ),
                                    presentationModel = CommandButtonPresentationModel(
                                        textStyle = TextStyle(
                                            fontSize = 12.sp,
                                            textAlign = TextAlign.Center
                                        )
                                    )
                                ).project()
                                AnimatedVisibility(checkingGitHubAPI || httpResponse != null) {
                                    UpdateCheckText(
                                        checkingGitHubAPI = checkingGitHubAPI,
                                        httpResponse = httpResponse,
                                        gitHubData = githubData,
                                        lastChecked = lastChecked
                                    )
                                }
                            }
                        }
                    }
                    CloseDialogFooter(onCloseRequest = onCloseRequest)
                }
            }
        }
    }
}
