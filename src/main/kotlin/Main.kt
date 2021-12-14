// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(hashedOutput)
            Button(onClick = { isOpen = true }) { Text("File") }
        }

        if (isOpen) {
            FileDialog(
                onCloseRequest = {
                    isOpen = false
                    hashedOutput = HashUtils.getCheckSumFromFile(MessageDigest.getInstance(MessageDigestAlgorithm.MD5), it[0]).uppercase()
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
    onCloseRequest: (result: Array<File>) -> Unit
) = AwtWindow(
    create = {
        object : FileDialog(parent, "Choose a file", LOAD) {
            override fun setVisible(value: Boolean) {
                super.setVisible(value)
                if (value) {
                    onCloseRequest(files)
                }
            }
        }
    },
    dispose = FileDialog::dispose
)