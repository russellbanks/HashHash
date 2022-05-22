package helper

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

}