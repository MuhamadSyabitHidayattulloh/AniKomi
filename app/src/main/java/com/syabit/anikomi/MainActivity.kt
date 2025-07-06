
package com.syabit.anikomi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.syabit.anikomi.ui.theme.AniKomiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AniKomiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AniKomiTheme {
        Greeting("Android")
    }
}





import android.util.Log
import com.syabit.anikomi.scraper.DonghuaFilmScraper
import com.syabit.anikomi.scraper.KomikCastScraper
import okhttp3.OkHttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
                    Greeting("Android")
                }
            }
        }

        // Call the test function
        testScrapers()
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


