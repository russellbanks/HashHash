package components.about

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.animation.child.childAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.animation.child.slide
import com.arkivanov.decompose.router.pop
import com.arkivanov.decompose.router.push
import com.arkivanov.decompose.router.router
import com.arkivanov.essenty.parcelable.Parcelable

/**
 * Navigator
 */
class NavHostComponent(
    componentContext: ComponentContext
) : Component, ComponentContext by componentContext {

    private val router = router<ScreenConfig, Component>(
        initialConfiguration = ScreenConfig.About,
        childFactory = ::createScreenComponent
    )

    /**
     * Factory function to create screen from given ScreenConfig
     */
    private fun createScreenComponent(
        screenConfig: ScreenConfig,
        componentContext: ComponentContext
    ): Component {
        return when (screenConfig) {

            is ScreenConfig.About -> AboutScreenComponent(
                componentContext,
                ::onGoClicked
            )

            is ScreenConfig.Attribution -> AttributionScreenComponent(
                componentContext,
                ::onGoBackClicked
            )
        }
    }


    /**
     * Invoked when `GO` button clicked (InputScreen)
     */
    private fun onGoClicked() {
        router.push(ScreenConfig.Attribution)
    }

    /**
     * Invoked when `GO BACK` button clicked (GreetingScreen)
     */
    private fun onGoBackClicked() {
        router.pop()
    }


    /**
     * Renders screen as per request
     */
    @OptIn(ExperimentalDecomposeApi::class)
    @Composable
    override fun render() {
        Children(
            routerState = router.state,
            animation = childAnimation(slide())
        ) {
            it.instance.render()
        }
    }

    private sealed class ScreenConfig : Parcelable {
        object About : ScreenConfig()
        object Attribution : ScreenConfig()
    }
}