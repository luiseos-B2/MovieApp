package com.example.movieapp.domain.models

data class MediaResponse (
    val page: Int = 0,
    val results: List<Media> = emptyList(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
)