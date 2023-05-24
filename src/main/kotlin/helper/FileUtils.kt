/**

HashHash
Copyright (C) 2023 Russell Banks

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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.lwjgl.PointerBuffer
import org.lwjgl.system.MemoryStack.stackPush
import org.lwjgl.util.nfd.NFDFilterItem
import org.lwjgl.util.nfd.NativeFileDialog.NFD_CANCEL
import org.lwjgl.util.nfd.NativeFileDialog.NFD_FreePath
import org.lwjgl.util.nfd.NativeFileDialog.NFD_GetError
import org.lwjgl.util.nfd.NativeFileDialog.NFD_OKAY
import org.lwjgl.util.nfd.NativeFileDialog.NFD_OpenDialog
import org.lwjgl.util.nfd.NativeFileDialog.NFD_SaveDialog
import java.io.File
import java.nio.ByteBuffer

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

    private fun openFileDialog(): String? {
        stackPush().use { stack ->
            val pointerBuffer: PointerBuffer = stack.mallocPointer(1)
            return when (NFD_OpenDialog(pointerBuffer, null, null as ByteBuffer?)) {
                NFD_OKAY -> pointerBuffer.getStringUTF8(0).also { NFD_FreePath(pointerBuffer.get(0)) }
                NFD_CANCEL -> {
                    CoroutineScope(Dispatchers.IO).launch {
                        logger.info("User pressed cancel in open file dialog")
                    }
                    null
                }
                else -> {
                    CoroutineScope(Dispatchers.IO).launch {
                        logger.error("${NFD_GetError()}")
                    }
                    null
                }
            }
        }
    }

    fun openFileDialogAndGetResult(): File? {
        openFileDialog().also {
            return if (it != null) File(it) else null
        }
    }

    fun openSaveFileDialog(fileName: String, filter: String, singleFilterDescription: String): String? {
        stackPush().use { stack ->
            val filters = NFDFilterItem.malloc(1)
            filters.first().name(stack.UTF8(singleFilterDescription)).spec(stack.UTF8(filter))
            val pointerBuffer = stack.mallocPointer(1)
            return when (NFD_SaveDialog(pointerBuffer, filters, null, fileName)) {
                NFD_OKAY -> pointerBuffer.getStringUTF8(0).also { NFD_FreePath(pointerBuffer.get(0)) }
                NFD_CANCEL -> {
                    CoroutineScope(Dispatchers.IO).launch {
                        logger.info("User pressed cancel in save file dialog")
                    }
                    null
                }
                else -> {
                    CoroutineScope(Dispatchers.IO).launch {
                        logger.error("Save dialog error: ${NFD_GetError()}")
                    }
                    null
                }
            }
        }
    }
}
