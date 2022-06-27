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

    suspend fun catchHashingExceptions(block: suspend () -> Unit) {
        try {
            withContext(Dispatchers.IO) { block() }
        } catch (_: CancellationException) {
            // Cancellations are intended
        } catch (IOException: IOException) {
            logger.error(IOException.message.toString(), IOException)
        } catch (fileNotFoundException: FileNotFoundException) {
            logger.error(fileNotFoundException.message.toString(), fileNotFoundException)
        } catch (illegalArgumentException: IllegalArgumentException) {
            logger.error(illegalArgumentException.message.toString(), illegalArgumentException)
        } catch (illegalStateException: IllegalStateException) {
            logger.error(illegalStateException.message.toString(), illegalStateException)
        }
    }
}
