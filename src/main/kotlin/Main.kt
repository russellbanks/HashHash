import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.AwtWindow
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.awt.FileDialog
import java.awt.Frame
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.io.File

lateinit var file: File

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {

        var isFileManagerOpen by remember { mutableStateOf(false) }
        var hashedOutput by remember { mutableStateOf("") }
        var providedHash by remember { mutableStateOf("") }
        var dropDownOpen by remember { mutableStateOf(false) }
        var algorithm by remember { mutableStateOf(Algorithms.MD5) }
        var selectedIndex by remember { mutableStateOf(1) }
        val snackbarHostState = remember { SnackbarHostState() }
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
            modifier = Modifier.fillMaxSize().padding(horizontal = 40.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    modifier = Modifier.weight(9f),
                    value = if (::file.isInitialized) file.path else "",
                    onValueChange = {},
                    label = { Text("Path") }
                )
                IconButton(
                    modifier = Modifier.weight(1f),
                    onClick = { isFileManagerOpen = true }
                ) {
                    Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
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
                if (::file.isInitialized) CoroutineScope(Dispatchers.IO).launch { hashedOutput = file.hash(algorithm) }
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
                            scope.launch {
                                snackbarHostState.showSnackbar("Copied to clipboard!")
                            }
                        }
                    }
                ) {
                    Icon(imageVector = Icons.Rounded.ContentCopy, contentDescription = null)
                }
            }
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = providedHash,
                onValueChange = { providedHash = it },
                isError = !providedHash.equals(hashedOutput, ignoreCase = true),
                trailingIcon = {
                    if (hashedOutput.isNotBlank() && providedHash.isNotBlank()) {
                        Icon(
                            imageVector = if (providedHash.equals(hashedOutput, ignoreCase = true)) {
                                Icons.Rounded.Check
                            } else {
                                Icons.Rounded.ErrorOutline
                            },
                            contentDescription = null
                        )
                    }
                },
                label = { Text("Provided Hash") }
            )
        }

        if (isFileManagerOpen) {
            FileDialog {
                file = it
                isFileManagerOpen = false
                CoroutineScope(Dispatchers.IO).launch { hashedOutput = it.hash(algorithm) }
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "HashHash") {
        App()
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
                    if(files.isNotEmpty()) onCloseRequest(files[0])
                }
            }
        }
    },
    dispose = FileDialog::dispose
)