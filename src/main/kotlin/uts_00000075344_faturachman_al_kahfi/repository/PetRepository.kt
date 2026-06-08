package uts_00000075344_faturachman_al_kahfi.repository

import uts_00000075344_faturachman_al_kahfi.model.Pet

class PetRepository : DataRepository<Pet> {
    private val patients = mutableListOf<Pet>() // MutableList sesuai spesifikasi

    override fun add(item: Pet) {
        patients.add(item)
    }

    override fun getAll(): List<Pet> = patients.toList()

    override fun findById(id: String): Pet? =
        patients.firstOrNull { it.id.equals(id, ignoreCase = true) }

    // High-Order Function filterPatients untuk lambda-based filter/search
    fun filterPatients(predicate: (Pet) -> Boolean): List<Pet> = patients.filter(predicate)
}
