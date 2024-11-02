package com.example.movieapp.presentation.home

import androidx.paging.PagingData
import com.example.movieapp.domain.models.Genre
import com.example.movieapp.domain.models.Media
import com.example.movieapp.domain.models.MediaType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class HomeUiState(
    val trendingMovies: Flow<PagingData<Media>> = emptyFlow(),
    val upcomingMovies: Flow<PagingData<Media>> = emptyFlow(),
    val topRatedMovies: Flow<PagingData<Media>> = emptyFlow(),
    val nowPlayingMovies: Flow<PagingData<Media>> = emptyFlow(),
    val popularMovies: Flow<PagingData<Media>> = emptyFlow(),

    val trendingTvSeries: Flow<PagingData<Media>> = emptyFlow(),
    val onAirTvSeries: Flow<PagingData<Media>> = emptyFlow(),
    val topRatedTvSeries: Flow<PagingData<Media>> = emptyFlow(),
    val airingTodayTvSeries: Flow<PagingData<Media>> = emptyFlow(),
    val popularTvSeries: Flow<PagingData<Media>> = emptyFlow(),

    val mediaType: MediaType = MediaType.MOVIE,
    val tvSeriesGenres: List<Genre> = emptyList(),
    val moviesGenres: List<Genre> = emptyList(),
    val selectedGenre: Genre? = null,
)
