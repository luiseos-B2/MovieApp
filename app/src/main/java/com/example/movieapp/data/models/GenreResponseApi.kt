package com.example.movieapp.data.models

import com.google.gson.annotations.SerializedName

data class GenreResponseApi(
    @SerializedName("genres")
    val genres: List<GenreApi>
)
