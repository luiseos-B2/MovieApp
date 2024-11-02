package com.example.movieapp.presentation.home

import com.example.movieapp.domain.models.Genre
import com.example.movieapp.domain.models.Media
import com.example.movieapp.domain.models.MediaType

sealed interface HomeUiEvents {
    data object NavigateBack : HomeUiEvents
    data object OnPullToRefresh : HomeUiEvents

    data class NavigateToMediaDetails(
        val media: Media,
        val mediaType: MediaType,
    ) : HomeUiEvents

    data class OnMediaGenreSelected(
        val genre: Genre,
        val mediaType: MediaType,
    ) : HomeUiEvents

    data class OnMediaOptionSelected(val mediaType: MediaType) : HomeUiEvents
}