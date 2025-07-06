
package com.syabit.anikomi.scraper

import com.syabit.anikomi.data.Anime
import com.syabit.anikomi.data.Episode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class DonghuaFilmScraper(private val client: OkHttpClient) : Scraper {

    override suspend fun getHtmlDocument(url: String): Document? {
        return withContext(Dispatchers.IO) {
            try {
                val request = Request.Builder().url(url).build()
                val response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    response.body?.string()?.let { Jsoup.parse(it) }
                } else {
                    null
                }
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    suspend fun getAnimeList(url: String): List<Anime> {
        val document = getHtmlDocument(url)
        val animeList = mutableListOf<Anime>()

        document?.select("div.film-item")?.forEach { element ->
            val title = element.select("h3.film-title a").text()
            val detailUrl = element.select("h3.film-title a").attr("href")
            val thumbnailUrl = element.select("img.film-poster-img").attr("data-src")

            if (title.isNotEmpty() && detailUrl.isNotEmpty() && thumbnailUrl.isNotEmpty()) {
                animeList.add(Anime(title, thumbnailUrl, detailUrl))
            }
        }
        return animeList
    }

    suspend fun getAnimeDetail(detailUrl: String): Anime? {
        val document = getHtmlDocument(detailUrl)

        return document?.let { doc ->
            val title = doc.select("h1.film-name").text()
            val thumbnailUrl = doc.select("img.film-poster-img").attr("src")
            val synopsis = doc.select("div.film-content").text()
            val genre = doc.select("div.film-info-genre a").map { it.text() }
            val status = doc.select("div.film-info-item:contains(Status) span.film-info-value").text()
            val totalEpisodes = doc.select("div.film-info-item:contains(Episodes) span.film-info-value").text()
            val rating = doc.select("div.film-info-item:contains(Rating) span.film-info-value").text()

            val episodes = mutableListOf<Episode>()
            doc.select("ul.episode-list a").forEach { element ->
                val episodeTitle = element.text()
                val videoUrl = element.attr("href")
                if (episodeTitle.isNotEmpty() && videoUrl.isNotEmpty()) {
                    episodes.add(Episode(episodeTitle, videoUrl))
                }
            }

            Anime(
                title = title,
                thumbnailUrl = thumbnailUrl,
                detailUrl = detailUrl,
                synopsis = synopsis,
                genre = genre,
                status = status,
                totalEpisodes = totalEpisodes,
                rating = rating,
                episodes = episodes
            )
        }
    }

    suspend fun getVideoUrl(episodeUrl: String): String? {
        val document = getHtmlDocument(episodeUrl)
        // This part might be tricky as video sources are often embedded in iframes or JavaScript
        // For donghuafilm.com, the video player is often in a div with class 'player-embed'
        // and the actual video URL is inside an iframe or a script.
        // We'll try to find an iframe src or a direct video tag.
        val videoSrc = document?.select("div.player-embed iframe").attr("src")
        if (videoSrc.isNotEmpty()) {
            return videoSrc
        }
        // Fallback for direct video tag if any
        return document?.select("video source").attr("src")
    }
}


