/**

HashHash
Copyright (C) 2024 Russell Banks

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateMap
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.appmattus.crypto.Algorithm
import com.hoc081098.flowext.interval
import components.Timer
import components.screens.ParentComponent
import helper.FileUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.launch
import java.io.File
import kotlin.time.Clock
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlin.time.toDuration

@OptIn(ExperimentalTime::class)
object FileScreenModel : ScreenModel {
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

    val footerText: String get() = when {
        exception != null -> exception?.message as String
        fileHashJob?.isActive == true -> "Hashing file"
        resultMap[ParentComponent.algorithm] != null -> "Done!"
        file != null -> "No hash"
        else -> "No file selected"
    }

    fun onCalculateClicked() {
        file?.let { file ->
            if (fileHashJob?.isActive != true) {
                screenModelScope.launch(Dispatchers.Default) {
                    interval(Duration.ZERO, 1.seconds)
                        .takeWhile { fileHashJob?.isActive == true }
                        .collect {
                            timer = Timer(minutes = it.toDuration(DurationUnit.SECONDS).inWholeMinutes, seconds = it)
                        }
                }
                fileHashJob = screenModelScope.launch(Dispatchers.IO) {
                    instantBeforeHash = Clock.System.now()
                    Hashing.catchFileHashingExceptions(exceptionCallback = { exception = it }) {
                        resultMap[ParentComponent.algorithm] = file.hash(
                            algorithm = ParentComponent.algorithm,
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

    fun onAlgorithmClick(algorithm: Algorithm) {
        resultMap[algorithm]?.run {
            resultMap[algorithm] = if (hashedTextUppercase) uppercase() else lowercase()
        }
    }

    fun selectFile() {
        FileUtils.openFileDialogAndGetResult().also(::setComponentFile)
    }

    fun setComponentFile(file: File?) {
        if (file != null && file != this@FileScreenModel.file) {
            resultMap.clear()
            this@FileScreenModel.file = file
        }
    }

    fun switchHashCase() {
        hashedTextUppercase = !hashedTextUppercase
        resultMap[ParentComponent.algorithm]?.run {
            resultMap[ParentComponent.algorithm] = if (hashedTextUppercase) uppercase() else lowercase()
        }
    }
}
