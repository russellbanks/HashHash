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

import com.appmattus.crypto.Algorithm
import io.klogging.Klogging
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException

object Hashing : Klogging {

    @Throws(IOException::class, IllegalArgumentException::class, IllegalStateException::class)
    suspend fun File.hash(
        algorithm: Algorithm,
        hashProgressCallback: (Float) -> Unit
    ): String {
        val digest = algorithm.createDigest()
        val fileInputStream = withContext(Dispatchers.IO) { FileInputStream(this@hash) }

        val byteArray = ByteArray(size = 32_768)
        var bytesCount: Int

        val totalRuns = ((length() / byteArray.size) + 1).toFloat()
        var count = 0
        while (withContext(Dispatchers.IO) { fileInputStream.read(byteArray) }.also { bytesCount = it } != -1) {
            digest.update(byteArray, 0, bytesCount)
            hashProgressCallback(count++ / totalRuns)
        }
        hashProgressCallback(count / totalRuns)

        withContext(Dispatchers.IO) { fileInputStream.close() }

        return buildHash(digest.digest())
    }

    @Throws(IllegalArgumentException::class, IllegalArgumentException::class)
    fun String.hash(algorithm: Algorithm) = buildHash(algorithm.createDigest().apply { update(toByteArray()) }.digest())

    private fun buildHash(bytes: ByteArray) = StringBuilder().apply {
        bytes.indices.forEach { index ->
            append(((bytes[index].toInt() and 0xff) + 0x100).toString(radix = 16).substring(startIndex = 1))
        }
    }.toString()

    suspend fun catchFileHashingExceptions(exceptionCallback: (Exception) -> Unit = {}, block: suspend () -> Unit) {
        try {
            withContext(Dispatchers.IO) { block() }
        } catch (_: CancellationException) {
            // Cancellations are intended
        } catch (IOException: IOException) {
            exceptionCallback(IOException)
            withContext(Dispatchers.Default) { logger.error(IOException.localizedMessage, IOException) }
        } catch (fileNotFoundException: FileNotFoundException) {
            exceptionCallback(fileNotFoundException)
            withContext(Dispatchers.Default) {
                logger.error(fileNotFoundException.localizedMessage, fileNotFoundException)
            }
        } catch (illegalArgumentException: IllegalArgumentException) {
            exceptionCallback(illegalArgumentException)
            withContext(Dispatchers.Default) {
                logger.error(illegalArgumentException.localizedMessage, illegalArgumentException)
            }
        } catch (illegalStateException: IllegalStateException) {
            exceptionCallback(illegalStateException)
            withContext(Dispatchers.Default) {
                logger.error(illegalStateException.localizedMessage, illegalStateException)
            }
        }
    }

    suspend fun catchTextHashingException(exceptionCallback: (Exception?) -> Unit = {}, block: suspend () -> Unit) {
        try {
            withContext(Dispatchers.Default) { block() }
            exceptionCallback(null)
        } catch (illegalArgumentException: IllegalArgumentException) {
            exceptionCallback(illegalArgumentException)
        } catch (illegalStateException: IllegalStateException) {
            exceptionCallback(illegalStateException)
        }
    }
}
