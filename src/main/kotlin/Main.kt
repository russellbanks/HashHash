import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.AwtWindow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandGroup
import org.pushingpixels.aurora.component.model.CommandMenuContentModel
import org.pushingpixels.aurora.theming.nightShadeSkin
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.auroraApplication
import theme.HashHashTheme
import java.awt.FileDialog
import java.awt.Frame
import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.StringSelection
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.math.abs
import kotlin.math.sign


fun getAllFilesInResources(): MutableList<String> {
    val projectDirAbsolutePath = Paths.get("").toAbsolutePath().toString()
    val resourcesPath = Paths.get(projectDirAbsolutePath, "/src/main/resources")
    val list = mutableListOf<String>()
    Files.walk(resourcesPath)
        .filter { item -> Files.isRegularFile(item) }
        .forEach { item -> list.add(item.fileName.toString()) }
    return list
}

fun main() = auroraApplication {
    var isFileManagerOpen by remember { mutableStateOf(false) }
    AuroraWindow(
        skin = nightShadeSkin(),
        title = "HashHash",
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
                )
            )
        )
    ) {
        HashHashTheme {
            var hashedOutput by remember { mutableStateOf("") }
            var comparisonHash by remember { mutableStateOf("") }
            var dropDownOpen by remember { mutableStateOf(false) }
            var algorithm by remember { mutableStateOf(Algorithms.MD5) }
            var selectedIndex by remember { mutableStateOf(1) }
            val snackbarHostState = remember { SnackbarHostState() }
            var hashTimer by remember { mutableStateOf("00:00") }
            var timerVisible by remember { mutableStateOf(false) }
            var file by remember { mutableStateOf(File("")) }
            val scope = rememberCoroutineScope()

            Box(Modifier.fillMaxSize()) {
                SnackbarHost(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    hostState = snackbarHostState
                ) { snackbarData: SnackbarData ->
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(1.dp, Color.Black),
                        modifier = Modifier
                            .padding(16.dp)
                            .wrapContentSize()
                    ) {
                        Row(
                            modifier = Modifier.padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(imageVector = Icons.Rounded.Check, contentDescription = null, tint = Color(0xFF4BB543))
                            Text(text = snackbarData.message)
                        }
                    }
                }
            }

            Column(
                modifier = Modifier.fillMaxSize().padding(40.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    shape = RoundedCornerShape(4.dp),
                    border = BorderStroke(1.dp, Color.Black),
                    modifier = Modifier.fillMaxWidth().height(100.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Image(
                            painter = painterResource(if (getAllFilesInResources().contains("${file.extension}.png")) "${file.extension}.png" else "file.png"),
                            contentDescription = null
                        )
                        Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
                            SelectionContainer { Text(text = if (file != File("")) file.name else "File name", fontSize = 20.sp) }
                            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                SelectionContainer { Text(text = if (file != File("")) getFileType(file) else "Type") }
                                Text(text = "|")
                                SelectionContainer { Text(text = if (file != File("")) getFormattedBytes(file.length()) else "Size") }
                            }
                        }
                    }
                }
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = if (file != File("")) file.path else "",
                    onValueChange = {},
                    label = { Text("Path") },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                if (hashedOutput.isBlank()) isFileManagerOpen = true else {
                                    hashedOutput = ""
                                    file = File("")
                                }
                            }
                        ) {
                            Icon(imageVector = if (hashedOutput.isBlank()) Icons.Rounded.Add else Icons.Rounded.Clear, contentDescription = null)
                        }
                    }
                )
                ComposeMenu(
                    menuItems = Algorithms.algorithmList,
                    menuExpandedState = dropDownOpen,
                    selectedIndex = selectedIndex,
                    updateMenuExpandStatus = { dropDownOpen = !dropDownOpen },
                    onDismissMenuView = { dropDownOpen = false }
                ) {
                    dropDownOpen = false
                    algorithm = Algorithms.algorithmList[it]
                    selectedIndex = it
                    if (file != File("")) {
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
                            hashedOutput = file.hash(algorithm)
                            job.cancel()
                            timerVisible = false
                        }
                    }
                }
                OutputHashField(hashedOutput, algorithm, snackbarHostState, scope)
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = comparisonHash,
                    onValueChange = { comparisonHash = it },
                    isError = if (comparisonHash.isNotBlank()) !comparisonHash.equals(hashedOutput, ignoreCase = true) else false,
                    label = { Text("Comparison Hash") },
                    trailingIcon = {
                        IconButton(
                            onClick = { comparisonHash = if (comparisonHash.isBlank()) readClipboard() else "" }
                        ) {
                            Icon(
                                imageVector = if (comparisonHash.isBlank()) Icons.Rounded.ContentPaste else Icons.Rounded.Clear,
                                contentDescription = null
                            )
                        }
                    }
                )

                HashTimer(timerVisible, hashTimer)
            }

            if (isFileManagerOpen) {
                FileDialog { chosenFile ->
                    isFileManagerOpen = false
                    file = chosenFile
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
                        hashedOutput = chosenFile.hash(algorithm)
                        job.cancel()
                        timerVisible = false
                    }
                }
            }
        }
    }
}

fun readClipboard() = Toolkit.getDefaultToolkit().systemClipboard.getData(DataFlavor.stringFlavor) as String

fun setClipboard(string: String) = Toolkit.getDefaultToolkit().systemClipboard.setContents(StringSelection(string), null)

fun getFileType(file: File) = Files.probeContentType(file.toPath())?.capitalize(Locale.current) ?: file.extension

@Composable
fun OutputHashField(
    hashedOutput: String,
    algorithm: String,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope = rememberCoroutineScope()
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = hashedOutput.uppercase(),
        onValueChange = {},
        label = { Text("$algorithm Hash") },
        trailingIcon = {
            IconButton(
                onClick = {
                    if (hashedOutput.isNotBlank()) {
                        setClipboard(hashedOutput.uppercase())
                        if (snackbarHostState.currentSnackbarData == null) scope.launch {
                            snackbarHostState.showSnackbar("Copied to clipboard!")
                        }
                    }
                }
            ) {
                Icon(imageVector = Icons.Rounded.ContentCopy, contentDescription = null)
            }
        }
    )
}

@Composable
fun HashTimer(timerVisible: Boolean, hashTimer: String) {
    AnimatedVisibility(
        visible = timerVisible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = hashTimer,
                fontSize = 20.sp
            )
            LinearProgressIndicator(Modifier.fillMaxWidth())
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

fun getFormattedBytes(bytes: Long): String {
    val absB = if (bytes == Long.MIN_VALUE) Long.MAX_VALUE else abs(bytes)
    if (absB < 1024) return "$bytes B"
    var value = absB
    val ci = "KMGTPE".iterator()
    var i = 40
    while (i >= 0 && absB > 0xfffccccccccccccL shr i) {
        value = value shr 10
        ci.next()
        i -= 10
    }
    value *= bytes.sign.toLong()
    return String.format("%.1f %ciB", value / 1024.0, ci.next())
}