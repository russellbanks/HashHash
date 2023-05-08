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

import Hashing.hash
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import com.appmattus.crypto.Algorithm
import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter
import components.screens.ParentComponent
import helper.FileUtils
import org.koin.core.annotation.Factory
import org.koin.core.component.KoinComponent
import org.lwjgl.BufferUtils
import org.lwjgl.system.MemoryUtil

@Factory
class TextScreenModel : ScreenModel, KoinComponent {
    var givenText by mutableStateOf("")
    var givenTextHash by mutableStateOf("")
    var comparisonHash by mutableStateOf("")
    var hashedTextUppercase by mutableStateOf(true)
    private var exception: Exception? by mutableStateOf(null)

    var ignoreEmptyLines by mutableStateOf(true)
    var isTextLineByLineErrorVisible by mutableStateOf(false)
    var isTextLineByLineUppercase by mutableStateOf(true)
    var includeSourceText by mutableStateOf(true)
    var selectedDelimiter by mutableStateOf(Delimiter.NewLine)

    val footerText: String get() = if (exception != null) {
        "${ParentComponent.algorithm.algorithmName}: ${exception?.localizedMessage?.replaceFirstChar(Char::titlecaseChar)}"
    } else {
        ""
    }

    fun hashGivenText(algorithm: Algorithm = ParentComponent.algorithm) {
        try {
            givenTextHash = if (givenText.isNotEmpty()) {
                givenText.hash(algorithm).run {
                    if (hashedTextUppercase) uppercase() else lowercase()
                }
            } else {
                ""
            }
        } catch (exception: Exception) {
            this.exception = exception
        }
    }

    fun onAlgorithmClick(algorithm: Algorithm) = hashGivenText(algorithm)

    suspend fun hashTextLineByLine(text: String) {
        hashTextLineByLine(text.splitToSequence(selectedDelimiter.delimiter))
    }

    private suspend fun hashTextLineByLine(lines: Sequence<String>) {
        FileUtils.openSaveFileDialog(
            title = "Save",
            defaultPathAndFile = null,
            filterPatterns = BufferUtils.createPointerBuffer(1).apply {
                put(MemoryUtil.memASCII("*.csv"))
                rewind()
            },
            singleFilterDescription = "Comma separated values (*.csv)"
        )?.let { path ->
            csvWriter().openAsync(path) {
                if (includeSourceText) {
                    writeRow("Text", "${ParentComponent.algorithm.algorithmName} hash")
                } else {
                    writeRow("Hash")
                }
                lines
                    .filterNot { if (ignoreEmptyLines) it.isEmpty() else false }
                    .forEach {
                        if (includeSourceText) {
                            writeRow(
                                it,
                                it.hash(ParentComponent.algorithm).run {
                                    if (isTextLineByLineUppercase) uppercase() else lowercase()
                                }
                            )
                        } else {
                            writeRow(it)
                        }
                    }
            }
        }
    }

    suspend fun hashFileLineByLine() {
        FileUtils.openFileDialogAndGetResult()?.useLines { lines ->
            if (selectedDelimiter == Delimiter.NewLine) {
                hashTextLineByLine(lines)
            } else {
                hashTextLineByLine(
                    lines.joinToString(Delimiter.NewLine.delimiter).splitToSequence(selectedDelimiter.delimiter)
                )
            }
        }
    }

    fun characterCountAsString(): String {
        return "${"%,d".format(givenText.count())} character${if (givenText.count() != 1) "s" else ""}"
    }
}
