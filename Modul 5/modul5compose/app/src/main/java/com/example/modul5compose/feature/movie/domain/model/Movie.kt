package com.example.modul5compose.feature.movie.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val posterUrl: String,
    val releaseDate: String,
    val overview: String,
    val imdbId: String? = null
)