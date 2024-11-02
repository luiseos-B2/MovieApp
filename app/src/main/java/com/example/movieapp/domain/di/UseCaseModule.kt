package com.example.movieapp.domain.di

import com.example.movieapp.domain.repository.CastRepository
import com.example.movieapp.domain.repository.GenreRepository
import com.example.movieapp.domain.repository.MediaDetailsRepository
import com.example.movieapp.domain.repository.MoviesRepository
import com.example.movieapp.domain.repository.TvShowRepository
import com.example.movieapp.domain.usecase.CastUseCase
import com.example.movieapp.domain.usecase.GenreUseCase
import com.example.movieapp.domain.usecase.MediaDetailsUseCase
import com.example.movieapp.domain.usecase.MoviesUseCase
import com.example.movieapp.domain.usecase.TvShowUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGenreUseCase(repository: GenreRepository): GenreUseCase = GenreUseCase(repository)

    @Provides
    fun provideMoviesUseCase(repository: MoviesRepository): MoviesUseCase = MoviesUseCase(repository)

    @Provides
    fun provideTvShowUseCase(repository: TvShowRepository): TvShowUseCase = TvShowUseCase(repository)

    @Provides
    fun provideMediaDetailsUseCase(repository: MediaDetailsRepository): MediaDetailsUseCase = MediaDetailsUseCase(repository)

    @Provides
    fun provideCastUseCase(repository: CastRepository): CastUseCase = CastUseCase(repository)

}