package com.example.modul5compose.feature.movie.data.mapper

import com.example.modul5compose.feature.movie.data.local.MovieEntity
import com.example.modul5compose.feature.movie.data.remote.dto.MovieDto
import com.example.modul5compose.feature.movie.domain.model.Movie

fun MovieDto.toEntity(imdbId: String? = null): MovieEntity {
    return MovieEntity(
        id = this.id,
        title = this.title,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        overview = this.overview,
        imdbId = imdbId
    )
}

fun MovieEntity.toDomain(): Movie {
    return Movie(
        id = this.id,
        title = this.title,
        posterUrl = this.posterPath ?: "",
        releaseDate = this.releaseDate ?: "Tanggal tidak diketahui",
        overview = this.overview,
        imdbId = this.imdbId
    )
}