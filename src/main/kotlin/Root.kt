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
import kotlinx.datetime.Instant
import java.io.File

interface Root {

    val routerState: Value<RouterState<*, Child>>

    fun onFileTabClicked(fileScreenComponent: FileScreenComponent)
    fun onTextTabClicked()
    fun onCompareFilesTabClicked()

    sealed class Child {
        class File(val component: FileScreenComponent) : Child()
        class Text(val component: Counter) : Child()
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
                fileScreenComponent.file,
                fileScreenComponent.fileHash,
                fileScreenComponent.algorithm,
                fileScreenComponent.instantBeforeHash,
                fileScreenComponent.instantAfterHash,
                fileScreenComponent.hashProgress,
                fileScreenComponent.onCaseClick
            )
        )
    }

    override fun onTextTabClicked() {
        router.bringToFront(Config.Text)
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
            is Config.Text -> Root.Child.Text(Counter(componentContext))
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
        object Text : Config()

        @Parcelize
        object CompareFiles : Config()
    }
}