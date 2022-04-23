package components.about

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext

class LibrariesScreenComponent(
    private val componentContext: ComponentContext,
    private val onGoBackClicked: () -> Unit
) : Component, ComponentContext by componentContext {

    @Composable
    override fun render() {
        GreetingScreen(
            onGoBackClicked = onGoBackClicked
        )
    }
}