package com.example.movieapp.presentation.details

sealed interface MediaDetailsUiEvents {
    data object NavigateBack : MediaDetailsUiEvents
}