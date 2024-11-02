package com.example.movieapp.data.service

import com.example.movieapp.data.models.CastResponseApi
import com.example.movieapp.data.models.GenreResponseApi
import com.example.movieapp.data.models.MovieDetailsApi
import com.example.movieapp.data.models.MoviesResponseApi
import com.example.movieapp.data.models.MultiSearchResponseApi
import com.example.movieapp.data.models.TvSeriesDetailsApi
import com.example.movieapp.data.models.TvSeriesResponseApi
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAppService {

    @GET("trending/movie/day")
    suspend fun getTrendingTodayMovies(
        @Query("page") page: Int = 0,
    ): MoviesResponseApi

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int = 0,
    ): MoviesResponseApi

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int = 0,
    ): MoviesResponseApi

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page: Int = 0,
    ): MoviesResponseApi

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int = 0,
    ): MoviesResponseApi

    @GET("trending/tv/day")
    suspend fun getTrendingTvSeries(
        @Query("page") page: Int = 0,
    ): TvSeriesResponseApi

    @GET("tv/top_rated")
    suspend fun getTopRatedTvSeries(
        @Query("page") page: Int = 0,
    ): TvSeriesResponseApi

    @GET("tv/on_the_air")
    suspend fun getOnTheAirTvSeries(
        @Query("page") page: Int = 0,
    ): TvSeriesResponseApi

    @GET("tv/popular")
    suspend fun getPopularTvSeries(
        @Query("page") page: Int = 0,
    ): TvSeriesResponseApi

    @GET("tv/airing_today")
    suspend fun getAiringTodayTvSeries(
        @Query("page") page: Int = 0,
    ): TvSeriesResponseApi

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
    ): MovieDetailsApi

    @GET("tv/{tv_id}")
    suspend fun getTvSeriesDetails(
        @Path("tv_id") tvSeriesId: Int,
    ): TvSeriesDetailsApi

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Int,
        ): CastResponseApi

    @GET("tv/{tv_id}/credits")
    suspend fun getTvSeriesCredits(
        @Path("tv_id") tvSeriesId: Int,
    ): CastResponseApi

    @GET("credit/{credit_id}")
    suspend fun getCreditDetails(
        @Path("credit_id") creditId: Int,
    )

    @GET("genre/movie/list")
    suspend fun getMovieGenres(): GenreResponseApi

    @GET("genre/tv/list")
    suspend fun getTvSeriesGenres(): GenreResponseApi

    @GET("search/multi")
    suspend fun multiSearch(
        @Query("page") page: Int = 0,
        @Query("query") query: String,
    ): MultiSearchResponseApi

}