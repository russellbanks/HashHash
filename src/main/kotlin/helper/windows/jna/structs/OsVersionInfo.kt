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

package helper.windows.jna.structs

import com.sun.jna.Pointer
import com.sun.jna.Structure.FieldOrder
import com.sun.jna.platform.win32.WinDef.ULONG

@Suppress("unused")
@FieldOrder(
    "osVersionInfoSize",
    "majorVersion",
    "minorVersion",
    "buildNumber",
    "platformId",
    "csdVersion"
)
internal class OsVersionInfo(
    @JvmField var majorVersion: Int = 0,
    @JvmField var minorVersion: Int = 0,
    @JvmField var buildNumber: Int = 0,
    @JvmField var platformId: Int = 0
) : BaseStructure() {

    @JvmField
    var osVersionInfoSize: Int = (ULONG.SIZE * 5) + 4

    @JvmField
    var csdVersion: Pointer? = null
}
