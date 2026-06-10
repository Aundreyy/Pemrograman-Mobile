package com.example.modul5compose.feature.movie.data.repository

import com.example.modul5compose.core.common.UiState
import com.example.modul5compose.core.network.ApiResult
import com.example.modul5compose.core.network.safeApiCall
import com.example.modul5compose.feature.movie.data.local.MovieDao
import com.example.modul5compose.feature.movie.data.mapper.toDomain
import com.example.modul5compose.feature.movie.data.mapper.toEntity
import com.example.modul5compose.feature.movie.data.remote.MovieApiService
import com.example.modul5compose.feature.movie.domain.model.Movie
import com.example.modul5compose.feature.movie.domain.repository.MovieRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(
    private val apiService: MovieApiService,
    private val movieDao: MovieDao
) : MovieRepository {

    override fun getPopularMovies(): Flow<UiState<List<Movie>>> = flow {
        emit(UiState.Loading)

        val apiResult = safeApiCall { apiService.getPopularMovies() }

        if (apiResult is ApiResult.Success) {

            val moviesWithDetails = coroutineScope {
                apiResult.data.results.map { movieDto ->
                    async {
                        val detailResult = safeApiCall { apiService.getMovieDetails(movieDto.id) }

                        val imdbId = if (detailResult is ApiResult.Success) {
                            detailResult.data.imdbId
                        } else {
                            null
                        }

                        movieDto.toEntity(imdbId)
                    }
                }.awaitAll()
            }

            movieDao.clearMovies()
            movieDao.insertMovies(moviesWithDetails)
        }

        movieDao.getPopularMovies().collect { entities ->
            if (entities.isEmpty()) {
                emit(UiState.Error("Tidak ada koneksi internet dan tidak ada data tersimpan."))
            } else {
                emit(UiState.Success(entities.map { it.toDomain() }))
            }
        }
    }
}