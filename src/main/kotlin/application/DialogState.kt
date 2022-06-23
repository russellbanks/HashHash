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
    private var dialogsMap = mutableStateMapOf(Dialogs.ABOUT to false, Dialogs.SETTINGS to false)

    fun areDialogsOpen() = dialogsMap.values.any { it }

    fun closeAllDialogs() = dialogsMap.forEach { dialogsMap[it.key] = false }

    inner class Settings : Dialog {
        override fun open() = dialogsMap.forEach { dialogsMap[it.key] = it.key == Dialogs.SETTINGS }

        override fun close() {
            dialogsMap[Dialogs.SETTINGS] = false
        }

        override fun isOpen() = dialogsMap[Dialogs.SETTINGS] == true
    }

    inner class About : Dialog {
        override fun open() = dialogsMap.forEach { dialogsMap[it.key] = it.key == Dialogs.ABOUT }

        override fun close() {
            dialogsMap[Dialogs.ABOUT] = false
        }

        override fun isOpen() = dialogsMap[Dialogs.ABOUT] == true
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
