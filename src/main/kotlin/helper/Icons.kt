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

        private const val prefixPath = "utility icons"

        @Composable
        fun check() = painterResource("$prefixPath/check.svg")

        @Composable
        fun chevronRight() = painterResource("$prefixPath/chevron-right.svg")

        @Composable
        fun clipboard() = painterResource("$prefixPath/clipboard.svg")

        @Composable
        fun copy() = painterResource("$prefixPath/copy.svg")

        @Composable
        fun cross() = painterResource("$prefixPath/cross.svg")

        @Composable
        fun eraser() = painterResource("$prefixPath/eraser.svg")

        @Composable
        fun switch() = painterResource("$prefixPath/switch.svg")
    }

    object FileTypes {

        @Composable
        fun getFileIcon(file: File?): Painter {
            listOf(
                "ai", "apk", "avi", "css", "csv", "dbf", "dll", "doc", "docx", "dwg", "exe", "file", "html", "iso",
                "jar", "java", "jpg", "js", "json", "kt", "log", "md", "mp3", "mp4", "msi", "pdf", "png", "ppt", "pptx",
                "rpf", "rtf", "svg", "txt", "xls", "xlsx", "xml", "zip"
            ).also {
                return painterResource("file types/${it.getOrNull(it.indexOf(file?.extension?.lowercase() ?: "file")) ?: "file"}.svg")
            }
        }

    }
}