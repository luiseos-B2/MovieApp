package com.example.movieapp.domain.usecase

import com.example.movieapp.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesUseCase @Inject constructor(
    private val repository: MoviesRepository
) {

    fun getTrendingMoviesThisWeek() = repository.getTrendingMoviesThisWeek()

    fun getUpcomingMovies() = repository.getUpcomingMovies()

    fun getTopRatedMovies() = repository.getTopRatedMovies()

    fun getNowPlayingMovies() = repository.getNowPlayingMovies()

    fun getPopularMovies() = repository.getPopularMovies()

}