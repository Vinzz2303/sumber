package uas_00000075344_faturachman_al_kahfi.storage

import uas_00000075344_faturachman_al_kahfi.model.Pet
import uas_00000075344_faturachman_al_kahfi.service.Grooming
import uas_00000075344_faturachman_al_kahfi.service.Vaccination
import java.io.File
import java.io.IOException
import kotlin.runCatching

class Storage(private val filename: String) {

    // CSV format: id,name,type,service1;service2;...
    fun load(): List<Pet> {
        val file = File(filename)
        if (!file.exists()) {
            runCatching { file.createNewFile() }
            return emptyList()
        }

        return runCatching {
            file.readLines()
                .map { it.trim() }
                .filter { it.isNotBlank() && !it.startsWith("#") }
                .mapNotNull { line ->
                    val parts = line.split(",").map { it.trim() }
                    if (parts.size >= 3) {
                        val id = parts[0]
                        val name = parts[1]
                        val type = parts[2]
                        val servicesField = parts.getOrNull(3) ?: ""
                        val services = servicesField
                            .split(";")
                            .mapNotNull { svc ->
                                when (svc.trim().uppercase()) {
                                    "GROOMING" -> Grooming()
                                    "VAKSINASI", "VACCINATION", "VACCINES" -> Vaccination()
                                    "" -> null
                                    else -> null
                                }
                            }.toMutableList()
                        Pet(id, name, type, services)
                    } else null
                }
        }.getOrElse {
            // Jika ada error saat baca file, kembalikan kosong
            emptyList()
        }
    }

    fun save(pets: List<Pet>): Boolean {
        val file = File(filename)
        return try {
            file.printWriter().use { out ->
                pets.forEach { pet ->
                    val svcField = pet.services.joinToString(";") { it.description.uppercase() }
                    out.println("${pet.id},${pet.name},${pet.type},$svcField")
                }
            }
            true
        } catch (e: IOException) {
            false
        }
    }
}
