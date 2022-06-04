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

package components.screens.compare

import Hashing.hash
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.arkivanov.decompose.ComponentContext
import components.screens.ParentComponent
import components.screens.ParentInterface
import io.klogging.Klogging
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import java.io.File

class CompareFilesComponent(
    componentContext: ComponentContext,
    parentComponent: ParentComponent
) : ComponentContext by componentContext, ParentInterface by parentComponent, Klogging {
    var fileOne: File? by mutableStateOf(null)
    var fileOneHash by mutableStateOf("")
    var fileOneHashUppercase by mutableStateOf(true)
    var fileOneHashProgress by mutableStateOf(0F)
    var fileTwo: File? by mutableStateOf(null)
    var fileTwoHash by mutableStateOf("")
    var fileTwoHashUppercase by mutableStateOf(true)
    var fileTwoHashProgress by mutableStateOf(0F)
    var comparisonJobList: List<Deferred<Unit>>? by mutableStateOf(null)
    var filesMatch by mutableStateOf(false)

    fun onCalculateClicked(scope: CoroutineScope) {
        if ((comparisonJobList?.count { it.isActive } ?: 0) <= 0) {
            scope.launch(Dispatchers.Default) {
                comparisonJobList = listOf(
                    async(Dispatchers.IO) {
                        try {
                            fileOneHash = fileOne?.hash(
                                algorithm = algorithm,
                                hashProgressCallback = { fileOneHashProgress = it }
                            )?.run { if (fileOneHashUppercase) uppercase() else lowercase() } ?: ""
                        } catch (_: CancellationException) {
                        } catch (illegalArgumentException: IllegalArgumentException) {
                            logger.error(illegalArgumentException)
                        }
                    },
                    async(Dispatchers.IO) {
                        try {
                            fileTwoHash = fileTwo?.hash(
                                algorithm = algorithm,
                                hashProgressCallback = { fileTwoHashProgress = it }
                            )?.run { if (fileTwoHashUppercase) uppercase() else lowercase() } ?: ""
                        } catch (_: CancellationException) {
                        } catch (illegalArgumentException: IllegalArgumentException) {
                            logger.error(illegalArgumentException)
                        }
                    }
                )
                comparisonJobList?.awaitAll()
                filesMatch = fileOneHash.equals(fileTwoHash, ignoreCase = true)
                comparisonJobList = null
            }
        } else {
            comparisonJobList?.forEach { it.cancel() }
            comparisonJobList = null
            fileOneHashProgress = 0F
            fileTwoHashProgress = 0F
        }
    }

    fun switchHashCase(fileComparison: FileComparison) {
        if (fileComparison == FileComparison.FileComparisonOne) {
            fileOneHashUppercase = !fileOneHashUppercase
            fileOneHash = fileOneHash.run { if (fileOneHashUppercase) uppercase() else lowercase() }
        } else if (fileComparison == FileComparison.FileComparisonTwo) {
            fileTwoHashUppercase = !fileTwoHashUppercase
            fileTwoHash = fileTwoHash.run { if (fileTwoHashUppercase) uppercase() else lowercase() }
        }
    }

    fun getFooterText(): String {
        return when {
            fileOne == null && fileTwo == null -> "No files selected"
            fileOne == null && fileTwo != null -> "1st file not selected"
            fileOne != null && fileTwo == null -> "2nd file not selected"
            fileOneHash.isBlank() && fileTwoHash.isBlank() -> "No hashes"
            fileOneHash.isBlank() && fileTwoHash.isNotBlank() -> "No hash for 1st file"
            fileOneHash.isNotBlank() && fileTwoHash.isBlank() -> "No hash for 2nd file"
            fileOneHash.isNotBlank() && fileTwoHash.isNotBlank() -> doFilesMatchString()
            else -> ""
        }
    }

    private fun doFilesMatchString() = if (filesMatch) "Files match" else "Files do not match"

    enum class FileComparison {
        FileComparisonOne,
        FileComparisonTwo
    }
}
