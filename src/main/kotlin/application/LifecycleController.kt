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

package application

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.window.WindowState
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.create
import com.arkivanov.essenty.lifecycle.resume
import com.arkivanov.essenty.lifecycle.stop

@ExperimentalDecomposeApi
@Composable
fun LifecycleController(lifecycleRegistry: LifecycleRegistry, windowState: WindowState) {
    LaunchedEffect(lifecycleRegistry, windowState) {
        snapshotFlow(windowState::isMinimized).collect { isMinimized ->
            if (isMinimized) {
                lifecycleRegistry.stop()
            } else {
                lifecycleRegistry.resume()
            }
        }
    }

    DisposableEffect(lifecycleRegistry) {
        lifecycleRegistry.create()
        onDispose(lifecycleRegistry::stop)
    }
}
