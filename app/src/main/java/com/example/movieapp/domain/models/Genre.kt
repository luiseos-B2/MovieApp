package com.example.movieapp.domain.models

data class Genre(
    val id: Int = 0,
    val name: String = ""
) {
    companion object {
        val empty = Genre()
    }
}
