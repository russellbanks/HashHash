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

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.router.bringToFront
import com.arkivanov.decompose.router.router
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

interface SettingsRoot {

    val routerState: Value<RouterState<*, Child>>

    fun onThemeTabClicked()

    fun onTitleBarTabClicked()

    fun onWindowCornerTabClicked()

    sealed class Child {

        object Theme : Child()

        object TitleBar : Child()

        object WindowCorner : Child()

    }
}

class SettingsRootComponent(
    componentContext: ComponentContext
) : SettingsRoot, ComponentContext by componentContext {

    private val router =
        router<Config, SettingsRoot.Child>(
            initialConfiguration = Config.Theme,
            childFactory = ::createChild
        )

    override val routerState: Value<RouterState<*, SettingsRoot.Child>> = router.state

    override fun onThemeTabClicked() {
        router.bringToFront(Config.Theme)
    }

    override fun onTitleBarTabClicked() {
        router.bringToFront(Config.TitleBar)
    }

    override fun onWindowCornerTabClicked() {
        router.bringToFront(Config.WindowCorner)
    }

    private fun createChild(config: Config, @Suppress("UNUSED_PARAMETER") componentContext: ComponentContext) =
        when (config) {
            is Config.Theme -> SettingsRoot.Child.Theme
            is Config.TitleBar -> SettingsRoot.Child.TitleBar
            is Config.WindowCorner -> SettingsRoot.Child.WindowCorner
        }

    private sealed class Config : Parcelable {
        @Parcelize
        object Theme : Config()

        @Parcelize
        object TitleBar : Config()

        @Parcelize
        object WindowCorner : Config()
    }
}
