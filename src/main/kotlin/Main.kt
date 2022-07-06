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

import api.Ktor
import application.ApplicationState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import components.RootComponent
import components.dialogs.DialogState
import components.dialogs.settings.SettingsRootComponent
import components.screens.ParentComponent
import components.screens.compare.CompareFilesComponent
import components.screens.file.FileScreenComponent
import components.screens.text.TextScreenComponent
import io.klogging.config.ANSI_CONSOLE
import io.klogging.config.loggingConfiguration
import koin.KoinLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import preferences.mode.ModeHandler
import preferences.theme.ThemeHandler
import preferences.titlebar.TitleBarHandler
import preferences.windowcorner.WindowCornerHandler

fun main() {
    loggingConfiguration { ANSI_CONSOLE() }

    val lifecycle = LifecycleRegistry()
    val root = RootComponent(DefaultComponentContext(lifecycle))
    val settingsRoot = SettingsRootComponent(DefaultComponentContext(lifecycle))

    startKoin {
        modules(
            module {
                singleOf(::Ktor)
                singleOf(::TitleBarHandler)
                singleOf(::WindowCornerHandler)
                singleOf(::DialogState)
                singleOf(::ThemeHandler)
                singleOf(::ModeHandler)
                singleOf(::ApplicationState)
                singleOf(::ParentComponent)
                single { FileScreenComponent(DefaultComponentContext(lifecycle)) }
                single { TextScreenComponent(DefaultComponentContext(lifecycle)) }
                single { CompareFilesComponent(DefaultComponentContext(lifecycle)) }
            }
        )
        logger(KoinLogger())
    }

    hashHashApplication(
        lifecycle = lifecycle,
        root = root,
        settingsRoot = settingsRoot
    )
}
