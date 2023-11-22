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

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.graphics.toPainter
import androidx.compose.ui.res.painterResource
import io.klogging.Klogging
import java.awt.AlphaComposite
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import javax.swing.Icon
import javax.swing.filechooser.FileSystemView

object Icons : Klogging {

    @Composable
    fun logo() = painterResource("logo.svg")

    @Composable
    fun file() = painterResource("file types/file.svg")

    @Composable
    fun FileImage(modifier: Modifier) = Image(
        painter = file(),
        contentDescription = null,
        modifier = modifier
    )

    @Composable
    fun SystemIcon(modifier: Modifier, file: File?) {
        if (file != null) {
            if (file.isImage()) {
                Image(
                    bitmap = org.jetbrains.skia.Image.makeFromEncoded(file.readBytes()).toComposeImageBitmap(),
                    contentDescription = null,
                    modifier = modifier,
                )
            } else {
                getSystemImage(modifier, file)
            }
        } else {
            FileImage(modifier)
        }
    }

    private fun File.isImage(): Boolean {
        return try {
            inputStream().use(ImageIO::read) != null
        } catch (_: Exception) {
            false
        }
    }

    @Composable
    private fun getSystemImage(modifier: Modifier, file: File) {
        val icon = getSystemIcon(file)
        return if (icon != null) {
            Image(
                painter = icon.toBufferedImage().toPainter(),
                contentDescription = null,
                modifier = modifier
            )
        } else {
            FileImage(modifier)
        }
    }

    private fun getSystemIcon(file: File): Icon? {
        return FileSystemView.getFileSystemView().getSystemIcon(file, /* width = */ 64, /* height = */ 64)
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

    @Suppress("TooManyFunctions")
    object Utility {

        private const val PREFIX_PATH = "utility icons"

        @Composable
        fun check() = painterResource("$PREFIX_PATH/check.svg")

        @Composable
        fun chevronRight() = painterResource("$PREFIX_PATH/chevron-right.svg")

        @Composable
        fun clipboard() = painterResource("$PREFIX_PATH/clipboard.svg")

        @Composable
        fun close() = painterResource("$PREFIX_PATH/window-close.svg")

        @Composable
        fun copy() = painterResource("$PREFIX_PATH/copy.svg")

        @Composable
        fun cross() = painterResource("$PREFIX_PATH/cross.svg")

        @Composable
        fun eraser() = painterResource("$PREFIX_PATH/eraser.svg")

        @Composable
        fun folderOpen() = painterResource("$PREFIX_PATH/bxs-folder-open.svg")

        @Composable
        fun info() = painterResource("$PREFIX_PATH/info.svg")

        @Composable
        fun microChip() = painterResource("$PREFIX_PATH/bxs-microchip.svg")

        @Composable
        fun paintBrush() = painterResource("$PREFIX_PATH/paint-brush.svg")

        @Composable
        fun refresh() = painterResource("$PREFIX_PATH/refresh.svg")

        @Composable
        fun settings() = painterResource("$PREFIX_PATH/settings.svg")

        @Composable
        fun window() = painterResource("$PREFIX_PATH/bx-window.svg")

        @Composable
        fun windowAlt() = painterResource("$PREFIX_PATH/bxs-window-alt.svg")

        @Composable
        fun capitalA() = painterResource("$PREFIX_PATH/capital-a.svg")

        @Composable
        fun lowerCaseA() = painterResource("$PREFIX_PATH/lowercase-a.svg")
    }
}
