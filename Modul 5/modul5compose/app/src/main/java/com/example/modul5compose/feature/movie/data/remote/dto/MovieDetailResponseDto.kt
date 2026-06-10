package com.example.modul5compose.feature.movie.data.remote.dto

import kotlin.OptIn
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@OptIn(InternalSerializationApi::class)
@Serializable
data class MovieDetailResponseDto(
    val id: Int,
    @SerialName("imdb_id")
    val imdbId: String? = null
)