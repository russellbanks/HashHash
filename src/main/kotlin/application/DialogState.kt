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
    private var listOfDialogs = hashMapOf(
        Dialogs.ABOUT to mutableStateOf(false),
        Dialogs.SETTINGS to mutableStateOf(false)
    )

    fun areDialogsOpen(): Boolean {
        return listOfDialogs.values.any { it.value }
    }

    fun closeAll() {
        listOfDialogs.forEach {
            it.value.value = false
        }
    }

    inner class Settings : Dialog {
        override fun open() {
            for (dialog in listOfDialogs) {
                dialog.value.value = dialog.key == Dialogs.SETTINGS
            }
        }

        override fun close() {
            listOfDialogs[Dialogs.SETTINGS]?.value = false
        }

        override fun isOpen() = listOfDialogs[Dialogs.SETTINGS]?.value == true
    }

    inner class About : Dialog {
        override fun open() {
            for (dialog in listOfDialogs) {
                dialog.value.value = dialog.key == Dialogs.ABOUT
            }
        }

        override fun close() {
            listOfDialogs[Dialogs.ABOUT]?.value = false
        }

        override fun isOpen() = listOfDialogs[Dialogs.ABOUT]?.value == true
    }

    interface Dialog {
        fun open()

        fun close()

        fun isOpen(): Boolean
    }

    enum class Dialogs {
        ABOUT,
        SETTINGS
    }

}
