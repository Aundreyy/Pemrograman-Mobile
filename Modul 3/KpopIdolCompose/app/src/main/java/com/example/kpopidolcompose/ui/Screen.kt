package com.example.kpopidolcompose.ui

sealed class Screen(val route: String) {
    object List : Screen("list")
    object Detail : Screen("detail/{nama}") {
        fun createRoute(nama: String) = "detail/$nama"
    }
}