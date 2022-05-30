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

package components

import com.appmattus.crypto.Algorithm
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.router.bringToFront
import com.arkivanov.decompose.router.router
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import components.screens.comparefiles.CompareFilesComponent
import components.screens.file.FileScreenComponent
import components.screens.text.TextScreenComponent
import kotlinx.datetime.Instant

interface Root {

    val routerState: Value<RouterState<*, Child>>

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

class RootComponent(
    componentContext: ComponentContext
) : Root, ComponentContext by componentContext {

    private val router =
        router<Config, Root.Child>(
            initialConfiguration = Config.File,
            childFactory = ::createChild
        )

    override val routerState: Value<RouterState<*, Root.Child>> = router.state

    override fun onFileTabClicked() {
        router.bringToFront(Config.File)
    }

    override fun onTextTabClicked() {
        router.bringToFront(Config.Text)
    }

    override fun onCompareFilesTabClicked() {
        router.bringToFront(Config.CompareFiles)
    }

    private fun createChild(config: Config, componentContext: ComponentContext) =
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