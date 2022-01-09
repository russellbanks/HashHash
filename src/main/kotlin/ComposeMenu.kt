import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun ComposeMenu(
    modifier: Modifier = Modifier.wrapContentSize(),
    menuItems: List<String>,
    menuExpandedState: Boolean,
    selectedIndex : Int,
    updateMenuExpandStatus : () -> Unit,
    onDismissMenuView : () -> Unit,
    onMenuItemClick : (Int) -> Unit,
) {
    Card(
        modifier = modifier,
        border = BorderStroke(width = 1.dp, color = Color.Black),
        shape = RoundedCornerShape(5.dp),
        onClick = { updateMenuExpandStatus() }
    ) {
        Column(Modifier.padding(16.dp)) {
            Row {
                Text(
                    text = menuItems[selectedIndex],
                )
                Box(contentAlignment = Alignment.CenterEnd, modifier = Modifier.fillMaxWidth()) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowDropDown,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp, 20.dp)
                    )
                }
            }

            DropdownMenu(
                expanded = menuExpandedState,
                onDismissRequest = { onDismissMenuView() },
                modifier = Modifier
                    .wrapContentSize()
                    .background(MaterialTheme.colors.surface)
            ) {
                menuItems.forEachIndexed { index, title ->
                    DropdownMenuItem(onClick = { onMenuItemClick(index) }) {
                        Text(text = title)
                    }
                }
            }
        }
    }
}