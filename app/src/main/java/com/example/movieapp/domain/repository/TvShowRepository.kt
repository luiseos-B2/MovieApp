package com.example.movieapp.domain.repository

import androidx.paging.PagingData
import com.example.movieapp.domain.models.Media
import kotlinx.coroutines.flow.Flow

interface TvShowRepository {

    fun getTrendingThisWeekTvSeries(): Flow<PagingData<Media>>

    fun getOnTheAirTvSeries(): Flow<PagingData<Media>>

    fun getTopRatedTvSeries(): Flow<PagingData<Media>>

    fun getAiringTodayTvSeries(): Flow<PagingData<Media>>

    fun getPopularTvSeries(): Flow<PagingData<Media>>
}