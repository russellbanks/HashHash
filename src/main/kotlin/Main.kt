import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.rememberWindowState
import com.appmattus.crypto.Algorithm
import components.algorithmList
import flowlayout.FlowColumn
import flowlayout.FlowMainAxisAlignment
import flowlayout.FlowRow
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import org.pushingpixels.aurora.component.AuroraBoxWithHighlights
import org.pushingpixels.aurora.component.AuroraVerticalScrollbar
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.*
import org.pushingpixels.aurora.theming.*
import org.pushingpixels.aurora.window.AuroraDecorationArea
import org.pushingpixels.aurora.window.AuroraWindow
import org.pushingpixels.aurora.window.auroraApplication
import java.awt.Desktop
import java.net.URI
import java.net.URISyntaxException
import java.net.URL
import java.text.SimpleDateFormat

fun main() = auroraApplication {
    var isFileManagerOpen by remember { mutableStateOf(false) }
    var isAboutWindowOpen by remember { mutableStateOf(false) }
    val windowState = rememberWindowState(
        position = WindowPosition(Alignment.Center),
        size = DpSize(width = 1035.dp, height = 770.dp)
    )
    AuroraWindow(
        skin = nightShadeSkin(),
        state = windowState,
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
                                ),
                                Command(
                                    text = "Quit HashHash",
                                    action = { exitApplication() }
                                )
                            )
                        )
                    )
                ),
                Command(
                    text = "View",
                    secondaryContentModel = CommandMenuContentModel(
                        CommandGroup(
                            commands = listOf(
                                Command(
                                    text = "Toggle Full Screen",
                                    action = {
                                        if (windowState.placement == WindowPlacement.Floating) {
                                            windowState.placement = WindowPlacement.Fullscreen
                                        } else {
                                            windowState.placement = WindowPlacement.Floating
                                        }
                                    }
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
                                    action = { openWebpage(URL("https://github.com/russellbanks/hashhash")) }
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
        val scope = rememberCoroutineScope()

        Row(Modifier.fillMaxSize()) {
            AuroraDecorationArea(decorationAreaType = DecorationAreaType.ControlPane) {
                Column(
                    modifier = Modifier.fillMaxHeight().auroraBackground().padding(vertical = 8.dp, horizontal = 12.dp).width(200.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        CommandButtonProjection(
                            contentModel = Command(
                                text = "Select file",
                                action = { isFileManagerOpen = true }
                            )
                        ).project(Modifier.fillMaxWidth().height(40.dp))
                        Box(Modifier.fillMaxWidth()) {
                            val lazyListState = rememberLazyListState()
                            val backgroundColorScheme = AuroraSkin.colors.getBackgroundColorScheme(
                                decorationAreaType = AuroraSkin.decorationAreaType
                            )
                            val backgroundEvenRows = backgroundColorScheme.backgroundFillColor
                            val backgroundOddRows = backgroundColorScheme.accentedBackgroundFillColor
                            LazyColumn (
                                modifier = Modifier.fillMaxSize().border(1.dp, Color.Gray, RoundedCornerShape(4.dp)).padding(horizontal = 6.dp),
                                state = lazyListState
                            ) {
                                itemsIndexed(algorithmList) { index, item ->
                                    AuroraBoxWithHighlights(
                                        modifier = Modifier.fillMaxWidth().height(32.dp)
                                            .background(if (index % 2 == 0) backgroundEvenRows else backgroundOddRows),
                                        selected = (algorithm == item),
                                        onClick = {
                                            algorithm = item
                                            hashedOutput = ""
                                            timeBeforeHashVisibility = false
                                            timeAfterHashVisibility = false
                                        },
                                        sides = Sides(straightSides = Side.values().toSet()),
                                        content = {
                                            LabelProjection(contentModel = LabelContentModel(text = item.algorithmName)).project()
                                        }
                                    )
                                }
                            }
                            AuroraVerticalScrollbar(
                                modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight().padding(2.dp),
                                adapter = rememberScrollbarAdapter(scrollState = lazyListState)
                            )
                        }
                    }
                    CommandButtonProjection(
                        contentModel = Command(
                            text = "Calculate",
                            action = {
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
                                                hashedOutput = file.hash(algorithm)
                                                System.nanoTime().also { nanosAtEnd ->
                                                    timeAfterHash = SimpleDateFormat("dd MMMM yyyy, HH:mm:ss").format(System.currentTimeMillis())
                                                    timeTaken = Time.formatElapsedTime(nanosAtEnd - nanosAtStart)
                                                }
                                                timeAfterHashVisibility = true
                                            } catch (exception: Exception) {
                                                timeBeforeHash = "Error: ${exception.localizedMessage.replaceFirstChar { it.titlecase() }}"
                                            }
                                            job.cancel()
                                            hashTimer = "00:00"
                                            isHashing = false
                                        }
                                    }
                                }
                            }
                        ),
                        presentationModel = CommandButtonPresentationModel(
                            presentationState = CommandButtonPresentationState.Medium
                        )
                    ).project(Modifier.fillMaxWidth().height(80.dp))
                }
            }
            Column(Modifier.fillMaxSize()) {
                Row(modifier = Modifier.height(120.dp).padding(horizontal = 20.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Image(
                        painter = painterResource(resourcePath = FileUtils.getFileIcon(file)),
                        contentDescription = null,
                        modifier = Modifier.height(60.dp).align(Alignment.CenterVertically)
                    )
                    SelectionContainer {
                        Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
                            FlowRow(mainAxisAlignment = FlowMainAxisAlignment.Center, mainAxisSpacing = 4.dp) {
                                LabelProjection(contentModel = LabelContentModel(
                                    text = FileUtils.getFileName(file)),
                                    presentationModel = LabelPresentationModel(textStyle = TextStyle(fontSize = 16.sp))
                                ).project()
                                VerticalSeparatorProjection().project(modifier = Modifier.height(20.dp))
                                LabelProjection(contentModel = LabelContentModel(
                                    text = FileUtils.getFileType(file)),
                                    presentationModel = LabelPresentationModel(textStyle = TextStyle(fontSize = 16.sp))
                                ).project()
                                if (FileUtils.getFileType(file) != FileUtils.getFileExtension(file)) {
                                    VerticalSeparatorProjection().project(modifier = Modifier.height(20.dp))
                                    LabelProjection(contentModel = LabelContentModel(
                                        text = FileUtils.getFileExtension(file)),
                                        presentationModel = LabelPresentationModel(textStyle = TextStyle(fontSize = 16.sp))
                                    ).project()
                                }
                                VerticalSeparatorProjection().project(modifier = Modifier.height(20.dp))
                                LabelProjection(contentModel = LabelContentModel(
                                    text = FileUtils.getFormattedBytes(file)),
                                    presentationModel = LabelPresentationModel(textStyle = TextStyle(fontSize = 16.sp))
                                ).project()
                                VerticalSeparatorProjection().project(modifier = Modifier.height(20.dp))
                                LabelProjection(contentModel = LabelContentModel(
                                    text = FileUtils.getFilePath(file)),
                                    presentationModel = LabelPresentationModel(textStyle = TextStyle(fontSize = 16.sp))
                                ).project()
                            }
                        }
                    }
                }
                HorizontalSeparatorProjection().project(Modifier.fillMaxWidth())
                Column {
                    Column(Modifier.weight(1f).padding(20.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            TextFieldValueProjection(
                                contentModel = TextFieldValueContentModel(
                                    value = TextFieldValue(annotatedString = AnnotatedString(text = hashedOutput.uppercase())),
                                    placeholder = "${algorithm.algorithmName} Hash",
                                    readOnly = true,
                                    onValueChange = {}
                                )
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
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            TextFieldValueProjection(
                                contentModel = TextFieldValueContentModel(
                                    value = TextFieldValue(annotatedString = AnnotatedString(text = comparisonHash)),
                                    placeholder = "Comparison Hash",
                                    onValueChange = { comparisonHash = it.text }
                                )
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
                        val areTextFieldsBlank = hashedOutput.isNotBlank() && comparisonHash.isNotBlank()
                        AnimatedVisibility(visible = areTextFieldsBlank) {
                            val hashesMatch = areTextFieldsBlank && hashedOutput.lowercase() == comparisonHash.lowercase()
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                LabelProjection(
                                    contentModel = LabelContentModel(
                                        text = "Hashes${if (!hashesMatch) " do not" else ""} match"
                                    )
                                ).project()
                                Image(
                                    painter = painterResource(resourcePath = "${if (hashesMatch) "check" else "cross"}.png"),
                                    contentDescription = null,
                                    modifier = Modifier.size(14.dp)
                                )
                            }
                        }
                        FlowColumn {
                            LabelProjection(contentModel = LabelContentModel(
                                text = "Started at: ${if (timeBeforeHashVisibility) timeBeforeHash else "---"}")
                            ).project()
                            LabelProjection(contentModel = LabelContentModel(
                                text = "Finished at: ${if (timeAfterHashVisibility) timeAfterHash else "---"}")
                            ).project()
                            LabelProjection(contentModel = LabelContentModel(
                                text = "Time taken: ${if (timeAfterHashVisibility) timeTaken else "---"}")
                            ).project()
                        }
                        AnimatedVisibility(visible = isHashing) {
                            LabelProjection(contentModel = LabelContentModel(text = hashTimer)).project()
                        }
                    }
                    AuroraDecorationArea(decorationAreaType = DecorationAreaType.Footer) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .auroraBackground()
                                .padding(horizontal = 8.dp, vertical = 6.dp)
                        ) {
                            LabelProjection(
                                contentModel = LabelContentModel(
                                    text = when {
                                        hashedOutput.isNotBlank() -> "Done!"
                                        isHashing -> "Hashing..."
                                        file != FileUtils.emptyFile -> "No hash"
                                        else -> "No file selected"
                                    }
                                )
                            ).project()
                        }
                    }
                }
            }
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

fun openWebpage(url: URL): Boolean {
    try {
        return openWebpage(url.toURI())
    } catch (exception: URISyntaxException) {
        exception.printStackTrace()
    }
    return false
}

