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

package api

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.russellbanks.HashHash.BuildConfig
import com.russellbanks.ktgithub.GitHub
import com.russellbanks.ktgithub.objects.GHRelease
import io.klogging.Klogging
import io.ktor.client.engine.java.Java
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

object GitHubImpl : Klogging {
    var checkingGitHubAPI by mutableStateOf(false)
    var lastChecked: Instant? by mutableStateOf(null)
    var isUpdateAvailable by mutableStateOf(false)
    var latestRelease: GHRelease? = null
    private val gitHub = GitHub.create(Java)

    private val isVersionLatest get() = latestRelease?.tagName?.contains(BuildConfig.appVersion) == false

    val releasedVersionName get() = latestRelease?.tagName?.replace("v", "")

    val updateResponseText get() = when {
        checkingGitHubAPI -> "Checking"
        !isUpdateAvailable -> "You have the latest version"
        isUpdateAvailable -> "Out of date. Latest version is ${latestRelease?.tagName?.removePrefix("v")}"
        else -> "Error accessing GitHub API"
    }

    init {
        CoroutineScope(Dispatchers.IO).launch {
            latestRelease = gitHub.fetchLatestRelease("russellbanks", "HashHash").getOrNull()
        }
        lastChecked = Clock.System.now()
        isUpdateAvailable = isVersionLatest
    }

    suspend fun checkForHashHashUpdate() {
        if (!checkingGitHubAPI) {
            checkingGitHubAPI = true
            latestRelease = gitHub.fetchLatestRelease("russellbanks", "HashHash").getOrNull()
            lastChecked = Clock.System.now()
            checkingGitHubAPI = false
            isUpdateAvailable = isVersionLatest
        }
    }
}
