package com.example.movieapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movieapp.data.paging.NowPlayingMoviesSource
import com.example.movieapp.data.paging.PopularMoviesSource
import com.example.movieapp.data.paging.TopRatedMoviesSource
import com.example.movieapp.data.paging.TrendingMoviesSource
import com.example.movieapp.data.paging.UpcomingMoviesSource
import com.example.movieapp.data.service.MovieAppService
import com.example.movieapp.domain.models.Media
import com.example.movieapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val api: MovieAppService
): MoviesRepository {
    override fun getTrendingMoviesThisWeek(): Flow<PagingData<Media>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                TrendingMoviesSource(api)
            }
        ).flow
    }

    override fun getUpcomingMovies(): Flow<PagingData<Media>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                UpcomingMoviesSource(api)
            }
        ).flow
    }

    override fun getTopRatedMovies(): Flow<PagingData<Media>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                TopRatedMoviesSource(api)
            }
        ).flow
    }

    override fun getNowPlayingMovies(): Flow<PagingData<Media>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                NowPlayingMoviesSource(api)
            }
        ).flow
    }

    override fun getPopularMovies(): Flow<PagingData<Media>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                PopularMoviesSource(api)
            }
        ).flow
    }
}