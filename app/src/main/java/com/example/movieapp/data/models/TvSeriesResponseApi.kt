package com.example.movieapp.data.models

import com.example.movieapp.domain.models.MediaResponse
import com.google.gson.annotations.SerializedName

data class TvSeriesResponseApi(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<SeriesApi>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)

fun TvSeriesResponseApi.toMediaResponse() = MediaResponse(
    page = page,
    results = results.map { it.toMediaSeries() },
    totalPages = totalPages,
    totalResults = totalResults
)

