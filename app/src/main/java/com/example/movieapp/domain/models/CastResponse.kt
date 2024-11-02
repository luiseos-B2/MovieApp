package com.example.movieapp.domain.models

data class CastResponse(
    val cast: List<Cast> = emptyList(),
    val id: Int = 0
) {
    companion object {
        val empty = CastResponse()
    }
}
