package com.example.movieapp.data.models

import com.example.movieapp.domain.models.MediaDetails
import com.google.gson.annotations.SerializedName

data class MovieDetailsApi(
    @SerializedName("adult")
    val adult: Boolean? = null,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("budget")
    val budget: Int? = null,
    @SerializedName("genres")
    val genres: List<GenreApi>? = null,
    @SerializedName("homepage")
    val homepage: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("imdb_id")
    val imdbId: String? = null,
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
    @SerializedName("revenue")
    val revenue: Int? = null,
    @SerializedName("runtime")
    val runtime: Int? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("tagline")
    val tagline: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("video")
    val video: Boolean? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null
)

fun MovieDetailsApi.toMediaDetails(): MediaDetails {
    return MediaDetails(
        adult = adult ?: false,
        backdropPath = backdropPath ?: "",
        budget = budget ?: 0,
        genres = genres ?: emptyList(),
        homepage = homepage ?: "",
        id = id ?: 0,
        imdbId = imdbId ?: "",
        originalTitle = originalTitle ?: "",
        originalLanguage = originalLanguage ?: "",
        overview = overview ?: "",
        popularity = popularity ?: 0.0,
        posterPath = posterPath ?: "",
        releaseDate = releaseDate ?: "",
        revenue = revenue ?: 0,
        runtime = runtime ?: 0,
        status = status ?: "",
        tagline = tagline ?: "",
        title = title ?: "",
        video = video ?: false,
        voteAverage = voteAverage ?: 0.0,
        voteCount = voteCount ?: 0
    )
}
