package com.example.movieapp.data.repository

import com.example.movieapp.data.models.toMediaDetails
import com.example.movieapp.data.service.MovieAppService
import com.example.movieapp.domain.models.MediaDetails
import com.example.movieapp.domain.repository.MediaDetailsRepository
import com.example.movieapp.domain.util.Resource
import javax.inject.Inject

class MediaDetailsRepositoryImpl @Inject constructor(
    private val api: MovieAppService,
) : MediaDetailsRepository {
    override suspend fun getMoviesDetails(movieId: Int): Resource<MediaDetails> {
        val response = try {
            api.getMovieDetails(movieId).toMediaDetails()
        } catch (e: Exception) {
            return Resource.Error("Unknown error occurred")
        }
        return Resource.Success(response)
    }

    override suspend fun getTvSeriesDetails(tvId: Int): Resource<MediaDetails> {
        val response = try {
            api.getTvSeriesDetails(tvId).toMediaDetails()
        } catch (e: Exception) {
            return Resource.Error("Unknown error occurred")
        }
        return Resource.Success(response)
    }
}