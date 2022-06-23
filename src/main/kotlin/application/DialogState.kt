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

import androidx.compose.runtime.mutableStateMapOf

class DialogState {
    private var listOfDialogs = mutableStateMapOf(Dialogs.ABOUT to false, Dialogs.SETTINGS to false)

    fun areDialogsOpen() = listOfDialogs.values.any { it }

    fun closeAllDialogs() = listOfDialogs.forEach { listOfDialogs[it.key] = false }

    inner class Settings : Dialog {
        override fun open() = listOfDialogs.forEach { listOfDialogs[it.key] = it.key == Dialogs.SETTINGS }

        override fun close() {
            listOfDialogs[Dialogs.SETTINGS] = false
        }

        override fun isOpen() = listOfDialogs[Dialogs.SETTINGS] == true
    }

    inner class About : Dialog {
        override fun open() = listOfDialogs.forEach { listOfDialogs[it.key] = it.key == Dialogs.ABOUT }

        override fun close() {
            listOfDialogs[Dialogs.ABOUT] = false
        }

        override fun isOpen() = listOfDialogs[Dialogs.ABOUT] == true
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
