package com.example.modul5compose.core.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class AppPreferences(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveLastOpenedMovie(title: String) {
        prefs.edit { putString(KEY_LAST_OPENED_MOVIE, title) }
    }

    fun getLastOpenedMovie(): String? {
        return prefs.getString(KEY_LAST_OPENED_MOVIE, null)
    }

    companion object {
        private const val PREFS_NAME = "movie_prefs"
        private const val KEY_LAST_OPENED_MOVIE = "last_opened_movie"
    }
}