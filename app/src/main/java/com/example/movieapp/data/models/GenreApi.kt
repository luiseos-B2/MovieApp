package com.example.movieapp.data.models

import com.example.movieapp.domain.models.Genre
import com.google.gson.annotations.SerializedName

data class GenreApi(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)

fun GenreApi.toGenre() = Genre(
    id = id,
    name = name
)
