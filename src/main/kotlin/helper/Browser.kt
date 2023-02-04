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

package helper

import io.klogging.Klogging
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.awt.Desktop
import java.io.IOException
import java.net.URI
import java.net.URISyntaxException
import java.net.URL

object Browser : Klogging {
    private suspend fun open(uri: URI?): Boolean {
        val desktop = if (Desktop.isDesktopSupported()) Desktop.getDesktop() else null
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                withContext(Dispatchers.IO) { desktop.browse(uri) }
                logger.info("Opened $uri")
                return true
            } catch (unsupportedOperationException: UnsupportedOperationException) {
                logger.error(unsupportedOperationException)
            } catch (ioException: IOException) {
                logger.error(ioException)
            } catch (securityException: SecurityException) {
                logger.error(securityException)
            }
        }
        return false
    }

    suspend fun open(url: URL): Boolean {
        try {
            return open(url.toURI())
        } catch (exception: URISyntaxException) {
            logger.error(exception)
        }
        return false
    }
}
