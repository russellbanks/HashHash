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

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import java.io.File

object Icons {

    @Composable
    fun logo() = painterResource("logo.svg")

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
                return painterResource("file types/${it.getOrNull(it.indexOf(file?.extension?.lowercase() ?: "file")) ?: "file"}.svg")
            }
        }

    }
}