package uts_00000075344_faturachman_al_kahfi.repository

interface DataRepository<T> {
    fun add(item: T)
    fun getAll(): List<T>
    fun findById(id: String): T?
}
