/**

HashHash
Copyright (C) 2023 Russell Banks

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
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import org.koin.core.annotation.Single

interface SettingsRoot {

    val childStack: Value<ChildStack<*, Child>>

    fun onThemeTabClicked()

    fun onTitleBarTabClicked()

    fun onWindowCornerTabClicked()

    sealed class Child {

        object Theme : Child()

        object TitleBar : Child()

        object WindowCorner : Child()
    }
}

@Single
class SettingsRootComponent(
    lifecycle: LifecycleRegistry
) : SettingsRoot, ComponentContext by DefaultComponentContext(lifecycle) {
    private val navigation = StackNavigation<Config>()

    private val stack = childStack(
        source = navigation,
        initialConfiguration = Config.Theme,
        childFactory = ::createChild
    )

    override val childStack: Value<ChildStack<*, SettingsRoot.Child>> get() = stack

    override fun onThemeTabClicked() {
        navigation.bringToFront(Config.Theme)
    }

    override fun onTitleBarTabClicked() {
        navigation.bringToFront(Config.TitleBar)
    }

    override fun onWindowCornerTabClicked() {
        navigation.bringToFront(Config.WindowCorner)
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
