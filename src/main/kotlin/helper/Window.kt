package helper

import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState

object Window {

    fun toggleFullscreen(windowState: WindowState) {
        if (windowState.placement != WindowPlacement.Fullscreen) {
            windowState.placement = WindowPlacement.Fullscreen
        } else {
            windowState.placement = WindowPlacement.Floating
        }
    }

}