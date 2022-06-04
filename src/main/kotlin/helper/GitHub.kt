package helper

import com.russellbanks.HashHash.BuildConfig
import data.GitHubData
import io.ktor.client.statement.HttpResponse
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

    fun getUpdateResponseText(
        checkingGitHubAPI: Boolean,
        gitHubData: GitHubData?,
        httpResponse: HttpResponse?
    ) = when {
        checkingGitHubAPI -> "Checking"
        gitHubData?.tagName?.contains(BuildConfig.appVersion) == true -> "You have the latest version"
        gitHubData?.tagName?.contains(BuildConfig.appVersion) == false -> {
            "Out of date. Latest version is ${gitHubData.tagName.removePrefix("v")}"
        }
        (httpResponse?.headers?.get("X-RateLimit-Remaining")?.toInt() ?: -1) == 0 -> {
            val epochSeconds = httpResponse?.headers?.get("X-RateLimit-Reset")?.toLong()
            val instantEpochSeconds = epochSeconds?.let { Instant.fromEpochSeconds(it) }
            var minutesLeft = instantEpochSeconds?.let { Clock.System.now().until(it, DateTimeUnit.MINUTE) }
            if (minutesLeft == 0L) minutesLeft = 1L
            "Rate limited. Check back in $minutesLeft minute${if (minutesLeft != 1L) "s" else ""}"
        }
        httpResponse != null -> "${httpResponse.status} - Check back later"
        else -> "Error accessing GitHub API"
    }
}
