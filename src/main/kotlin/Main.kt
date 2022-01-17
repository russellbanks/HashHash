import FileUtils.emptyFile
import FileUtils.getFileName
import FileUtils.getFormattedBytes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.AwtWindow
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import com.appmattus.crypto.Algorithm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.pushingpixels.aurora.component.AuroraVerticalScrollbar
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.*
import org.pushingpixels.aurora.theming.BackgroundAppearanceStrategy
import org.pushingpixels.aurora.theming.auroraBackground
import org.pushingpixels.aurora.theming.nightShadeSkin
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.auroraApplication
import theme.HashHashTheme
import java.awt.FileDialog
import java.awt.Frame
import java.io.File
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit


fun main() = auroraApplication {
    var isFileManagerOpen by remember { mutableStateOf(false) }
    val state = rememberWindowState(
        position = WindowPosition.Aligned(Alignment.Center),
    )
    AuroraWindow(
        skin = nightShadeSkin(),
        state = state,
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
                                ),
                            )
                        )
                    )
                ),
                Command(
                    text = "About",
                    action = {}
                )
            )
        )
    ) {
        HashHashTheme {
            var hashedOutput by remember { mutableStateOf("") }
            var comparisonHash by remember { mutableStateOf("") }
            var algorithm: Algorithm by remember { mutableStateOf(Algorithm.MD5) }
            val snackbarHostState = remember { SnackbarHostState() }
            var hashTimer by remember { mutableStateOf("00:00") }
            var timerVisible by remember { mutableStateOf(false) }
            var file by remember { mutableStateOf(emptyFile) }
            val scope = rememberCoroutineScope()
            var timeBeforeHash by remember { mutableStateOf(SimpleDateFormat("dd MMMM yyyy, HH:mm:ss").format(System.currentTimeMillis())) }
            var timeAfterHash by remember { mutableStateOf(SimpleDateFormat("dd MMMM yyyy, HH:mm:ss").format(System.currentTimeMillis())) }
            var timeBeforeHashVisibility by remember { mutableStateOf(false) }
            var timeAfterHashVisibility by remember { mutableStateOf(false) }
            var timeTaken by remember { mutableStateOf("00:00") }

            Box(Modifier.fillMaxSize()) {
                SnackbarHost(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    hostState = snackbarHostState
                ) { snackbarData: SnackbarData ->
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(1.dp, Color.Black),
                        modifier = Modifier.padding(16.dp).wrapContentSize()
                    ) {
                        Row(
                            modifier = Modifier.padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Image(
                                painter = painterResource(resourcePath = "check.png"),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                            Text(text = snackbarData.message)
                        }
                    }
                }
            }

            Column(
                modifier = Modifier.fillMaxSize().padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                FileInfoCard(file)
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
                                if (hashedOutput.isNotBlank()) {
                                    Clipboard.setContent(hashedOutput.uppercase())
                                    scope.launch { snackbarHostState.showSnackbar("Copied to clipboard") }
                                }
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
                                if (file != emptyFile) {
                                    val job = scope.launch {
                                        flow {
                                            System.currentTimeMillis().also { millisAtStart ->
                                                while (true) {
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
                        ),
                        presentationModel = CommandButtonPresentationModel(
                            presentationState = CommandButtonPresentationState.Medium
                        )
                    ).project(Modifier.weight(0.2f).fillMaxHeight())
                }
                OutputConsole(timeBeforeHashVisibility, timeAfterHashVisibility, timeBeforeHash, timeAfterHash, timeTaken, hashedOutput, algorithm, file)
                CommandButtonProjection(
                    contentModel = Command(
                        text = algorithm.algorithmName,
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
                        popupMenuPresentationModel = CommandPopupMenuPresentationModel(maxVisibleMenuCommands = 8)
                    )
                ).project()
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
    Box(modifier = Modifier.fillMaxWidth().height(100.dp).clip(RoundedCornerShape(4.dp)).auroraBackground()) {
        Row(modifier = Modifier.padding(14.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
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
    SelectionContainer(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(4.dp)).auroraBackground().padding(14.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            AnimatedVisibility(visible = file != emptyFile, enter = fadeIn(), exit = fadeOut()) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(text = "File path and name: ${file.absolutePath}", fontSize = fontSize)
                    Text(text = "Name: ${file.name}", fontSize = fontSize)
                    Text(text = "Type: ${file.extension}", fontSize = fontSize)
                    Text(text = "Size: ${getFormattedBytes(file.length())}", fontSize = fontSize)
                    Text(text = "Last modified: ${SimpleDateFormat("dd MMMM yyyy, HH:mm:ss").format(file.lastModified())}", fontSize = fontSize)
                    Text(text = "${algorithm.algorithmName}: $hashedOutput", fontSize = fontSize)
                }
            }
            AnimatedVisibility(visible = timeBeforeHashVisibility, enter = fadeIn(), exit = fadeOut()) {
                Text(text = timeBeforeHash, fontSize = fontSize)
            }
            AnimatedVisibility(visible = timeAfterHashVisibility, enter = fadeIn(), exit = fadeOut()) {
                Text(text = timeAfterHash, fontSize = fontSize)
            }
            AnimatedVisibility(visible = timeAfterHashVisibility, enter = fadeIn(), exit = fadeOut()) {
                Text(text = timeTaken, fontSize = fontSize)
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