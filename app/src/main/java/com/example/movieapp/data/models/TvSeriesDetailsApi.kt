package com.example.movieapp.data.models

import com.example.movieapp.domain.models.MediaDetails
import com.google.gson.annotations.SerializedName

data class TvSeriesDetailsApi(
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("episode_run_time")
    val episodeRunTime: List<Int>,
    @SerializedName("first_air_date")
    val firstAirDate: String,
    @SerializedName("genres")
    val genres: List<GenreApi>,
    @SerializedName("homepage")
    val homepage: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("in_production")
    val inProduction: Boolean,
    @SerializedName("languages")
    val languages: List<String>,
    @SerializedName("last_air_date")
    val lastAirDate: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("next_episode_to_air")
    val nextEpisodeToAir: Any,
    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Int,
    @SerializedName("number_of_seasons")
    val numberOfSeasons: Int,
    @SerializedName("origin_country")
    val originCountry: List<String>,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_name")
    val originalName: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
)

fun TvSeriesDetailsApi.toMediaDetails(): MediaDetails {
    return MediaDetails(
        adult = adult ?: false,
        backdropPath = backdropPath ?: "",
        genres = genres ?: emptyList(),
        homepage = homepage ?: "",
        id = id ?: 0,
        originalTitle = originalName ?: "",
        originalLanguage = originalLanguage ?: "",
        overview = overview ?: "",
        popularity = popularity ?: 0.0,
        posterPath = posterPath ?: "",
        releaseDate = lastAirDate ?: "",
        status = status ?: "",
        tagline = tagline ?: "",
        title = name ?: "",
        voteAverage = voteAverage ?: 0.0,
        voteCount = voteCount ?: 0
    )
}
