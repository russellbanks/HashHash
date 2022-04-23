package components.about

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext

class AttributionScreenComponent(
    private val componentContext: ComponentContext,
    private val onGoBackClicked: () -> Unit
) : Component, ComponentContext by componentContext {

    @Composable
    override fun render() {
        AttributionScreen(
            onGoBackClicked = onGoBackClicked
        )
    }
}