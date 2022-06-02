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

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.appmattus.crypto.Algorithm
import com.arkivanov.decompose.ComponentContext
import hash

class TextScreenComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext {
    var givenText by mutableStateOf("")
    var comparisonHash by mutableStateOf("")
    var hashedTextUppercase by mutableStateOf(true)
    var algorithm: Algorithm by mutableStateOf(Algorithm.MD5)

    fun hashGivenText() = givenText.hash(algorithm).run { if (hashedTextUppercase) uppercase() else lowercase() }

    companion object {
        const val characterLimit = 20000
    }
}
