package com.example.modul5compose.feature.movie.domain.usecase

import com.example.modul5compose.core.common.UiState
import com.example.modul5compose.feature.movie.domain.model.Movie
import com.example.modul5compose.feature.movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetPopularMoviesUseCase(private val repository: MovieRepository) {
    operator fun invoke(): Flow<UiState<List<Movie>>> {
        return repository.getPopularMovies()
    }
}