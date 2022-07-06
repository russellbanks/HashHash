/**

HashHash
Copyright (C) 2022  Russell Banks

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

package components.screens.file

import Hashing
import Hashing.hash
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.appmattus.crypto.Algorithm
import com.arkivanov.decompose.ComponentContext
import com.hoc081098.flowext.interval
import components.Timer
import components.screens.ParentComponent
import components.screens.ParentInterface
import helper.FileUtils
import helper.Icons
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.CommandButtonPresentationModel
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.theming.IconFilterStrategy
import java.io.File
import java.nio.file.Files
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class FileScreenComponent(
    componentContext: ComponentContext,
    parentComponent: ParentComponent
) : ComponentContext by componentContext, ParentInterface by parentComponent {
    var comparisonHash by mutableStateOf("")
    var file: File? by mutableStateOf(null)
    var fileHashJob: Job? by mutableStateOf(null)
    var hashProgress by mutableStateOf(0F)
    var instantBeforeHash: Instant? by mutableStateOf(null)
    var instantAfterHash: Instant? by mutableStateOf(null)
    var hashedTextUppercase by mutableStateOf(true)
    var resultMap: SnapshotStateMap<Algorithm, String> = mutableStateMapOf()
    var timer by mutableStateOf(Timer(minutes = 0L, seconds = 0L))
    private var exception: Exception? by mutableStateOf(null)

    private fun onCalculateClicked(scope: CoroutineScope) {
        file?.let { file ->
            if (fileHashJob?.isActive != true) {
                scope.launch(Dispatchers.Default) {
                    interval(Duration.ZERO, 1.seconds)
                        .takeWhile { fileHashJob?.isActive == true }
                        .collect {
                            timer = Timer(minutes = it.toDuration(DurationUnit.SECONDS).inWholeMinutes, seconds = it)
                        }
                }
                fileHashJob = scope.launch(Dispatchers.IO) {
                    instantBeforeHash = Clock.System.now()
                    Hashing.catchFileHashingExceptions(exceptionCallback = { exception = it }) {
                        resultMap[algorithm] = file.hash(
                            algorithm = algorithm,
                            hashProgressCallback = { hashProgress = it }
                        ).run { if (hashedTextUppercase) uppercase() else lowercase() }
                        instantAfterHash = Clock.System.now()
                    }
                    fileHashJob = null
                }
            } else {
                hashProgress = 0F
                fileHashJob?.cancel()
                fileHashJob = null
            }
        }
    }

    private fun selectFile() {
        FileUtils.openFileDialogAndGetResult().also {
            setComponentFile(it)
        }
    }

    fun setComponentFile(file: File?) {
        if (file != null && file != this@FileScreenComponent.file) {
            resultMap.clear()
            this@FileScreenComponent.file = file
        }
    }

    fun switchHashCase() {
        hashedTextUppercase = !hashedTextUppercase
        resultMap[algorithm]?.run { resultMap[algorithm] = if (hashedTextUppercase) uppercase() else lowercase() }
    }

    fun getFooterText(): String {
        return when {
            exception != null -> exception?.message!!
            fileHashJob?.isActive == true -> "Hashing file"
            resultMap[algorithm] != null -> "Done!"
            file != null -> "No hash"
            else -> "No file selected"
        }
    }

    @Composable
    fun FileInfoRow() {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Icons.SystemIcon(modifier = Modifier.size(60.dp), file = file)
            SelectionContainer {
                Column {
                    file?.let { file ->
                        Files.probeContentType(file.toPath())?.let {
                            LabelProjection(contentModel = LabelContentModel(text = "Type: $it")).project()
                        }
                    }
                    LabelProjection(
                        contentModel = LabelContentModel(text = "Extension: ${file?.extension ?: ""}")
                    ).project()
                    LabelProjection(
                        contentModel = LabelContentModel(
                            text = "Size: ${file?.let { FileUtils.getFormattedBytes(it.length()) } ?: ""}"
                        )
                    ).project()
                    LabelProjection(
                        contentModel = LabelContentModel(text = "Path: ${file?.absolutePath ?: ""}")
                    ).project()
                }
            }
        }
    }

    @Composable
    fun SelectFileRow() {
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            CommandButtonProjection(
                contentModel = Command(
                    text = "Select file",
                    icon = Icons.Utility.folderOpen(),
                    action = ::selectFile
                ),
                presentationModel = CommandButtonPresentationModel(
                    iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                    iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText,
                    textStyle = TextStyle(textAlign = TextAlign.Center)
                )
            ).project()
            LabelProjection(contentModel = LabelContentModel(text = "or drag and drop a file")).project()
        }
    }

    @Composable
    fun HashButton() {
        val scope = rememberCoroutineScope()
        CommandButtonProjection(
            contentModel = Command(
                text = if (fileHashJob?.isActive == true) "Cancel" else "Hash",
                icon = Icons.Utility.microChip(),
                action = { onCalculateClicked(scope) },
                isActionEnabled = file != null
            ),
            presentationModel = CommandButtonPresentationModel(
                iconDisabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                iconEnabledFilterStrategy = IconFilterStrategy.ThemedFollowText,
                iconActiveFilterStrategy = IconFilterStrategy.ThemedFollowText,
                textStyle = TextStyle(textAlign = TextAlign.Center)
            )
        ).project()
    }

}
