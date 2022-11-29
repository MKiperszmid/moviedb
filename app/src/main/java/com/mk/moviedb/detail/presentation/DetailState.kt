package com.mk.moviedb.detail.presentation

import com.mk.moviedb.core.domain.model.MovieDetail

data class DetailState(
    val movie: MovieDetail? = null,
    val isLoading: Boolean = false
)
