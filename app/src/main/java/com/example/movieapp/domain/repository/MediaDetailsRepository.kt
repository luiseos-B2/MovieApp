package com.example.movieapp.domain.repository

import com.example.movieapp.domain.models.MediaDetails
import com.example.movieapp.domain.util.Resource

interface MediaDetailsRepository {
    suspend fun getMoviesDetails(movieId: Int): Resource<MediaDetails>

    suspend fun getTvSeriesDetails(tvId: Int): Resource<MediaDetails>
}