package com.mk.moviedb.home.presentation

import com.mk.moviedb.core.domain.model.FilterType
import com.mk.moviedb.core.domain.model.Movie

data class HomeState(
    val upcomingMovies: List<Movie> = emptyList(),
    val popularMovies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val selectedFilter: FilterType = FilterType.SPANISH,
    val filteredMovies: List<Movie> = emptyList()
)
