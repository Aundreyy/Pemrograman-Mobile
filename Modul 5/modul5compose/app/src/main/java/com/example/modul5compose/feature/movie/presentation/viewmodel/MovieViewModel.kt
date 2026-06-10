package com.example.modul5compose.feature.movie.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modul5compose.core.common.UiState
import com.example.modul5compose.feature.movie.domain.model.Movie
import com.example.modul5compose.feature.movie.domain.usecase.GetLastOpenedMovieTitleUseCase
import com.example.modul5compose.feature.movie.domain.usecase.GetPopularMoviesUseCase
import com.example.modul5compose.feature.movie.domain.usecase.SaveLastOpenedMovieUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class MovieViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val saveLastOpenedMovieUseCase: SaveLastOpenedMovieUseCase,
    private val getLastOpenedMovieTitleUseCase: GetLastOpenedMovieTitleUseCase
) : ViewModel() {

    private val _movieListState = MutableStateFlow<UiState<List<Movie>>>(UiState.Loading)
    val movieListState: StateFlow<UiState<List<Movie>>> = _movieListState.asStateFlow()

    private val _lastOpenedMovie = MutableStateFlow<String?>(null)
    val lastOpenedMovie: StateFlow<String?> = _lastOpenedMovie.asStateFlow()

    init {
        fetchPopularMovies()
        loadLastOpenedMovie()
    }

    private fun fetchPopularMovies() {
        viewModelScope.launch {
            getPopularMoviesUseCase().collect { state ->
                _movieListState.value = state
                if (state is UiState.Success) {
                    Timber.d("Data film berhasil dimuat: ${state.data.size} item")
                } else if (state is UiState.Error) {
                    Timber.e("Gagal memuat data film: ${state.errorMessage}")
                }
            }
        }
    }

    private fun loadLastOpenedMovie() {
        _lastOpenedMovie.value = getLastOpenedMovieTitleUseCase()
    }

    fun onMovieClicked(movie: Movie) {
        Timber.d("Tombol Detail ditekan")
        Timber.d("Data dari list yang dipilih berpindah ke halaman Detail: ${movie.title}")

        saveLastOpenedMovieUseCase(movie.title)
        _lastOpenedMovie.value = movie.title
    }
}