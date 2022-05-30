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

package components.screens.comparefiles

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.appmattus.crypto.Algorithm
import com.arkivanov.decompose.ComponentContext
import hash
import kotlinx.coroutines.*
import java.io.File

class CompareFilesComponent(
    componentContext: ComponentContext,
    val algorithm: Algorithm
) : ComponentContext by componentContext {
    var fileComparisonOne: File? by mutableStateOf(null)
    var fileComparisonOneHash by mutableStateOf("")
    var fileComparisonOneHashUppercase by mutableStateOf(true)
    var fileComparisonOneProgress by mutableStateOf(0F)
    var fileComparisonTwo: File? by mutableStateOf(null)
    var fileComparisonTwoHash by mutableStateOf("")
    var fileComparisonTwoUppercase by mutableStateOf(true)
    var fileComparisonTwoProgress by mutableStateOf(0F)
    var comparisonJobList: List<Deferred<Unit>>? = null
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
                            )?.run { if (fileComparisonOneHashUppercase) uppercase() else lowercase() } ?: ""
                        } catch (_: CancellationException) {
                        } catch (exception: Exception) {
                        }
                    },
                    async(Dispatchers.IO) {
                        try {
                            fileComparisonTwoHash = fileComparisonTwo?.hash(
                                algorithm = algorithm,
                                hashProgressCallback = { fileComparisonTwoProgress = it }
                            )?.run { if (fileComparisonTwoUppercase) uppercase() else lowercase() } ?: ""
                        } catch (_: CancellationException) {
                        } catch (exception: Exception) {
                        }
                    }
                )
                comparisonJobList?.awaitAll()
                filesMatch = fileComparisonOneHash.equals(fileComparisonTwoHash, ignoreCase = true)
            }
        } else {
            comparisonJobList?.forEach { it.cancel() }
            fileComparisonOneProgress = 0F
            fileComparisonTwoProgress = 0F
        }
    }
}