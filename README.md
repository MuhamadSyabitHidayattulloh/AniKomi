# AniKomi

Aplikasi Android untuk menonton anime dan membaca komik, dibangun dengan Kotlin dan Jetpack Compose. Aplikasi ini akan memiliki fitur web scraping untuk mendapatkan konten, serta fitur unduh, riwayat, dan favorit.

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
│   │   │   │   └── MainActivity.kt    # Main activity dan entry point aplikasi
│   │   │   └── res/                  # Sumber daya Android (layout, values, dll.)
│   │   └── AndroidManifest.xml
│   └── build.gradle
├── build.gradle
├── settings.gradle
└── README.md
```

## Cara Membangun dan Menjalankan (Dalam Pengembangan)

Proyek ini masih dalam tahap pengembangan awal. Untuk membangun dan menjalankan aplikasi ini, Anda memerlukan:

- Android Studio terbaru
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

## Kontribusi

Kontribusi sangat dihargai! Silakan buka *issue* atau *pull request* jika Anda memiliki saran atau menemukan *bug*.

## Lisensi

Proyek ini dilisensikan di bawah Lisensi MIT. Lihat file `LICENSE` untuk detail lebih lanjut.


