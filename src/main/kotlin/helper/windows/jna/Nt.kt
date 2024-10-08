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

package helper.windows.jna

import com.sun.jna.Native
import com.sun.jna.win32.StdCallLibrary
import com.sun.jna.win32.W32APIOptions
import helper.windows.jna.structs.OsVersionInfo

internal object Nt {
    fun getVersion() = OsVersionInfo().also(NtImpl::RtlGetVersion)
}

@Suppress("SpellCheckingInspection")
private object NtImpl : NtApi by Native.load("Ntdll", NtApi::class.java, W32APIOptions.DEFAULT_OPTIONS)

@Suppress("FunctionName")
private interface NtApi : StdCallLibrary {
    fun RtlGetVersion(osVersionInfo: OsVersionInfo): Int
}
