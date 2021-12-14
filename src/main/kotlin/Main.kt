// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.AwtWindow
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.awt.FileDialog
import java.awt.Frame
import java.io.File
import java.security.MessageDigest

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        var isOpen by remember { mutableStateOf(false) }
        var hashedOutput by remember { mutableStateOf("No hashed file") }
        var dropDownOpen by remember { mutableStateOf(false) }
        var algorithm by remember { mutableStateOf(MessageDigestAlgorithm.MD5) }
        var selectedIndex by remember { mutableStateOf(1) }
        val menuItems = listOf(
            MessageDigestAlgorithm.MD2,
            MessageDigestAlgorithm.MD5,
            MessageDigestAlgorithm.SHA_1,
            MessageDigestAlgorithm.SHA_224,
            MessageDigestAlgorithm.SHA_256,
            MessageDigestAlgorithm.SHA_384,
            MessageDigestAlgorithm.SHA_512,
            MessageDigestAlgorithm.SHA_512_224,
            MessageDigestAlgorithm.SHA_512_256,
            MessageDigestAlgorithm.SHA3_224,
            MessageDigestAlgorithm.SHA3_256,
            MessageDigestAlgorithm.SHA3_384,
            MessageDigestAlgorithm.SHA3_512
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ComposeMenu(
                menuItems = menuItems,
                menuExpandedState = dropDownOpen,
                seletedIndex = selectedIndex,
                updateMenuExpandStatus = { dropDownOpen = !dropDownOpen},
                onDismissMenuView = { dropDownOpen = false },
                onMenuItemclick = {
                    dropDownOpen = false
                    algorithm = menuItems[it]
                    selectedIndex = it
                }
            )
            Text(hashedOutput)
            Button(onClick = { isOpen = true }) { Text("File") }
        }

        if (isOpen) {
            FileDialog(
                onCloseRequest = {
                    isOpen = false
                    hashedOutput = HashUtils.getCheckSumFromFile(MessageDigest.getInstance(algorithm), it).uppercase()
                }
            )
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}

@Composable
private fun FileDialog(
    parent: Frame? = null,
    onCloseRequest: (result: File) -> Unit
) = AwtWindow(
    create = {
        object : FileDialog(parent, "Choose a file", LOAD) {
            override fun setVisible(value: Boolean) {
                super.setVisible(value)
                if (value) {
                    onCloseRequest(files[0])
                }
            }
        }
    },
    dispose = FileDialog::dispose
)