package com.example.movieapp.data.repository

import com.example.movieapp.data.models.toGenre
import com.example.movieapp.data.service.MovieAppService
import com.example.movieapp.domain.models.Genre
import com.example.movieapp.domain.repository.GenreRepository
import com.example.movieapp.domain.util.Resource
import timber.log.Timber
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    private val api: MovieAppService,
) : GenreRepository {
    override suspend fun getMovieGenres(): Resource<List<Genre>> {
        val response = try {
            api.getMovieGenres()
        } catch (e: Exception) {
            return Resource.Error("Unknown error occurred")
        }
        Timber.d("Movies genres: $response")
        return Resource.Success(
            response.genres.map { it.toGenre() }
        )
    }

    override suspend fun getTvSeriesGenres(): Resource<List<Genre>> {
        val response = try {
            api.getTvSeriesGenres()
        } catch (e: Exception) {
            return Resource.Error("Unknown error occurred")
        }
        Timber.d("Series genres: $response")
        return Resource.Success(
            response.genres.map { it.toGenre() }
        )
    }
}