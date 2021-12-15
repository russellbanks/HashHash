import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.security.MessageDigest
import kotlin.experimental.and

@Throws(IOException::class)
fun File.hash(
    algorithm: String
): String {
    val digest = MessageDigest.getInstance(algorithm)
    val fis = FileInputStream(this)

    val byteArray = ByteArray(8192)
    var bytesCount: Int

    while (fis.read(byteArray).also { bytesCount = it } != -1) {
        digest.update(byteArray, 0, bytesCount)
    }

    fis.close()

    val bytes = digest.digest()
    val sb = StringBuilder()

    for (i in bytes.indices) {
        sb.append(((bytes[i] and 0xff.toByte()) + 0x100).toString(16).substring(1))
    }

    return sb.toString()
}