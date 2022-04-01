import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.rememberWindowState
import com.appmattus.crypto.Algorithm
import components.*
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.IndeterminateLinearProgressProjection
import org.pushingpixels.aurora.component.projection.TextFieldValueProjection
import org.pushingpixels.aurora.theming.nightShadeSkin
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.auroraApplication
import theme.HashHashTheme
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
            var file by remember { mutableStateOf(FileUtils.emptyFile) }
            var timeBeforeHash by remember { mutableStateOf(SimpleDateFormat("dd MMMM yyyy, HH:mm:ss").format(System.currentTimeMillis())) }
            var timeAfterHash by remember { mutableStateOf(SimpleDateFormat("dd MMMM yyyy, HH:mm:ss").format(System.currentTimeMillis())) }
            var timeBeforeHashVisibility by remember { mutableStateOf(false) }
            var timeAfterHashVisibility by remember { mutableStateOf(false) }
            var timeTaken by remember { mutableStateOf("00:00") }

            Column(modifier = Modifier.fillMaxSize().padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(modifier = Modifier.height(80.dp), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    FileInfoCard(file)
                    AlgorithmButton(
                        modifier = Modifier.weight(0.2f).fillMaxHeight(),
                        algorithm = algorithm
                    ) { selectedAlgorithm ->
                        if (selectedAlgorithm != algorithm) {
                            algorithm = selectedAlgorithm
                            hashedOutput = ""
                        }
                    }
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
                                file = FileUtils.emptyFile
                                timeBeforeHashVisibility = false
                                timeAfterHashVisibility = false
                            }
                        )
                    ).project(Modifier.weight(0.1f))
                }
                ComparisonRow(
                    givenHash = comparisonHash,
                    onComparisonValueChange = { comparisonHash = it.text },
                    pasteAction = { comparisonHash = Clipboard.readContent() },
                    clearAction = { comparisonHash = "" }
                )
                Row(modifier = Modifier.height(50.dp), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    ProgressCard(hashedOutput, comparisonHash, hashTimer, timerVisible, file)
                    CalculateButton(
                        modifier = Modifier.weight(0.2f).fillMaxHeight(),
                        file = file,
                        timerCall = { hashTimer = "${it.first}${it.second}" },
                        hashCall = { job ->
                            timerVisible = true
                            System.nanoTime().also { nanosAtStart ->
                                timeBeforeHash = "Started at: ${SimpleDateFormat("dd MMMM yyyy, HH:mm:ss").format(System.currentTimeMillis())}"
                                timeBeforeHashVisibility = true
                                hashedOutput = file.hash(algorithm)
                                System.nanoTime().also { nanosAtEnd ->
                                    timeAfterHash = "Ended at: ${SimpleDateFormat("dd MMMM yyyy, HH:mm:ss").format(System.currentTimeMillis())}"
                                    timeTaken = "Time taken: ${Time.formatElapsedTime(nanosAtEnd - nanosAtStart)}"
                                }
                                timeAfterHashVisibility = true
                                job.cancel()
                                hashTimer = "00:00"
                                timerVisible = false
                            }
                        }
                    )
                }
                OutputConsole(
                    timeBeforeHashVisibility = timeBeforeHashVisibility,
                    timeAfterHashVisibility = timeAfterHashVisibility,
                    timeBeforeHash = timeBeforeHash,
                    timeAfterHash = timeAfterHash,
                    timeTaken = timeTaken,
                    hashedOutput = hashedOutput,
                    algorithm = algorithm,
                    file = file
                )
            }

            if (isFileManagerOpen) {
                FileUtils.FileDialog { chosenFile ->
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
