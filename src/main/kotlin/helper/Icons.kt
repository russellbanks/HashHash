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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import java.awt.AlphaComposite
import java.awt.image.BufferedImage
import java.io.File
import javax.swing.Icon
import javax.swing.filechooser.FileSystemView

object Icons {

    @Composable
    fun logo() = painterResource("logo.svg")

    @Composable
    fun file() = painterResource("file types/file.svg")

    @Composable
    fun FileImage() = Image(
        painter = file(),
        contentDescription = null,
        modifier = Modifier.size(80.dp).padding(start = 20.dp)
    )

    @Composable
    fun SystemIcon(file: File?) {
        file?.let {
            FileSystemView.getFileSystemView().getSystemIcon(it, /* width = */ 64, /* height = */ 64)?.also { icon ->
                Image(
                    painter = icon.toBufferedImage().toPainter(),
                    contentDescription = null,
                    modifier = Modifier.size(80.dp).padding(start = 20.dp)
                )
            } ?: FileImage()
        } ?: FileImage()
    }

    private fun Icon.toBufferedImage(): BufferedImage {
        return BufferedImage(iconWidth, iconHeight, BufferedImage.TYPE_INT_ARGB).apply {
            createGraphics().apply {
                composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER)
                paintIcon(null, this, 0, 0)
                dispose()
            }
        }
    }


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
        fun refresh() = painterResource("$prefixPath/refresh.svg")

        @Composable
        fun capitalA() = painterResource("$prefixPath/capital-a.svg")

        @Composable
        fun lowerCaseA() = painterResource("$prefixPath/lowercase-a.svg")
    }
}
