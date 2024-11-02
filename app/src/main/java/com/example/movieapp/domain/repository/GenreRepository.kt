package com.example.movieapp.domain.repository

import com.example.movieapp.domain.models.Genre
import com.example.movieapp.domain.util.Resource

interface GenreRepository {
    suspend fun getMovieGenres(): Resource<List<Genre>>
    suspend fun getTvSeriesGenres(): Resource<List<Genre>>
}