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

import components.Root
import components.screens.compare.CompareFilesComponent
import components.screens.file.FileScreenComponent
import io.klogging.Klogging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.UnsupportedFlavorException
import java.awt.dnd.DnDConstants
import java.awt.dnd.DropTarget
import java.awt.dnd.DropTargetDropEvent
import java.io.File
import java.io.IOException

object DragAndDrop : KoinComponent, Klogging {
    private val fileScreenComponent: FileScreenComponent by inject()
    private val compareFilesComponent: CompareFilesComponent by inject()
    private val root: Root by inject()

    fun target(
        scope: CoroutineScope,
        result: (List<*>) -> Unit
    ) = object : DropTarget() {
        @Synchronized
        override fun drop(event: DropTargetDropEvent) {
            try {
                event.acceptDrop(DnDConstants.ACTION_REFERENCE)
                result(event.transferable.getTransferData(DataFlavor.javaFileListFlavor) as List<*>)
            } catch (ioException: IOException) {
                scope.launch(Dispatchers.Default) { logger.error(ioException.message.toString(), ioException) }
            } catch (unsupportedFlavorException: UnsupportedFlavorException) {
                scope.launch(Dispatchers.Default) {
                    logger.error(unsupportedFlavorException.message.toString(), unsupportedFlavorException)
                }
            }
        }
    }

    fun setResult(droppedItems: List<*>) {
        val activeComponent = root.childStack.value.active.instance
        droppedItems.first().let {
            if (it is File && it.isFile) {
                if (activeComponent is Root.Child.File) {
                    fileScreenComponent.setComponentFile(it)
                } else if (activeComponent is Root.Child.CompareFiles) {
                    compareFilesComponent.setDroppedFile(it)
                }
            }
        }
    }
}
