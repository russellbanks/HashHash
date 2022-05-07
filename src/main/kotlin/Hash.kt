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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield
import java.io.File
import java.io.FileInputStream
import java.io.IOException

@Throws(IOException::class)
suspend fun File.hash(
    algorithm: Algorithm,
    hashProgressCallback: (Float) -> Unit
): String {
    val digest = algorithm.createDigest()
    val fis = withContext(Dispatchers.IO) { FileInputStream(this@hash) }

    val byteArray = ByteArray(32768)
    var bytesCount: Int

    val totalRuns = ((length() / byteArray.size) + 1).toFloat()
    var count = 0
    while (withContext(Dispatchers.IO) { fis.read(byteArray) }.also { bytesCount = it } != -1) {
        yield()
        digest.update(byteArray, 0, bytesCount)
        hashProgressCallback(count++ / totalRuns)
    }

    withContext(Dispatchers.IO) { fis.close() }

    val bytes = digest.digest()
    val sb = StringBuilder()

    for (i in bytes.indices) {
        sb.append(((bytes[i].toInt() and 0xff) + 0x100).toString(16).substring(1))
    }

    return sb.toString()
}