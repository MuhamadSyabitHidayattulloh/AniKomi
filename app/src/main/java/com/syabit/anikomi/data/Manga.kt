

package com.syabit.anikomi.data

data class Manga(
    val title: String,
    val thumbnailUrl: String,
    val detailUrl: String,
    val synopsis: String? = null,
    val genre: List<String>? = null,
    val status: String? = null,
    val chapters: List<Chapter>? = null
)

data class Chapter(
    val title: String,
    val chapterUrl: String,
    val imageUrls: List<String>? = null
)


