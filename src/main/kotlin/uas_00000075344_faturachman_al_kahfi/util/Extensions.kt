package uas_00000075344_faturachman_al_kahfi.util

import uas_00000075344_faturachman_al_kahfi.model.Pet

// Extension function untuk standardisasi ID input
fun String?.toIdFormat(): String {
    val s = this?.trim() ?: ""
    if (s.isBlank()) return ""
    // ganti spasi/karakter yang bukan alnum dengan underscore, uppercase
    return s.replace(Regex("[^A-Za-z0-9]"), "_").uppercase()
}

// Extension function untuk menampilkan info pet rapi
fun Pet.displayInfo(): String = "${this.id} - ${this.name} (${this.type})"
