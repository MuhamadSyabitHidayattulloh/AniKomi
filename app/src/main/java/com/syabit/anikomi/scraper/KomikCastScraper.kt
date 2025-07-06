

package com.syabit.anikomi.scraper

import com.syabit.anikomi.data.Chapter
import com.syabit.anikomi.data.Manga
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class KomikCastScraper(private val client: OkHttpClient) : Scraper {

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

    suspend fun getMangaList(url: String): List<Manga> {
        val document = getHtmlDocument(url)
        val mangaList = mutableListOf<Manga>()

        document?.select("div.list-update_item")?.forEach { element ->
            val title = element.select("h3.title a").text()
            val detailUrl = element.select("h3.title a").attr("href")
            val thumbnailUrl = element.select("div.img-in_ar img").attr("data-src")

            if (title.isNotEmpty() && detailUrl.isNotEmpty() && thumbnailUrl.isNotEmpty()) {
                mangaList.add(Manga(title, thumbnailUrl, detailUrl))
            }
        }
        return mangaList
    }

    suspend fun getMangaDetail(detailUrl: String): Manga? {
        val document = getHtmlDocument(detailUrl)

        return document?.let { doc ->
            val title = doc.select("h1.komik-title").text()
            val thumbnailUrl = doc.select("div.komik-thumb img").attr("src")
            val synopsis = doc.select("div.komik-synopsis").text()
            val genre = doc.select("span.genre-info a").map { it.text() }
            val status = doc.select("span.status-info").text()

            val chapters = mutableListOf<Chapter>()
            doc.select("ul.komik-chapter-list li a").forEach { element ->
                val chapterTitle = element.text()
                val chapterUrl = element.attr("href")
                if (chapterTitle.isNotEmpty() && chapterUrl.isNotEmpty()) {
                    chapters.add(Chapter(chapterTitle, chapterUrl))
                }
            }

            Manga(
                title = title,
                thumbnailUrl = thumbnailUrl,
                detailUrl = detailUrl,
                synopsis = synopsis,
                genre = genre,
                status = status,
                chapters = chapters
            )
        }
    }

    suspend fun getChapterImages(chapterUrl: String): List<String> {
        val document = getHtmlDocument(chapterUrl)
        val imageUrls = mutableListOf<String>()

        document?.select("div#chapter_body img")?.forEach { element ->
            val imageUrl = element.attr("src")
            if (imageUrl.isNotEmpty()) {
                imageUrls.add(imageUrl)
            }
        }
        return imageUrls
    }
}


