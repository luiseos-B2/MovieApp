package com.example.movieapp.domain.usecase

import com.example.movieapp.domain.repository.TvShowRepository
import javax.inject.Inject

class TvShowUseCase @Inject constructor(
    private val repository: TvShowRepository
) {

    fun getTrendingThisWeekTvSeries() = repository.getTrendingThisWeekTvSeries()

    fun getOnTheAirTvSeries() = repository.getOnTheAirTvSeries()

    fun getTopRatedTvSeries() = repository.getTopRatedTvSeries()

    fun getAiringTodayTvSeries() = repository.getAiringTodayTvSeries()

    fun getPopularTvSeries() = repository.getPopularTvSeries()

}