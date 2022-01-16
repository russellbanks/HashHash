import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.StringSelection

object Clipboard {
    fun readContent() = Toolkit.getDefaultToolkit().systemClipboard.getData(DataFlavor.stringFlavor) as String

    fun setContent(string: String) = Toolkit.getDefaultToolkit().systemClipboard.setContents(StringSelection(string), null)
}