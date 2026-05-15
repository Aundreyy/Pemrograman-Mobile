package com.example.kpopidolcompose.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kpopidolcompose.ui.screen.DetailScreen
import com.example.kpopidolcompose.ui.screen.ListScreen
import com.example.kpopidolcompose.viewmodel.KpopViewModel

@Composable
fun AppNavGraph(navController: NavHostController, viewModel: KpopViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screen.List.route
    ) {
        composable(Screen.List.route) {
            ListScreen(navController = navController, viewModel = viewModel)
        }
        composable(Screen.Detail.route) { backStackEntry ->
            val namaIdol = backStackEntry.arguments?.getString("nama")
            DetailScreen(nama = namaIdol)
        }
    }
}