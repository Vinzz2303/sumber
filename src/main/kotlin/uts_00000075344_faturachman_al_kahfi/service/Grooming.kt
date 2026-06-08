package uts_00000075344_faturachman_al_kahfi.service

class Grooming : Service {
    override val description: String = "GROOMING"
    override fun calculateCost(): Double = 150_000.0
    override fun toString(): String = description
}
