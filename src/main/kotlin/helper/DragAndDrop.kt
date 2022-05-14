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