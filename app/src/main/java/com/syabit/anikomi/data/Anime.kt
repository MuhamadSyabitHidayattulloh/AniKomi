
package com.syabit.anikomi.data

data class Anime(
    val title: String,
    val thumbnailUrl: String,
    val detailUrl: String,
    val synopsis: String? = null,
    val genre: List<String>? = null,
    val status: String? = null,
    val totalEpisodes: String? = null,
    val rating: String? = null,
    val episodes: List<Episode>? = null
)

data class Episode(
    val title: String,
    val videoUrl: String
)


