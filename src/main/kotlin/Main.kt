// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.AwtWindow
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.awt.FileDialog
import java.awt.Frame
import java.awt.SystemColor.text
import java.io.File


lateinit var file: File

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {

        var cardColour by remember { mutableStateOf(Color.Transparent) }
        var cardText by remember { mutableStateOf("") }
        var buttonText by remember { mutableStateOf("Select File") }
        var isOpen by remember { mutableStateOf(false) }
        var hashedOutput by remember { mutableStateOf("") }
        var providedHash by remember { mutableStateOf("") }
        var dropDownOpen by remember { mutableStateOf(false) }
        var algorithm by remember { mutableStateOf(Algorithm.MD5) }
        var selectedIndex by remember { mutableStateOf(1) }
        val menuItems = listOf(
            Algorithm.MD2,
            Algorithm.MD5,
            Algorithm.SHA_1,
            Algorithm.SHA_224,
            Algorithm.SHA_256,
            Algorithm.SHA_384,
            Algorithm.SHA_512,
            Algorithm.SHA_512_224,
            Algorithm.SHA_512_256,
            Algorithm.SHA3_224,
            Algorithm.SHA3_256,
            Algorithm.SHA3_384,
            Algorithm.SHA3_512
        )

        Scaffold(
            topBar = { SmallTopAppBar(title = { Text("HashHash") } ) }
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(horizontal = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(modifier = Modifier.fillMaxWidth(), onClick = { isOpen = true }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                    Text(buttonText)
                }
                Spacer(modifier = Modifier.size(10.dp))
                ComposeMenu(
                    menuItems = menuItems,
                    menuExpandedState = dropDownOpen,
                    seletedIndex = selectedIndex,
                    updateMenuExpandStatus = { dropDownOpen = !dropDownOpen },
                    onDismissMenuView = { dropDownOpen = false },
                    onMenuItemclick = {
                        dropDownOpen = false
                        algorithm = menuItems[it]
                        selectedIndex = it

                        if (::file.isInitialized) {
                            hashedOutput = file.readBytes().hash(algorithm)
                            if(providedHash == hashedOutput) {
                                cardColour = Color.Green
                                cardText = "Match"
                            }else {
                                cardColour = Color.Yellow
                                cardText = "No match"
                            }
                        }
                    }
                )
                Spacer(modifier = Modifier.size(10.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = hashedOutput,
                    onValueChange = {},
                    label = { Text("Hash") }
                )
                Spacer(modifier = Modifier.size(10.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = providedHash,
                    onValueChange = {
                        providedHash = it

                        if(providedHash == hashedOutput) {
                            cardColour = Color.Green
                            cardText = "Match"
                        }else {
                            cardColour = Color.Yellow
                            cardText = "No match"
                        } },
                    label = { Text("Provided Hash") }
                )
                Spacer(modifier = Modifier.size(10.dp))
                Card (
                    modifier = Modifier.background(cardColour)
                        .fillMaxWidth()
                        .height(20.dp)
                ) {
                    Text(modifier = Modifier.background(cardColour), text = cardText)
                }
            }
        }

        if (isOpen) {
            FileDialog(
                onCloseRequest = {
                    file = it
                    isOpen = false
                    buttonText = it.name

                    hashedOutput = file.readBytes().hash(algorithm)
                    if(providedHash == hashedOutput) {
                        cardColour = Color.Green
                        cardText = "Match"
                    }else {
                        cardColour = Color.Yellow
                        cardText = "No match"
                    }
                }
            )
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