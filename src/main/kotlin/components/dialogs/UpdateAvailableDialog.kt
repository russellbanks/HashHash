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

package components.dialogs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import api.Ktor
import com.russellbanks.HashHash.BuildConfig
import helper.Browser
import helper.GitHub
import helper.Icons
import koin.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.pushingpixels.aurora.component.model.Command
import org.pushingpixels.aurora.component.model.LabelContentModel
import org.pushingpixels.aurora.component.model.LabelPresentationModel
import org.pushingpixels.aurora.component.projection.CommandButtonProjection
import org.pushingpixels.aurora.component.projection.LabelProjection
import java.net.URL

@Composable
fun UpdateAvailableDialog(ktor: Ktor = get()) {
    val scope = rememberCoroutineScope { Dispatchers.Default }
    Dialog(dialog = DialogState.Dialogs.Update) {
        Row(Modifier.padding(30.dp)) {
            Box(Modifier.padding(end = 30.dp)) {
                Image(
                    painter = Icons.logo(),
                    contentDescription = "${BuildConfig.appName} logo",
                    modifier = Modifier.size(60.dp)
                )
            }
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                LabelProjection(
                    contentModel = LabelContentModel(text = "There is a new update available!"),
                    presentationModel = LabelPresentationModel(textStyle = TextStyle(fontWeight = FontWeight.SemiBold))
                ).project()
                LabelProjection(
                    contentModel = LabelContentModel(text = "${BuildConfig.appVersion} ➝ ${ktor.getReleasedVersion()}")
                ).project()
                CommandButtonProjection(
                    contentModel = Command(
                        text = "Go to release page",
                        action = {
                            scope.launch {
                                Browser.open(URL(ktor.githubData?.htmlUrl ?: GitHub.HashHash.Repository.releases))
                            }
                        }
                    )
                ).project()
            }
        }
    }
}
