# Paws & Care - UTS IF433L (NIM 00000075344)

Project Kotlin console app untuk UTS "Paws & Care" — dibuat untuk NIM 00000075344, nama: faturachman al-kahfi.

Cara menjalankan:
1. (Direkomendasikan) Gunakan Gradle:
   - Jika belum ada wrapper: jalankan `gradle wrapper` di root project.
   - Jalankan: `./gradlew run` (Linux/Mac) atau `gradlew run` (Windows).

2. Atau buka di IntelliJ IDEA: Import sebagai Gradle project, lalu run `MainKt`.

File penting:
- Source: `src/main/kotlin/uts_00000075344_faturachman_al_kahfi/...`
- CSV data: `patients.csv` (letakkan di working directory saat menjalankan)

Instruksi pembuatan ZIP untuk upload UTS:
- ZIP hanya folder `src` (bukan root project).
- Nama file ZIP harus persis:
  uts_oop_00000075344_faturachman_al_kahfi.zip

Contoh perintah (Linux/Mac):
- Jika struktur project di folder project-root:
  zip -r uts_oop_00000075344_faturachman_al_kahfi.zip src

Catatan:
- Program membaca `patients.csv` pada startup (jika tidak ada, akan dibuat).
- Program menimpa `patients.csv` saat memilih menu: Simpan Data & Keluar.
