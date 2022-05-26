import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.router.bringToFront
import com.arkivanov.decompose.router.router
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

interface Root {

    val routerState: Value<RouterState<*, Child>>

    fun onFileTabClicked()
    fun onTextTabClicked()
    fun onCompareFilesTabClicked()

    sealed class Child {
        class File(val component: Counter) : Child()
        class Text(val component: Counter) : Child()
        class CompareFiles(val component: Counter) : Child()
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

    private fun createChild(config: Config, componentContext: ComponentContext): Root.Child =
        when (config) {
            is Config.File -> Root.Child.File(Counter(componentContext))
            is Config.Text -> Root.Child.Text(Counter(componentContext))
            is Config.CompareFiles -> Root.Child.CompareFiles(Counter(componentContext))
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