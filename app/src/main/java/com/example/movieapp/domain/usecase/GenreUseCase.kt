package com.example.movieapp.domain.usecase

import com.example.movieapp.domain.repository.GenreRepository
import javax.inject.Inject

class GenreUseCase @Inject constructor(
    private val repository: GenreRepository
) {

    suspend fun getMovieGenres() = repository.getMovieGenres()

    suspend fun getTvSeriesGenres() = repository.getTvSeriesGenres()

}