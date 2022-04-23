package components.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.LabelProjection

@Composable
fun AboutScreen(
    onGoClicked: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Image(
            painter = painterResource(resourcePath = "hash.png"),
            contentDescription = "HashHash logo",
            modifier = Modifier.size(60.dp)
        )
        LabelProjection(
            contentModel = LabelContentModel(text = "HashHash"),
            presentationModel = LabelPresentationModel(
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        ).project()
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LabelProjection(
                contentModel = LabelContentModel(
                    text = "Version v1.0.0"
                )
            ).project()
            LabelProjection(
                contentModel = LabelContentModel(
                    text = "A Multiplatform GUI for Hashing, written in Compose for Desktop"
                )
            ).project()
        }
        CommandButtonProjection(
            contentModel = Command(
                text = "Attribution",
                action = { onGoClicked() }
            )
        ).project(Modifier.width(150.dp))
    }
}