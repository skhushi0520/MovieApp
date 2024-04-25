package com.example.movieapplication.data.remote.local.movie

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import java.util.Locale.Category

@Dao
interface MovieDao {
    @Upsert
    suspend fun upsertMovieList(moviList:List<MovieEntity>)

    @Query("SELECT * FROM MovieEntity WHERE id= :id")
    suspend fun getMovieByID(id:Int):MovieEntity

    @Query("SELECT * FROM MovieEntity WHERE category= :category")
    suspend fun getMovieListByCategory(category: String):List<MovieEntity>
}