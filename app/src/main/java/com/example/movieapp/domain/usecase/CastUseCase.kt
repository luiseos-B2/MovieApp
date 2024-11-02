package com.example.movieapp.domain.usecase

import com.example.movieapp.domain.repository.CastRepository
import javax.inject.Inject

class CastUseCase @Inject constructor(
    private val repository: CastRepository
) {

    suspend fun getTvSeriesCasts(id: Int) = repository.getTvSeriesCasts(id)

    suspend fun getMovieCasts(id: Int) = repository.getMovieCasts(id)

    suspend fun getCastDetails(id: Int) = repository.getCastDetails(id)


}