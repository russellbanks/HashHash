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

import androidx.compose.ui.graphics.painter.Painter
import org.lwjgl.PointerBuffer
import org.lwjgl.system.MemoryUtil
import org.lwjgl.util.nfd.NativeFileDialog
import svg.filetypes.*
import java.io.File
import java.nio.file.Files

object FileUtils {

    private fun getFormattedBytes(bytes: Long): String {
        if (bytes < 1024) return "$bytes B"
        val unitIndex = (63 - bytes.countLeadingZeroBits()) / 10
        return String.format("%.1f %sB", bytes.toDouble() / (1L shl unitIndex * 10), " KMGTPE"[unitIndex])
    }

    fun getFormattedBytes(file: File?) = if (file != null) getFormattedBytes(file.length()) else "Size"

    fun getFileIcon(file: File?): Painter {
        return mapOf(
            "ai" to PainterAi(), "avi" to PainterAvi(), "css" to PainterCss(), "csv" to PainterCsv(),
            "dbf" to PainterDbf(), "doc" to PainterDoc(), "docx" to PainterDocx(), "dwg" to PainterDwg(),
            "exe" to PainterExe(), "html" to PainterHtml(), "iso" to PainterIso(), "jpg" to PainterJpg(),
            "js" to PainterJs(), "json" to PainterJson(), "md" to PainterMd(), "mp3" to PainterMp3(),
            "mp4" to PainterMp4(), "msi" to PainterMsi(), "odt" to PainterOdt(), "pdf" to PainterPdf(),
            "png" to PainterPng(), "ppt" to PainterPpt(), "pptx" to PainterPptx(), "rtf" to PainterRtf(),
            "svg" to PainterSvg(), "txt" to PainterTxt(), "xls" to PainterXls(), "xml" to PainterXml(),
            "zip" to PainterZip()
        )[file?.extension] ?: PainterFile()
    }

    fun getFileType(file: File?): String {
        return if (file != null) {
            Files.probeContentType(file.toPath())?.replaceFirstChar { it.titlecase() } ?: file.extension
        } else "Type"
    }

    fun getFileName(file: File?): String = file?.name ?: "File name"

    fun getFileExtension(file: File?) = file?.extension ?: "Extension"

    fun getFilePath(file: File?): String = file?.absolutePath ?: "Path"

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