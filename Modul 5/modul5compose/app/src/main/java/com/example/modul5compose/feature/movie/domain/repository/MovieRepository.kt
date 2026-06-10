package com.example.modul5compose.feature.movie.domain.repository

import com.example.modul5compose.core.common.UiState
import com.example.modul5compose.feature.movie.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(): Flow<UiState<List<Movie>>>
}