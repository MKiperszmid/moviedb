package com.mk.moviedb.core.domain.model

data class Movie(
    val description: String,
    val title: String,
    val releaseYear: Int,
    val language: String,
    val rating: Double,
    val poster: String,
    val genres: List<String>
)
