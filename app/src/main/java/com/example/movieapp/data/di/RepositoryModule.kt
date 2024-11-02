package com.example.movieapp.data.di

import com.example.movieapp.data.repository.CastRepositoryImpl
import com.example.movieapp.data.repository.GenreRepositoryImpl
import com.example.movieapp.data.repository.MediaDetailsRepositoryImpl
import com.example.movieapp.data.repository.MoviesRepositoryImpl
import com.example.movieapp.data.repository.TvShowRepositoryImpl
import com.example.movieapp.domain.repository.CastRepository
import com.example.movieapp.domain.repository.GenreRepository
import com.example.movieapp.domain.repository.MediaDetailsRepository
import com.example.movieapp.domain.repository.MoviesRepository
import com.example.movieapp.domain.repository.TvShowRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    internal abstract fun bindsGenreRepository(
        genreRepositoryImpl: GenreRepositoryImpl
    ): GenreRepository

    @Binds
    @Singleton
    internal abstract fun bindsMovieRepository(
        movieRepositoryImpl: MoviesRepositoryImpl
    ): MoviesRepository

    @Binds
    @Singleton
    internal abstract fun bindsTvShowRepository(
        tvShowRepositoryImpl: TvShowRepositoryImpl
    ): TvShowRepository

    @Binds
    @Singleton
    internal abstract fun bindsMediaDetailsRepositoryRepository(
        mediaDetailsRepositoryImpl: MediaDetailsRepositoryImpl
    ): MediaDetailsRepository

    @Binds
    @Singleton
    internal abstract fun bindsCastRepositoryRepository(
        castRepositoryImpl: CastRepositoryImpl
    ): CastRepository
}