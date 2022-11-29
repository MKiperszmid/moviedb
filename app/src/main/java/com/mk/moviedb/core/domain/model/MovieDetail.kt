package com.mk.moviedb.core.domain.model

data class MovieDetail(
    val id: Int,
    val image: String,
    val name: String,
    val year: Int,
    val language: String,
    val rating: Double,
    val genres: List<String>,
    val description: String
)
