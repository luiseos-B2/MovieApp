package com.example.movieapp.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Media(
    val adult: Boolean = false,
    val backdropPath: String = "",
    val firstAirDate: String = "",
    val genreIds: List<Int> = emptyList(),
    val id: Int = 0,
    val name: String = "",
    val originCountry: List<String> = emptyList(),
    val originalLanguage: String = "",
    val originalName: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterPath: String = "",
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0,
    val releaseDate: String = "",
): Parcelable {
    companion object {
        val empty = Media()
    }
}
