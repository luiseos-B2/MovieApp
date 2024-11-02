package com.example.movieapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movieapp.data.paging.AiringTodayTvSeriesSource
import com.example.movieapp.data.paging.OnTheAirSeriesSource
import com.example.movieapp.data.paging.PopularSeriesSource
import com.example.movieapp.data.paging.TopRatedSeriesSource
import com.example.movieapp.data.paging.TrendingSeriesSource
import com.example.movieapp.data.service.MovieAppService
import com.example.movieapp.domain.models.Media
import com.example.movieapp.domain.repository.TvShowRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TvShowRepositoryImpl @Inject constructor(
    private val api: MovieAppService
): TvShowRepository {
    override fun getTrendingThisWeekTvSeries(): Flow<PagingData<Media>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                TrendingSeriesSource(api)
            }
        ).flow
    }

    override fun getOnTheAirTvSeries(): Flow<PagingData<Media>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                OnTheAirSeriesSource(api)
            }
        ).flow
    }

    override fun getTopRatedTvSeries(): Flow<PagingData<Media>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                TopRatedSeriesSource(api)
            }
        ).flow
    }

    override fun getAiringTodayTvSeries(): Flow<PagingData<Media>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                AiringTodayTvSeriesSource(api)
            }
        ).flow
    }

    override fun getPopularTvSeries(): Flow<PagingData<Media>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                PopularSeriesSource(api)
            }
        ).flow
    }
}