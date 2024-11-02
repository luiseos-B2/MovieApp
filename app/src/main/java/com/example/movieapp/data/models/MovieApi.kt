package com.example.movieapp.data.models

import com.example.movieapp.domain.models.Media
import com.google.gson.annotations.SerializedName

data class MovieApi(
    @SerializedName("adult")
    val adult: Boolean? = null,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("genre_ids")
    val genreIds: List<Int>? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("original_language")
    val originalLanguage: String? = null,
    @SerializedName("original_title")
    val originalTitle: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("popularity")
    val popularity: Double? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("video")
    val video: Boolean? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null
)

fun MovieApi.toMediaMovie(): Media {
    return Media(
        adult = adult ?: false,
        backdropPath = backdropPath ?: "",
        genreIds = genreIds ?: emptyList(),
        id = id ?: 0,
        originalLanguage = originalLanguage ?: "",
        originalName = originalTitle ?: "",
        overview = overview ?: "",
        popularity = popularity ?: 0.0,
        posterPath = posterPath ?: "",
        voteAverage = voteAverage ?: 0.0,
        voteCount = voteCount ?: 0,
        releaseDate = releaseDate ?: "",
        name = title ?: ""
    )
}
