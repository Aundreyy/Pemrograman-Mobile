package com.example.modul5compose.feature.movie.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.modul5compose.core.common.UiState
import com.example.modul5compose.feature.movie.presentation.components.MovieHorizontalItem
import com.example.modul5compose.feature.movie.presentation.components.MovieVerticalItem
import com.example.modul5compose.feature.movie.presentation.viewmodel.MovieViewModel

@Composable
fun MovieScreen(
    viewModel: MovieViewModel,
    navigateToDetail: (Int) -> Unit
) {
    val state by viewModel.movieListState.collectAsState()
    val lastOpened by viewModel.lastOpenedMovie.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        if (lastOpened != null) {
            Surface(color = MaterialTheme.colorScheme.secondaryContainer, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Terakhir Dilihat: $lastOpened",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }

        when (state) {
            is UiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is UiState.Success -> {
                val movies = (state as UiState.Success).data

                val trendingMovies = movies.take(5)
                val otherMovies = movies.drop(5)

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    if (trendingMovies.isNotEmpty()) {
                        item {
                            Text(
                                text = "Sedang Tren",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                            )

                            LazyRow(
                                contentPadding = PaddingValues(horizontal = 16.dp),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                items(trendingMovies) { movie ->
                                    MovieHorizontalItem(
                                        title = movie.title,
                                        posterPath = movie.posterUrl,
                                        onClick = {
                                            viewModel.onMovieClicked(movie)
                                            navigateToDetail(movie.id)
                                        }
                                    )
                                }
                            }
                        }
                    }

                    if (otherMovies.isNotEmpty()) {
                        item {
                            Text(
                                text = "Rekomendasi Lainnya",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(
                                    start = 16.dp,
                                    top = 24.dp,
                                    bottom = 8.dp,
                                    end = 16.dp
                                )
                            )
                        }

                        items(otherMovies) { movie ->
                            MovieVerticalItem(
                                title = movie.title,
                                posterPath = movie.posterUrl,
                                releaseDate = movie.releaseDate,
                                imdbId = movie.imdbId,
                                onClick = {
                                    viewModel.onMovieClicked(movie)
                                    navigateToDetail(movie.id)
                                }
                            )
                        }
                    }
                }
            }
            is UiState.Error -> {
                val errorMessage = (state as UiState.Error).errorMessage
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}