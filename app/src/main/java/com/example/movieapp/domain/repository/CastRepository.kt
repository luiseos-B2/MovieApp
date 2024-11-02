package com.example.movieapp.domain.repository

import com.example.movieapp.domain.models.CastResponse
import com.example.movieapp.domain.util.Resource

interface CastRepository {
    suspend fun getTvSeriesCasts(id: Int): Resource<CastResponse>
    suspend fun getMovieCasts(id: Int): Resource<CastResponse>
    suspend fun getCastDetails(id: Int): Resource<Unit>
}
