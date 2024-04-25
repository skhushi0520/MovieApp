package com.example.movieapplication.domain.repository

import com.example.movieapplication.data.domain.model.Movie
import com.example.movieapplication.presentation.onboarding.Page
import com.example.movieapplication.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieListRepository {
    suspend fun getMovieList(
        forceFetchFromRemote:Boolean,
        category: String,
        page: Int
    ):Flow<Resource<List<Movie>>>

    suspend fun getMovie(id:Int):Flow<Resource<Movie>>
}