package uas_00000075344_faturachman_al_kahfi.model

import uas_00000075344_faturachman_al_kahfi.service.Service

data class Pet(
    val id: String,
    val name: String,
    val type: String,
    val services: MutableList<Service> = mutableListOf()
)
