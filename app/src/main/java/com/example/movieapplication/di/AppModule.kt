package com.example.movieapplication.di

import android.app.Application
import androidx.room.Room
import com.example.movieapplication.data.manager.LocalUserManagerImpl
import com.example.movieapplication.data.remote.MovieAPI
import com.example.movieapplication.data.remote.local.movie.MovieDataBase
import com.example.movieapplication.domain.manager.LocalUserManager
import com.example.movieapplication.domain.repository.MovieListRepository
import com.example.movieapplication.domain.usecases.app_entry.AppEntryUseCases
import com.example.movieapplication.domain.usecases.app_entry.ReadAppEntry
import com.example.movieapplication.domain.usecases.app_entry.SaveAppEntry
import com.example.movieapplication.domain.usecases.app_entry.movie.GetMovies
import com.example.movieapplication.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(localUserManager: LocalUserManager) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry((localUserManager))
    )

    @Provides
    @Singleton
    fun provideMovieApi(): MovieAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(MovieAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieUseCases(
        movieListRepository: MovieListRepository
    ): GetMovies {
        return GetMovies(movieListRepository = movieListRepository)
    }

    @Provides
    @Singleton
    fun provideMovieDataBase(app: Application): MovieDataBase {
        return Room.databaseBuilder(
            app, MovieDataBase::class.java, "moviedb.db"
        ).build()
    }

}