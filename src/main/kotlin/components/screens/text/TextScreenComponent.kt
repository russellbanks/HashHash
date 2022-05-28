package components.screens.text

import com.appmattus.crypto.Algorithm
import com.arkivanov.decompose.ComponentContext

class TextScreenComponent(
    componentContext: ComponentContext,
    val algorithm: Algorithm,
    val givenText: String,
    val givenTextHash: String,
    val textComparisonHash: String,
    val onValueChange: (String) -> Unit,
    val onUppercaseClick: () -> Unit,
    val onLowercaseClick: () -> Unit,
    val onClearTextClick: () -> Unit,
    val onComparisonClearClick: () -> Unit,
    val onCaseClick: () -> Unit,
    val onPasteClick: () -> Unit,
    val onComparisonTextFieldChange: (String) -> Unit
) : ComponentContext by componentContext {
    companion object {
        const val characterLimit = 20000
    }
}