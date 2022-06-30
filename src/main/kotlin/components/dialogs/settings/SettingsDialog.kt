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

package components.dialogs.settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import application.ApplicationWindowState
import application.DialogState
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.router.RouterState
import com.mayakapps.compose.windowstyler.WindowCornerPreference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.pushingpixels.aurora.component.AuroraBoxWithHighlights
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.projection.HorizontalSeparatorProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import org.pushingpixels.aurora.theming.AuroraSkin
import preferences.theme.Theme
import preferences.theme.ThemeHandler
import preferences.titlebar.TitleBar
import preferences.titlebar.TitleBarHandler
import preferences.windowcorner.WindowCornerHandler

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun SettingsDialog(
    activeComponent: SettingsRoot.Child,
    root: SettingsRoot,
    routerState: State<RouterState<*, SettingsRoot.Child>>,
    dialogState: DialogState,
    themeHandler: ThemeHandler,
    titleBarHandler: TitleBarHandler,
    windowCornerHandler: WindowCornerHandler,
    window: ApplicationWindowState
) {
    val backgroundColorScheme = AuroraSkin.colors.getBackgroundColorScheme(
        decorationAreaType = AuroraSkin.decorationAreaType
    )
    val scope = rememberCoroutineScope()
    AnimatedVisibility(
        visible = dialogState.Settings().isOpen(),
        enter = fadeIn() + slideInVertically(initialOffsetY = { -it / 10 }),
        exit = fadeOut() + slideOutVertically(targetOffsetY = { -it / 10 })
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Surface(
                modifier = Modifier.width(400.dp),
                shape = RoundedCornerShape(8.dp),
                color = backgroundColorScheme.backgroundFillColor,
                border = BorderStroke(1.dp, Color.Black),
                elevation = 4.dp
            ) {
                Column {
                    Column(modifier = Modifier.weight(weight = 1f, fill = false)) {
                        LabelProjection(
                            contentModel = LabelContentModel(text = "Settings"),
                            presentationModel = LabelPresentationModel(
                                textStyle = TextStyle(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        ).project(Modifier.padding(14.dp))
                        HorizontalSeparatorProjection().project(Modifier.fillMaxWidth())
                        Row {
                            LazyColumn(Modifier.background(backgroundColorScheme.accentedBackgroundFillColor)) {
                                items(Category.values()) { category ->
                                    AuroraBoxWithHighlights(
                                        modifier = Modifier
                                            .width(120.dp)
                                            .padding(6.dp),
                                        selected = activeComponent == category.toComponent(),
                                        onClick = {
                                            when (category.ordinal) {
                                                0 -> root.onThemeTabClicked()
                                                1 -> root.onTitleBarTabClicked()
                                                2 -> root.onWindowCornerTabClicked()
                                            }
                                        }
                                    ) {
                                        LabelProjection(
                                            contentModel = LabelContentModel(
                                                text = category.name.toFriendlyCase()
                                            ),
                                            presentationModel = LabelPresentationModel(
                                                textStyle = TextStyle(fontSize = 12.sp)
                                            )
                                        ).project(Modifier.padding(4.dp))
                                    }
                                }
                            }
                            Children(routerState = routerState.value) {
                                when (it.instance) {
                                    is SettingsRoot.Child.Theme -> {
                                        LazyColumn(Modifier.padding(10.dp)) {
                                            items(Theme.values()) { theme ->
                                                AuroraBoxWithHighlights(
                                                    modifier = Modifier.fillMaxWidth().padding(6.dp),
                                                    selected = theme == themeHandler.getTheme(),
                                                    onClick = {
                                                        if (themeHandler.getTheme() != theme) {
                                                            scope.launch(Dispatchers.Default) {
                                                                themeHandler.putTheme(theme)
                                                            }
                                                        }
                                                    }
                                                ) {
                                                    LabelProjection(
                                                        contentModel = LabelContentModel(text = theme.name),
                                                        presentationModel = LabelPresentationModel(
                                                            textStyle = TextStyle(
                                                                fontSize = 12.sp,
                                                                fontWeight = FontWeight.SemiBold
                                                            )
                                                        )
                                                    ).project(Modifier.padding(4.dp))
                                                }
                                            }
                                        }
                                    }
                                    is SettingsRoot.Child.TitleBar -> {
                                        LazyColumn {
                                            items(TitleBar.values()) { titleBar ->
                                                AuroraBoxWithHighlights(
                                                    modifier = Modifier.fillMaxWidth().padding(6.dp),
                                                    selected = titleBar == titleBarHandler.getTitleBar(),
                                                    onClick = {
                                                        if (titleBarHandler.getTitleBar() != titleBar) {
                                                            scope.launch(Dispatchers.Default) {
                                                                titleBarHandler.putTitleBar(titleBar)
                                                                window.checkWindowNeedsRestarting(
                                                                    titleBarHandler = titleBarHandler,
                                                                    windowCornerHandler = windowCornerHandler
                                                                )
                                                            }
                                                        }
                                                    }
                                                ) {
                                                    LabelProjection(
                                                        contentModel = LabelContentModel(text = titleBar.name),
                                                        presentationModel = LabelPresentationModel(
                                                            textStyle = TextStyle(
                                                                fontSize = 12.sp,
                                                                fontWeight = FontWeight.SemiBold
                                                            )
                                                        )
                                                    ).project(Modifier.padding(4.dp))
                                                }
                                            }
                                        }
                                    }
                                    is SettingsRoot.Child.WindowCorner -> {
                                        LazyColumn {
                                            items(WindowCornerPreference.values()) { windowCornerPreference ->
                                                AuroraBoxWithHighlights(
                                                    modifier = Modifier.fillMaxWidth().padding(6.dp),
                                                    selected = windowCornerPreference ==
                                                            windowCornerHandler.getWindowCorner(),
                                                    onClick = {
                                                        if (
                                                            windowCornerHandler.getWindowCorner() != windowCornerPreference
                                                        ) {
                                                            scope.launch(Dispatchers.Default) {
                                                                windowCornerHandler.putWindowCorner(windowCornerPreference)
                                                                window.checkWindowNeedsRestarting(
                                                                    titleBarHandler = titleBarHandler,
                                                                    windowCornerHandler = windowCornerHandler
                                                                )
                                                            }
                                                        }
                                                    }
                                                ) {
                                                    LabelProjection(
                                                        contentModel = LabelContentModel(
                                                            text = windowCornerPreference.name
                                                                .lowercase()
                                                                .replaceFirstChar { char -> char.titlecase() }
                                                                .replace(oldValue = "_", newValue = " ")
                                                        ),
                                                        presentationModel = LabelPresentationModel(
                                                            textStyle = TextStyle(
                                                                fontSize = 12.sp,
                                                                fontWeight = FontWeight.SemiBold
                                                            )
                                                        )
                                                    ).project(Modifier.padding(4.dp))
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    RestartWindowFooter(dialogState = dialogState, window = window)
                }
            }
        }
    }
}

enum class Category {
    Theme,
    TitleBar,
    WindowCorner
}

fun Category.toComponent(): SettingsRoot.Child {
    return when (this) {
        Category.Theme -> SettingsRoot.Child.Theme
        Category.TitleBar -> SettingsRoot.Child.TitleBar
        Category.WindowCorner -> SettingsRoot.Child.WindowCorner
    }
}
