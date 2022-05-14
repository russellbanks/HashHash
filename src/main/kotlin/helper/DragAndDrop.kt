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

import java.awt.datatransfer.DataFlavor
import java.awt.dnd.DnDConstants
import java.awt.dnd.DropTarget
import java.awt.dnd.DropTargetDropEvent

object DragAndDrop {
    fun target(
        result: (List<*>) -> Unit
    ) = object: DropTarget() {
        @Synchronized
        override fun drop(event: DropTargetDropEvent) {
            runCatching {
                event.acceptDrop(DnDConstants.ACTION_REFERENCE)
                result(event.transferable.getTransferData(DataFlavor.javaFileListFlavor) as List<*>)
            }
        }
    }
}