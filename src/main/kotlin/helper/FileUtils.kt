/**

HashHash
Copyright (C) 2022  Russell Banks

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.

 */

package helper

import org.lwjgl.PointerBuffer
import org.lwjgl.system.MemoryUtil
import org.lwjgl.util.nfd.NativeFileDialog
import java.io.File
import java.nio.file.Files

object FileUtils {

    val emptyFile = File("")

    private fun getFormattedBytes(bytes: Long): String {
        if (bytes < 1024) return "$bytes B"
        val unitIndex = (63 - bytes.countLeadingZeroBits()) / 10
        return String.format("%.1f %sB", bytes.toDouble() / (1L shl unitIndex * 10), " KMGTPE"[unitIndex])
    }

    fun getFormattedBytes(file: File) = if (file != emptyFile) getFormattedBytes(file.length()) else "Size"

    fun getFileIcon(file: File): String {
        listOf(
            "ai", "avi", "css", "csv", "dbf", "doc", "docx", "dwg", "exe", "html", "iso", "jpg", "js", "json", "mp3",
            "mp4", "msi", "odt", "pdf", "png", "ppt", "pptx", "rtf", "svg", "txt", "xls", "xml", "zip"
        ).also { return "filetypes/${it.getOrNull(it.indexOf(file.extension)) ?: "file"}.svg" }
    }

    fun getFileType(file: File) = if (file != emptyFile) Files.probeContentType(file.toPath())
        ?.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        ?: file.extension else "Type"

    fun getFileName(file: File): String = if (file != emptyFile) file.name else "File name"

    fun getFileExtension(file: File) = if (file != emptyFile) file.extension else "Extension"

    fun getFilePath(file: File): String = if (file != emptyFile) file.absolutePath else "Path"

    fun openFileDialogAndGetResult(): String? {
        val outPath: PointerBuffer = MemoryUtil.memAllocPointer(1)

        var selectedFile: String? = null
        try {
            val result: Int = NativeFileDialog.NFD_OpenDialog("*", null, outPath)
            if (result == NativeFileDialog.NFD_OKAY) {
                selectedFile = outPath.getStringUTF8(0)
                NativeFileDialog.nNFD_Free(outPath.get(0))
            }
        } finally {
            MemoryUtil.memFree(outPath)
        }
        return selectedFile
    }


}