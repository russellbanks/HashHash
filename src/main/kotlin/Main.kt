import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.ui.window.singleWindowApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.awt.FileDialog
import java.awt.Frame
import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.StringSelection
import java.awt.dnd.DnDConstants
import java.awt.dnd.DropTarget
import java.awt.dnd.DropTargetAdapter
import java.awt.dnd.DropTargetDropEvent
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.text.CharacterIterator
import java.text.StringCharacterIterator
import kotlin.math.abs

lateinit var file: File

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {

        var isFileManagerOpen by remember { mutableStateOf(false) }
        var hashedOutput by remember { mutableStateOf("") }
        var comparisonHash by remember { mutableStateOf("") }
        var dropDownOpen by remember { mutableStateOf(false) }
        var algorithm by remember { mutableStateOf(Algorithms.MD5) }
        var selectedIndex by remember { mutableStateOf(1) }
        val snackbarHostState = remember { SnackbarHostState() }
        var hashTimer by remember { mutableStateOf("00:00") }
        var timerVisible by remember { mutableStateOf(false) }
        var fileName by remember { mutableStateOf("File name") }
        var fileType by remember { mutableStateOf("Type") }
        var fileSize by remember { mutableStateOf("Size") }
        var filePath by remember { mutableStateOf("") }
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
                        painter = painterResource(
                            if (::file.isInitialized) {
                                if (getAllFilesInResources().contains("${file.extension}.png")) "${file.extension}.png"
                                else "file.png"
                            } else "file.png"
                        ),
                        contentDescription = null
                    )
                    Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
                        SelectionContainer { Text(text = fileName, fontSize = 20.sp) }
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            SelectionContainer { Text(text = fileType) }
                            Text(text = "|")
                            SelectionContainer { Text(text = fileSize) }
                        }
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    modifier = Modifier.weight(9f),
                    value = filePath,
                    onValueChange = {},
                    label = { Text("Path") }
                )
                IconButton(
                    modifier = Modifier.weight(1f),
                    onClick = { isFileManagerOpen = true }
                ) {
                    Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
                }
                IconButton(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        hashedOutput = ""
                        fileSize = "Size"
                        fileName = "File name"
                        fileType = "Type"
                        filePath = ""
                    }
                ) {
                    Icon(imageVector = Icons.Rounded.Clear, contentDescription = null)
                }
            }
            ComposeMenu(
                menuItems = Algorithms.algorithmList,
                menuExpandedState = dropDownOpen,
                seletedIndex = selectedIndex,
                updateMenuExpandStatus = { dropDownOpen = !dropDownOpen },
                onDismissMenuView = { dropDownOpen = false }
            ) {
                dropDownOpen = false
                algorithm = Algorithms.algorithmList[it]
                selectedIndex = it
                if (::file.isInitialized) scope.launch(Dispatchers.IO) { hashedOutput = file.hash(algorithm) }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth().weight(9f),
                    value = hashedOutput.uppercase(),
                    onValueChange = {},
                    label = { Text("$algorithm Hash") }
                )
                IconButton(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        if (hashedOutput.isNotBlank()) {
                            Toolkit.getDefaultToolkit()
                                .systemClipboard
                                .setContents(
                                    StringSelection(hashedOutput.uppercase()),
                                    null
                                )
                            scope.launch { snackbarHostState.showSnackbar("Copied to clipboard!") }
                        }
                    }
                ) {
                    Icon(imageVector = Icons.Rounded.ContentCopy, contentDescription = null)
                }
            }
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = comparisonHash,
                onValueChange = { comparisonHash = it },
                isError = !comparisonHash.equals(hashedOutput, ignoreCase = true),
                trailingIcon = {
                    if (hashedOutput.isNotBlank() && comparisonHash.isNotBlank()) {
                        Icon(
                            imageVector = if (comparisonHash.equals(hashedOutput, ignoreCase = true)) {
                                Icons.Rounded.Check
                            } else {
                                Icons.Rounded.ErrorOutline
                            },
                            contentDescription = null
                        )
                    }
                },
                label = { Text("Comparison Hash") }
            )

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

        if (isFileManagerOpen) {
            FileDialog { chosenFile ->
                file = chosenFile
                fileName = chosenFile.name
                fileType = getFileType(chosenFile)
                fileSize = getFormattedBytes(file.length())
                filePath = file.path
                isFileManagerOpen = false
                val job = scope.launch {
                    flow {
                        System.currentTimeMillis().also { millisAtStart ->
                            while(true) {
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

fun getAllFilesInResources(): MutableList<String> {
    val projectDirAbsolutePath = Paths.get("").toAbsolutePath().toString()
    val resourcesPath = Paths.get(projectDirAbsolutePath, "/src/main/resources")
    val list = mutableListOf<String>()
    Files.walk(resourcesPath)
        .filter { item -> Files.isRegularFile(item) }
        .forEach { item -> list.add(item.fileName.toString()) }
    return list
}

fun main() = singleWindowApplication(
    title = "HashHash",
    resizable = false
) {
    App()
    LaunchedEffect(Unit) {
        window.dropTarget = DropTarget().apply {
            addDropTargetListener(object : DropTargetAdapter() {
                override fun drop(event: DropTargetDropEvent) {
                    event.acceptDrop(DnDConstants.ACTION_COPY)
                    val fileName = event.transferable.getTransferData(DataFlavor.javaFileListFlavor)
                    println(fileName)
                }
            })
        }
    }
}

fun getFileType(file: File) = Files.probeContentType(file.toPath())?.capitalize(Locale.current) ?: file.extension

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
                    if(files.isNotEmpty()) onCloseRequest(files[0])
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
    val ci: CharacterIterator = StringCharacterIterator("KMGTPE")
    var i = 40
    while (i >= 0 && absB > 0xfffccccccccccccL shr i) {
        value = value shr 10
        ci.next()
        i -= 10
    }
    value *= java.lang.Long.signum(bytes).toLong()
    return String.format("%.1f %ciB", value / 1024.0, ci.current())
}