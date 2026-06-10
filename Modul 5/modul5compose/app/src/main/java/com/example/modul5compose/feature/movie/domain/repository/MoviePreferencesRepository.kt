package com.example.modul5compose.feature.movie.domain.repository

interface MoviePreferencesRepository {
    fun saveLastOpenedMovie(title: String)
    fun getLastOpenedMovie(): String?
}