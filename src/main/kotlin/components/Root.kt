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

    fun onFileTabClicked(fileScreenComponent: FileScreenComponent)
    fun onTextTabClicked(textScreenComponent: TextScreenComponent)
    fun onCompareFilesTabClicked(compareFilesComponent: CompareFilesComponent)

    sealed class Child {
        class File(val component: FileScreenComponent) : Child()
        class Text(val component: TextScreenComponent) : Child()
        class CompareFiles(val component: CompareFilesComponent) : Child()

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
            initialConfiguration = Config.File(
                file = null,
                fileHash = "",
                algorithm = Algorithm.MD5,
                instantBeforeHash = null,
                instantAfterHash = null,
                hashProgress = 0f,
                onCaseClick = { }
            ),
            childFactory = ::createChild
        )

    override val routerState: Value<RouterState<*, Root.Child>> = router.state

    override fun onFileTabClicked(fileScreenComponent: FileScreenComponent) {
        router.bringToFront(
            Config.File(
                file = fileScreenComponent.file,
                fileHash = fileScreenComponent.fileHash,
                algorithm = fileScreenComponent.algorithm,
                instantBeforeHash = fileScreenComponent.instantBeforeHash,
                instantAfterHash = fileScreenComponent.instantAfterHash,
                hashProgress = fileScreenComponent.hashProgress,
                onCaseClick = fileScreenComponent.onCaseClick
            )
        )
    }

    override fun onTextTabClicked(textScreenComponent: TextScreenComponent) {
        router.bringToFront(
            Config.Text(
                algorithm = textScreenComponent.algorithm,
                givenTextHash = textScreenComponent.givenTextHash,
                textComparisonHash = textScreenComponent.textComparisonHash,
                onUppercaseClick = textScreenComponent.onUppercaseClick,
                onLowercaseClick = textScreenComponent.onLowercaseClick,
                onClearTextClick = textScreenComponent.onClearTextClick,
                onComparisonClearClick = textScreenComponent.onComparisonClearClick,
                onCaseClick = textScreenComponent.onCaseClick,
                onPasteClick = textScreenComponent.onPasteClick,
                onComparisonTextFieldChange = textScreenComponent.onComparisonTextFieldChange
            )
        )
    }

    override fun onCompareFilesTabClicked(compareFilesComponent: CompareFilesComponent) {
        router.bringToFront(
            Config.CompareFiles(
                algorithm = compareFilesComponent.algorithm,
                fileComparisonOne = compareFilesComponent.fileComparisonOne,
                fileComparisonOneHash = compareFilesComponent.fileComparisonOneHash,
                fileComparisonOneProgress = compareFilesComponent.fileComparisonOneProgress,
                fileComparisonOneOnCaseClick = compareFilesComponent.fileComparisonOneOnCaseClick,
                fileComparisonTwo = compareFilesComponent.fileComparisonTwo,
                fileComparisonTwoHash = compareFilesComponent.fileComparisonTwoHash,
                fileComparisonTwoProgress = compareFilesComponent.fileComparisonTwoProgress,
                fileComparisonTwoOnCaseClick = compareFilesComponent.fileComparisonTwoOnCaseClick
            )
        )
    }

    private fun createChild(config: Config, componentContext: ComponentContext) =
        when (config) {
            is Config.File -> Root.Child.File(
                FileScreenComponent(
                    componentContext = componentContext,
                    file = config.file,
                    fileHash = config.fileHash,
                    algorithm = config.algorithm,
                    instantBeforeHash = config.instantBeforeHash,
                    instantAfterHash = config.instantAfterHash,
                    hashProgress = config.hashProgress,
                    onCaseClick = config.onCaseClick
                )
            )
            is Config.Text -> Root.Child.Text(
                TextScreenComponent(
                    componentContext = componentContext,
                    algorithm = config.algorithm,
                    givenTextHash = config.givenTextHash,
                    textComparisonHash = config.textComparisonHash,
                    onUppercaseClick = config.onUppercaseClick,
                    onLowercaseClick = config.onLowercaseClick,
                    onClearTextClick = config.onClearTextClick,
                    onComparisonClearClick = config.onComparisonClearClick,
                    onCaseClick = config.onCaseClick,
                    onPasteClick = config.onPasteClick,
                    onComparisonTextFieldChange = config.onComparisonTextFieldChange
                )
            )
            is Config.CompareFiles -> Root.Child.CompareFiles(
                CompareFilesComponent(
                    componentContext = componentContext,
                    algorithm = config.algorithm,
                    fileComparisonOne = config.fileComparisonOne,
                    fileComparisonOneHash = config.fileComparisonOneHash,
                    fileComparisonOneProgress = config.fileComparisonOneProgress,
                    fileComparisonOneOnCaseClick = config.fileComparisonOneOnCaseClick,
                    fileComparisonTwo = config.fileComparisonTwo,
                    fileComparisonTwoHash = config.fileComparisonTwoHash,
                    fileComparisonTwoProgress = config.fileComparisonTwoProgress,
                    fileComparisonTwoOnCaseClick = config.fileComparisonTwoOnCaseClick
                )
            )
        }

    private sealed class Config : Parcelable {
        @Parcelize
        class File(
            val file: java.io.File?,
            val fileHash: String,
            val algorithm: Algorithm,
            val instantBeforeHash: Instant?,
            val instantAfterHash: Instant?,
            val hashProgress: Float,
            val onCaseClick: () -> Unit
        ) : Config()

        @Parcelize
        class Text(
            val algorithm: Algorithm,
            val givenTextHash: String,
            val textComparisonHash: String,
            val onUppercaseClick: () -> Unit,
            val onLowercaseClick: () -> Unit,
            val onClearTextClick: () -> Unit,
            val onComparisonClearClick: () -> Unit,
            val onCaseClick: () -> Unit,
            val onPasteClick: () -> Unit,
            val onComparisonTextFieldChange: (String) -> Unit
        ) : Config()

        @Parcelize
        class CompareFiles(
            val algorithm: Algorithm,
            val fileComparisonOne: java.io.File?,
            val fileComparisonOneHash: String,
            val fileComparisonOneProgress: Float,
            val fileComparisonOneOnCaseClick: () -> Unit,
            val fileComparisonTwo: java.io.File?,
            val fileComparisonTwoHash: String,
            val fileComparisonTwoProgress: Float,
            val fileComparisonTwoOnCaseClick: () -> Unit
        ) : Config()
    }
}