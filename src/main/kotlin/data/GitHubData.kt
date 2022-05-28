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

package data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GitHubData(
    @SerialName("assets") val assets: List<Asset>,
    @SerialName("assets_url") val assetsUrl: String,
    @SerialName("author") val author: Author,
    @SerialName("body") val body: String,
    @SerialName("created_at") val createdAt: String,
    @SerialName("draft") val draft: Boolean,
    @SerialName("html_url") val htmlUrl: String,
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("node_id") val nodeId: String,
    @SerialName("prerelease") val prerelease: Boolean,
    @SerialName("published_at") val publishedAt: String,
    @SerialName("tag_name") val tagName: String,
    @SerialName("tarball_url") val tarballUrl: String,
    @SerialName("target_commitish") val targetCommitish: String,
    @SerialName("upload_url") val uploadUrl: String,
    @SerialName("url") val url: String,
    @SerialName("zipball_url") val zipballUrl: String
) {
    @Serializable
    data class Asset(
        @SerialName("browser_download_url") val browserDownloadUrl: String,
        @SerialName("content_type") val contentType: String,
        @SerialName("created_at") val createdAt: String,
        @SerialName("download_count") val downloadCount: Int,
        @SerialName("id") val id: Int,
        @SerialName("label") val label: String,
        @SerialName("name") val name: String,
        @SerialName("node_id") val nodeId: String,
        @SerialName("size") val size: Int,
        @SerialName("state") val state: String,
        @SerialName("updated_at") val updatedAt: String,
        @SerialName("uploader") val uploader: Uploader,
        @SerialName("url") val url: String
    ) {
        @Serializable
        data class Uploader(
            @SerialName("avatar_url") val avatarUrl: String,
            @SerialName("events_url") val eventsUrl: String,
            @SerialName("followers_url") val followersUrl: String,
            @SerialName("following_url") val followingUrl: String,
            @SerialName("gists_url") val gistsUrl: String,
            @SerialName("gravatar_id") val gravatarId: String,
            @SerialName("html_url") val htmlUrl: String,
            @SerialName("id") val id: Int,
            @SerialName("login") val login: String,
            @SerialName("node_id") val nodeId: String,
            @SerialName("organizations_url") val organizationsUrl: String,
            @SerialName("received_events_url") val receivedEventsUrl: String,
            @SerialName("repos_url") val reposUrl: String,
            @SerialName("site_admin") val siteAdmin: Boolean,
            @SerialName("starred_url") val starredUrl: String,
            @SerialName("subscriptions_url") val subscriptionsUrl: String,
            @SerialName("type") val type: String,
            @SerialName("url") val url: String
        )
    }

    @Serializable
    data class Author(
        @SerialName("avatar_url") val avatarUrl: String,
        @SerialName("events_url") val eventsUrl: String,
        @SerialName("followers_url") val followersUrl: String,
        @SerialName("following_url") val followingUrl: String,
        @SerialName("gists_url") val gistsUrl: String,
        @SerialName("gravatar_id") val gravatarId: String,
        @SerialName("html_url") val htmlUrl: String,
        @SerialName("id") val id: Int,
        @SerialName("login") val login: String,
        @SerialName("node_id") val nodeId: String,
        @SerialName("organizations_url") val organizationsUrl: String,
        @SerialName("received_events_url") val receivedEventsUrl: String,
        @SerialName("repos_url") val reposUrl: String,
        @SerialName("site_admin") val siteAdmin: Boolean,
        @SerialName("starred_url") val starredUrl: String,
        @SerialName("subscriptions_url") val subscriptionsUrl: String,
        @SerialName("type") val type: String,
        @SerialName("url") val url: String
    )
}