package com.example.modul5compose.feature.movie.data.remote

import com.example.modul5compose.feature.movie.data.remote.dto.MovieResponseDto
import com.example.modul5compose.feature.movie.data.remote.dto.MovieDetailResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<MovieResponseDto>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "en-US"
    ): Response<MovieDetailResponseDto>
}