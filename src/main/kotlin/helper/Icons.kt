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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toPainter
import androidx.compose.ui.res.painterResource
import io.kamel.image.KamelImage
import io.kamel.image.lazyPainterResource
import io.klogging.Klogging
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
                KamelImage(
                    resource = lazyPainterResource(file),
                    contentDescription = null,
                    modifier = modifier,
                    onLoading = { getSystemImage(modifier, file) },
                    onFailure = {
                        rememberCoroutineScope(Dispatchers::Default).launch { logger.error(it.message) }
                        getSystemImage(modifier, file)
                    }
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

        private const val prefixPath = "utility icons"

        @Composable
        fun check() = painterResource("$prefixPath/check.svg")

        @Composable
        fun chevronRight() = painterResource("$prefixPath/chevron-right.svg")

        @Composable
        fun clipboard() = painterResource("$prefixPath/clipboard.svg")

        @Composable
        fun close() = painterResource("$prefixPath/window-close.svg")

        @Composable
        fun copy() = painterResource("$prefixPath/copy.svg")

        @Composable
        fun cross() = painterResource("$prefixPath/cross.svg")

        @Composable
        fun eraser() = painterResource("$prefixPath/eraser.svg")

        @Composable
        fun folderOpen() = painterResource("$prefixPath/bxs-folder-open.svg")

        @Composable
        fun info() = painterResource("$prefixPath/info.svg")

        @Composable
        fun microChip() = painterResource("$prefixPath/bxs-microchip.svg")

        @Composable
        fun paintBrush() = painterResource("$prefixPath/paint-brush.svg")

        @Composable
        fun refresh() = painterResource("$prefixPath/refresh.svg")

        @Composable
        fun settings() = painterResource("$prefixPath/settings.svg")

        @Composable
        fun window() = painterResource("$prefixPath/bx-window.svg")

        @Composable
        fun windowAlt() = painterResource("$prefixPath/bxs-window-alt.svg")

        @Composable
        fun capitalA() = painterResource("$prefixPath/capital-a.svg")

        @Composable
        fun lowerCaseA() = painterResource("$prefixPath/lowercase-a.svg")
    }
}
