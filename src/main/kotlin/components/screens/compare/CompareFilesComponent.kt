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
    var fileComparisonOne: File? by mutableStateOf(null)
    var fileComparisonOneHash by mutableStateOf("")
    var firstHashUppercase by mutableStateOf(true)
    var fileComparisonOneProgress by mutableStateOf(0F)
    var fileComparisonTwo: File? by mutableStateOf(null)
    var fileComparisonTwoHash by mutableStateOf("")
    var secondHashUppercase by mutableStateOf(true)
    var fileComparisonTwoProgress by mutableStateOf(0F)
    var comparisonJobList: List<Deferred<Unit>>? by mutableStateOf(null)
    var filesMatch by mutableStateOf(false)

    fun onCalculateClicked(scope: CoroutineScope) {
        if ((comparisonJobList?.count { it.isActive } ?: 0) <= 0) {
            scope.launch(Dispatchers.Default) {
                comparisonJobList = listOf(
                    async(Dispatchers.IO) {
                        try {
                            fileComparisonOneHash = fileComparisonOne?.hash(
                                algorithm = algorithm,
                                hashProgressCallback = { fileComparisonOneProgress = it }
                            )?.run { if (firstHashUppercase) uppercase() else lowercase() } ?: ""
                        } catch (_: CancellationException) {
                        } catch (exception: Exception) {
                            logger.error(exception)
                        }
                    },
                    async(Dispatchers.IO) {
                        try {
                            fileComparisonTwoHash = fileComparisonTwo?.hash(
                                algorithm = algorithm,
                                hashProgressCallback = { fileComparisonTwoProgress = it }
                            )?.run { if (secondHashUppercase) uppercase() else lowercase() } ?: ""
                        } catch (_: CancellationException) {
                        } catch (exception: Exception) {
                            logger.error(exception)
                        }
                    }
                )
                comparisonJobList?.awaitAll()
                filesMatch = fileComparisonOneHash.equals(fileComparisonTwoHash, ignoreCase = true)
                comparisonJobList = null
            }
        } else {
            comparisonJobList?.forEach { it.cancel() }
            comparisonJobList = null
            fileComparisonOneProgress = 0F
            fileComparisonTwoProgress = 0F
        }
    }

    fun switchHashCase(fileComparison: FileComparison) {
        if (fileComparison == FileComparison.FileComparisonOne) {
            firstHashUppercase = !firstHashUppercase
            fileComparisonOneHash = fileComparisonOneHash.run { if (firstHashUppercase) uppercase() else lowercase() }
        } else if (fileComparison == FileComparison.FileComparisonTwo) {
            secondHashUppercase = !secondHashUppercase
            fileComparisonTwoHash = fileComparisonTwoHash.run { if (secondHashUppercase) uppercase() else lowercase() }
        }
    }

    enum class FileComparison {
        FileComparisonOne,
        FileComparisonTwo
    }
}
