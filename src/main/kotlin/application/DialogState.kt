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

package application

import androidx.compose.runtime.mutableStateOf

class DialogState {
    var isAboutOpen = mutableStateOf(false)
    var isSettingsOpen = mutableStateOf(false)

    var listOfDialogs = listOf(
        isAboutOpen,
        isSettingsOpen
    )

    inner class Settings : Dialog {
        override fun open() {
            for (dialog in listOfDialogs) {
                dialog.value = dialog == isSettingsOpen
            }
        }

        override fun close() {
            isSettingsOpen.value = false
        }

        override fun isOpen() = isSettingsOpen.value
    }

    inner class About : Dialog {
        override fun open() {
            for (dialog in listOfDialogs) {
                dialog.value = dialog == isAboutOpen
            }
        }

        override fun close() {
            isAboutOpen.value = false
        }

        override fun isOpen() = isAboutOpen.value
    }

    interface Dialog {
        fun open()

        fun close()

        fun isOpen(): Boolean
    }

}
