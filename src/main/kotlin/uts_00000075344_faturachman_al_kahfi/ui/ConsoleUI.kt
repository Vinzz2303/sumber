package uts_00000075344_faturachman_al_kahfi.ui

import uts_00000075344_faturachman_al_kahfi.model.Pet
import uts_00000075344_faturachman_al_kahfi.repository.PetRepository
import uts_00000075344_faturachman_al_kahfi.service.Grooming
import uts_00000075344_faturachman_al_kahfi.service.Vaccination
import uts_00000075344_faturachman_al_kahfi.storage.Storage
import uts_00000075344_faturachman_al_kahfi.util.toIdFormat
import uts_00000075344_faturachman_al_kahfi.util.displayInfo
import kotlin.system.exitProcess

class ConsoleUI(private val repo: PetRepository, private val storage: Storage) {

    fun start() {
        while (true) {
            println()
            println("Main Menu:")
            println("1. Daftarkan Pasien Baru")
            println("2. Lihat Semua Pasien")
            println("3. Cari & Berikan Layanan (Grooming/Vaksinasi)")
            println("4. Simpan Data & Keluar")
            print("Pilih menu (1 - 4): ")

            val input = readLine()
            val choice = input?.toIntOrNull()
            if (choice == null) {
                println("[ERROR] Input tidak valid! Harap masukkan format angka.")
                continue
            }

            when (choice) {
                1 -> registerPatient()
                2 -> viewAllPatients()
                3 -> searchAndApplyService()
                4 -> {
                    println("[INFO] Menyimpan data ke patients.csv...")
                    val success = storage.save(repo.getAll())
                    if (success) {
                        println("[INFO] Data berhasil disimpan. Terima kasih telah menggunakan Paws & Care!")
                    } else {
                        println("[ERROR] Gagal menyimpan data.")
                    }
                    return
                }
                else -> println("[ERROR] Pilihan tidak valid.")
            }
        }
    }

    private fun registerPatient() {
        println("-- PENDAFTARAN PASIEN --")
        print("Masukkan ID Pasien: ")
        val rawId = readLine().toIdFormat()
        if (rawId.isBlank()) {
            println("[ERROR] ID tidak boleh kosong.")
            return
        }

        print("Masukkan Nama Hewan: ")
        val name = readLine()?.trim().takeIf { !it.isNullOrBlank() } ?: run {
            println("[ERROR] Nama tidak boleh kosong.")
            return
        }

        print("Masukkan Jenis Hewan: ")
        val type = readLine()?.trim().takeIf { !it.isNullOrBlank() } ?: run {
            println("[ERROR] Jenis hewan tidak boleh kosong.")
            return
        }

        // gunakan scope function apply untuk membangun dan menambahkan Pet
        val pet = Pet(rawId, name, type).apply {
            // apply digunakan bila perlu pengaturan tambahan
        }
        repo.add(pet)
        println("[INFO] Pasien ${pet.name} (${pet.id}) berhasil didaftarkan!")
    }

    private fun viewAllPatients() {
        println("-- DAFTAR SEMUA PASIEN --")
        val all = repo.getAll()
        all.forEachIndexed { idx, pet ->
            println("${idx + 1}. ${pet.displayInfo()}")
        }
        println("[INFO] Total: ${all.size} pasien terdaftar.")
    }

    private fun searchAndApplyService() {
        println("-- LAYANAN KLINIK --")
        print("Masukkan ID Pasien yang dicari: ")
        val rawId = readLine().toIdFormat()
        val patient = repo.findById(rawId)

        // gunakan let sebagai scope function untuk menjalankan blok bila patient != null
        patient?.let {
            println("[INFO] Pasien ditemukan: ${it.displayInfo()}")
            println("Pilih Layanan:")
            println("1. Grooming (Rp 150.000)")
            println("2. Vaksinasi (Rp 250.000)")
            print("Pilihan Anda: ")
            val svcInput = readLine()?.toIntOrNull()
            when (svcInput) {
                1 -> {
                    it.services.add(Grooming())
                    println("[INFO] Layanan Grooming berhasil ditambahkan untuk ${it.name}.")
                }
                2 -> {
                    it.services.add(Vaccination())
                    println("[INFO] Layanan Vaksinasi berhasil ditambahkan untuk ${it.name}.")
                }
                else -> {
                    println("[ERROR] Pilihan layanan tidak valid.")
                    return
                }
            }
            // run digunakan untuk menghitung total dan menampilkan hasil
            it.run {
                val total = services.sumOf { s -> s.calculateCost() }
                println("Total biaya yang harus dibayar: Rp ${"%,.0f".format(total)}")
            }
        } ?: run {
            // pasien tidak ditemukan -> penanganan yang informatif
            println("[INFO] Pasien dengan ID $rawId tidak ditemukan.")
        }
    }
}
