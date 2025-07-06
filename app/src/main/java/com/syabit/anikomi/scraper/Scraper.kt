
package com.syabit.anikomi.scraper

import org.jsoup.nodes.Document

interface Scraper {
    suspend fun getHtmlDocument(url: String): Document?
}


