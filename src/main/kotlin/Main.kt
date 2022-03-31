import FileUtils.emptyFile
import FileUtils.getFileName
import FileUtils.getFormattedBytes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.AwtWindow
import androidx.compose.ui.window.rememberWindowState
import com.appmattus.crypto.Algorithm
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import org.pushingpixels.aurora.component.AuroraVerticalScrollbar
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.*
import org.pushingpixels.aurora.theming.auroraBackground
import org.pushingpixels.aurora.theming.nightShadeSkin
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.auroraApplication
import theme.HashHashTheme
import java.awt.FileDialog
import java.awt.Frame
import java.io.File
import java.text.SimpleDateFormat

fun main() = auroraApplication {
    var isFileManagerOpen by remember { mutableStateOf(false) }
    var isAboutWindowOpen by remember { mutableStateOf(false) }
    AuroraWindow(
        skin = nightShadeSkin(),
        title = "HashHash",
        icon = painterResource(resourcePath = "hash.png"),
        undecorated = true,
        onCloseRequest = ::exitApplication,
        menuCommands = CommandGroup(
            commands = listOf(
                Command(
                    text = "File",
                    secondaryContentModel = CommandMenuContentModel(
                        CommandGroup(
                            commands = listOf(
                                Command(
                                    text = "Open",
                                    action = { isFileManagerOpen = true }
                                )
                            )
                        )
                    )
                ),
                Command(
                    text = "About",
                    action = { isAboutWindowOpen = true }
                )
            )
        )
    ) {
        HashHashTheme {
            var hashedOutput by remember { mutableStateOf("") }
            var comparisonHash by remember { mutableStateOf("") }
            var algorithm: Algorithm by remember { mutableStateOf(Algorithm.MD5) }
            var hashTimer by remember { mutableStateOf("00:00") }
            var timerVisible by remember { mutableStateOf(false) }
            var file by remember { mutableStateOf(emptyFile) }
            val scope = rememberCoroutineScope()
            var timeBeforeHash by remember { mutableStateOf(SimpleDateFormat("dd MMMM yyyy, HH:mm:ss").format(System.currentTimeMillis())) }
            var timeAfterHash by remember { mutableStateOf(SimpleDateFormat("dd MMMM yyyy, HH:mm:ss").format(System.currentTimeMillis())) }
            var timeBeforeHashVisibility by remember { mutableStateOf(false) }
            var timeAfterHashVisibility by remember { mutableStateOf(false) }
            var timeTaken by remember { mutableStateOf("00:00") }

            Column(modifier = Modifier.fillMaxSize().padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(modifier = Modifier.height(80.dp), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    FileInfoCard(file)
                    CommandButtonProjection(
                        contentModel = Command(
                            text = algorithm.algorithmName,
                            icon = painterResource(resourcePath = "algorithm.png"),
                            secondaryContentModel = CommandMenuContentModel(
                                group = CommandGroup(
                                    commands = Menus.cascadingAlgorithmMenu() {
                                        if (it != algorithm) {
                                            algorithm = it
                                            hashedOutput = ""
                                        }
                                    }
                                )
                            )
                        ),
                        presentationModel = CommandButtonPresentationModel(
                            presentationState = CommandButtonPresentationState.Big,
                            popupMenuPresentationModel = CommandPopupMenuPresentationModel(maxVisibleMenuCommands = 8)
                        )
                    ).project(Modifier.weight(0.2f).fillMaxHeight())
                }
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    TextFieldValueProjection(
                        contentModel = TextFieldValueContentModel(
                            value = TextFieldValue(annotatedString = AnnotatedString(text = hashedOutput.uppercase())),
                            placeholder = "${algorithm.algorithmName} Hash",
                            readOnly = true,
                            onValueChange = {}
                        ),
                        presentationModel = TextFieldPresentationModel( showBorder = false)
                    ).project(Modifier.fillMaxWidth(0.8f))
                    CommandButtonProjection(
                        contentModel = Command(
                            text = "Copy",
                            icon = painterResource(resourcePath = "copy.png"),
                            action = {
                                if (hashedOutput.isNotBlank()) Clipboard.setContent(hashedOutput.uppercase())
                            }
                        )
                    ).project(Modifier.weight(0.1f))
                    CommandButtonProjection(
                        contentModel = Command(
                            text = "Clear",
                            icon = painterResource(resourcePath = "eraser.png"),
                            action = {
                                hashedOutput = ""
                                file = emptyFile
                                timeBeforeHashVisibility = false
                                timeAfterHashVisibility = false
                            }
                        )
                    ).project(Modifier.weight(0.1f))
                }
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    TextFieldValueProjection(
                        contentModel = TextFieldValueContentModel(
                            value = TextFieldValue(annotatedString = AnnotatedString(text = comparisonHash)),
                            placeholder = "Comparison Hash",
                            onValueChange = { comparisonHash = it.text }
                        ),
                        presentationModel = TextFieldPresentationModel(showBorder = false)
                    ).project(Modifier.fillMaxWidth(0.8f))
                    CommandButtonProjection(
                        contentModel = Command(
                            text = "Paste",
                            icon = painterResource(resourcePath = "paste.png"),
                            action = { comparisonHash = Clipboard.readContent() }
                        )
                    ).project(Modifier.weight(0.1f))
                    CommandButtonProjection(
                        contentModel = Command(
                            text = "Clear",
                            icon = painterResource(resourcePath = "eraser.png"),
                            action = { comparisonHash = "" }
                        )
                    ).project(Modifier.weight(0.1f))
                }
                Row(modifier = Modifier.height(50.dp), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    ProgressCard(hashedOutput, comparisonHash, hashTimer, timerVisible, file)
                    CommandButtonProjection(
                        contentModel = Command(
                            text = "Calculate",
                            action = {
                                if (file.exists()) {
                                    if (file != emptyFile) {
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
                                            timerVisible = true
                                            System.nanoTime().also { nanosAtStart ->
                                                timeBeforeHash = "Started at: " + SimpleDateFormat("dd MMMM yyyy, HH:mm:ss").format(System.currentTimeMillis())
                                                timeBeforeHashVisibility = true
                                                hashedOutput = file.hash(algorithm)
                                                System.nanoTime().also { nanosAtEnd ->
                                                    timeAfterHash = "Ended at: " + SimpleDateFormat("dd MMMM yyyy, HH:mm:ss").format(System.currentTimeMillis())
                                                    timeTaken = "Time taken: ${Time.formatElapsedTime(nanosAtEnd - nanosAtStart)}"
                                                }
                                                timeAfterHashVisibility = true
                                                job.cancel()
                                                hashTimer = "00:00"
                                                timerVisible = false
                                            }
                                        }
                                    }
                                }
                            }
                        ),
                        presentationModel = CommandButtonPresentationModel(
                            presentationState = CommandButtonPresentationState.Medium
                        )
                    ).project(Modifier.weight(0.2f).fillMaxHeight())
                }
                OutputConsole(timeBeforeHashVisibility, timeAfterHashVisibility, timeBeforeHash, timeAfterHash, timeTaken, hashedOutput, algorithm, file)
            }

            if (isFileManagerOpen) {
                FileDialog { chosenFile ->
                    isFileManagerOpen = false
                    hashedOutput = ""
                    timeBeforeHashVisibility = false
                    timeAfterHashVisibility = false
                    file = chosenFile
                }
            }

            if (isAboutWindowOpen) {
                AuroraWindow(
                    skin = nightShadeSkin(),
                    onCloseRequest = { isAboutWindowOpen = false },
                    state = rememberWindowState(width = 400.dp, height = 400.dp),
                    title = "About",
                    icon = painterResource(resourcePath = "hash.png"),
                    undecorated = true,
                    resizable = false
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("HashHash")
                    }
                }
            }
        }
    }
}

@Composable
fun HashTimer(timerVisible: Boolean, hashTimer: String) {
    AnimatedVisibility(visible = timerVisible, enter = fadeIn(), exit = fadeOut()) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = hashTimer, fontSize = 20.sp)
            IndeterminateLinearProgressProjection().project(Modifier.fillMaxWidth())
        }
    }
}

@Composable
fun FileDialog(
    parent: Frame? = null,
    onCloseRequest: (result: File) -> Unit
) = AwtWindow(
    create = {
        object : FileDialog(parent, "Choose a file", LOAD) {
            override fun setVisible(value: Boolean) {
                super.setVisible(value)
                if (value) {
                    if (files.isNotEmpty()) onCloseRequest(files[0])
                }
            }
        }
    },
    dispose = FileDialog::dispose
)

@Composable
fun FileInfoCard(file: File) {
    Row(modifier = Modifier.fillMaxWidth(0.8f).clip(RoundedCornerShape(4.dp)).auroraBackground().padding(14.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Image(painter = painterResource(resourcePath = FileUtils.getFileIcon(file)), contentDescription = null)
        Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
            SelectionContainer { Text(text = getFileName(file), fontSize = 20.sp) }
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                SelectionContainer { Text(text = FileUtils.getFileType(file)) }
                VerticalSeparatorProjection().project(modifier = Modifier.height(20.dp))
                SelectionContainer { Text(text = getFormattedBytes(file)) }
            }
        }
    }
}

@Composable
fun OutputConsole(
    timeBeforeHashVisibility: Boolean,
    timeAfterHashVisibility: Boolean,
    timeBeforeHash: String,
    timeAfterHash: String,
    timeTaken: String,
    hashedOutput: String,
    algorithm: Algorithm,
    file: File
) {
    val fontSize = 13.sp
    val fileInfo = listOf(
        "File path and name: ${file.absolutePath}",
        "Name: ${file.name}",
        "Type: ${file.extension}",
        "Size: ${getFormattedBytes(file.length())}",
        "Last modified: ${SimpleDateFormat("dd MMMM yyyy, HH:mm:ss").format(file.lastModified())}",
        "${algorithm.algorithmName}: $hashedOutput"
    )
    val timeList = listOf(timeBeforeHash, timeAfterHash, timeTaken)
    Column(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(4.dp)).auroraBackground().padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Console output")
        SelectionContainer {
            Box(Modifier.fillMaxSize()) {
                val lazyListState = rememberLazyListState()
                LazyColumn (
                    modifier = Modifier.fillMaxSize().border(1.dp, Color.Gray, RoundedCornerShape(4.dp)).padding(horizontal = 10.dp),
                    state = lazyListState,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    item { Spacer(Modifier.size(4.dp)) }
                    items(fileInfo) {
                        androidx.compose.animation.AnimatedVisibility(visible = file != emptyFile, enter = fadeIn(), exit = fadeOut()) {
                            Text(text = it, fontSize = fontSize)
                        }
                    }
                    item {
                        androidx.compose.animation.AnimatedVisibility(visible = timeBeforeHashVisibility, enter = fadeIn(), exit = fadeOut()) {
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                HorizontalSeparatorProjection().project(Modifier.fillMaxWidth().padding(vertical = 4.dp))
                            }
                        }
                    }
                    items(timeList) {
                        androidx.compose.animation.AnimatedVisibility(visible = timeAfterHashVisibility, enter = fadeIn(), exit = fadeOut()) {
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text(text = it, fontSize = fontSize)
                            }
                        }
                    }
                    item { Spacer(Modifier.size(4.dp)) }
                }
                AuroraVerticalScrollbar(
                    modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight().padding(2.dp),
                    adapter = rememberScrollbarAdapter(scrollState = lazyListState)
                )
            }
        }
    }
}

@Composable
fun ProgressCard(
    hashedOutput: String,
    comparisonHash: String,
    hashTimer: String,
    timerVisible: Boolean,
    file: File
) {
    Row(
        modifier = Modifier.fillMaxWidth(0.8f).height(50.dp).clip(RoundedCornerShape(4.dp)).auroraBackground(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(if (hashedOutput.isNotBlank() && comparisonHash.isNotBlank()) 0.5f else 1f),
            contentAlignment = Alignment.Center
        ) {
            Crossfade(targetState = timerVisible) { isHashing ->
                if (isHashing) {
                    HashTimer(timerVisible = true, hashTimer = hashTimer)
                } else {
                    Text(
                        modifier = Modifier.padding(14.dp),
                        text = if (hashedOutput.isNotBlank()) "Done!" else if (file != emptyFile) "No hash" else "No file selected"
                    )
                }
            }
        }
        AnimatedVisibility(
            visible = hashedOutput.isNotBlank() && comparisonHash.isNotBlank(),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            VerticalSeparatorProjection().project(Modifier.fillMaxHeight())
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                Text(
                    modifier = Modifier.padding(14.dp),
                    text = if (hashedOutput.isNotBlank() && comparisonHash.isNotBlank() && hashedOutput.lowercase() == comparisonHash.lowercase()) "Hashes match" else  "Hashes do not match"
                )
                Image(
                    painter = painterResource(
                        resourcePath = if (hashedOutput.isNotBlank() && comparisonHash.isNotBlank() && hashedOutput.lowercase() == comparisonHash.lowercase()) "check.png" else "cross.png"
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(14.dp)
                )
            }
        }
    }
}