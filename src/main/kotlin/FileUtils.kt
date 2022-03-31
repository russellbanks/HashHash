import androidx.compose.runtime.Composable
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.window.AwtWindow
import java.awt.FileDialog
import java.awt.Frame
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.math.abs
import kotlin.math.sign

object FileUtils {

    val emptyFile = File("")

    fun getFormattedBytes(bytes: Long): String {
        val absB = if (bytes == Long.MIN_VALUE) Long.MAX_VALUE else abs(bytes)
        if (absB < 1024) return "$bytes B"
        var value = absB
        val ci = "KMGTPE".iterator()
        var i = 40
        while (i >= 0 && absB > 0xfffccccccccccccL shr i) {
            value = value shr 10
            ci.next()
            i -= 10
        }
        value *= bytes.sign.toLong()
        return String.format("%.1f %ciB", value / 1024.0, ci.next())
    }

    private fun getAllFilesInResources(): MutableList<String> {
        val projectDirAbsolutePath = Paths.get("").toAbsolutePath().toString()
        val resourcesPath = Paths.get(projectDirAbsolutePath, "/src/main/resources")
        val list = mutableListOf<String>()
        Files.walk(resourcesPath)
            .filter { item -> Files.isRegularFile(item) }
            .forEach { item -> list.add(item.fileName.toString()) }
        return list
    }

    fun getFormattedBytes(file: File) = if (file != emptyFile) getFormattedBytes(file.length()) else "Size"

    fun getFileIcon(file: File) = if (getAllFilesInResources().contains("${file.extension}.png")) "${file.extension}.png" else "file.png"

    fun getFileType(file: File) = if (file != emptyFile) Files.probeContentType(file.toPath())
        ?.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        ?: file.extension else "Type"

    fun getFileName(file: File): String = if (file != emptyFile) file.name else "File name"

    @Composable
    fun FileDialog(
        parent: Frame? = null,
        onCloseRequest: (result: File) -> Unit
    ) = AwtWindow(
        create = {
            object : FileDialog(parent, "Choose a file", LOAD) {
                override fun setVisible(value: Boolean) {
                    super.setVisible(value)
                    if (value) {
                        if (files.isNotEmpty()) onCloseRequest(files[0])
                    }
                }
            }
        },
        dispose = FileDialog::dispose
    )

}