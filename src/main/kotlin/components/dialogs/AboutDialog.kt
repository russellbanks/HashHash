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
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.russellbanks.HashHash.BuildConfig
import data.GitHubData
import helper.GitHub
import helper.Icons
import io.klogging.logger
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.until
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandButtonPresentationModel
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.HorizontalSeparatorProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.theming.AuroraSkin
import java.text.SimpleDateFormat

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
    val logger = logger("About Dialog")
    var checkingGitHubAPI by remember { mutableStateOf(false) }
    var lastChecked: Instant? by remember { mutableStateOf(null) }
    TranslucentDialogOverlay(
        visible = visible,
        onClick = onCloseRequest
    )
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
                                        contentModel = LabelContentModel(text = "${BuildConfig.appName} ${BuildConfig.appVersion}"),
                                        presentationModel = LabelPresentationModel(
                                            textStyle = TextStyle(
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.SemiBold
                                            )
                                        )
                                    ).project()
                                    Column {
                                        LabelProjection(
                                            contentModel = LabelContentModel(text = "Runtime version: ${System.getProperty("java.runtime.version")}"),
                                            presentationModel = LabelPresentationModel(
                                                textStyle = TextStyle(fontSize = 12.sp)
                                            )
                                        ).project()
                                        LabelProjection(
                                            contentModel = LabelContentModel(text = "VM: ${System.getProperty("java.vm.name")} by ${System.getProperty("java.vm.vendor")}"),
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
                                                        logger.info("Retrieving GitHub data")
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
                                    val infiniteTransition = rememberInfiniteTransition()
                                    val rotationAngle by infiniteTransition.animateFloat(
                                        initialValue = 0f,
                                        targetValue = 360f,
                                        animationSpec = infiniteRepeatable(
                                            animation = tween(1000, easing = LinearEasing)
                                        )
                                    )
                                    SelectionContainer {
                                        Column {
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                if (checkingGitHubAPI) {
                                                    Image(
                                                        painter = Icons.Utility.refresh(),
                                                        contentDescription = null,
                                                        modifier = Modifier.size(16.dp).graphicsLayer { rotationZ = rotationAngle },
                                                        colorFilter = ColorFilter.tint(backgroundColorScheme.foregroundColor)
                                                    )
                                                }
                                                LabelProjection(
                                                    contentModel = LabelContentModel(
                                                        text = when {
                                                            checkingGitHubAPI -> "Checking"
                                                            githubData?.tagName?.contains(BuildConfig.appVersion) == true -> "You have the latest version"
                                                            githubData?.tagName?.contains(BuildConfig.appVersion) == false -> "Out of date. Latest version is ${githubData.tagName.removePrefix("v")}"
                                                            (httpResponse?.headers?.get("X-RateLimit-Remaining")?.toInt() ?: -1) == 0 -> {
                                                                var minutesLeft = httpResponse
                                                                    ?.headers // Retrieve headers array
                                                                    ?.get("X-RateLimit-Reset") // Retrieve rate-limit header
                                                                    ?.toLong() // Convert from String to Long for math
                                                                    ?.times(1000) // Convert seconds to milliseconds
                                                                    ?.let { Instant.fromEpochMilliseconds(it) } // Convert milliseconds to Instant
                                                                    ?.let { Clock.System.now().until(it, DateTimeUnit.MINUTE) } // Work out how long there is left from now until it resets
                                                                if (minutesLeft == 0L) minutesLeft = 1L // If there is less than 1 minute left, still say to check in a minute
                                                                "Rate limited. Check back in $minutesLeft ${if (minutesLeft == 1L) "minute" else "minutes"}"
                                                            }
                                                            httpResponse != null -> "${httpResponse.status} - Check back later"
                                                            else -> "Error accessing GitHub API"
                                                        }
                                                    ),
                                                    presentationModel = LabelPresentationModel(
                                                        textStyle = TextStyle(fontSize = 12.sp)
                                                    )
                                                ).project()
                                            }
                                            AnimatedVisibility(lastChecked != null) {
                                                LabelProjection(
                                                    contentModel = LabelContentModel(
                                                        text = "Last checked: ${SimpleDateFormat("dd MMMM yyyy, HH:mm:ss").format(lastChecked?.toEpochMilliseconds())}"
                                                    ),
                                                    presentationModel = LabelPresentationModel(
                                                        textStyle = TextStyle(fontSize = 12.sp)
                                                    )
                                                ).project()
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Column {
                        HorizontalSeparatorProjection().project(Modifier.fillMaxWidth())
                        CommandButtonProjection(
                            contentModel = Command(
                                text = "Close",
                                action = onCloseRequest
                            ),
                            presentationModel = CommandButtonPresentationModel(
                                textStyle = TextStyle(
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.Center
                                ),
                                horizontalGapScaleFactor = 1.8f,
                                verticalGapScaleFactor = 1.5f
                            )
                        ).project(Modifier.width(150.dp).align(Alignment.End).padding(20.dp))
                    }
                }
            }
        }
    }
}
