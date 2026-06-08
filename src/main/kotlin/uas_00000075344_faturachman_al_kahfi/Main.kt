package uas_00000075344_faturachman_al_kahfi

import uas_00000075344_faturachman_al_kahfi.repository.PetRepository
import uas_00000075344_faturachman_al_kahfi.storage.Storage
import uas_00000075344_faturachman_al_kahfi.ui.ConsoleUI

fun main() {
    println("===================================================")
    println("SISTEM MANAJEMEN KLINIK \"PAWS & CARE\"")
    println("===================================================")

    val storage = Storage("patients.csv")
    val repo = PetRepository()

    // muat data saat startup (Storage.load sudah aman terhadap error)
    val loaded = storage.load()
    loaded.forEach { repo.add(it) }

    println("[INFO] Memuat data dari patients.csv... (Sukses: ${repo.getAll().size} data ditemukan)")

    val ui = ConsoleUI(repo, storage)
    ui.start()
}
