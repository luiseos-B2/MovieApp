package com.example.movieapp.domain.repository

import androidx.paging.PagingData
import com.example.movieapp.domain.models.Media
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getTrendingMoviesThisWeek(): Flow<PagingData<Media>>

    fun getUpcomingMovies(): Flow<PagingData<Media>>

    fun getTopRatedMovies(): Flow<PagingData<Media>>

    fun getNowPlayingMovies(): Flow<PagingData<Media>>

    fun getPopularMovies(): Flow<PagingData<Media>>
}