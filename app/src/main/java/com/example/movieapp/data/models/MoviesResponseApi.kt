package com.example.movieapp.data.models

import com.example.movieapp.domain.models.MediaResponse
import com.google.gson.annotations.SerializedName

data class MoviesResponseApi(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MovieApi>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)

fun MoviesResponseApi.toMediaResponse() = MediaResponse(
    page = page,
    results = results.map { it.toMediaMovie() },
    totalPages = totalPages,
    totalResults = totalResults
)
