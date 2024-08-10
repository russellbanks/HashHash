/**

HashHash
Copyright (C) 2024 Russell Banks

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

import io.klogging.Klogging
import java.io.File
import org.lwjgl.PointerBuffer
import org.lwjgl.system.Checks.remainingSafe
import org.lwjgl.system.MemoryStack.stackGet
import org.lwjgl.system.MemoryUtil.NULL
import org.lwjgl.system.MemoryUtil.memAddressSafe
import org.lwjgl.system.MemoryUtil.memUTF8Safe
import org.lwjgl.system.NativeType
import org.lwjgl.util.tinyfd.TinyFileDialogs.ntinyfd_openFileDialog
import org.lwjgl.util.tinyfd.TinyFileDialogs.ntinyfd_saveFileDialog

object FileUtils : Klogging {

    fun getFormattedBytes(bytes: Long): String {
        // Check for a simple case when bytes are less than 1024
        if (bytes < 1024) return "$bytes B"

        // Define an array of unit prefixes
        val unitPrefixes = arrayOf(" ", "K", "M", "G", "T", "P", "E")

        // Calculate the unit index by dividing the number of bits needed for the bytes value by 10
        // The 'coerceAtMost' function ensures the index stays within the bounds of the 'unitPrefixes' array
        val unitIndex = ((63 - bytes.countLeadingZeroBits()) / 10).coerceAtMost(unitPrefixes.lastIndex)

        // Calculate the value with a corresponding unit index
        val value = bytes.toDouble() / (1L shl unitIndex * 10)

        // Format the result string using the value and unit prefix
        return "%.1f %sB".format(value, unitPrefixes[unitIndex])
    }

    /**
     * Displays a file open dialog.
     *
     * @param title                   the dialog title or {@code NULL}
     * @param defaultPathAndFile      the default path and/or file or {@code NULL}
     * @param filterPatterns          an array of file type patterns ({@code NULL} or {"*.jpg","*.png"}
     * @param singleFilterDescription {@code NULL} or "image files"
     * @param allowMultipleSelects    if true, multiple selections are allowed
     *
     * @return the file(s) selected or {@code NULL} on cancel. In case of multiple files, the separator is '|'.
     */
    @NativeType("char const *")
    fun openFileDialog(
        @NativeType("char const *") title: CharSequence?,
        @NativeType("char const *") defaultPathAndFile: CharSequence?,
        @NativeType("char const * const *") filterPatterns: PointerBuffer?,
        @NativeType("char const *") singleFilterDescription: CharSequence?,
        @NativeType("int") allowMultipleSelects: Boolean
    ): String? {
        val stack = stackGet()
        val stackPointer = stack.pointer
        return try {
            stack.nUTF8Safe(title, true)
            val titleEncoded = if (title == null) NULL else stack.pointerAddress
            stack.nUTF8Safe(defaultPathAndFile, true)
            val defaultPathAndFileEncoded = if (defaultPathAndFile == null) NULL else stack.pointerAddress
            stack.nUTF8Safe(singleFilterDescription, true)
            val singleFilterDescriptionEncoded = if (singleFilterDescription == null) NULL else stack.pointerAddress
            val result = ntinyfd_openFileDialog(
                titleEncoded,
                defaultPathAndFileEncoded,
                remainingSafe(filterPatterns),
                memAddressSafe(filterPatterns),
                singleFilterDescriptionEncoded,
                if (allowMultipleSelects) 1 else 0
            )
            memUTF8Safe(result)
        } finally {
            stack.pointer = stackPointer
        }
    }

    fun openFileDialogAndGetResult(): File? {
        openFileDialog(
            title = "Open",
            defaultPathAndFile = null,
            filterPatterns = null,
            singleFilterDescription = null,
            allowMultipleSelects = false
        ).also {
            return if (it != null) File(it) else null
        }
    }

    /**
     * Displays a file save dialog.
     *
     * @param title                   the dialog title or `NULL`
     * @param defaultPathAndFile      the default path and/or file or `NULL`
     * @param filterPatterns          an array of file type patterns (`NULL` or {"*.jpg","*.png"}
     * @param singleFilterDescription `NULL` or "image files"
     *
     * @return the selected file path or `NULL` on cancel
     */
    @NativeType("char const *")
    fun openSaveFileDialog(
        @NativeType("char const *") title: CharSequence?,
        @NativeType("char const *") defaultPathAndFile: CharSequence?,
        @NativeType("char const * const *") filterPatterns: PointerBuffer?,
        @NativeType("char const *") singleFilterDescription: CharSequence?
    ): String? {
        val stack = stackGet()
        val stackPointer = stack.pointer
        return try {
            stack.nUTF8Safe(title, true)
            val titleEncoded = if (title == null) NULL else stack.pointerAddress
            stack.nUTF8Safe(defaultPathAndFile, true)
            val defaultPathAndFileEncoded = if (defaultPathAndFile == null) NULL else stack.pointerAddress
            stack.nUTF8Safe(singleFilterDescription, true)
            val singleFilterDescriptionEncoded = if (singleFilterDescription == null) NULL else stack.pointerAddress
            val result = ntinyfd_saveFileDialog(
                titleEncoded,
                defaultPathAndFileEncoded,
                remainingSafe(filterPatterns),
                memAddressSafe(filterPatterns),
                singleFilterDescriptionEncoded
            )
            memUTF8Safe(result)
        } finally {
            stack.pointer = stackPointer
        }
    }
}
