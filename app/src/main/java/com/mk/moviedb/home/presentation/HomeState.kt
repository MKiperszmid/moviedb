package com.mk.moviedb.home.presentation

import com.mk.moviedb.core.domain.model.Movie

data class HomeState(
    val upcoming: List<Movie> = emptyList(),
    val isLoading: Boolean = false
)
