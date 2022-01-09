import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.StringSelection

object Clipboard {
    fun readClipboard() = Toolkit.getDefaultToolkit().systemClipboard.getData(DataFlavor.stringFlavor) as String

    fun setClipboard(string: String) = Toolkit.getDefaultToolkit().systemClipboard.setContents(StringSelection(string), null)
}