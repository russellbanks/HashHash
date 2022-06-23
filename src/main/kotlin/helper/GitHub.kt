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

package helper

import api.Ktor
import com.russellbanks.HashHash.BuildConfig
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.until

object GitHub {
    private const val githubWebsite = "https://github.com"
    private const val APIWebsite = "https://api.github.com"

    object HashHash {
        object API {
            const val latest = "$APIWebsite/repos/russellbanks/HashHash/releases/latest"
        }
        object Repository {
            const val website = "$githubWebsite/russellbanks/HashHash"
            const val releases = "$website/releases"
            const val newIssue = "$website/issues/new/choose"
        }
    }

    fun getUpdateResponseText(checkingGitHubAPI: Boolean, ktor: Ktor) = when {
        checkingGitHubAPI -> "Checking"
        ktor.githubData?.tagName?.contains(BuildConfig.appVersion) == true -> "You have the latest version"
        ktor.githubData?.tagName?.contains(BuildConfig.appVersion) == false -> {
            "Out of date. Latest version is ${ktor.githubData?.tagName?.removePrefix("v")}"
        }
        (ktor.httpResponse?.headers?.get("X-RateLimit-Remaining")?.toInt() ?: -1) == 0 -> {
            val epochSeconds = ktor.httpResponse?.headers?.get("X-RateLimit-Reset")?.toLong()
            val instantEpochSeconds = epochSeconds?.let { Instant.fromEpochSeconds(it) }
            var minutesLeft = instantEpochSeconds?.let { Clock.System.now().until(it, DateTimeUnit.MINUTE) }
            if (minutesLeft == 0L) minutesLeft = 1L
            "Rate limited. Check back in $minutesLeft minute${if (minutesLeft != 1L) "s" else ""}"
        }
        ktor.httpResponse != null -> "${ktor.httpResponse?.status} - Check back later"
        else -> "Error accessing GitHub API"
    }
}
