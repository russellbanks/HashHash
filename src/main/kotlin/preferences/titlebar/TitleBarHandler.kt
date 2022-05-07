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

package preferences.titlebar

import java.util.prefs.Preferences

object TitleBarHandler {

    private val preferences = Preferences.userNodeForPackage(javaClass)

    fun getTitleBar(): TitleBar {
        return when (preferences.getInt(titleBarKey, defaultTitleBarOrdinal)) {
            TitleBar.Native.ordinal -> TitleBar.Native
            else -> TitleBar.Custom
        }
    }

    fun putTitleBar(titleBar: TitleBar) = preferences.putInt(titleBarKey, titleBar.ordinal)

    private const val titleBarKey = "titleBar"
    private const val defaultTitleBarOrdinal = -1
}