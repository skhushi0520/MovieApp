package com.example.movieapplication.domain.usecases.app_entry.movie

import androidx.paging.PagingData
import com.example.movieapplication.data.domain.model.Movie
import com.example.movieapplication.domain.repository.MovieListRepository
import com.example.movieapplication.util.Resource
import kotlinx.coroutines.flow.Flow
import java.security.CodeSource

class GetMovies( private val movieListRepository:MovieListRepository) {
   suspend operator fun invoke(forceFetchFromRemote:Boolean,
                               category: String,
                               page: Int):Flow<Resource<List<Movie>>>
   {
       return movieListRepository.getMovieList(forceFetchFromRemote=forceFetchFromRemote,
           category=category,
           page=page)
   }
}