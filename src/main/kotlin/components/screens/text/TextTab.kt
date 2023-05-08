/**

HashHash
Copyright (C) 2023 Russell Banks

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.

 */

package components.screens.text

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import components.ComparisonTextFieldRow
import components.OutputTextFieldRow
import components.dialogs.settings.toFriendlyCase
import koin.getScreenModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.pushingpixels.aurora.component.model.ComboBoxContentModel
import org.pushingpixels.aurora.component.model.ComboBoxPresentationModel
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.model.SelectorContentModel
import org.pushingpixels.aurora.component.model.TextFieldStringContentModel
import org.pushingpixels.aurora.component.projection.CheckBoxProjection
import org.pushingpixels.aurora.component.projection.ComboBoxProjection
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.component.projection.TextFieldStringProjection
import org.pushingpixels.aurora.theming.AuroraSkin

object TextTab : Tab {
    override val options: TabOptions
        @Composable
        get() = remember {
            TabOptions(
                index = 1u,
                title = "Text"
            )
        }

    @OptIn(ExperimentalStdlibApi::class)
    @Composable
    override fun Content() {
        val textScreenModel = getScreenModel<TextScreenModel>()
        val backgroundColorScheme = AuroraSkin.colors.getBackgroundColorScheme(
            decorationAreaType = AuroraSkin.decorationAreaType
        )
        val scope = rememberCoroutineScope { Dispatchers.Default }
        Column(
            modifier = Modifier.padding(16.dp).border(1.dp, Color.Gray, RoundedCornerShape(6.dp)).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            val clipboardManager = LocalClipboardManager.current
            Column {
                LabelProjection(
                    contentModel = LabelContentModel(text = textScreenModel.characterCountAsString())
                ).project(Modifier.align(Alignment.End))
                TextFieldStringProjection(
                    contentModel = TextFieldStringContentModel(
                        value = textScreenModel.givenText,
                        onValueChange = {
                            with(textScreenModel) {
                                givenText = it
                                isTextLineByLineErrorVisible = false
                                scope.launch { hashGivenText() }
                            }
                        }
                    )
                ).project(Modifier.fillMaxWidth().fillMaxHeight(0.4f).padding(horizontal = 4.dp))
                TextFieldShortcuts()
            }
            Box {
                Column(Modifier.padding(top = 10.dp).border(1.dp, Color.Gray, RoundedCornerShape(6.dp)).padding(20.dp)) {
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Column {
                            CommandButtonProjection(
                                contentModel = Command(
                                    text = "Hash text line-by-line",
                                    extraText = "test",
                                    action = {
                                        if (textScreenModel.givenText.isNotEmpty()) {
                                            scope.launch { textScreenModel.hashTextLineByLine(textScreenModel.givenText) }
                                        } else {
                                            textScreenModel.isTextLineByLineErrorVisible = true
                                        }
                                    }
                                )
                            ).project()
                            AnimatedVisibility(visible = textScreenModel.isTextLineByLineErrorVisible) {
                                LabelProjection(
                                    contentModel = LabelContentModel(text = "No text entered")
                                ).project()
                            }
                        }
                        CommandButtonProjection(
                            contentModel = Command(
                                text = "Hash file line-by-line",
                                action = { scope.launch { textScreenModel.hashFileLineByLine() } }
                            )
                        ).project()
                        Row {
                            LabelProjection(
                                contentModel = LabelContentModel(text = "Delimiter:")
                            ).project()
                            ComboBoxProjection(
                                contentModel = ComboBoxContentModel(
                                    items = Delimiter.entries,
                                    selectedItem = textScreenModel.selectedDelimiter,
                                    onTriggerItemSelectedChange = {
                                        textScreenModel.selectedDelimiter = it
                                    }
                                ),
                                presentationModel = ComboBoxPresentationModel(
                                    displayConverter = { it.name.toFriendlyCase() }
                                )
                            ).project()
                        }
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        CheckBoxProjection(
                            contentModel = SelectorContentModel(
                                text = "Ignore empty lines",
                                selected = textScreenModel.ignoreEmptyLines,
                                onClick = { textScreenModel.ignoreEmptyLines = !textScreenModel.ignoreEmptyLines }
                            )
                        ).project()
                        CheckBoxProjection(
                            contentModel = SelectorContentModel(
                                text = "Uppercase hash",
                                selected = textScreenModel.isTextLineByLineUppercase,
                                onClick = { textScreenModel.isTextLineByLineUppercase = !textScreenModel.isTextLineByLineUppercase }
                            )
                        ).project()
                        CheckBoxProjection(
                            contentModel = SelectorContentModel(
                                text = "Include source text in output",
                                selected = textScreenModel.includeSourceText,
                                onClick = { textScreenModel.includeSourceText = !textScreenModel.includeSourceText }
                            )
                        ).project()
                    }
                }
                LabelProjection(
                    contentModel = LabelContentModel(text = "Line-By-Line hashing options"),
                    presentationModel = LabelPresentationModel(textStyle = TextStyle(fontSize = 12.sp))
                ).project(Modifier.padding(horizontal = 10.dp).background(backgroundColorScheme.backgroundFillColor))
            }
            OutputTextFieldRow(
                value = textScreenModel.givenTextHash,
                isValueUppercase = textScreenModel.hashedTextUppercase,
                onCaseClick = {
                    with(textScreenModel) {
                        hashedTextUppercase = !hashedTextUppercase
                        givenTextHash = givenTextHash.run { if (hashedTextUppercase) uppercase() else lowercase() }
                    }
                }
            )
            ComparisonTextFieldRow(
                hashedOutput = textScreenModel.givenTextHash,
                comparisonHash = textScreenModel.comparisonHash,
                onPasteClick = {
                    textScreenModel.comparisonHash = (clipboardManager.getText()?.text ?: "").filterNot(Char::isWhitespace)
                },
                onClearClick = { textScreenModel.comparisonHash = "" },
                onTextFieldChange = { textScreenModel.comparisonHash = it.filterNot(Char::isWhitespace) }
            )
        }
        LabelProjection(
            contentModel = LabelContentModel(text = "Text hashing"),
            presentationModel = LabelPresentationModel(textStyle = TextStyle(fontSize = 18.sp))
        ).project(
            Modifier.padding(horizontal = 32.dp, vertical = 3.dp).background(backgroundColorScheme.backgroundFillColor)
        )
    }
}
