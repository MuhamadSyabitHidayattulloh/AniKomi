package com.syabit.anikomi

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.syabit.anikomi.data.Anime
import com.syabit.anikomi.data.Manga
import com.syabit.anikomi.scraper.DonghuaFilmScraper
import com.syabit.anikomi.scraper.KomikCastScraper
import com.syabit.anikomi.ui.screens.HomeScreen
import com.syabit.anikomi.ui.screens.SearchScreen
import com.syabit.anikomi.ui.theme.AniKomiTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient

class MainActivity : ComponentActivity() {
    private val TAG = "AniKomiApp"
    private val okHttpClient = OkHttpClient()
    private val donghuaFilmScraper = DonghuaFilmScraper(okHttpClient)
    private val komikCastScraper = KomikCastScraper(okHttpClient)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AniKomiTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AniKomiApp()
                }
            }
        }

        // Call the test function
        testScrapers()
    }

    @Composable
    fun AniKomiApp() {
        var currentScreen by remember { mutableStateOf("home") }
        var popularAnime by remember { mutableStateOf<List<Anime>>(emptyList()) }
        var latestAnime by remember { mutableStateOf<List<Anime>>(emptyList()) }
        var popularManga by remember { mutableStateOf<List<Manga>>(emptyList()) }
        var latestManga by remember { mutableStateOf<List<Manga>>(emptyList()) }
        var searchResults by remember { mutableStateOf<List<Any>>(emptyList()) }
        var searchQuery by remember { mutableStateOf("") }
        var isLoading by remember { mutableStateOf(false) }

        // Load initial data
        LaunchedEffect(Unit) {
            loadInitialData { popular, latest, popularM, latestM ->
                popularAnime = popular
                latestAnime = latest
                popularManga = popularM
                latestManga = latestM
            }
        }

        when (currentScreen) {
            "home" -> {
                HomeScreen(
                    popularAnime = popularAnime,
                    latestAnime = latestAnime,
                    popularManga = popularManga,
                    latestManga = latestManga,
                    onAnimeClick = { anime ->
                        Log.d(TAG, "Anime clicked: ${anime.title}")
                        // TODO: Navigate to anime detail screen
                    },
                    onMangaClick = { manga ->
                        Log.d(TAG, "Manga clicked: ${manga.title}")
                        // TODO: Navigate to manga detail screen
                    },
                    onSearchClick = {
                        currentScreen = "search"
                    }
                )
            }
            "search" -> {
                SearchScreen(
                    searchQuery = searchQuery,
                    searchResults = searchResults,
                    isLoading = isLoading,
                    onSearchQueryChange = { query ->
                        searchQuery = query
                    },
                    onSearch = { query ->
                        performSearch(query) { results ->
                            searchResults = results
                        }
                    },
                    onAnimeClick = { anime ->
                        Log.d(TAG, "Anime clicked: ${anime.title}")
                        // TODO: Navigate to anime detail screen
                    },
                    onMangaClick = { manga ->
                        Log.d(TAG, "Manga clicked: ${manga.title}")
                        // TODO: Navigate to manga detail screen
                    },
                    onBackClick = {
                        currentScreen = "home"
                    }
                )
            }
        }
    }

    private fun loadInitialData(
        onDataLoaded: (List<Anime>, List<Anime>, List<Manga>, List<Manga>) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Load sample data for demonstration
                val sampleAnime = listOf(
                    Anime("Sample Anime 1", "https://via.placeholder.com/300x400", "", "A great anime", listOf("Action", "Adventure"), "Ongoing", "12"),
                    Anime("Sample Anime 2", "https://via.placeholder.com/300x400", "", "Another great anime", listOf("Romance", "Drama"), "Completed", "24"),
                    Anime("Sample Anime 3", "https://via.placeholder.com/300x400", "", "Yet another anime", listOf("Comedy", "Slice of Life"), "Ongoing", "8")
                )
                
                val sampleManga = listOf(
                    Manga("Sample Manga 1", "https://via.placeholder.com/300x400", "", "A great manga", listOf("Action", "Adventure"), "Ongoing"),
                    Manga("Sample Manga 2", "https://via.placeholder.com/300x400", "", "Another great manga", listOf("Romance", "Drama"), "Completed"),
                    Manga("Sample Manga 3", "https://via.placeholder.com/300x400", "", "Yet another manga", listOf("Comedy", "Slice of Life"), "Ongoing")
                )

                onDataLoaded(sampleAnime, sampleAnime, sampleManga, sampleManga)
            } catch (e: Exception) {
                Log.e(TAG, "Error loading initial data: ${e.message}", e)
            }
        }
    }

    private fun performSearch(
        query: String,
        onSearchComplete: (List<Any>) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // For demonstration, return sample search results
                val searchResults = mutableListOf<Any>()
                
                if (query.isNotEmpty()) {
                    searchResults.add(
                        Anime("Search Result Anime", "https://via.placeholder.com/300x400", "", "Search result anime", listOf("Action"), "Ongoing", "12")
                    )
                    searchResults.add(
                        Manga("Search Result Manga", "https://via.placeholder.com/300x400", "", "Search result manga", listOf("Action"), "Ongoing")
                    )
                }

                onSearchComplete(searchResults)
            } catch (e: Exception) {
                Log.e(TAG, "Error performing search: ${e.message}", e)
                onSearchComplete(emptyList())
            }
        }
    }

    private fun testScrapers() {
        CoroutineScope(Dispatchers.IO).launch {
            // Test DonghuaFilmScraper
            Log.d(TAG, "Testing DonghuaFilmScraper...")
            try {
                val popularAnime = donghuaFilmScraper.getAnimeList("https://donghuafilm.com/anime/?order=popular")
                Log.d(TAG, "Popular Anime: ${popularAnime.size} items")
                popularAnime.take(2).forEach { anime ->
                    Log.d(TAG, "  Title: ${anime.title}, Detail URL: ${anime.detailUrl}")
                    val detail = donghuaFilmScraper.getAnimeDetail(anime.detailUrl)
                    Log.d(TAG, "    Detail for ${detail?.title}: Synopsis: ${detail?.synopsis?.take(50)}...")
                    detail?.episodes?.firstOrNull()?.let { episode ->
                        Log.d(TAG, "      First Episode: ${episode.title}, Video URL: ${episode.videoUrl}")
                        val videoSrc = donghuaFilmScraper.getVideoUrl(episode.videoUrl)
                        Log.d(TAG, "        Actual Video Source: $videoSrc")
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error testing DonghuaFilmScraper: ${e.message}", e)
            }

            // Test KomikCastScraper
            Log.d(TAG, "Testing KomikCastScraper...")
            try {
                val updatedManga = komikCastScraper.getMangaList("https://komikcast.li/daftar-komik/?sortby=update")
                Log.d(TAG, "Updated Manga: ${updatedManga.size} items")
                updatedManga.take(2).forEach { manga ->
                    Log.d(TAG, "  Title: ${manga.title}, Detail URL: ${manga.detailUrl}")
                    val detail = komikCastScraper.getMangaDetail(manga.detailUrl)
                    Log.d(TAG, "    Detail for ${detail?.title}: Synopsis: ${detail?.synopsis?.take(50)}...")
                    detail?.chapters?.firstOrNull()?.let { chapter ->
                        Log.d(TAG, "      First Chapter: ${chapter.title}, Chapter URL: ${chapter.chapterUrl}")
                        val imageUrls = komikCastScraper.getChapterImages(chapter.chapterUrl)
                        Log.d(TAG, "        Chapter Images: ${imageUrls.size} images")
                        imageUrls.take(2).forEach { img -> Log.d(TAG, "          Image: $img") }
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error testing KomikCastScraper: ${e.message}", e)
            }
        }
    }
}

