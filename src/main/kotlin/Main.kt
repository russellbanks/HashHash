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
import androidx.compose.foundation.isSystemInDarkTheme
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
import components.controlpane.ControlPane
import components.dialogs.AboutDialog
import components.dialogs.PreferencesDialog
import flowlayout.FlowColumn
import helper.Clipboard
import helper.FileUtils.openFileDialogAndGetResult
import helper.Time
import helper.mode.Mode
import helper.mode.ModeHandler
import helper.titlebar.TitleBar
import helper.titlebar.TitleBarHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.*
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.auroraApplication
import preferences.theme.ThemeHandler
import java.io.File
import java.text.SimpleDateFormat

fun main() = auroraApplication {
    var isAboutOpen by remember { mutableStateOf(false) }
    var isPreferencesOpen by remember { mutableStateOf(false) }
    var hashedOutput by remember { mutableStateOf("") }
    var timeBeforeHash: String? by remember { mutableStateOf(null) }
    var timeAfterHash: String? by remember { mutableStateOf(null) }
    var error: String? by remember { mutableStateOf(null) }
    var file: File? by remember { mutableStateOf(null) }
    val windowState = rememberWindowState(
        position = WindowPosition(Alignment.Center),
        size = DpSize(width = 1035.dp, height = 770.dp)
    )
    val themeHandler = ThemeHandler(isSystemInDarkTheme())
    var auroraSkin by remember { mutableStateOf(themeHandler.getAuroraTheme()) }
    val undecorated by remember { mutableStateOf(TitleBarHandler.getTitleBar() == TitleBar.Custom) }
    AuroraWindow(
        skin = auroraSkin,
        state = windowState,
        title = "HashHash",
        icon = painterResource(resourcePath = "hash.png"),
        onCloseRequest = ::exitApplication,
        menuCommands = Header.commands(
            openAction = {
                openFileDialogAndGetResult().also {
                    if (it != null && file != File(it)) {
                        file = File(it)
                        hashedOutput = ""
                        timeBeforeHash = null
                        timeAfterHash = null
                        error = null
                    }
                }
                         },
            preferencesAction = { isPreferencesOpen = true },
            quitAction = { exitApplication() },
            toggleFullScreenAction = {
                if (windowState.placement == WindowPlacement.Floating) {
                    windowState.placement = WindowPlacement.Fullscreen
                } else {
                    windowState.placement = WindowPlacement.Floating
                }
            },
            aboutAction = { isAboutOpen = true }
        ),
        undecorated = undecorated
    ) {
        var comparisonHash by remember { mutableStateOf("") }
        var algorithm: Algorithm by remember { mutableStateOf(Algorithm.MD5) }
        var isHashing by remember { mutableStateOf(false) }
        var timeTaken by remember { mutableStateOf("00:00") }
        val scope = rememberCoroutineScope()
        var hashProgress by remember { mutableStateOf(0F) }
        var mode by remember { mutableStateOf(ModeHandler.getMode()) }
        Box {
            Column {
                Row(Modifier.fillMaxSize().weight(1f)) {
                    ControlPane(
                        algorithm = algorithm,
                        mode = mode,
                        onTriggerModeChange = {
                            val newMode = if (mode == Mode.SIMPLE) Mode.ADVANCED else Mode.SIMPLE
                            ModeHandler.putMode(newMode)
                            mode = newMode
                                              },
                        onSoloAlgorithmClick = { item ->
                            if (item != algorithm) {
                                algorithm = item
                                hashedOutput = ""
                                timeBeforeHash = null
                                timeAfterHash = null
                                error = null
                            }
                        },
                        onSubAlgorithmClick = { nestedItem ->
                            if (nestedItem != algorithm) {
                                algorithm = nestedItem
                                hashedOutput = ""
                                timeBeforeHash = null
                                timeAfterHash = null
                                error = null
                            }
                        },
                        onSelectFileResult = { result ->
                            if (result != null && file != File(result)) {
                                file = File(result)
                                hashedOutput = ""
                                timeBeforeHash = null
                                timeAfterHash = null
                                error = null
                            }
                        },
                        onCalculateClick = {
                            file?.let {
                                if (it.exists() && !isHashing) {
                                    scope.launch(Dispatchers.IO) {
                                        isHashing = true
                                        Clock.System.now().also { instantAtStart ->
                                            timeBeforeHash = SimpleDateFormat("dd MMMM yyyy, HH:mm:ss").format(System.currentTimeMillis())
                                            try {
                                                hashedOutput = it.hash(
                                                    algorithm,
                                                    hashProgressCallback = { float -> hashProgress = float }
                                                ).uppercase()
                                                Clock.System.now().also { instantAtEnd ->
                                                    timeAfterHash = SimpleDateFormat("dd MMMM yyyy, HH:mm:ss").format(System.currentTimeMillis())
                                                    timeTaken = Time.formatElapsedTime(instantAtEnd - instantAtStart)
                                                }
                                            } catch (exception: Exception) {
                                                error = "Error: ${exception.localizedMessage.replaceFirstChar { char -> char.titlecase() }}"
                                            }
                                            isHashing = false
                                        }
                                    }
                                }
                            }
                        }
                    )
                    VerticalSeparatorProjection().project(Modifier.fillMaxHeight())
                    Column(Modifier.fillMaxSize()) {
                        FileInfoSection(
                            modifier = Modifier.defaultMinSize(minHeight = 120.dp).padding(horizontal = 20.dp),
                            file = file
                        )
                        HorizontalSeparatorProjection().project(Modifier.fillMaxWidth())
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
                                                    icon = Icons.Utility.copy(),
                                                    action = {
                                                        if (hashedOutput.isNotBlank()) Clipboard.setContent(hashedOutput)
                                                    }
                                                ),
                                                Command(
                                                    text = "Case",
                                                    icon = Icons.Utility.switch(),
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
                                                    icon = Icons.Utility.clipboard(),
                                                    action = {
                                                        runCatching { comparisonHash = Clipboard.readContent() }
                                                    }
                                                ),
                                                Command(
                                                    text = "Clear",
                                                    icon = Icons.Utility.eraser(),
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
                                            icon = if (hashesMatch) Icons.Utility.check() else Icons.Utility.cross()
                                        )
                                    ).project()
                                }
                            }
                            FlowColumn {
                                LabelProjection(contentModel = LabelContentModel(
                                    text = "Started at: ${if (timeBeforeHash != null) timeBeforeHash else "⎯"}")
                                ).project()
                                LabelProjection(contentModel = LabelContentModel(
                                    text = "Finished at: ${if (timeAfterHash != null) timeAfterHash else "⎯"}")
                                ).project()
                                LabelProjection(contentModel = LabelContentModel(
                                    text = "Time taken: ${if (timeAfterHash != null) timeTaken else "⎯"}")
                                ).project()
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
            PreferencesDialog(
                visible = isPreferencesOpen,
                themeHandler = themeHandler,
                onThemeChange = {
                    themeHandler.putTheme(it.first)
                    auroraSkin = it.second
                                },
                onCloseRequest = { isPreferencesOpen = false }
            )
            AboutDialog(visible = isAboutOpen, onCloseRequest = { isAboutOpen = false })
        }
    }
}
