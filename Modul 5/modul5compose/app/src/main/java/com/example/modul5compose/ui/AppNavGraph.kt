package com.example.modul5compose.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.modul5compose.feature.movie.presentation.screens.MovieDetailScreen
import com.example.modul5compose.feature.movie.presentation.screens.MovieScreen
import com.example.modul5compose.feature.movie.presentation.viewmodel.MovieViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    viewModel: MovieViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.List.route
    ) {
        composable(Screen.List.route) {
            MovieScreen(
                viewModel = viewModel,
                navigateToDetail = { movieId ->
                    navController.navigate(Screen.Detail.createRoute(movieId))
                }
            )
        }

        composable(Screen.Detail.route) { backStackEntry ->
            val movieIdString = backStackEntry.arguments?.getString("movieId")
            val movieId = movieIdString?.toIntOrNull()

            if (movieId != null) {
                MovieDetailScreen(
                    movieId = movieId,
                    viewModel = viewModel,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}