package com.example.movieapp.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.models.CastResponse
import com.example.movieapp.domain.models.MediaType
import com.example.movieapp.domain.usecase.CastUseCase
import com.example.movieapp.domain.usecase.MediaDetailsUseCase
import com.example.movieapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MediaDetailsViewModel @Inject constructor(
    private val mediaDetailsUseCase: MediaDetailsUseCase,
    private val castUseCase: CastUseCase,
) : ViewModel() {

    private val _filmDetailsUiState = MutableStateFlow(MediaDetailsUiState())
    val filmDetailsUiState = _filmDetailsUiState.asStateFlow()

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            _filmDetailsUiState.update {
                it.copy(
                    isLoading = true
                )
            }
            when (val result = mediaDetailsUseCase.getMoviesDetails(movieId)) {
                is Resource.Error -> {
                    _filmDetailsUiState.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }

                is Resource.Success -> {
                    _filmDetailsUiState.update {
                        it.copy(
                            isLoading = false,
                            mediaDetails = result.data
                        )
                    }
                }

                else -> {
                    filmDetailsUiState
                }
            }
        }
    }

    fun getTvSeriesDetails(tvId: Int) {
        viewModelScope.launch {
            _filmDetailsUiState.update {
                it.copy(
                    isLoading = true
                )
            }
            when (val result = mediaDetailsUseCase.getTvSeriesDetails(tvId)) {
                is Resource.Error -> {
                    _filmDetailsUiState.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }

                is Resource.Success -> {
                    _filmDetailsUiState.update {
                        it.copy(
                            isLoading = false,
                            mediaDetails = result.data
                        )
                    }
                }

                else -> {
                    filmDetailsUiState
                }
            }
        }
    }

    fun getMovieCasts(movieId: Int) {
        viewModelScope.launch {
            _filmDetailsUiState.update {
                it.copy(isLoadingCasts = true)
            }
            when (val result = castUseCase.getMovieCasts(movieId)) {
                is Resource.Error -> {
                    _filmDetailsUiState.update {
                        it.copy(
                            isLoadingCasts = false,
                            errorCasts = result.message
                        )
                    }
                }

                is Resource.Success -> {
                    _filmDetailsUiState.update {
                        it.copy(
                            isLoadingCasts = false,
                            casts = result.data as CastResponse
                        )
                    }
                }

                else -> {
                    filmDetailsUiState
                }
            }
        }
    }

    fun getTvSeriesCasts(tvId: Int) {
        viewModelScope.launch {
            _filmDetailsUiState.update {
                it.copy(isLoadingCasts = true)
            }
            when (val result = castUseCase.getTvSeriesCasts(tvId)) {
                is Resource.Error -> {
                    _filmDetailsUiState.update {
                        it.copy(
                            isLoadingCasts = false,
                            errorCasts = result.message
                        )
                    }
                }

                is Resource.Success -> {
                    _filmDetailsUiState.update {
                        it.copy(
                            isLoadingCasts = false,
                            casts = result.data as CastResponse
                        )
                    }
                }

                else -> {
                    filmDetailsUiState
                }
            }
        }
    }

    fun getMediaDetails(mediaId: Int, mediaType: MediaType) {
        when (mediaType) {
            MediaType.MOVIE -> {
                getMovieDetails(mediaId)
                getMovieCasts(mediaId)
            }
            MediaType.TV_SHOW -> {
                getTvSeriesDetails(mediaId)
                getTvSeriesCasts(mediaId)
            }
        }
    }

}