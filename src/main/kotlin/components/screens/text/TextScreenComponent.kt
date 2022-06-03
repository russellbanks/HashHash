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

import Hashing.hash
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.arkivanov.decompose.ComponentContext
import components.screens.ParentComponent
import components.screens.ParentInterface

class TextScreenComponent(
    componentContext: ComponentContext,
    parentComponent: ParentComponent
) : ComponentContext by componentContext, ParentInterface by parentComponent {
    var givenText by mutableStateOf("")
    var comparisonHash by mutableStateOf("")
    var hashedTextUppercase by mutableStateOf(true)

    fun hashGivenText() = givenText.hash(algorithm).run { if (hashedTextUppercase) uppercase() else lowercase() }

    fun characterCountAsString(): String {
        return "${"%,d".format(givenText.count())} ${if (givenText.count() == 1) "character" else "characters"}"
    }

    companion object {
        const val characterLimit = 20000
    }
}
