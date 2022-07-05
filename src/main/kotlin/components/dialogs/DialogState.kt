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

package components.dialogs

import androidx.compose.runtime.mutableStateMapOf

class DialogState {
    private var dialogsMap = mutableStateMapOf(
        Dialogs.About to false, Dialogs.Settings to false, Dialogs.Update to false
    )

    fun areDialogsOpen() = dialogsMap.values.any { it }

    fun closeAllDialogs() = dialogsMap.forEach { dialogsMap[it.key] = false }

    fun getClass(dialog: Dialogs) = when (dialog) {
        Dialogs.About -> About()
        Dialogs.Settings -> Settings()
        Dialogs.Update -> Update()
    }

    inner class Settings : Dialog {
        override fun open() = dialogsMap.forEach { dialogsMap[it.key] = it.key == Dialogs.Settings }

        override fun close() {
            dialogsMap[Dialogs.Settings] = false
        }

        override fun isOpen() = dialogsMap[Dialogs.Settings] == true
    }

    inner class About : Dialog {
        override fun open() = dialogsMap.forEach { dialogsMap[it.key] = it.key == Dialogs.About }

        override fun close() {
            dialogsMap[Dialogs.About] = false
        }

        override fun isOpen() = dialogsMap[Dialogs.About] == true
    }

    inner class Update : Dialog {
        override fun open() = dialogsMap.forEach { dialogsMap[it.key] = it.key == Dialogs.Update }

        override fun close() {
            dialogsMap[Dialogs.Update] = false
        }

        override fun isOpen() = dialogsMap[Dialogs.Update] == true

    }

    interface Dialog {
        fun open()

        fun close()

        fun isOpen(): Boolean
    }

    enum class Dialogs {
        About,
        Settings,
        Update
    }

}
