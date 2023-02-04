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

package components

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

interface Root {

    val childStack: Value<ChildStack<*, Child>>

    fun onFileTabClicked()

    fun onTextTabClicked()

    fun onCompareFilesTabClicked()

    sealed class Child {

        object File : Child()

        object Text : Child()

        object CompareFiles : Child()

        fun toInt(): Int {
            return when (this) {
                is File -> 0
                is Text -> 1
                is CompareFiles -> 2
            }
        }
    }
}

@Single
class RootComponent(lifecycle: LifecycleRegistry) : Root, ComponentContext by DefaultComponentContext(lifecycle) {
    private val navigation = StackNavigation<Config>()

    private val stack = childStack(
        source = navigation,
        initialConfiguration = Config.File,
        childFactory = ::createChild
    )

    override val childStack: Value<ChildStack<*, Root.Child>> get() = stack

    override fun onFileTabClicked() {
        navigation.bringToFront(Config.File)
    }

    override fun onTextTabClicked() {
        navigation.bringToFront(Config.Text)
    }

    override fun onCompareFilesTabClicked() {
        navigation.bringToFront(Config.CompareFiles)
    }

    private fun createChild(config: Config, @Suppress("UNUSED_PARAMETER") componentContext: ComponentContext) =
        when (config) {
            is Config.File -> Root.Child.File
            is Config.Text -> Root.Child.Text
            is Config.CompareFiles -> Root.Child.CompareFiles
        }

    private sealed class Config : Parcelable {
        @Parcelize
        object File : Config()

        @Parcelize
        object Text : Config()

        @Parcelize
        object CompareFiles : Config()
    }
}
