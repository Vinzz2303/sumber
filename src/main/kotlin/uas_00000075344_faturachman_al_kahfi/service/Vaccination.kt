package uas_00000075344_faturachman_al_kahfi.service

class Vaccination : Service {
    override val description: String = "VAKSINASI"
    override fun calculateCost(): Double = 250_000.0
    override fun toString(): String = description
}
