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

package components.controlpane

import com.appmattus.crypto.Algorithm
import components.screens.compare.CompareFilesComponent
import components.screens.file.FileScreenComponent
import components.screens.text.TextScreenComponent
import io.klogging.Klogging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ControlPaneHelper(
    val fileScreen: FileScreenComponent,
    val textScreen: TextScreenComponent,
    private val compareScreen: CompareFilesComponent
) : Klogging {

    fun onAlgorithmClick(algorithm: Algorithm) {
        if (algorithm != fileScreen.algorithm) {
            fileScreen.algorithm = algorithm
            fileScreen.resultMap[algorithm]?.run {
                fileScreen.resultMap[algorithm] = if (fileScreen.hashedTextUppercase) uppercase() else lowercase()
            }
            CoroutineScope(Dispatchers.Default).launch { textScreen.hashGivenText() }
            compareScreen.fileOneResultMap[algorithm]?.run {
                compareScreen.fileOneResultMap[algorithm] = if (compareScreen.fileOneHashUppercase) uppercase()
                else lowercase()
            }
            compareScreen.fileTwoResultMap[algorithm]?.run {
                compareScreen.fileTwoResultMap[algorithm] = if (compareScreen.fileTwoHashUppercase) uppercase()
                else lowercase()
            }
        }
    }

    companion object {
        const val BoxHeight = 32
    }
}
