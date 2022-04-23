package components.about

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import java.net.URL

@Composable
fun GreetingScreen(
    onGoBackClicked: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LabelProjection(
            contentModel = LabelContentModel("Libraries screen")
        ).project()
        CommandButtonProjection(
            contentModel = Command(
                text = "Go back",
                action = onGoBackClicked
            )
        ).project()
    }
}

data class AttributionItem(
    val text: String,
    val link: URL
)