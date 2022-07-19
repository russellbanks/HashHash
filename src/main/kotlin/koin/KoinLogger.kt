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

package koin

import io.klogging.Klogging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.logger.Level
import org.koin.core.logger.Logger

class KoinLogger : Logger(), Klogging {
    val scope = CoroutineScope(Dispatchers.Default)

    override fun log(level: Level, msg: String) {
        scope.launch {
            when (level) {
                Level.DEBUG -> logger.debug(msg)
                Level.INFO -> logger.info(msg)
                Level.ERROR -> logger.error(msg)
                Level.NONE -> logger.log(io.klogging.Level.NONE, msg)
            }
        }
    }
}
