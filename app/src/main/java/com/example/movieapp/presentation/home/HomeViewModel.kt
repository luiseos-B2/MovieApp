package com.example.movieapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.filter
import com.example.movieapp.domain.models.Genre
import com.example.movieapp.domain.models.MediaType
import com.example.movieapp.domain.usecase.GenreUseCase
import com.example.movieapp.domain.usecase.MoviesUseCase
import com.example.movieapp.domain.usecase.TvShowUseCase
import com.example.movieapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moviesUseCase: MoviesUseCase,
    private val seriesUseCase: TvShowUseCase,
    private val genreUseCase: GenreUseCase,
) : ViewModel() {

    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState = _homeUiState.asStateFlow()


    fun getTrendingMovies(genreId: Int?) {
        _homeUiState.update {
            it.copy(
                trendingMovies = if (genreId != null) {
                    moviesUseCase.getTrendingMoviesThisWeek().map { pagingData ->
                        pagingData.filter {
                            it.genreIds.contains(genreId)
                        }
                    }.cachedIn(viewModelScope)
                } else {
                    moviesUseCase.getTrendingMoviesThisWeek().cachedIn(viewModelScope)
                }
            )
        }
    }


    fun getUpcomingMovies(genreId: Int?) {
        _homeUiState.update {
            it.copy(
                upcomingMovies = if (genreId != null) {
                    moviesUseCase.getUpcomingMovies().map { pagingData ->
                        pagingData.filter {
                            it.genreIds.contains(genreId)
                        }
                    }.cachedIn(viewModelScope)
                } else {
                    moviesUseCase.getUpcomingMovies().cachedIn(viewModelScope)
                }
            )
        }
    }

    fun getTopRatedMovies(genreId: Int?) {
        _homeUiState.update {
            it.copy(
                topRatedMovies = if (genreId != null) {
                    moviesUseCase.getTopRatedMovies().map { pagingData ->
                        pagingData.filter {
                            it.genreIds.contains(genreId)
                        }
                    }.cachedIn(viewModelScope)
                } else {
                    moviesUseCase.getTopRatedMovies().cachedIn(viewModelScope)
                }
            )
        }
    }

    fun getNowPayingMovies(genreId: Int?) {
        _homeUiState.update {
            it.copy(
                nowPlayingMovies = if (genreId != null) {
                    moviesUseCase.getNowPlayingMovies().map { pagingData ->
                        pagingData.filter {
                            it.genreIds.contains(genreId)
                        }
                    }.cachedIn(viewModelScope)
                } else {
                    moviesUseCase.getNowPlayingMovies().cachedIn(viewModelScope)
                }
            )
        }
    }

    fun getPopularMovies(genreId: Int?) {
        _homeUiState.update {
            it.copy(
                popularMovies = if (genreId != null) {
                    moviesUseCase.getPopularMovies().map { pagingData ->
                        pagingData.filter {
                            it.genreIds.contains(genreId)
                        }
                    }.cachedIn(viewModelScope)
                } else {
                    moviesUseCase.getPopularMovies().cachedIn(viewModelScope)
                }
            )
        }
    }

    private fun getMoviesGenres() {
        viewModelScope.launch {
            when (val result = genreUseCase.getMovieGenres()) {
                is Resource.Success -> {
                    _homeUiState.update {
                        it.copy(
                            moviesGenres = result.data ?: emptyList()
                        )
                    }
                }

                is Resource.Error -> {}

                else -> {}
            }
        }
    }

    fun getTrendingTvSeries(genreId: Int?) {
        _homeUiState.update {
            it.copy(
                trendingTvSeries = if (genreId != null) {
                    seriesUseCase.getTrendingThisWeekTvSeries().map { pagingData ->
                        pagingData.filter {
                            it.genreIds.contains(genreId)
                        }
                    }.cachedIn(viewModelScope)
                } else {
                    seriesUseCase.getTrendingThisWeekTvSeries().cachedIn(viewModelScope)
                }
            )
        }
    }

    fun getOnTheAirTvSeries(genreId: Int?) {
        _homeUiState.update {
            it.copy(
                onAirTvSeries = if (genreId != null) {
                    seriesUseCase.getOnTheAirTvSeries().map { pagingData ->
                        pagingData.filter {
                            it.genreIds.contains(genreId)
                        }
                    }.cachedIn(viewModelScope)
                } else {
                    seriesUseCase.getOnTheAirTvSeries().cachedIn(viewModelScope)
                }
            )
        }
    }

    fun getTopRatedTvSeries(genreId: Int?) {
        _homeUiState.update {
            it.copy(
                topRatedTvSeries = if (genreId != null) {
                    seriesUseCase.getTopRatedTvSeries().map { pagingData ->
                        pagingData.filter {
                            it.genreIds.contains(genreId)
                        }
                    }.cachedIn(viewModelScope)
                } else {
                    seriesUseCase.getTopRatedTvSeries().cachedIn(viewModelScope)
                }
            )
        }
    }

    fun getAiringTodayTvSeries(genreId: Int?) {
        _homeUiState.update {
            it.copy(
                airingTodayTvSeries = if (genreId != null) {
                    seriesUseCase.getAiringTodayTvSeries().map { pagingData ->
                        pagingData.filter {
                            it.genreIds.contains(genreId)
                        }
                    }.cachedIn(viewModelScope)
                } else {
                    seriesUseCase.getAiringTodayTvSeries().cachedIn(viewModelScope)
                }
            )
        }
    }

    fun getPopularTvSeries(genreId: Int?) {
        _homeUiState.update {
            it.copy(
                popularTvSeries = if (genreId != null) {
                    seriesUseCase.getPopularTvSeries().map { pagingData ->
                        pagingData.filter {
                            it.genreIds.contains(genreId)
                        }
                    }.cachedIn(viewModelScope)
                } else {
                    seriesUseCase.getPopularTvSeries().cachedIn(viewModelScope)
                }
            )
        }
    }

    private fun getSeriesGenres() {
        viewModelScope.launch {
            when (val result = genreUseCase.getTvSeriesGenres()) {
                is Resource.Success -> {
                    _homeUiState.update {
                        it.copy(
                            tvSeriesGenres = result.data ?: emptyList()
                        )
                    }
                }

                is Resource.Error -> {}

                else -> {}
            }
        }
    }

    fun setGenre(genre: Genre?) {
        _homeUiState.update {
            it.copy(
                selectedGenre = genre
            )
        }
    }

    fun setSelectedOption(mediaType: MediaType) {
        _homeUiState.update {
            it.copy(
                mediaType = mediaType
            )
        }
    }

    fun refreshAllData() {
        getSeriesGenres()
        getMoviesGenres()
        getTrendingMovies(homeUiState.value.selectedGenre?.id)
        getNowPayingMovies(homeUiState.value.selectedGenre?.id)
        getUpcomingMovies(homeUiState.value.selectedGenre?.id)
        getTopRatedMovies(homeUiState.value.selectedGenre?.id)
        getPopularMovies(homeUiState.value.selectedGenre?.id)
        getPopularTvSeries(homeUiState.value.selectedGenre?.id)
        getAiringTodayTvSeries(homeUiState.value.selectedGenre?.id)
        getTrendingTvSeries(homeUiState.value.selectedGenre?.id)
        getOnTheAirTvSeries(homeUiState.value.selectedGenre?.id)
        getTopRatedTvSeries(homeUiState.value.selectedGenre?.id)
        getOnTheAirTvSeries(homeUiState.value.selectedGenre?.id)
    }

}