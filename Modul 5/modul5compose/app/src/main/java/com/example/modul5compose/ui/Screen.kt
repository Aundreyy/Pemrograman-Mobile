package com.example.modul5compose.ui

sealed class Screen(val route: String) {
    object List : Screen("list")
    object Detail : Screen("detail/{movieId}") {
        fun createRoute(movieId: Int) = "detail/$movieId"
    }
}