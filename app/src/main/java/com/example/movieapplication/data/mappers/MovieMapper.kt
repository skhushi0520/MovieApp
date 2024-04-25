package com.example.movieapplication.data.mappers

import com.example.movieapplication.data.domain.model.Movie
import com.example.movieapplication.data.remote.dto.MovieDto
import com.example.movieapplication.data.remote.local.movie.MovieEntity

fun MovieDto.toMovieEntity(
    category: String
): MovieEntity {
    return MovieEntity(
        adult = adult,
        backdrop_path = backdrop_path,
        id = id,
        original_language = original_language,
        original_title = original_title,
        overview = overview,
        popularity = popularity,
        poster_path = poster_path,
        release_date = release_date,
        title = title,
        video = video,
        vote_average = vote_average,
        vote_count = vote_count,
        int = int,
        category = category,
        genre_ids = try {
            genre_ids.joinToString(",") ?: "-1,-2"
        } catch (e: Exception) {
            "-1, -2"
        }


    )
}


fun MovieEntity.toMovie(
    category: String
): Movie {
    return Movie(adult = adult,
        backdrop_path = backdrop_path,
        id = id,
        original_language = original_language,
        original_title = original_title,
        overview = overview,
        popularity = popularity,
        poster_path = poster_path,
        release_date = release_date,
        title = title,
        video = video,
        vote_average = vote_average,
        vote_count = vote_count,
        int = int,
        category = category,
        genre_ids = try {
            genre_ids.split(",").map { it.toInt() }
        } catch (e: Exception) {
            listOf(-1, -2)
        }

    )
}