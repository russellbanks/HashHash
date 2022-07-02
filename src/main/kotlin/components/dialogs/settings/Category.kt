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

package components.dialogs.settings

enum class Category {
    Theme,
    TitleBar,
    WindowCorner
}

fun Category.toComponent(): SettingsRoot.Child {
    return when (this) {
        Category.Theme -> SettingsRoot.Child.Theme
        Category.TitleBar -> SettingsRoot.Child.TitleBar
        Category.WindowCorner -> SettingsRoot.Child.WindowCorner
    }
}
