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
import components.Root
import components.screens.compare.CompareFilesComponent
import components.screens.file.FileScreenComponent
import io.klogging.Klogging
import kotlinx.coroutines.CoroutineScope
import preferences.mode.Mode
import preferences.mode.ModeHandler

class ControlPaneHelper(
    val fileScreen: FileScreenComponent,
    private val compareScreen: CompareFilesComponent,
    private val activeComponent: Root.Child,
    val modeHandler: ModeHandler,
    val scope: CoroutineScope
) : Klogging {

    fun getSelectFileButtonText(fileSelectButton: FileSelectButton): String {
        return if (fileSelectButton == FileSelectButton.ONE) {
            when (activeComponent) {
                is Root.Child.File -> "Select file"
                is Root.Child.CompareFiles -> "Select 1st file"
                else -> ""
            }
        } else {
            if (activeComponent is Root.Child.CompareFiles) "Select 2nd file" else ""
        }
    }

    fun getSelectFileButtonAction(fileSelectButton: FileSelectButton) {
        if (activeComponent is Root.Child.File) fileScreen.selectFile()
        else if (activeComponent is Root.Child.CompareFiles) compareScreen.selectFile(fileSelectButton)
    }

    fun isActionButtonEnabled(): Boolean {
        return when (activeComponent) {
            is Root.Child.File -> fileScreen.isActionButtonEnabled()
            is Root.Child.CompareFiles -> compareScreen.isActionButtonEnabled()
            else -> false
        }
    }

    fun getActionButtonText(): String {
        return when (activeComponent) {
            is Root.Child.File -> fileScreen.getActionButtonText()
            is Root.Child.CompareFiles -> compareScreen.getActionButtonText()
            else -> ""
        }
    }

    fun getActionButtonAction() {
        if (activeComponent is Root.Child.File) {
            fileScreen.onCalculateClicked(scope)
        } else if (activeComponent is Root.Child.CompareFiles) {
            compareScreen.onCalculateClicked(scope)
        }
    }

    fun getListSelectorText() = "${Mode.SIMPLE.name.lowercase().replaceFirstChar { it.titlecase() }} list"

    fun onAlgorithmClick(algorithm: Algorithm) {
        if (algorithm != fileScreen.algorithm) {
            fileScreen.algorithm = algorithm
            fileScreen.resultMap[algorithm]?.run {
                fileScreen.resultMap[algorithm] = if (fileScreen.hashedTextUppercase) uppercase() else lowercase()
            }
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
