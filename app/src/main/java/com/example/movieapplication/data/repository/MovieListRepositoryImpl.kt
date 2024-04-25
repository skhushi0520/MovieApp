package com.example.movieapplication.data.repository

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.movieapplication.data.domain.model.Movie
import com.example.movieapplication.data.mappers.toMovie
import com.example.movieapplication.data.mappers.toMovieEntity
import com.example.movieapplication.data.remote.MovieAPI
import com.example.movieapplication.data.remote.local.movie.MovieDataBase
import com.example.movieapplication.domain.repository.MovieListRepository
import com.example.movieapplication.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class MovieListRepositoryImpl @Inject constructor(
    private val movieAPI: MovieAPI,
    private val movieDataBase: MovieDataBase
)
    :MovieListRepository {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        category: String,
        page: Int
    ): Flow<Resource<List<Movie>>> {
        return flow{
            emit(Resource.Loading(true))
            val localMovieList=movieDataBase.movieDao.getMovieListByCategory(category)

            val shouldLoadLocalMovie= localMovieList.isEmpty()&& !forceFetchFromRemote

            if(shouldLoadLocalMovie)
            {
                emit(Resource.Success(data = localMovieList.map{movieEntity -> movieEntity.toMovie(category)}))
                emit((Resource.Loading(false)))
                return@flow
            }
            val movieListEmptyApi= try{
                movieAPI.getMovieList(category,page)
            }
            catch (e:IOException)
            {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies"))
                return@flow
            }
            catch (e:HttpException)
            {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies"))
                return@flow
            }
            catch (e:Exception)
            {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies"))
                return@flow
            }
            val movieEntities=movieListEmptyApi.results.let{
                it.map{
                    movieDto ->  movieDto.toMovieEntity(category)
                }
            }
            movieDataBase.movieDao.upsertMovieList(movieEntities)
            emit(Resource.Success(movieEntities.map{it.toMovie(category)}))
            emit((Resource.Loading(false)))
        }
    }

    override suspend fun getMovie(id: Int): Flow<Resource<Movie>> {
       return flow {
           emit(Resource.Loading(true))

           val movieEntity =movieDataBase.movieDao.getMovieByID(id)

           if(movieEntity!=null)
           {
               emit(Resource.Success(movieEntity.toMovie((movieEntity.category))))
               emit((Resource.Loading(false)))
               return@flow
           }
           emit(Resource.Error("Error no such movie"))
           emit((Resource.Loading(false)))
       }
    }

}