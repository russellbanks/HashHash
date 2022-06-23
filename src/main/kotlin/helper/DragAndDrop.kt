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
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.UnsupportedFlavorException
import java.awt.dnd.DnDConstants
import java.awt.dnd.DropTarget
import java.awt.dnd.DropTargetDropEvent
import java.io.File
import java.io.IOException

object DragAndDrop : Klogging {
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
                scope.launch(Dispatchers.Default) { logger.error(ioException) }
            } catch (unsupportedFlavorException: UnsupportedFlavorException) {
                scope.launch(Dispatchers.Default) { logger.error(unsupportedFlavorException) }
            }
        }
    }

    suspend fun setResult(
        droppedItems: List<*>,
        fileScreenComponent: FileScreenComponent,
        compareFilesComponent: CompareFilesComponent,
        activeComponent: Root.Child
    ) {
        droppedItems.first().let {
            if (it is File && it.isFile) {
                logger.info("User drag and dropped file: ${it.absoluteFile}")
                if (activeComponent is Root.Child.File) {
                    setFileScreenFile(file = it, fileScreenComponent = fileScreenComponent)
                } else if (activeComponent is Root.Child.CompareFiles) {
                    setCompareScreenFiles(file = it, compareFilesComponent = compareFilesComponent)
                }
            }
        }
    }

    private suspend fun setFileScreenFile(file: File, fileScreenComponent: FileScreenComponent) {
        fileScreenComponent.file = file
        logger.info("Set ${file.name} as main file")
    }

    private suspend fun setCompareScreenFiles(file: File, compareFilesComponent: CompareFilesComponent) {
        if (compareFilesComponent.fileOne == null) compareFilesComponent.fileOne = file.also {
            logger.info("Set ${it.name} as the 1st comparison file")
        } else compareFilesComponent.fileTwo = file.also {
            logger.info("Set ${it.name} as the 2nd comparison file")
        }
    }
}
