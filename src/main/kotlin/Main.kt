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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import com.appmattus.crypto.Algorithm
import components.FileInfoSection
import components.Footer
import components.Header
import components.Mode
import components.controlpane.ControlPane
import components.dialogs.AboutDialog
import components.dialogs.TranslucentDialogOverlay
import flowlayout.FlowColumn
import helper.Clipboard
import helper.FileUtils
import helper.FileUtils.openFileDialogAndGetResult
import helper.Time
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CommandButtonStripProjection
import org.pushingpixels.aurora.component.projection.HorizontalSeparatorProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.component.projection.TextFieldStringProjection
import org.pushingpixels.aurora.theming.nightShadeSkin
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.auroraApplication
import java.io.File
import java.text.SimpleDateFormat

fun main() = auroraApplication {
    var isAboutOpen by remember { mutableStateOf(false) }
    var file by remember { mutableStateOf(FileUtils.emptyFile) }
    val windowState = rememberWindowState(
        position = WindowPosition(Alignment.Center),
        size = DpSize(width = 1035.dp, height = 770.dp)
    )
    var error: String? by remember { mutableStateOf(null) }
    AuroraWindow(
        skin = nightShadeSkin(),
        state = windowState,
        title = "HashHash",
        icon = painterResource(resourcePath = "hash.png"),
        onCloseRequest = ::exitApplication,
        menuCommands = Header.commands(
            openAction = {
                openFileDialogAndGetResult().also {
                    if (it != null) file = File(it)
                    error = null
                }
                         },
            quitAction = { exitApplication() },
            toggleFullScreenAction = {
                if (windowState.placement == WindowPlacement.Floating) {
                    windowState.placement = WindowPlacement.Fullscreen
                } else {
                    windowState.placement = WindowPlacement.Floating
                }
            },
            aboutAction = { isAboutOpen = true }
        )
    ) {
        var hashedOutput by remember { mutableStateOf("") }
        var comparisonHash by remember { mutableStateOf("") }
        var algorithm: Algorithm by remember { mutableStateOf(Algorithm.MD5) }
        var hashTimer by remember { mutableStateOf("00:00") }
        var isHashing by remember { mutableStateOf(false) }
        var timeBeforeHash by remember { mutableStateOf(SimpleDateFormat("dd MMMM yyyy, HH:mm:ss").format(System.currentTimeMillis())) }
        var timeAfterHash by remember { mutableStateOf(SimpleDateFormat("dd MMMM yyyy, HH:mm:ss").format(System.currentTimeMillis())) }
        var timeBeforeHashVisibility by remember { mutableStateOf(false) }
        var timeAfterHashVisibility by remember { mutableStateOf(false) }
        var timeTaken by remember { mutableStateOf("00:00") }
        val scope = rememberCoroutineScope()
        var hashProgress by remember { mutableStateOf(0F) }
        var mode by remember { mutableStateOf(Mode.SIMPLE) }

        Box {
            Column {
                Row(Modifier.fillMaxSize().weight(1f)) {
                    ControlPane(
                        algorithm = algorithm,
                        mode = mode,
                        onTriggerModeChange = { mode = if (mode == Mode.SIMPLE) Mode.ADVANCED else Mode.SIMPLE },
                        onSoloAlgorithmClick = { item ->
                            if (item != algorithm) {
                                algorithm = item
                                hashedOutput = ""
                                error = null
                                timeBeforeHashVisibility = false
                                timeAfterHashVisibility = false
                            }
                        },
                        onSubAlgorithmClick = { nestedItem ->
                            if (nestedItem != algorithm) {
                                algorithm = nestedItem
                                hashedOutput = ""
                                error = null
                                timeBeforeHashVisibility = false
                                timeAfterHashVisibility = false
                            }
                        },
                        onSelectFileResult = { result ->
                            if (result != null) file = File(result)
                            error = null
                        },
                        onCalculateClick = {
                            if (file.exists() && file != FileUtils.emptyFile && !isHashing) {
                                val job = scope.launch {
                                    flow {
                                        System.currentTimeMillis().also { millisAtStart ->
                                            while (currentCoroutineContext().isActive) {
                                                delay(1000)
                                                emit(System.currentTimeMillis() - millisAtStart)
                                            }
                                        }
                                    }.collect { milliseconds ->
                                        val minutes = "%02d".format((milliseconds / 1000) / 60)
                                        val seconds = "%02d".format((milliseconds / 1000) % 60)
                                        hashTimer = "$minutes:$seconds"
                                    }
                                }
                                scope.launch(Dispatchers.IO) {
                                    isHashing = true
                                    System.nanoTime().also { nanosAtStart ->
                                        timeBeforeHash = SimpleDateFormat("dd MMMM yyyy, HH:mm:ss").format(System.currentTimeMillis())
                                        timeBeforeHashVisibility = true
                                        try {
                                            hashedOutput = file.hash(
                                                algorithm,
                                                hashProgressCallback = { hashProgress = it }
                                            ).uppercase()
                                            System.nanoTime().also { nanosAtEnd ->
                                                timeAfterHash = SimpleDateFormat("dd MMMM yyyy, HH:mm:ss").format(System.currentTimeMillis())
                                                timeTaken = Time.formatElapsedTime(nanosAtEnd - nanosAtStart)
                                            }
                                            timeAfterHashVisibility = true
                                        } catch (exception: Exception) {
                                            error = "Error: ${exception.localizedMessage.replaceFirstChar { it.titlecase() }}"
                                        }
                                        job.cancel()
                                        hashTimer = "00:00"
                                        isHashing = false
                                    }
                                }
                            }
                        }
                    )
                    Column(Modifier.fillMaxSize()) {
                        FileInfoSection(
                            modifier = Modifier.defaultMinSize(minHeight = 120.dp).padding(horizontal = 20.dp),
                            file = file
                        )
                        HorizontalSeparatorProjection().project(Modifier.fillMaxWidth())
                        Column {
                            Column(
                                modifier = Modifier.weight(1f).padding(20.dp),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                    LabelProjection(
                                        contentModel = LabelContentModel(text = "${algorithm.algorithmName} Hash"),
                                        presentationModel = LabelPresentationModel(
                                            textStyle = TextStyle(
                                                fontSize = 15.sp,
                                                fontWeight = FontWeight.SemiBold
                                            )
                                        )
                                    ).project()
                                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                        Box(Modifier.weight(1f)) {
                                            TextFieldStringProjection(
                                                contentModel = TextFieldStringContentModel(
                                                    value = hashedOutput,
                                                    placeholder = "Output Hash",
                                                    readOnly = true,
                                                    onValueChange = {}
                                                )
                                            ).project(Modifier.fillMaxWidth())
                                        }
                                        CommandButtonStripProjection(
                                            contentModel = CommandGroup(
                                                commands = listOf(
                                                    Command(
                                                        text = "Copy",
                                                        icon = painterResource(resourcePath = "copy.png"),
                                                        action = {
                                                            if (hashedOutput.isNotBlank()) Clipboard.setContent(hashedOutput)
                                                        }
                                                    ),
                                                    Command(
                                                        text = "Case",
                                                        icon = painterResource(resourcePath = "switch.png"),
                                                        action = {
                                                            hashedOutput = if (hashedOutput == hashedOutput.uppercase()) {
                                                                hashedOutput.lowercase()
                                                            } else {
                                                                hashedOutput.uppercase()
                                                            }
                                                        }
                                                    )
                                                )
                                            ),
                                            presentationModel = CommandStripPresentationModel(
                                                orientation = StripOrientation.Horizontal,
                                                commandPresentationState = CommandButtonPresentationState.Medium
                                            )
                                        ).project()
                                    }
                                }
                                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                    LabelProjection(
                                        contentModel = LabelContentModel(text = "Comparison Hash"),
                                        presentationModel = LabelPresentationModel(
                                            textStyle = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                                        )
                                    ).project()
                                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                        Box(Modifier.weight(1f)) {
                                            TextFieldStringProjection(
                                                contentModel = TextFieldStringContentModel(
                                                    value = comparisonHash,
                                                    placeholder = "Comparison Hash",
                                                    onValueChange = { comparisonHash = it }
                                                )
                                            ).project(Modifier.fillMaxWidth())
                                        }
                                        CommandButtonStripProjection(
                                            contentModel = CommandGroup(
                                                commands = listOf(
                                                    Command(
                                                        text = "Paste",
                                                        icon = painterResource(resourcePath = "paste.png"),
                                                        action = {
                                                            runCatching { comparisonHash = Clipboard.readContent() }
                                                        }
                                                    ),
                                                    Command(
                                                        text = "Clear",
                                                        icon = painterResource(resourcePath = "eraser.png"),
                                                        action = { comparisonHash = "" }
                                                    )
                                                )
                                            ),
                                            presentationModel = CommandStripPresentationModel(
                                                orientation = StripOrientation.Horizontal,
                                                commandPresentationState = CommandButtonPresentationState.Medium
                                            )
                                        ).project()
                                    }
                                    val areTextFieldsBlank = hashedOutput.isNotBlank() && comparisonHash.isNotBlank()
                                    AnimatedVisibility(visible = areTextFieldsBlank) {
                                        val hashesMatch = areTextFieldsBlank && hashedOutput.equals(comparisonHash, true)
                                        LabelProjection(
                                            contentModel = LabelContentModel(
                                                text = "Hashes${if (!hashesMatch) " do not" else ""} match",
                                                icon = painterResource(resourcePath = "${if (hashesMatch) "check" else "cross"}.png")
                                            )
                                        ).project()
                                    }
                                }
                                FlowColumn {
                                    LabelProjection(contentModel = LabelContentModel(
                                        text = "Started at: ${if (timeBeforeHashVisibility) timeBeforeHash else "⎯"}")
                                    ).project()
                                    LabelProjection(contentModel = LabelContentModel(
                                        text = "Finished at: ${if (timeAfterHashVisibility) timeAfterHash else "⎯"}")
                                    ).project()
                                    LabelProjection(contentModel = LabelContentModel(
                                        text = "Time taken: ${if (timeAfterHashVisibility) timeTaken else "⎯"}")
                                    ).project()
                                }
                            }
                        }
                    }
                }
                Footer(
                    error = error,
                    hashedOutput = hashedOutput,
                    isHashing = isHashing,
                    hashProgress = hashProgress,
                    file = file
                )
            }
            TranslucentDialogOverlay(visible = isAboutOpen, onClick = { isAboutOpen = false})
            AboutDialog(visible = isAboutOpen, onCloseRequest = { isAboutOpen = false })
        }
    }
}
