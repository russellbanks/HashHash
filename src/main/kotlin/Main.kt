import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import com.appmattus.crypto.Algorithm
import components.*
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandGroup
import org.pushingpixels.aurora.component.model.CommandMenuContentModel
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.theming.nightShadeSkin
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.auroraApplication
import theme.HashHashTheme
import java.awt.Desktop
import java.net.URI
import java.text.SimpleDateFormat

fun main() = auroraApplication {
    var isFileManagerOpen by remember { mutableStateOf(false) }
    var isAboutWindowOpen by remember { mutableStateOf(false) }
    AuroraWindow(
        skin = nightShadeSkin(),
        state = WindowState(position = WindowPosition(Alignment.Center)),
        title = "HashHash",
        icon = painterResource(resourcePath = "hash.png"),
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
                    text = "Help",
                    secondaryContentModel = CommandMenuContentModel(
                        CommandGroup(
                            commands = listOf(
                                Command(
                                    text = "Go to GitHub",
                                    action = { openWebpage(URI("https://github.com/russellbanks/hashhash")) }
                                ),
                                Command(
                                    text = "About",
                                    action = { isAboutWindowOpen = true }
                                )
                            )
                        )
                    )
                )
            )
        )
    ) {
        HashHashTheme {
            var hashedOutput by remember { mutableStateOf("") }
            var comparisonHash by remember { mutableStateOf("") }
            var algorithm: Algorithm by remember { mutableStateOf(Algorithm.MD5) }
            var hashTimer by remember { mutableStateOf("00:00") }
            var isHashing by remember { mutableStateOf(false) }
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
                            timeBeforeHashVisibility = false
                            timeAfterHashVisibility = false
                        }
                    }
                }
                HashResultRow(
                    algorithm = algorithm,
                    hashedOutput = hashedOutput,
                    onClearClick = {
                        hashedOutput = ""
                        file = FileUtils.emptyFile
                        timeBeforeHashVisibility = false
                        timeAfterHashVisibility = false
                    }
                )
                ComparisonRow(
                    givenHash = comparisonHash,
                    onComparisonValueChange = { comparisonHash = it.text },
                    pasteAction = { comparisonHash = Clipboard.readContent() },
                    clearAction = { comparisonHash = "" }
                )
                Row(modifier = Modifier.height(50.dp), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    ProgressCard(hashedOutput, comparisonHash, hashTimer, isHashing, file)
                    CalculateButton(
                        modifier = Modifier.weight(0.2f).fillMaxHeight(),
                        file = file,
                        isHashing = isHashing,
                        timerCall = { hashTimer = "${it.first}:${it.second}" },
                        hashCall = { job ->
                            isHashing = true
                            System.nanoTime().also { nanosAtStart ->
                                timeBeforeHash = "Started at: ${SimpleDateFormat("dd MMMM yyyy, HH:mm:ss").format(System.currentTimeMillis())}"
                                timeBeforeHashVisibility = true
                                try {
                                    hashedOutput = file.hash(algorithm)
                                    System.nanoTime().also { nanosAtEnd ->
                                        timeAfterHash = "Ended at: ${SimpleDateFormat("dd MMMM yyyy, HH:mm:ss").format(System.currentTimeMillis())}"
                                        timeTaken = "Time taken: ${Time.formatElapsedTime(nanosAtEnd - nanosAtStart)}"
                                    }
                                    timeAfterHashVisibility = true
                                } catch(exception: Exception) {
                                    timeBeforeHash = "Error: ${exception.localizedMessage.replaceFirstChar { it.titlecase() }}"
                                }
                                job.cancel()
                                hashTimer = "00:00"
                                isHashing = false
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
                    state = WindowState(
                        position = WindowPosition(Alignment.Center),
                        size = DpSize(width = 500.dp, height = 500.dp)
                    ),
                    title = "About HashHash",
                    icon = painterResource(resourcePath = "hash.png"),
                    resizable = false
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(resourcePath = "hash.png"),
                            contentDescription = null,
                            modifier = Modifier.size(150.dp).padding(10.dp)
                        )
                        LabelProjection(contentModel = LabelContentModel(text = "1.0.0")).project()
                    }
                }
            }
        }
    }
}

fun openWebpage(uri: URI?): Boolean {
    val desktop = if (Desktop.isDesktopSupported()) Desktop.getDesktop() else null
    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
        try {
            desktop.browse(uri)
            return true
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }
    return false
}

