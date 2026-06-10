package com.example.modul5compose.feature.movie.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.modul5compose.core.database.AppDatabase
import com.example.modul5compose.core.network.ApiClient
import com.example.modul5compose.core.preferences.AppPreferences
import com.example.modul5compose.feature.movie.data.repository.MoviePreferencesRepositoryImpl
import com.example.modul5compose.feature.movie.data.repository.MovieRepositoryImpl
import com.example.modul5compose.feature.movie.domain.usecase.GetLastOpenedMovieTitleUseCase
import com.example.modul5compose.feature.movie.domain.usecase.GetPopularMoviesUseCase
import com.example.modul5compose.feature.movie.domain.usecase.SaveLastOpenedMovieUseCase

class MovieViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {

            val database = AppDatabase.getDatabase(context)
            val apiService = ApiClient.movieApiService
            val appPreferences = AppPreferences(context)

            val movieRepo = MovieRepositoryImpl(apiService, database.movieDao())
            val prefRepo = MoviePreferencesRepositoryImpl(appPreferences)

            return MovieViewModel(
                GetPopularMoviesUseCase(movieRepo),
                SaveLastOpenedMovieUseCase(prefRepo),
                GetLastOpenedMovieTitleUseCase(prefRepo)
            ) as T
        }
        throw IllegalArgumentException("ViewModel tidak dikenali")
    }
}