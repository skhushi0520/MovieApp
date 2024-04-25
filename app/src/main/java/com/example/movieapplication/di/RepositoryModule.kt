package com.example.movieapplication.di

import com.example.movieapplication.data.repository.MovieListRepositoryImpl
import com.example.movieapplication.domain.repository.MovieListRepository
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
    abstract fun bindMovieListRepository(movieListRepositoryImpl: MovieListRepositoryImpl): MovieListRepository
}