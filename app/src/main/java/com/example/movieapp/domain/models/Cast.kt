package com.example.movieapp.domain.models

data class Cast(
    val adult: Boolean = false,
    val castId: Int = 0,
    val character: String = "",
    val creditId: String = "",
    val gender: Int = 0,
    val id: Int = 0,
    val knownForDepartment: String = "",
    val name: String = "",
    val order: Int = 0,
    val originalName: String = "",
    val popularity: Double = 0.0,
    val profilePath: String? = "https://pixy.org/src/9/94083.png"
)
