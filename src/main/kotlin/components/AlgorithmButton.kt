package components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.appmattus.crypto.Algorithm
import org.pushingpixels.aurora.component.model.*
import org.pushingpixels.aurora.component.projection.CommandButtonProjection

@Composable
fun AlgorithmButton(
    modifier: Modifier,
    algorithm: Algorithm,
    onMenuItemClick: (algorithm: Algorithm) -> Unit
) {
    CommandButtonProjection(
        contentModel = Command(
            text = algorithm.algorithmName,
            icon = painterResource(resourcePath = "algorithm.png"),
            secondaryContentModel = CommandMenuContentModel(
                group = CommandGroup(
                    commands = Menus.cascadingAlgorithmMenu {
                        onMenuItemClick(it)
                    }
                )
            )
        ),
        presentationModel = CommandButtonPresentationModel(
            presentationState = CommandButtonPresentationState.TileFitToIcon,
            popupMenuPresentationModel = CommandPopupMenuPresentationModel(maxVisibleMenuCommands = 8)
        )
    ).project(modifier)
}