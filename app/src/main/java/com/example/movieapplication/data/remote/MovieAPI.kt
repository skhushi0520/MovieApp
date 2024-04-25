package com.example.movieapplication.data.remote

import com.example.movieapplication.domain.model.MovieListDto
import com.example.movieapplication.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {
    @GET("movie/{category}")
    suspend fun getMovieList(
        @Path("category") category: String,
        @Query("page") page:Int,
        @Query("api_key") apiKey: String= API_KEY
    ): MovieListDto
}