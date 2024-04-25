package com.example.movieapplication.data.remote.local.movie

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class MovieEntity(
    var adult: Boolean,
    var backdrop_path: String,
    var genre_ids: String,
    @PrimaryKey
    var id: Int,
    var original_language: String,
    var original_title: String,
    var overview: String,
    var popularity: Double,
    var poster_path: String,
    var release_date: String,
    var title: String,
    var video: Boolean,
    var vote_average: Double,
    var vote_count: Int,
    var int: Int,
    var category: String
)
{
    constructor() : this(
        false,
        "",
        "",
        0,
        "",
        "",
        "",
        0.0,
        "",
        "",
        "",
        false,
        0.0,
        0,
        0,
        "")
}