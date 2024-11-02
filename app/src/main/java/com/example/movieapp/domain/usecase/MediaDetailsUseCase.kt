package com.example.movieapp.domain.usecase

import com.example.movieapp.domain.repository.MediaDetailsRepository
import javax.inject.Inject

class MediaDetailsUseCase @Inject constructor(
    private val repository: MediaDetailsRepository
) {

    suspend fun getMoviesDetails(movieId: Int) = repository.getMoviesDetails(movieId)

    suspend fun getTvSeriesDetails(tvId: Int) = repository.getTvSeriesDetails(tvId)


}