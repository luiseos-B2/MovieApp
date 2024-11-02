package com.example.movieapp.data.models

import com.google.gson.annotations.SerializedName

data class MultiSearchResponseApi(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val searches: List<SearchApi>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
