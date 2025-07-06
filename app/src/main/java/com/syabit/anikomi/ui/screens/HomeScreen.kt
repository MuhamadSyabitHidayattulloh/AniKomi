package com.syabit.anikomi.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.syabit.anikomi.data.Anime
import com.syabit.anikomi.data.Manga
import com.syabit.anikomi.ui.components.AnimeCard
import com.syabit.anikomi.ui.components.MangaCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    popularAnime: List<Anime> = emptyList(),
    latestAnime: List<Anime> = emptyList(),
    popularManga: List<Manga> = emptyList(),
    latestManga: List<Manga> = emptyList(),
    onAnimeClick: (Anime) -> Unit = {},
    onMangaClick: (Manga) -> Unit = {},
    onSearchClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top App Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "AniKomi",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            
            IconButton(onClick = onSearchClick) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
        
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Popular Anime Section
            item {
                SectionHeader(title = "Popular Anime")
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(horizontal = 4.dp)
                ) {
                    items(popularAnime) { anime ->
                        AnimeCard(
                            anime = anime,
                            onClick = { onAnimeClick(anime) }
                        )
                    }
                }
            }
            
            // Latest Anime Section
            item {
                SectionHeader(title = "Latest Anime")
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(horizontal = 4.dp)
                ) {
                    items(latestAnime) { anime ->
                        AnimeCard(
                            anime = anime,
                            onClick = { onAnimeClick(anime) }
                        )
                    }
                }
            }
            
            // Popular Manga Section
            item {
                SectionHeader(title = "Popular Manga")
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(horizontal = 4.dp)
                ) {
                    items(popularManga) { manga ->
                        MangaCard(
                            manga = manga,
                            onClick = { onMangaClick(manga) }
                        )
                    }
                }
            }
            
            // Latest Manga Section
            item {
                SectionHeader(title = "Latest Manga")
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(horizontal = 4.dp)
                ) {
                    items(latestManga) { manga ->
                        MangaCard(
                            manga = manga,
                            onClick = { onMangaClick(manga) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 12.dp),
        fontSize = 20.sp
    )
}

