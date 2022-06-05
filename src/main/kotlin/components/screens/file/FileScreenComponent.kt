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

import Hashing.hash
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateMap
import com.appmattus.crypto.Algorithm
import com.arkivanov.decompose.ComponentContext
import components.screens.ParentComponent
import components.screens.ParentInterface
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import java.io.File

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
    private var exception: Exception? by mutableStateOf(null)
    var hashedTextUppercase by mutableStateOf(true)
    var resultMap: SnapshotStateMap<Algorithm, String> = mutableStateMapOf()

    fun onCalculateClicked(scope: CoroutineScope) {
        if (fileHashJob?.isActive != true) {
            fileHashJob = scope.launch(Dispatchers.IO) {
                instantBeforeHash = Clock.System.now()
                try {
                    resultMap[algorithm] = file?.hash(
                        algorithm = algorithm,
                        hashProgressCallback = { hashProgress = it }
                    )?.run { if (hashedTextUppercase) uppercase() else lowercase() }.toString()
                    instantAfterHash = Clock.System.now()
                } catch (_: CancellationException) {
                } catch (illegalArgumentException: IllegalArgumentException) {
                    exception = illegalArgumentException
                } catch (illegalStateException: IllegalStateException) {
                    exception = illegalStateException
                }
                fileHashJob = null
            }
        } else {
            hashProgress = 0F
            fileHashJob?.cancel()
            fileHashJob = null
        }
    }

    fun switchHashCase() {
        hashedTextUppercase = !hashedTextUppercase
        resultMap[algorithm]?.let {
            resultMap[algorithm] = it.run { if (hashedTextUppercase) uppercase() else lowercase() }
        }
    }

    fun getFooterText(): String {
        return when {
            resultMap[algorithm] != null -> "Done!"
            file != null -> "No hash"
            else -> "No file selected"
        }
    }
}