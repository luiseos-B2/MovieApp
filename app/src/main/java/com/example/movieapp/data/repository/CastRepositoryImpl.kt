package com.example.movieapp.data.repository

import com.example.movieapp.data.models.toCastResponse
import com.example.movieapp.data.service.MovieAppService
import com.example.movieapp.domain.models.CastResponse
import com.example.movieapp.domain.repository.CastRepository
import com.example.movieapp.domain.util.Resource
import timber.log.Timber
import javax.inject.Inject

class CastRepositoryImpl @Inject constructor(
    private val api: MovieAppService,
) : CastRepository {
    override suspend fun getTvSeriesCasts(id: Int): Resource<CastResponse> {
        val response = try {
            api.getTvSeriesCredits(id)
        } catch (e: Exception) {
            Timber.d(e.message)
            return Resource.Error("Unknown error occurred")
        }

        return Resource.Success(response.toCastResponse())
    }

    override suspend fun getMovieCasts(id: Int): Resource<CastResponse> {
        val response = try {
            api.getMovieCredits(id)
        } catch (e: Exception) {
            Timber.d(e.message)
            return Resource.Error("Unknown error occurred")
        }

        return Resource.Success(response.toCastResponse())
    }

    override suspend fun getCastDetails(id: Int): Resource<Unit> {
        val response = try {
            api.getCreditDetails(creditId = id)
        } catch (e: Exception) {
            Timber.d(e.message)
            return Resource.Error("Unknown error occurred")
        }
        return Resource.Success(Unit)
    }
}