package com.example.movieapp.presentation.details

import com.example.movieapp.domain.models.CastResponse
import com.example.movieapp.domain.models.MediaDetails

data class MediaDetailsUiState(
    val casts: CastResponse = CastResponse.empty,
    val isLoading: Boolean = false,
    val isLoadingCasts: Boolean = false,
    val error: String? = null,
    val errorCasts: String? = null,
    val mediaDetails: MediaDetails? = null,
)