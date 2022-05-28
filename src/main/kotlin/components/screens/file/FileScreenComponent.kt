package components.screens.file

import com.appmattus.crypto.Algorithm
import com.arkivanov.decompose.ComponentContext
import kotlinx.datetime.Instant
import java.io.File

class FileScreenComponent(
    componentContext: ComponentContext,
    val file: File?,
    val fileHash: String,
    val algorithm: Algorithm,
    val instantBeforeHash: Instant?,
    val instantAfterHash: Instant?,
    val hashProgress: Float,
    val onCaseClick: () -> Unit
) : ComponentContext by componentContext