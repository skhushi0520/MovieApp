package com.example.movieapplication.domain.model

import com.example.movieapplication.data.remote.dto.MovieDto

data class  MovieListDto(
    val dates: Dates,
    val page: Int,
    val results: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
)