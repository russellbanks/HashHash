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

import com.appmattus.crypto.Algorithm
import com.arkivanov.decompose.ComponentContext
import java.io.File

class CompareFilesComponent(
    componentContext: ComponentContext,
    val algorithm: Algorithm,
    val fileComparisonOne: File?,
    val fileComparisonOneHash: String,
    val fileComparisonOneProgress: Float,
    val fileComparisonOneOnCaseClick: () -> Unit,
    val fileComparisonTwo: File?,
    val fileComparisonTwoHash: String,
    val fileComparisonTwoProgress: Float,
    val fileComparisonTwoOnCaseClick: () -> Unit
) : ComponentContext by componentContext