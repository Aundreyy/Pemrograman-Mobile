package com.example.kpopidolcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.kpopidolcompose.ui.AppNavGraph
import com.example.kpopidolcompose.ui.theme.KpopIdolComposeTheme
import com.example.kpopidolcompose.viewmodel.KpopViewModel
import com.example.kpopidolcompose.viewmodel.KpopViewModelFactory
import timber.log.Timber

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Timber.treeCount == 0) {
            Timber.plant(Timber.DebugTree())
        }

        enableEdgeToEdge()

        setContent {
            KpopIdolComposeTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val factory = KpopViewModelFactory("Daftar Kpop Compose")
                    val viewModel: KpopViewModel = viewModel(factory = factory)
                    val navController = rememberNavController()

                    AppNavGraph(navController = navController, viewModel = viewModel)
                }
            }
        }
    }
}