import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import java.io.File

object Icons {

    object Utility {
        @Composable
        fun check() = painterResource("utility icons/check.svg")

        @Composable
        fun clipboard() = painterResource("utility icons/clipboard.svg")

        @Composable
        fun copy() = painterResource("utility icons/copy.svg")

        @Composable
        fun cross() = painterResource("utility icons/cross.svg")

        @Composable
        fun eraser() = painterResource("utility icons/eraser.svg")

        @Composable
        fun switch() = painterResource("utility icons/switch.svg")
    }

    object FileTypes {

        @Composable
        fun getFileIcon(file: File?): Painter {
            listOf(
                "ai", "avi", "css", "csv", "dbf", "doc", "docx", "dwg", "exe", "html", "iso", "jpg", "js", "json", "mp3",
                "mp4", "msi", "pdf", "png", "ppt", "pptx", "rtf", "svg", "txt", "xls", "xml", "zip", "file"
            ).also {
                return painterResource("file types/${it.getOrNull(it.indexOf(file?.extension ?: "file")) ?: "file"}.svg")
            }
        }

    }
}