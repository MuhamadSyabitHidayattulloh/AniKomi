# AniKomi

Aplikasi Android untuk menonton anime dan membaca komik, dibangun dengan Kotlin dan Jetpack Compose. Aplikasi ini memiliki fitur web scraping untuk mendapatkan konten, serta fitur unduh, riwayat, dan favorit.

## Status Pengembangan

✅ **Struktur Proyek Android** - Setup dasar proyek Android dengan Kotlin  
✅ **Web Scraping Implementation** - Interface dan implementasi scraper untuk DonghuaFilm dan KomikCast  
✅ **Data Models** - Model data untuk Anime, Manga, Episode, dan Chapter  
✅ **Professional UI Design** - Desain antarmuka pengguna modern dengan Jetpack Compose  
✅ **Material Design 3 Theme** - Tema profesional dengan skema warna yang konsisten  
✅ **Home Screen** - Halaman utama dengan section anime dan manga populer/terbaru  
✅ **Search Screen** - Halaman pencarian dengan grid layout untuk hasil  
✅ **Reusable Components** - AnimeCard dan MangaCard yang dapat digunakan kembali  
🔄 **Detail Screens** - Halaman detail anime dan manga (dalam pengembangan)  
🔄 **Video Player** - Pemutar video untuk anime (dalam pengembangan)  
🔄 **Manga Reader** - Pembaca komik dengan navigasi chapter (dalam pengembangan)  
🔄 **Download Feature** - Fitur unduh konten offline (dalam pengembangan)  
🔄 **History & Favorites** - Riwayat dan favorit dengan Room database (dalam pengembangan)

## Fitur yang Direncanakan

- **Menonton Anime:** Mengambil data anime populer, terbaru, dan hasil pencarian dari `donghuafilm.com`.
- **Membaca Komik:** Mengambil data manga populer, terbaru, dan hasil pencarian dari `komikcast.li`.
- **Detail Konten:** Menampilkan sinopsis, genre, status, daftar episode/chapter, dan informasi relevan lainnya.
- **Pemutar Video:** Memutar video anime langsung di aplikasi.
- **Pembaca Komik:** Menampilkan gambar-gambar chapter komik secara berurutan.
- **Unduh:** Mengunduh episode anime atau chapter komik untuk ditonton/dibaca secara offline.
- **Riwayat:** Melacak anime yang sudah ditonton dan komik yang sudah dibaca.
- **Favorit:** Menyimpan daftar anime dan komik favorit.
- **Desain Profesional:** Antarmuka pengguna yang intuitif dan menarik dengan Jetpack Compose.

## Teknologi yang Digunakan

- **Kotlin:** Bahasa pemrograman utama untuk pengembangan Android.
- **Jetpack Compose:** Toolkit UI modern untuk membangun antarmuka pengguna Android.
- **Material Design 3:** Sistem desain terbaru dari Google untuk konsistensi visual.
- **OkHttp:** HTTP client untuk melakukan permintaan jaringan.
- **Jsoup:** Library untuk parsing HTML dari halaman web.
- **Kotlin Coroutines:** Untuk manajemen konkurensi dan operasi asinkron.
- **Room Persistence Library:** Untuk penyimpanan data lokal (riwayat, favorit).
- **ExoPlayer:** Library pemutar media untuk video anime.
- **Coil:** Library untuk memuat dan menampilkan gambar.

## Struktur Proyek

```
AniKomi/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/syabit/anikomi/
│   │   │   │   ├── data/             # Data models (Anime, Episode, Manga, Chapter)
│   │   │   │   ├── scraper/          # Implementasi web scraping (DonghuaFilmScraper, KomikCastScraper)
│   │   │   │   ├── ui/
│   │   │   │   │   ├── components/   # Komponen UI yang dapat digunakan kembali
│   │   │   │   │   ├── screens/      # Layar-layar utama aplikasi
│   │   │   │   │   └── theme/        # Tema dan styling Material Design 3
│   │   │   │   └── MainActivity.kt    # Main activity dan entry point aplikasi
│   │   │   └── res/                  # Sumber daya Android (layout, values, dll.)
│   │   └── AndroidManifest.xml
│   └── build.gradle
├── build.gradle
├── settings.gradle
├── .gitignore
└── README.md
```

## Fitur UI yang Sudah Diimplementasi

### 🎨 **Tema Profesional**
- Skema warna biru dan oranye yang modern
- Support untuk mode gelap dan terang
- Typography yang konsisten dengan Material Design 3

### 🏠 **Home Screen**
- Section untuk anime populer dan terbaru
- Section untuk manga populer dan terbaru
- Horizontal scrolling untuk setiap kategori
- Search button untuk navigasi ke halaman pencarian

### 🔍 **Search Screen**
- Search bar dengan clear button
- Grid layout untuk hasil pencarian
- Support untuk pencarian anime dan manga
- Loading state dan empty state

### 🎴 **Komponen Card**
- **AnimeCard:** Menampilkan thumbnail, judul, status, dan jumlah episode
- **MangaCard:** Menampilkan thumbnail, judul, status, dan jumlah chapter
- Desain yang konsisten dengan Material Design 3
- Hover effects dan elevation

## Cara Membangun dan Menjalankan

Untuk membangun dan menjalankan aplikasi ini, Anda memerlukan:

- Android Studio terbaru (Hedgehog atau lebih baru)
- Java Development Kit (JDK) 17 atau lebih tinggi
- Android SDK Platform 34
- Android SDK Build-Tools 30.0.3

Langkah-langkah:

1.  Clone repositori ini:
    ```bash
    git clone https://github.com/MuhamadSyabitHidayattulloh/AniKomi.git
    ```
2.  Buka proyek di Android Studio.
3.  Pastikan semua dependensi Gradle telah disinkronkan.
4.  Jalankan aplikasi di emulator atau perangkat fisik.

## Screenshot

*Screenshot akan ditambahkan setelah aplikasi dapat dijalankan dengan sempurna.*

## Kontribusi

Kontribusi sangat dihargai! Silakan buka *issue* atau *pull request* jika Anda memiliki saran atau menemukan *bug*.

## Lisensi

Proyek ini dilisensikan di bawah Lisensi MIT. Lihat file `LICENSE` untuk detail lebih lanjut.

## Roadmap

- [ ] Implementasi detail screen untuk anime dan manga
- [ ] Integrasi web scraping dengan UI
- [ ] Video player dengan ExoPlayer
- [ ] Manga reader dengan zoom dan navigasi
- [ ] Database Room untuk history dan favorites
- [ ] Download manager untuk konten offline
- [ ] Settings dan preferences
- [ ] Dark/Light theme toggle
- [ ] Notification untuk update anime/manga baru


