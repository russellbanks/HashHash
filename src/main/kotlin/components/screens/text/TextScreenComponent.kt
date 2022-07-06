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

package components.screens.text

import Hashing.catchTextHashingException
import Hashing.hash
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.arkivanov.decompose.ComponentContext
import components.screens.ParentComponent
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.ext.inject

class TextScreenComponent(componentContext: ComponentContext) : ComponentContext by componentContext, KoinComponent {
    private val parent: ParentComponent by inject()
    var givenText by mutableStateOf("")
    var givenTextHash by mutableStateOf("")
    var comparisonHash by mutableStateOf("")
    var hashedTextUppercase by mutableStateOf(true)
    private var exception: Exception? by mutableStateOf(null)

    suspend fun hashGivenText() {
        if (givenText.isNotEmpty()) {
            catchTextHashingException(exceptionCallback = {
                exception = it
                if (exception != null) givenTextHash = ""
            }) {
                givenTextHash = givenText.hash(parent.algorithm).run {
                    if (hashedTextUppercase) uppercase() else lowercase()
                }
            }
        } else givenTextHash = ""
    }

    suspend fun onAlgorithmClick() = hashGivenText()

    fun characterCountAsString(): String {
        return "${"%,d".format(givenText.count())} character${if (givenText.count() != 1) "s" else ""}"
    }

    fun getFooterText(): String {
        return if (exception != null) {
            "${parent.algorithm.algorithmName}: ${exception?.localizedMessage?.replaceFirstChar { it.titlecase() }}"
        } else ""
    }

}
