package com.example.kpopidolcompose.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kpopidolcompose.ui.screen.DetailScreen
import com.example.kpopidolcompose.ui.screen.ListScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.List.route
    ) {
        composable(Screen.List.route) {
            ListScreen(navController = navController)
        }
        composable(Screen.Detail.route) { backStackEntry ->
            val namaIdol = backStackEntry.arguments?.getString("nama")
            DetailScreen(nama = namaIdol)
        }
    }
}