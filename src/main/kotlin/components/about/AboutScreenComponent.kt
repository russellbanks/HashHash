package components.about

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext

class AboutScreenComponent(
    private val componentContext: ComponentContext,
    private val onGoClicked: (name: String) -> Unit
) : Component, ComponentContext by componentContext {


    @Composable
    override fun render() {
        AboutScreen(
            onGoClicked = onGoClicked
        )
    }

}
