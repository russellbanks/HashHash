/**

HashHash
Copyright (C) 2024 Russell Banks

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

object Hashing : Klogging {
    suspend fun File.hash(
        algorithm: Algorithm,
        hashProgressCallback: (Float) -> Unit = {}
    ): String {
        val digest = algorithm.createDigest()
        withContext(Dispatchers.IO) {
            FileInputStream(this@hash).use {
                val buffer = ByteArray(size = 32_768)
                var bytesCount: Int

                val totalRuns = ((length() / buffer.size) + 1).toFloat()
                var count = 0
                while (withContext(Dispatchers.IO) { it.read(buffer) }.also { bytesCount = it } != -1) {
                    digest.update(buffer, 0, bytesCount)
                    hashProgressCallback(count++ / totalRuns)
                }
                hashProgressCallback(count / totalRuns)
            }
        }
        return buildHash(digest.digest())
    }

    fun String.hash(algorithm: Algorithm) = buildHash(algorithm.createDigest().apply { update(toByteArray()) }.digest())

    private fun buildHash(bytes: ByteArray) = buildString {
        bytes.forEach { byte ->
            append(((byte.toInt() and 255) + 256).toString(radix = 16).substring(startIndex = 1))
        }
    }

    suspend fun catchFileHashingExceptions(exceptionCallback: (Exception) -> Unit = {}, block: suspend () -> Unit) {
        try {
            withContext(Dispatchers.IO) { block() }
        } catch (_: CancellationException) {
            // Cancellations are intended
        } catch (exception: Exception) {
            exceptionCallback(exception)
            withContext(Dispatchers.Default) { logger.error(exception.localizedMessage, exception) }
        }
    }
}
