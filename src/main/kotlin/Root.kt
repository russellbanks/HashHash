import com.appmattus.crypto.Algorithm
import com.arkivanov.decompose.Child
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.router.bringToFront
import com.arkivanov.decompose.router.router
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import components.screens.file.FileScreenComponent
import components.screens.text.TextScreenComponent
import kotlinx.datetime.Instant
import java.io.File

interface Root {

    val routerState: Value<RouterState<*, Child>>

    fun onFileTabClicked(fileScreenComponent: FileScreenComponent)
    fun onTextTabClicked(textScreenComponent: TextScreenComponent)
    fun onCompareFilesTabClicked()

    sealed class Child {
        class File(val component: FileScreenComponent) : Child()
        class Text(val component: TextScreenComponent) : Child()
        class CompareFiles(val component: Counter) : Child()

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
                givenText = textScreenComponent.givenText,
                givenTextHash = textScreenComponent.givenTextHash,
                textComparisonHash = textScreenComponent.textComparisonHash,
                onValueChange = textScreenComponent.onValueChange,
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

    override fun onCompareFilesTabClicked() {
        router.bringToFront(Config.CompareFiles)
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
                    givenText = config.givenText,
                    givenTextHash = config.givenTextHash,
                    textComparisonHash = config.textComparisonHash,
                    onValueChange = config.onValueChange,
                    onUppercaseClick = config.onUppercaseClick,
                    onLowercaseClick = config.onLowercaseClick,
                    onClearTextClick = config.onClearTextClick,
                    onComparisonClearClick = config.onComparisonClearClick,
                    onCaseClick = config.onCaseClick,
                    onPasteClick = config.onPasteClick,
                    onComparisonTextFieldChange = config.onComparisonTextFieldChange
                )
            )
            is Config.CompareFiles -> Root.Child.CompareFiles(Counter(componentContext))
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
            val givenText: String,
            val givenTextHash: String,
            val textComparisonHash: String,
            val onValueChange: (String) -> Unit,
            val onUppercaseClick: () -> Unit,
            val onLowercaseClick: () -> Unit,
            val onClearTextClick: () -> Unit,
            val onComparisonClearClick: () -> Unit,
            val onCaseClick: () -> Unit,
            val onPasteClick: () -> Unit,
            val onComparisonTextFieldChange: (String) -> Unit
        ) : Config()

        @Parcelize
        object CompareFiles : Config()
    }
}